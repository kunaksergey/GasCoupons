package ua.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.station.model.entity.Station;
import ua.station.model.service.StationService;
import static ua.station.controller.StationController.STATION_URI;
/**
 * Created by sa on 07.11.17.
 */
@Controller
@RequestMapping(STATION_URI)
public class StationController {
    static final String STATION_URI="/admin/station/";
    @Autowired
    StationService stationService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    String showList(Model model) {
        model.addAttribute("stations", stationService.findAll());
        return STATION_URI+"stationList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String edit(@PathVariable int id, Model model) {
        Station editedStation = stationService.findOne(id);
        if (editedStation != null) {
            model.addAttribute("station", stationService.findOne(id));
            return STATION_URI+"stationEdit";

        } else {
            return "/404.jsp";
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    String save(@ModelAttribute("station") Station station) {
        stationService.save(station);
        return "redirect:"+STATION_URI;
    }
}
