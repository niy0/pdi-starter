package com.pidSpringBoot.pidSpringBoot.user;

import com.pidSpringBoot.pidSpringBoot.location.Location;
import com.pidSpringBoot.pidSpringBoot.location.LocationNotFoundException;
import com.pidSpringBoot.pidSpringBoot.location.LocationService;
import com.pidSpringBoot.pidSpringBoot.role.Role;
import com.pidSpringBoot.pidSpringBoot.show.Show;
import com.pidSpringBoot.pidSpringBoot.show.ShowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ShowService showService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private LocationService service;

    //pour l'admin
    @PostMapping("/admin/users/save")
    public String saveUser(User user, Model model) {
        customUserDetailsService.save(user);
        model.addAttribute("message", user.getRoles());
        return "redirect:/admin/list_users";
    }



    @GetMapping("/admin/admin_home")
    public String homeAdmin(Model model){
        List<Show> shows = showService.getAll();
        model.addAttribute("isAdmin", true);
        model.addAttribute("shows",shows);
        model.addAttribute("title", "Liste des spectacles");
        return "admin/admin_home";
    }

    @GetMapping("/admin/edit/{id}")
    public String adminEditUser(@PathVariable("id") Integer id, Model model){
        if(userRepository.findById(id).isPresent()){
            userToEdit = userRepository.findById(id).get();
            List<Role> roleList = customUserDetailsService.listRoles();
            model.addAttribute("isAdmin", true);
            model.addAttribute("userToEdit", userToEdit);
            model.addAttribute("listRoles", roleList);
            return "admin/admin_edit_user";
        }
        return "redirect:admin/list_users";
    }
    @GetMapping("/user/member_home")
    public String homeMember(Model model){
        List<Show> shows = showService.getAll();
        model.addAttribute("isAdmin", false);
        model.addAttribute("isMember", true);
        model.addAttribute("shows",shows);
        model.addAttribute("title", "Liste des spectacles");
        return "user/index";
    }



    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findUser(currentUser);
        System.out.println("---------------------------User = " + user);
        model.addAttribute("user",user);
        model.addAttribute("isMember", true);
        model.addAttribute("isAdmin", false);
        return "user/member_home";
    }

    @GetMapping("/admin/list_users")
    public String showListUsers(Model model){

        List<User> listUsers = (List<User>) userRepository.findAll();
        model.addAttribute("isAdmin", true);
        model.addAttribute("listUsers", listUsers);
        return "admin/list_users";
    }


    @PostMapping("/process_signup")
    public String processRegistration(User user, RedirectAttributes redirectAttributes,Model model) {
        User userTest = userService.findByLogin(user.getLogin());
        if (userTest != null) {
            //model.addAttribute("currentUser", user);
            redirectAttributes.addFlashAttribute("message", "Login existe déjà !");
            return "redirect:/signup";
        } else {
            model.addAttribute("isMember", true);
            model.addAttribute("isAdmin", false);
            customUserDetailsService.setPasswordEncoder(user);//encoder le password
            customUserDetailsService.registerDefaultUser(user);//mettre par defaut role "Member"
            return "user/signup_success";
        }
    }

        @GetMapping("/users/{id}")
        public String show(Model model, @PathVariable("id") String id) {
            Optional<User> user = userService.getUser(id);

            model.addAttribute("user", user);
            model.addAttribute("isMember", true);
            model.addAttribute("title", "Fiche d'un user");

            return "user/show";
        }

       @GetMapping("/users/create")
        public String create(Model model) {
            User user = new User();

            model.addAttribute("user", user);
            model.addAttribute("isMember", true);

            return "user/create";
        }

        @PostMapping("/users/create")
        public String store(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

            if (bindingResult.hasErrors()) {
                return "user/create";
            }

            userService.addUser(user);

            return "redirect:/users/"+user.getId();
        }


    @GetMapping("/users/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        Optional<User> userOptional = userService.getUser(id);

        userOptional.ifPresent(user -> model.addAttribute("user", user));

        // Generate the back link for cancellation
        String referrer = request.getHeader("Referer");

        if(referrer != null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            userOptional.ifPresent(user -> model.addAttribute("back", "/users/" + user.getId()));
        }

        model.addAttribute("isMember", true);
        return "user/edit.html";
    }

        @PutMapping("/users/edit/{id}")
        public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") String id, Model model) {

            if (bindingResult.hasErrors()) {
                return "user/edit.html";
            }

            Optional<User> existing = userService.getUser(id);

            if(existing==null) {
                return "user/index";
            }

            Long indice = (long) Integer.parseInt(id);

            user.setId(Math.toIntExact(indice));
            userService.updateUser(String.valueOf(user.getId()), user);

            model.addAttribute("user", user);
            model.addAttribute("isMember", true);

            return "redirect:/users/"+user.getId();
        }


        @DeleteMapping("/users/{id}")
        public String delete(@PathVariable("id") String id, Model model) throws LocationNotFoundException {
                userService.deleteUser(id);
                model.addAttribute("isAdmin", true);
            return "redirect:/admin/list_users";
        }



    }



