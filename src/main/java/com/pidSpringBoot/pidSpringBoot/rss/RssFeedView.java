package com.pidSpringBoot.pidSpringBoot.rss;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RssFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, 
      Channel feed, HttpServletRequest request) {
        feed.setTitle("Projet reservation");
        feed.setDescription("Projet des reservations des spectacles");
        feed.setLink("https://laughable-soap-production.up.railway.app/");
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Item entryOne = new Item();
        entryOne.setTitle("Projet reservation");
        entryOne.setLink("https://laughable-soap-production.up.railway.app/");
        entryOne.setPubDate(Date.from(Instant.parse("2017-12-19T00:00:00Z")));
        return Arrays.asList(entryOne);
    }
}
