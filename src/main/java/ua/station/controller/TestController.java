package ua.station.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sa on 07.11.17.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test")
    public String index() {
        return "test";
    }
}
