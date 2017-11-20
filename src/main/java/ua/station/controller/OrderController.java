package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.station.model.exception.EntityIsNotExistException;
import ua.station.model.service.OrderService;

import static ua.station.controller.OrderController.ORDER_URI;

/**
 * Created by sa on 07.11.17.
 */
@Controller
@RequestMapping(ORDER_URI)
public class OrderController {
    static final String ORDER_URI = "/admin/orders";

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/prepared", method = RequestMethod.GET)
    String showPrepared(Model model) {
        model.addAttribute("status", "Prepared");
        model.addAttribute("orders", orderService.findAllByStatus(0));
        return "/admin/order/orderList";
    }

    @RequestMapping(method = RequestMethod.GET)
    String showAll(Model model) {
        model.addAttribute("status", "ALL");
        model.addAttribute("orders", orderService.findAll());
        return "/admin/order/orderList";
    }

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    String showDone(Model model) {
        model.addAttribute("status", "DONE");
        model.addAttribute("orders", orderService.findAllByStatus(1));
        return "/admin/order/orderList";
    }


    @RequestMapping(value = "/send/{id}", method = RequestMethod.GET)
    String apply(@PathVariable int id, Model model) {
        try {
            orderService.changeStatus(id, 1);
            model.addAttribute("orders", orderService.findAllByStatus(1));
            return "redirect:" + ORDER_URI + "/done";
        } catch (EntityIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    String cancel(@PathVariable int id, Model model) {
        try {
            orderService.changeStatus(id, 0);
            model.addAttribute("orders", orderService.findAllByStatus(0));
            return "redirect:" + ORDER_URI + "/prepared";
        } catch (EntityIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("order", orderService.findOne(id));
            return "/admin/order/orderEdit";
        } catch (EntityIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    String delete(@RequestParam int id, Model model) {
        try {
            orderService.delete(id);
            return "redirect:" + ORDER_URI + "/prepared";
        } catch (EntityIsNotExistException e) {
            return "404";
        }
    }



    @RequestMapping(value = "/delete-item", method = RequestMethod.POST)
    String deleteItem(@RequestParam int id,@RequestParam int itemId, Model model) {
        try {
            orderService.deleteItem(id,itemId);
            model.addAttribute("order", orderService.findOne(id));
            return "/admin/order/orderEdit";
        } catch (EntityIsNotExistException e) {
            return "404";
        }

    }

}
