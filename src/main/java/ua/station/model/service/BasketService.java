package ua.station.model.service;

import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Price;
import ua.station.model.entity.User;

/**
 * Created by sa on 07.11.17.
 */
public interface BasketService  {
    Basket findByUser(User user);
    Basket findByEmail(String email);
    Basket delete(Basket basket, Price price);
    Basket save(Basket basket);
    Basket add(Basket basket, Price price, int count);
    Basket update(Basket basket, Price price, int countIn);
    void clean(Basket basket);
}
