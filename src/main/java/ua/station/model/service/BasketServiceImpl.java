package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Price;
import ua.station.model.entity.User;
import ua.station.model.repository.BasketRepository;
import ua.station.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sa on 04.11.17.
 */
@Service("basketService")
@Transactional
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;

    public Basket findByUser(User user) {
        return basketRepository.findOneByUser(user);
    }

    @Override
    public Basket findByEmail(String email) {
        return findByUser(userRepository.findByEmail(email));
    }

    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public Basket add(Basket basket, Price price, int countIn) {
        boolean isNew = true;
        for (BasketItem item : basket.getBasketItems()) {
            if (item.getPrice().equals(price)) {
                int count = ((item.getCount() + countIn) > 0) ? (item.getCount() + countIn) : 0;
                item.setCount(count);
                isNew = false;
            }
        }

        if (isNew) {
            basket.getBasketItems().add(new BasketItem(basket, price, countIn));
        }
        return basketRepository.save(basket);
    }

    @Override
    public Basket delete(Basket basket, Price price) {
        Iterator<BasketItem> iterator = basket.getBasketItems().iterator();
        while (iterator.hasNext()) {
            BasketItem next = iterator.next();
            if (next.getPrice().equals(price)) {
                iterator.remove();
            }
        }
       return basketRepository.save(basket);
    }

    @Override
    public Basket update(Basket basket, Price price, int countIn) {

        for (BasketItem item : basket.getBasketItems()) {
            if (item.getPrice().equals(price)) {
                int count = (countIn > 0)?( countIn) : 0;
                item.setCount(count);
           }
        }

        return basketRepository.save(basket);
    }

    @Override
    public void clean(Basket basket) {
        basket.setBasketItems(new ArrayList<BasketItem>());
        basketRepository.save(basket);
    }
}
