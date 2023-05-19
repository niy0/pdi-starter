package com.pidSpringBoot.pidSpringBoot.rss;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Component;


import com.pidSpringBoot.pidSpringBoot.show.Show;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;

@Component
public class RssFeedView  {

    protected List<SyndEntry> buildFeedItems(List<Show> shows) {
        List<SyndEntry> entries = new ArrayList<>();
    
        for (Show show : shows) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(show.getTitle());
            entry.setLink("https://laughable-soap-production.up.railway.app/shows/" + show.getId());
            entry.setPublishedDate(Date.from(Instant.now()));
    
            SyndContent description = new SyndContentImpl();
            description.setValue(show.getDescription());
            entry.setDescription(description);
    
            entries.add(entry);
        }
    
        return entries;
    }


    
}
