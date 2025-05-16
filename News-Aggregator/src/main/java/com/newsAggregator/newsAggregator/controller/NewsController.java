package com.newsAggregator.newsAggregator.controller;

import com.newsAggregator.newsAggregator.services.GNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/news")
public class NewsController {


    @Autowired
    private GNewsService gNewsService;

    @GetMapping("/news")
    public ResponseEntity<String> getNews(@RequestParam String userName) {
        return ResponseEntity.ok(gNewsService.fetchNews(userName));
    }
}
