package com.pidSpringBoot.pidSpringBoot.reservation;


import com.pidSpringBoot.pidSpringBoot.Representation.Representation;
import com.pidSpringBoot.pidSpringBoot.Representation.RepresentationService;
import com.pidSpringBoot.pidSpringBoot.user.User;
import com.pidSpringBoot.pidSpringBoot.user.UserService;

import jakarta.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    RepresentationService service;

    @Autowired
    UserService userService;

    @PostMapping("/addToCart/{id}")
public String addToCart(@PathVariable int id, HttpSession session) {
    Representation representation = service.getRepresentationById(id).orElse(null);
    if (representation != null) {
        List<Representation> cart = getCartFromSession(session);
        if (cart != null) {
            cart.add(representation);
            System.out.println("Added to cart: " + representation);
        } else {
            System.out.println("Cart is null");
        }
    } else {
        System.out.println("Representation is null");
    }
    return "redirect:/cart";
}


    @PostMapping("/removeFromCart/{id}")
    public String removeFromCart(@PathVariable int id, HttpSession session) {
        List<Representation> cart = getCartFromSession(session);
        Representation representationToRemove = null;
        for (Representation representation : cart) {
            if (representation.getId() == id) {
                representationToRemove = representation;
                break;
            }
        }
        if (representationToRemove != null) {
            cart.remove(representationToRemove);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cartGet(Model model, HttpSession session) {
        List<Representation> cart = getCartFromSession(session);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findUser(currentUser);
        model.addAttribute("user",user);
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("cart", cart);

        return "cart";
    }

    private List<Representation> getCartFromSession(HttpSession session) {
        List<Representation> cart = (List<Representation>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

}
