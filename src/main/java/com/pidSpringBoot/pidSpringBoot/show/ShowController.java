package com.pidSpringBoot.pidSpringBoot.show;

import com.pidSpringBoot.pidSpringBoot.ArtistType.ArtistType;
import com.pidSpringBoot.pidSpringBoot.artist.Artist;
import com.pidSpringBoot.pidSpringBoot.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.pidSpringBoot.pidSpringBoot.location.LocationService;

import java.util.*;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

    @Autowired
    ShowRepository repository;
    
     @Autowired
    LocationService locationService;
    
    

    @GetMapping("/shows")
    public String index(Model model) {
        List<Show> shows = service.getAll();

        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.get(id);

        //Récupérer les artistes du spectacle et les grouper par type
        Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();

        for(ArtistType at : show.getArtistTypes()) {
            String type = at.getType().getType();

            if(collaborateurs.get(type) == null) {
                collaborateurs.put(type, new ArrayList<>());
            }

            collaborateurs.get(type).add(at.getArtist());
        }

        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("show", show);
        model.addAttribute("isAdmin", true);
        model.addAttribute("title", "Fiche d'un spectacle");

        return "show/show";
    }
  
    @GetMapping("/shows/create")
    public String create(Model model) {
        Show show = new Show();
        model.addAttribute("isAdmin", true);
        model.addAttribute("show", show);
        return "show/create";
    }
    @PostMapping("/shows/create")
    public String store(@Valid @ModelAttribute("show") Show show, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "show/create";
        }

        service.add(show);

        return "redirect:/shows/" + show.getId();
    }
    @GetMapping("/shows/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        Show show = service.get(id);

         model.addAttribute("show", show);
        model.addAttribute("locations", locationService.listAll());

        model.addAttribute("isAdmin", true);
        return "show/edit";
    }

    @PostMapping("/shows/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute Show show, Model model) {
        Show existingShow = null;
        if (repository.findById(id).isPresent()) {
            existingShow = repository.findById(id).get();
        }else{
             return "show/index";
        }
        existingShow.setTitle(show.getTitle());
        existingShow.setDescription(show.getDescription());
        existingShow.setPosterUrl(show.getPosterUrl());
        existingShow.setLocation(show.getLocation());
        existingShow.setPrice(show.getPrice());
        existingShow.setBookable(show.isBookable());

      
        service.update(existingShow);
        model.addAttribute("isAdmin", true);

        return "redirect:/admin_home";
    }

   @DeleteMapping("/shows/delete/{id}")
public ResponseEntity<?> delete(@PathVariable("id") String id, Model model) {
    Optional<Show> existing = repository.findById(Long.parseLong(id));

    if(existing.isPresent()) { 
        service.deleteShow(id);
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
}



}



