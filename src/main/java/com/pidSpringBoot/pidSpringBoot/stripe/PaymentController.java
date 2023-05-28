package com.pidSpringBoot.pidSpringBoot.stripe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pidSpringBoot.pidSpringBoot.stripe.ChargeRequest.Currency;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class PaymentController {
    @Autowired
    StripeService paymentsService;

    @Value("${stripe.public.key}")
    private String stripePublicKey;
    
    @RequestMapping("/checkout")
    public String checkout(@RequestParam int amount, Model model) {
        model.addAttribute("amount", amount * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        model.addAttribute("isAdmin", true);
        return "stripe/checkout";
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("isAdmin", true);
        return "stripe/result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("isAdmin", true);
        return "stripe/result";
    }

}