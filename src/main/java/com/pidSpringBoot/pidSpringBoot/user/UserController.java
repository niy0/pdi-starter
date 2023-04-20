package com.pidSpringBoot.pidSpringBoot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/admin/admin_home")
    public String homeAdmin(){
        return "admin_home";
    }

    /**
    @GetMapping("/admin/admin_home/{id}")
    public String homeAdmin2(@PathVariable("id") Integer id, Model model , RedirectAttributes redirectAttributes){
        try {
            User userConnect = userRepository.getById(id);
            model.addAttribute("user", userConnect);
            model.addAttribute("pageTitle", "Bienvenu : "+userConnect.getFirstName());
            return "admin_home";
        }catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message", "User pas touvé");
            return "redirect:/";
        }
    }**/


    @GetMapping("/member/member_home")
    public String homeMember(){
        return "member_home";
    }

    @GetMapping("/admin/list_users")
    public String showListUsers(Model model){
        List<User> listUsers = (List<User>) userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "list_users";
    }

    @PostMapping("/process_signup")
    public String processRegistration(User user, RedirectAttributes redirectAttributes){
        User userTest =  userRepository.findByLogin(user.getLogin());
        if(userTest != null) {
            //model.addAttribute("currentUser", user);
            redirectAttributes.addFlashAttribute("message", "Login existe déjà !");
            return "redirect:/signup";
        }else {
            customUserDetailsService.setPasswordEncoder(user);//encoder le password
            customUserDetailsService.registerDefaultUser(user);//mettre par defaut role "Member"
            return "signup_success";
        }

    }
}
