package ua.station.model.service;

import ua.station.model.entity.Product;
import ua.station.model.entity.Station;

/**
 * Created by sa on 07.11.17.
 */
public interface ProductService {
    Iterable<Product> findAll();
    Iterable<Product> findAllOrderById();
    void save(Product station);
    Product findById(int id);
}
