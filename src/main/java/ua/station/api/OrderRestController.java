package ua.station.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.station.model.dto.OrderDTO;
import ua.station.model.entity.Basket;
import ua.station.model.entity.Order;
import ua.station.model.entity.Product;
import ua.station.model.service.BasketService;
import ua.station.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static ua.station.api.OrderRestController.ORDER_REST_URI;
import static ua.station.api.OrderRestController.PRODUCT_REST_URI;

/**
 * Created by sa on 07.11.17.
 */
@RestController
@RequestMapping(ORDER_REST_URI)
public class OrderRestController {
    static final String ORDER_REST_URI="api/v1/order";

    @Autowired
    BasketService basketService;

    @Autowired
    OrderService orderService;

    @RequestMapping( method = RequestMethod.POST)
    OrderDTO order(Principal principal){
        Basket basket = basketService.findByEmail(principal.getName());
        OrderDTO orderDto=new OrderDTO(orderService.create(basket));
        basketService.clean(basket);
        return orderDto;
    }
}
