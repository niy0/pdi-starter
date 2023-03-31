package com.pidSpringBoot.pidSpringBoot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControlller {

    @RequestMapping("/")
    public String index() {

        return "Greetings from Spring boot!";
    }
}
