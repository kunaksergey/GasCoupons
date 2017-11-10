package ua.station.model.service;

import ua.station.model.entity.Price;

/**
 * Created by sa on 07.11.17.
 */
public interface PriceService {
    Iterable<Price> findAll();
    Price findById(int id);
}
