package com.pidSpringBoot.pidSpringBoot.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LocationController {
    @Autowired private LocationService service;

    @GetMapping("/locations")
    public String showLocationList(Model model){
        List<Location> listLocations = service.listAll();
        model.addAttribute("listLocations", listLocations);
        return "locations";
    }
}
