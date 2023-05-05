package com.pidSpringBoot.pidSpringBoot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RepresentationController {
        @Autowired
        private RepresentationService representationService;

       /* @Autowired
        private ShowService showService;*/

       /* @Autowired
        private LocationService locationService;*/

        // GET - récupérer toutes les représentations
        @GetMapping("/representations")
        public String getAllRepresentations(Model model) {
            List<Representation> representations = representationService.findAll();
            model.addAttribute("representations", representations);
            return "representation/list";
        }

        // GET - récupérer une représentation par ID
        @GetMapping("/representations/{id}")
        public String getRepresentationById(@PathVariable Long id, Model model) {
            Representation representation = representationService.findById(id);
            model.addAttribute("representation", representation);
            return "representation/view";
        }

        // GET - créer une nouvelle représentation
        @GetMapping("/representations/new")
        public String createRepresentation(Model model) {
            Representation representation = new Representation();
            //  List<Show> shows = showService.findAll();
            //  List<Location> locations = locationService.findAll();
            model.addAttribute("representation", representation);
            //  model.addAttribute("shows", shows);
            //  model.addAttribute("locations", locations);
            return "representation/form";
        }

        // POST - sauvegarder une nouvelle représentation
        @PostMapping("/representations")
        public String saveRepresentation(@ModelAttribute("representation") Representation representation) {
            representationService.save(representation);
            return "redirect:/representations";
        }

        // GET - éditer une représentation existante
        @GetMapping("/representations/edit/{id}")
        public String editRepresentation(@PathVariable Long id, Model model) {
            Representation representation = representationService.findById(id);
            //   List<Show> shows = showService.findAll();
            //  List<Location> locations = locationService.findAll();
            model.addAttribute("representation", representation);
            //   model.addAttribute("shows", shows);
            //   model.addAttribute("locations", locations);
            return "representation/form";
        }

        // PUT - mettre à jour une représentation existante
        @PutMapping("/representations/{id}")
        public String updateRepresentation(@PathVariable Long id, @ModelAttribute("representation") Representation representation) {
            //  representation.setId(id);
            // representationService.update(id,representation);
            return "redirect:/representations";
        }

        // DELETE - supprimer une représentation existante
        @DeleteMapping("/representations/{id}")
        public String deleteRepresentation(@PathVariable Long id) {
            representationService.delete(id);
            return "redirect:/representations";
        }

    }

