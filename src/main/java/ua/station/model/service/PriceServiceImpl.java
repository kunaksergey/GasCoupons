package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.station.model.entity.Price;
import ua.station.model.repository.PriceRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by sa on 05.11.17.
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {
    @Autowired
    PriceRepository priceRepository;

    @Override
    public Iterable<Price> findAll() {

        return priceRepository.findAll();
    }

    @Override
    public Price findById(int id) {
        Optional<Price> byId = priceRepository.findById(id);
        return (byId.isPresent()?byId.get():null);
    }

//
//    public Price findOne(int id) {
//        return rep.findOne(id);
//    }

}
