package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.station.model.exception.OrderIsNotExistException;
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
        } catch (OrderIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    String cancel(@PathVariable int id, Model model) {
        try {
            orderService.changeStatus(id, 0);
            model.addAttribute("orders", orderService.findAllByStatus(0));
            return "redirect:" + ORDER_URI + "/prepared";
        } catch (OrderIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("order", orderService.findOne(id));
            return "/admin/order/orderEdit";
        } catch (OrderIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable int id, Model model) {
        try {
            orderService.delete(id);
            return "redirect:" + ORDER_URI + "/prepared";
        } catch (OrderIsNotExistException e) {
            return "404";
        }

    }

    @RequestMapping(value = "/delete/{id}/item/{idItem}", method = RequestMethod.GET)
    String deleteItem(@PathVariable int id,@PathVariable int idItem, Model model) {
        try {
            orderService.deleteItem(id,idItem);
            model.addAttribute("order", orderService.findOne(id));
            return "/admin/order/orderEdit";
        } catch (OrderIsNotExistException e) {
            return "404";
        }

    }

}
