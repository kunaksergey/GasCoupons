package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.station.model.entity.Product;
import ua.station.model.service.ProductService;

import static ua.station.controller.ProductController.PRODUCT_URI;

/**
 * Created by sa on 07.11.17.
 */
@Controller
@RequestMapping(PRODUCT_URI)
public class ProductController {
    static final String PRODUCT_URI = "/admin/product";

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String showList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/admin/product/productList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String edit(@PathVariable int id, Model model) {
        Product editedProduct = productService.findById(id);
        if (editedProduct != null) {
            model.addAttribute("product", editedProduct);
            return "/admin/product/productEdit";

        }
        return "/404.jsp";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String save(@ModelAttribute("product") Product receivedProduct) {
        Product product = productService.findById(receivedProduct.getId());
        product.setName(receivedProduct.getName());
        productService.save(product);
        return "redirect:/admin/product/";
    }
}



