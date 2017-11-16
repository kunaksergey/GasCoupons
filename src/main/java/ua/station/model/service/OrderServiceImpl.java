package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Order;
import ua.station.model.entity.OrderItem;
import ua.station.model.exception.BasketEmptyExeption;
import ua.station.model.repository.OrderRepository;

import java.math.BigDecimal;

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
            if(item.getCount()==0){
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
        if(summAll.equals(new BigDecimal(0))) throw new BasketEmptyExeption("Basket is Empty");
        order.setSumm(summAll); //общая сумма заказа
        order.setUser(basket.getUser()); //пользователь заказа
        order.setStatus(0); //статус-подготовлен
        basketService.clean(basket);
        return orderRepository.save(order);

    }
}
