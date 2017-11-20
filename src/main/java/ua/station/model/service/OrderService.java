package ua.station.model.service;

import ua.station.model.entity.Basket;
import ua.station.model.entity.Order;
import ua.station.model.exception.BasketEmptyExeption;
import ua.station.model.exception.OrderIsNotExistException;

/**
 * Created by sa on 07.11.17.
 */
public interface OrderService {
    Order create(Basket basket) throws BasketEmptyExeption;

    Iterable<Order> findAll();

    Iterable<Order> findAllByStatus(int status);

    Order changeStatus(int id,int status) throws OrderIsNotExistException;

    Order findOne(int id) throws OrderIsNotExistException;

    void delete(int id) throws OrderIsNotExistException;

    void deleteItem(int id, int idItem) throws OrderIsNotExistException;
}
