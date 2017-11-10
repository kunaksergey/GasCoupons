package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import ua.station.model.service.OrderServiceImpl;

/**
 * Created by sa on 07.11.17.
 */
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    String showList(Model model){
        return "/admin/order/orderList";
    }
}
