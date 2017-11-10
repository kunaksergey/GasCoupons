package ua.station.model.service;

import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.User;

/**
 * Created by sa on 07.11.17.
 */
public interface BasketService  {
    Basket findByUser(User user);
    Basket findByEmail(String email);
    void delete(int id, String name);
    Basket save(Basket basket);
}
