package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.station.model.entity.Basket;
import ua.station.model.entity.Order;
import ua.station.model.entity.OrderItem;
import ua.station.model.entity.Product;
import ua.station.model.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 04.11.17.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order create(Basket basket) {
        Order order= new Order();
        List<OrderItem> orderItemList=new ArrayList<>();
        BigDecimal summAll=new BigDecimal(0);
        basket.getBasketItems().forEach(item->{
            Product product=item.getPrice().getProduct();
            BigDecimal price=item.getPrice().getPrice();
            int count=item.getCount();
            BigDecimal summ=price.multiply(new BigDecimal(count));
            summAll.add(summ);
            orderItemList.add(new OrderItem(product,price,count,summ));
          });

        order.setOrderItemList(orderItemList);
        order.setSumm(summAll);
        order.setUser(basket.getUser());
        return orderRepository.save(order);
    }
}
