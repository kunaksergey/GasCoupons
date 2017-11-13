package ua.station.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.station.model.entity.Price;
import ua.station.model.entity.Product;
import ua.station.model.service.PriceService;
import ua.station.model.service.PriceServiceImpl;

import java.util.List;

import static ua.station.api.PriceRestController.PRICE_REST_URI;

/**
 * Created by sa on 05.11.17.
 */
@RestController
@RequestMapping(PRICE_REST_URI)
public class PriceRestController {
    static final String PRICE_REST_URI="api/v1/price";

    @Autowired
    PriceService priceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Iterable<Price> priceList() {
         return priceService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    Price price(@PathVariable int id) {
        return priceService.findById(id);
    }

}
