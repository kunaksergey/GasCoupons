package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.station.model.entity.Basket;
import ua.station.model.entity.User;
import ua.station.model.service.BasketServiceImpl;
import ua.station.model.service.UserService;

import java.security.Principal;

import static ua.station.controller.BasketController.BASKET_URI;

/**
 * Created by sa on 07.11.17.
 */
@Controller
@RequestMapping(BASKET_URI)
public class BasketController {
    static final String BASKET_URI ="/basket/";
    @Autowired
    BasketServiceImpl basketService;
    @Autowired
    UserService userService;
    @RequestMapping("/")
    String showBasket(Model model,Principal principal)
    {

        User user=userService.findByEmail(principal.getName());
        Basket basket=user.getBasket();
        model.addAttribute("basket",basket);
        return "basket";
    }
}
