package com.pidSpringBoot.pidSpringBoot.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LocationController {
    @Autowired private LocationService service;

    @GetMapping("/admin/locations")
    public String showLocationList(Model model){
        List<Location> listLocations = service.listAll();
        model.addAttribute("listLocations", listLocations);
        return "/location/locations";
    }

    @GetMapping("/admin/locations/new")
    public String showNewForm(Model model){
        model.addAttribute("location", new Location());
        model.addAttribute("pageTitle", "Add new location");
        return "location/location_form";
    }

    @PostMapping("/admin/locations/save")
    public String saveLocation(Model model,Location location, RedirectAttributes redirectAttributes){
        service.save(location);
        redirectAttributes.addFlashAttribute("message", "Location modifier !");
        return "redirect:/admin/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String showEditLocation(@PathVariable("id") Integer id, Model model , RedirectAttributes redirectAttributes){
        try {
            Location location = service.get(id);
            model.addAttribute("location", location);
            model.addAttribute("location id", location.getLocality().getId());
            model.addAttribute("pageTitle", "Edit location (ID: "+id+")");
            return "location/location_form";
        } catch (LocationNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Location rajouté");
            model.addAttribute("isAdmin", true);
            return "redirect:/admin/locations";
        }
    }

    @DeleteMapping("/locations/delete/{id}")
    public String deleteLocation(Model model,@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Location avec cette ID: ("+ id +") a été supprimé.");
        } catch (LocationNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        model.addAttribute("isAdmin", true);
        return "redirect:/admin/locations";
    }
}
