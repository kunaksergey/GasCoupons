package ua.station.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.station.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static ua.station.api.ProductRestController.PRODUCT_REST_URI;

/**
 * Created by sa on 07.11.17.
 */
@RestController
@RequestMapping(PRODUCT_REST_URI)
public class ProductRestController {
    static final String PRODUCT_REST_URI="api/v1/order/";

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    Product putInBasket(HttpServletRequest request, Principal principal){


        return new Product();
    }
}
