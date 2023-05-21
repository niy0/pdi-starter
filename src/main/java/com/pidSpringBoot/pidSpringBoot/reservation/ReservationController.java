package com.pidSpringBoot.pidSpringBoot.reservation;


import com.pidSpringBoot.pidSpringBoot.Representation.Representation;
import com.pidSpringBoot.pidSpringBoot.Representation.RepresentationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        Representation representation = service.getRepresentationById(id).orElse(null);
        if (representation != null) {
            List<Representation> cart = getCartFromSession(session);
            cart.add(representation);
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
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("cart", cart);
        model.addAttribute("total", calculateTotalPrice(cart));
        model.addAttribute("isAdmin", true);

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

    private double calculateTotalPrice(List<Representation> cart) {
        double total = 0;
        for (Representation representation : cart) {
            total += representation.getShow().getPrice();
        }
        return total;
    }
}
