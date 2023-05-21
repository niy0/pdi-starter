package com.pidSpringBoot.pidSpringBoot.show;

import com.pidSpringBoot.pidSpringBoot.ArtistType.ArtistType;
import com.pidSpringBoot.pidSpringBoot.Representation.Representation;
import com.pidSpringBoot.pidSpringBoot.Type.TypeService;
import com.pidSpringBoot.pidSpringBoot.artist.Artist;
import com.pidSpringBoot.pidSpringBoot.artist.ArtistRepository;
import com.pidSpringBoot.pidSpringBoot.artist.ArtistService;
import com.pidSpringBoot.pidSpringBoot.user.User;
import com.pidSpringBoot.pidSpringBoot.location.Location;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.pidSpringBoot.pidSpringBoot.location.LocationService;
import com.pidSpringBoot.pidSpringBoot.location.LocationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

    @Autowired
    ShowRepository repository;
    
     @Autowired
    LocationService locationService;
    
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ArtistService artistService;

    @Autowired
   TypeService typeService;

   

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
        List<Artist> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("isAdmin", true);
        model.addAttribute("show", show);
        model.addAttribute("locations", locationService.listAll());
        return "show/create";
    }
    @PostMapping("/shows/create")
    public String store(@Valid @ModelAttribute("show")Show show, @RequestParam("representationDateTime[]") List<String> representationDateTimes, Model model) {
       
        Optional<Location> optionalLocation = locationRepository.findById(show.getLocationId());
        Artist author = artistService.getArtist(show.getAuthorId());
       
        Artist director = artistService.getArtist(show.getDirectorId());
        Artist distributor = artistService.getArtist(show.getDistributionId());
        if(optionalLocation.isPresent()) {
            show.setLocation(optionalLocation.get());
        }
        
        
        ArtistType artistType = new ArtistType();
        artistType.setArtist(author);
        artistType.setType(typeService.getType("1"));
        artistType.addShow(show);
        show.addArtistType(artistType);
     
    
        ArtistType artistType2 = new ArtistType();
        artistType2.setArtist(director);
        artistType2.setType(typeService.getType("2"));
        artistType2.addShow(show);
        show.addArtistType(artistType2);

        ArtistType artistType3 = new ArtistType();
        artistType3.setArtist(distributor);
        artistType3.setType(typeService.getType("3"));
        artistType3.addShow(show);
        show.addArtistType(artistType3);
        
        
        for(String representationDateTime: representationDateTimes) {
            System.out.println("--------------------"+representationDateTime);
            Representation representation = new Representation();
            LocalDateTime dateTime = LocalDateTime.parse(representationDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            representation.setEventTime(dateTime);
            representation.setShow(show);
            representation.setShow(show);
            representation.setLocation(optionalLocation.get());
            show.getRepresentations().add(representation);
        }

        service.add(show);
        
        model.addAttribute("isAdmin", true);
        return "redirect:/shows/" + show.getId();
    }
    @GetMapping("/shows/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        Show show = null;
        
        if (repository.findById(id).isPresent()){
            show = repository.findById(id).get();
        }
        List<Artist> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("show", show);
        model.addAttribute("locations", locationService.listAll());

        model.addAttribute("isAdmin", true);
        return "show/edit";
    }
    @PutMapping("/shows/edit/{id}")
    public String updateShow(@PathVariable("id") Long id, @ModelAttribute("show") Show show,@RequestParam("representationDateTime[]") List<String> representationDateTimes, Model model) {
        Optional<Location> optionalLocation = locationRepository.findById(show.getLocationId());
        Artist author = artistService.getArtist(show.getAuthorId());
       
        Artist director = artistService.getArtist(show.getDirectorId());
        Artist distributor = artistService.getArtist(show.getDistributionId());
        if(optionalLocation.isPresent()) {
            show.setLocation(optionalLocation.get());
        }
        
        
        ArtistType artistType = new ArtistType();
        artistType.setArtist(author);
        artistType.setType(typeService.getType("1"));
        artistType.addShow(show);
        show.addArtistType(artistType);
     
    
        ArtistType artistType2 = new ArtistType();
        artistType2.setArtist(director);
        artistType2.setType(typeService.getType("2"));
        artistType2.addShow(show);
        show.addArtistType(artistType2);

        ArtistType artistType3 = new ArtistType();
        artistType3.setArtist(distributor);
        artistType3.setType(typeService.getType("3"));
        artistType3.addShow(show);
        show.addArtistType(artistType3);
        
        
        for(String representationDateTime: representationDateTimes) {
            System.out.println("--------------------"+representationDateTime);
            Representation representation = new Representation();
            LocalDateTime dateTime = LocalDateTime.parse(representationDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            representation.setEventTime(dateTime);
            representation.setShow(show);
            representation.setShow(show);
            representation.setLocation(optionalLocation.get());
            show.getRepresentations().add(representation);
        }

        service.add(show);
        
        model.addAttribute("isAdmin", true);
        return "redirect:/shows/" + show.getId();
    }

     

@DeleteMapping("/shows/delete/{id}")
public String delete(@PathVariable("id") String id, Model model) {
    Optional<Show> existing = repository.findById(Long.parseLong(id));

    if(existing.isPresent()) { 
        service.deleteShow(id);
        
    }
    return "redirect:/admin/home" ;
}



}



