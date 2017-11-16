package ua.station.model.service;

import ua.station.model.entity.Basket;
import ua.station.model.entity.Order;
import ua.station.model.exception.BasketEmptyExeption;

/**
 * Created by sa on 07.11.17.
 */
public interface OrderService {
    Order create(Basket basket) throws BasketEmptyExeption;
}
