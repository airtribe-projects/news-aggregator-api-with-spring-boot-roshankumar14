package com.newsAggregator.newsAggregator.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gnewsClient", url = "https://gnews.io/api/v4")
public interface GnewsClient {


    @GetMapping("/search")
    String getNews(
            @RequestParam("q") String query,
            @RequestParam("token") String token,
            @RequestParam("lang") String lang,
            @RequestParam("max") int max
    );
}
