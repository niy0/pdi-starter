package com.pidSpringBoot.pidSpringBoot;

import com.pidSpringBoot.pidSpringBoot.show.Show;
import com.pidSpringBoot.pidSpringBoot.show.ShowService;
import com.pidSpringBoot.pidSpringBoot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ShowService service;



    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Show> shows = service.getAll();
        model.addAttribute("isAdmin", false);
        model.addAttribute("isMember", false);
        model.addAttribute("shows",shows);
        model.addAttribute("title", "Liste des spectacles");
        return "index";
    }

    @GetMapping("/signup")
    public String showUserSignUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isAdmin", false);
        model.addAttribute("isMember", false);
        return "user/user_signup_form";
    }






}
