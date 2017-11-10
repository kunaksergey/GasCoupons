package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.station.model.service.ProductService;

/**
 * Created by sa on 05.11.17.
 */
@Controller
@RequestMapping("/")
public class MainController {
@Autowired
    ProductService productService;
    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
        model.addAttribute("products",productService.findAll());
        return "index";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String startAdmin(Model model){
        return "/admin/index";
    }

}

