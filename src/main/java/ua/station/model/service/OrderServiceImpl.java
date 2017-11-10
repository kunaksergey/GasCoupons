package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.station.model.repository.OrderRepository;

/**
 * Created by sa on 04.11.17.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
}
