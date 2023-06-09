package com.pidSpringBoot.pidSpringBoot.user;

import com.pidSpringBoot.pidSpringBoot.location.LocationNotFoundException;
import com.pidSpringBoot.pidSpringBoot.location.LocationService;
import com.pidSpringBoot.pidSpringBoot.role.Role;
import com.pidSpringBoot.pidSpringBoot.show.Show;
import com.pidSpringBoot.pidSpringBoot.show.ShowService;
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
    @PostMapping("/profile/edit")
    public String saveProfileInfo(User user, Model model) {
        customUserDetailsService.save(user);
        model.addAttribute("message", user.getRoles());
        return "redirect:/profile";
    }
    
    @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable("id") Integer id, Model model){
            User userToEdit = userRepository.findById(id).get();
            List<Role> roleList = customUserDetailsService.listRoles();
            model.addAttribute("userToEdit", userToEdit);
            model.addAttribute("listRoles", roleList);
            return "user/edit";
       
    }


    
    @GetMapping("/admin/home")
    public String homeAdmin(Model model){
        List<Show> shows = showService.getAll();
        model.addAttribute("shows",shows);
        model.addAttribute("title", "Liste des spectacles");
        return "admin/admin_home";
    }

    @GetMapping("/admin/edit/{id}")
    public String adminEditUser(@PathVariable("id") Integer id, Model model){
            User userToEdit = userRepository.findById(id).get();
            List<Role> roleList = customUserDetailsService.listRoles();
            model.addAttribute("userToEdit", userToEdit);
            model.addAttribute("listRoles", roleList);
            return "admin/admin_edit_user";
       
    }
    @GetMapping("/member/home")
    public String homeMember(Model model){
        List<Show> shows = showService.getAll();
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
        
        return "user/profile";
    }

    @GetMapping("/admin/list_users")
    public String showListUsers(Model model){

        List<User> listUsers = (List<User>) userRepository.findAll();
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
            customUserDetailsService.setPasswordEncoder(user);//encoder le password
            customUserDetailsService.registerDefaultUser(user);//mettre par defaut role "Member"
            return "user/signup_success";
        }
    }

        @GetMapping("/users/{id}")
        public String show(Model model, @PathVariable("id") String id) {
            Optional<User> user = userService.getUser(id);

            model.addAttribute("user", user);
            model.addAttribute("title", "Fiche d'un user");

            return "user/show";
        }

       @GetMapping("/users/create")
        public String create(Model model) {
            User user = new User();

            model.addAttribute("user", user);

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


   

        @DeleteMapping("/users/{id}")
        public String delete(@PathVariable("id") String id, Model model) throws LocationNotFoundException {
                userService.deleteUser(id);
                
            return "redirect:/admin/list_users";
        }



    }



