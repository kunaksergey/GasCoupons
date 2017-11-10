package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.station.model.entity.User;
import ua.station.model.entity.UserDto;
import ua.station.model.exception.EmailExistsException;
import ua.station.model.service.UserService;

import javax.validation.Valid;

/**
 * Created by sa on 06.11.17.
 */
@Controller
public class RegController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/registration",method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        return "/login/reg-form";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("/login/reg-form", "user", accountDto);
        }
        else {
            return new ModelAndView("/login/successRegister", "user", accountDto);
        }
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

 }
