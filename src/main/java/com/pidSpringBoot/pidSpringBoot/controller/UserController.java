package com.pidSpringBoot.pidSpringBoot.controller;



import com.pidSpringBoot.pidSpringBoot.model.User;
import com.pidSpringBoot.pidSpringBoot.model.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/users")
    public String index(Model model){
        List<User> users = service.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("title", "Liste d'users");

        return "user/index";

    }
    @GetMapping("/users/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        User user = service.getUser(id);

        model.addAttribute("user", user);
        model.addAttribute("title", "Fiche d'un user");

        return "user/show";
    }

        @GetMapping("/users/create")
    public String create(Model model) {
        User user = new User(null, null,null);

        model.addAttribute("user", user);

        return "user/create";
    }

    @PostMapping("/users/create")
    public String store(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/create";
        }

        service.addUser(user);

        return "redirect:/users/"+user.getId();
    }


    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        User user = service.getUser(id);

        model.addAttribute("user", user);


        //Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if(referrer!=null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/users/"+user.getId());
        }

        return "user/edit";
    }

    @PutMapping("/users/{id}/edit")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        User existing = service.getUser(id);

        if(existing==null) {
            return "user/index";
        }

        Long indice = (long) Integer.parseInt(id);

        user.setId(indice);
        service.updateUser(String.valueOf(user.getId()), user);

        model.addAttribute("user", user);

        return "redirect:/users/"+user.getId();
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        User existing = service.getUser(id);

        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);

            service.deleteUser(String.valueOf(indice));
        }

        return "redirect:/users";
    }




}
