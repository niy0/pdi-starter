package com.pidSpringBoot.pidSpringBoot.rss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssFeedController {

    @Autowired
    private RssFeedView view;
    
    @GetMapping("/rss")
    public RssFeedView getFeed() {
        return view;
    }
}