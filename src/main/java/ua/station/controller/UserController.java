package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.station.model.entity.User;
import ua.station.model.service.UserService;

/**
 * Created by sa on 04.11.17.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String showList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/user/userList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String edit(@PathVariable int id, Model model) {
        User editedUser = userService.findById(id);
        if (editedUser != null) {
            model.addAttribute("user", userService.findById(id));
            return "/admin/user/userEdit";

        }
        return "/404.jsp";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String save(@ModelAttribute("user") User receivedUser) {
        User user = userService.findById(receivedUser.getId());
        user.setEmail(receivedUser.getEmail());
        user.setStatus(receivedUser.getStatus());
        userService.save(user);
        return "redirect:/admin/user/";
    }

}
