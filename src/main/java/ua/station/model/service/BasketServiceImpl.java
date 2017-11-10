package ua.station.model.service;

import org.springframework.stereotype.Service;
import ua.station.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.station.model.repository.BasketRepository;
import ua.station.model.repository.UserRepository;

/**
 * Created by sa on 04.11.17.
 */
@Service("basketService")
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

    public void delete(int id, String name) {
    }

    public BasketItem update(int id, String name) {
        return null;
    }
}
