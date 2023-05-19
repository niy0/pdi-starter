package com.pidSpringBoot.pidSpringBoot.rss;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pidSpringBoot.pidSpringBoot.show.Show;
import com.pidSpringBoot.pidSpringBoot.show.ShowRepository;
import com.pidSpringBoot.pidSpringBoot.show.ShowService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RssFeedController {
    @Autowired
    private RssFeedView view;

    @Autowired
    private ShowService service;
    
    @GetMapping("/rss")
    public void generateRssFeed(HttpServletResponse response) throws Exception {
        // Retrieve the list of shows from your data source
        List<Show> shows = service.getAll();
    
        // Create the RSS feed
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("My Website RSS Feed");
        feed.setDescription("Latest updates from My Website");
        feed.setLink("https://laughable-soap-production.up.railway.app/");
        feed.setPublishedDate(new Date());
    
        // Build the feed items
        List<SyndEntry> entries =   view.buildFeedItems(shows);
        feed.setEntries(entries);
    
        // Write the feed to the response
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, response.getWriter());
    }
    



}