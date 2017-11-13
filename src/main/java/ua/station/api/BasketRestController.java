package ua.station.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.station.model.dto.BasketDto;
import ua.station.model.entity.Basket;
import ua.station.model.entity.Price;
import ua.station.model.service.BasketItemService;
import ua.station.model.service.BasketService;
import ua.station.model.service.PriceService;

import java.security.Principal;

import static ua.station.api.BasketRestController.BASKET_REST_URI;

/**
 * Created by sa on 04.11.17.
 */
@RestController
@RequestMapping(BASKET_REST_URI)
class BasketRestController {
    static final String BASKET_REST_URI = "api/v1/basket";

    @Autowired
    BasketService basketService;

    @Autowired
    BasketItemService basketItemService;

    @Autowired
    PriceService priceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    BasketDto getBasket(Principal principal) {
        return new BasketDto(basketService.findByEmail(principal.getName()));
    }

    @RequestMapping(value = "/{idPrice}/count/{count}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    BasketDto add(@PathVariable int idPrice, @PathVariable int count, Principal principal) {
        Basket basket = basketService.findByEmail(principal.getName());
        Price price = priceService.findById(idPrice);
        //if(price==null) return new BasketDto();
        return new BasketDto(basketService.add(basket, price, count));
    }

    @RequestMapping(value = "/{idPrice}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    BasketDto delete(@PathVariable int idPrice, Principal principal) {
        Basket basket = basketService.findByEmail(principal.getName());
        Price price = priceService.findById(idPrice);
        return new BasketDto(basketService.delete(basket, price));
    }


    @RequestMapping(value = "/{idPrice}/count/{count}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    BasketDto update(@PathVariable int idPrice, @PathVariable int count, Principal principal) {
        Basket basket = basketService.findByEmail(principal.getName());
        Price price = priceService.findById(idPrice);
        return new BasketDto(basketService.update(basket, price, count));
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    BasketDto order(Principal principal) {
        return new BasketDto(basketService.findByEmail(principal.getName()));
    }


}
