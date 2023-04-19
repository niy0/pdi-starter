package com.pidSpringBoot.pidSpringBoot;

import com.pidSpringBoot.pidSpringBoot.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    /**private UserRepository userRepository;**/

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public String showUserSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "user_signup_form";
    }

    /**
    @GetMapping("/login")
    public String showUserLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }**/

    /**@PostMapping("/process_signup")
    public String processRegistration(User user){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "signup_success";
    }**/


}
