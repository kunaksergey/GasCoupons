package ua.station.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Price;
import ua.station.model.entity.Product;
import ua.station.model.service.BasketService;
import ua.station.model.service.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import ua.station.model.service.PriceService;

import java.security.Principal;

/**
 * Created by sa on 04.11.17.
 */
@RestController
@RequestMapping("api/v1/basket")
   class BasketRestController {
    @Autowired
    BasketService basketService;

    @Autowired
    PriceService priceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    Basket getBasket(Principal principal)
    {
        Basket basket=basketService.findByEmail(principal.getName());
        return basket;
    }
    @RequestMapping(value = "/{id}/count/{count}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Basket add(@PathVariable int idPrice, @PathVariable int count, Principal principal)
    {
        Price price = priceService.findById(idPrice);
        BasketItem basketItem=new BasketItem(price,count);
        Basket basket=basketService.findByEmail(principal.getName());
        basket.getBasketItems().add(basketItem);
        basket.setSumm(basket.getSumm()+count);
        return basketService.save(basket);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable int id,Principal principal) {
        basketService.delete(id,principal.getName());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    BasketItem update(@PathVariable int id,Principal principal) {
         return null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
     BasketItem test() {
        System.out.println();
        return null;
    }



}
