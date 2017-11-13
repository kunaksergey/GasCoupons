package ua.station.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.station.model.dto.BasketDto;
import ua.station.model.dto.BasketItemDto;
import ua.station.model.entity.Basket;
import ua.station.model.entity.BasketItem;
import ua.station.model.entity.Price;
import ua.station.model.service.BasketItemService;
import ua.station.model.service.BasketService;
import ua.station.model.service.PriceService;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sa on 04.11.17.
 */
@RestController
@RequestMapping("api/v1/basket")
class BasketRestController {
    @Autowired
    BasketService basketService;

    @Autowired
    BasketItemService basketItemService;

    @Autowired
    PriceService priceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    BasketDto getBasket(Principal principal) {
        BasketDto basketDto = new BasketDto(basketService.findByEmail(principal.getName()));
        return basketDto;
    }

    @RequestMapping(value = "/{idPrice}/count/{count}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    BasketItemDto add(@PathVariable int idPrice, @PathVariable int count, Principal principal) {
        Basket basket = basketService.findByEmail(principal.getName());
        Price price = priceService.findById(idPrice);
        if(price==null) return new BasketItemDto();
        BasketItemDto basketItemDto=new BasketItemDto(basketItemService.add(basket,price,count));
        return basketItemDto;
    }

    @RequestMapping(value = "/{idPrice}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable int idPrice, Principal principal) {
        Price price = priceService.findById(idPrice);
        Basket basket = basketService.findByEmail(principal.getName());
        basketItemService.deleteByBasketAndPrice(basket,price);
     }


    @RequestMapping(value = "/{idPrice}/count/{count}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    BasketItem update(@PathVariable int idPrice, @PathVariable int count,Principal principal) {
        BasketItem basketItemUpdated = null;///????
        Basket basket = basketService.findByEmail(principal.getName());
        List<BasketItem> basketItems = basket.getBasketItems();
        Iterator<BasketItem> iterator = basketItems.iterator();
        while (iterator.hasNext()) {
            BasketItem next = iterator.next();
            if (next.equals(basketItemUpdated)) {
                next.setCount(count);
                break;
            }
        }
        basketService.save(basket);
        return basketItemUpdated;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    BasketItem test() {
        System.out.println();
        return null;
    }


}
