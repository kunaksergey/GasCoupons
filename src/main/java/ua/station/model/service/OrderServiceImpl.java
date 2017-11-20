package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Order;
import ua.station.model.entity.OrderItem;
import ua.station.model.exception.BasketEmptyExeption;
import ua.station.model.exception.OrderIsNotExistException;
import ua.station.model.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by sa on 04.11.17.
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BasketService basketService;

    @Override
    public Order create(Basket basket) throws BasketEmptyExeption {
        Order order = new Order();
        BigDecimal summAll = new BigDecimal(0);
        for (BasketItem item : basket.getBasketItems()) {
            if (item.getCount() == 0) {
                continue;
            } //если кол-во=0, то пропускаем
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getPrice().getProduct());//добавляем продукт
            orderItem.setStation(item.getPrice().getStation());//добавляем станцию
            orderItem.setCount(item.getCount());//добавляем кол-во
            orderItem.setPrice(item.getPrice().getPrice()); //добавляем стоимость
            orderItem.setSumm(                              //добавляем сумму
                    item.getPrice().getPrice().multiply(
                            new BigDecimal(item.getCount())
                    )
            );
            summAll = summAll.add(orderItem.getSumm()); //считаем общую сумму
            order.addOrderItem(orderItem); //добавляем в список
        }
        if (summAll.equals(new BigDecimal(0))) throw new BasketEmptyExeption("Basket is Empty");
        order.setSumm(summAll); //общая сумма заказа
        order.setUser(basket.getUser()); //пользователь заказа
        order.setStatus(0); //статус-подготовлен
        basketService.clean(basket);
        return orderRepository.save(order);

    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<Order> findAllByStatus(int status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public Order changeStatus(int id, int status) throws OrderIsNotExistException {
        Order order = findOne(id);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order findOne(int id) throws OrderIsNotExistException {
        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isPresent()) throw new OrderIsNotExistException("There is not order");
        return byId.get();
    }

    @Override
    public void delete(int id) throws OrderIsNotExistException {
        System.out.println();
        orderRepository.delete(findOne(id));
    }

    @Override
    public void deleteItem(int id, int idItem) throws OrderIsNotExistException {
        Order order = findOne(id);
        for (OrderItem item : order.getOrderItemList()) {
            if (item.getId() == idItem) {
                order.getOrderItemList().remove(item);
                order.setSumm(order.getSumm().subtract(item.getSumm()));
                break;
            }
        }
        orderRepository.save(order);
    }


}
