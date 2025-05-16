package com.newsAggregator.newsAggregator.services;

import com.newsAggregator.newsAggregator.entity.NewsPreferences;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import com.newsAggregator.newsAggregator.repository.GnewsClient;
import com.newsAggregator.newsAggregator.repository.NewsReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GNewsService {

    @Value("${gnews.api.token}")
    private String apiKey;

    @Autowired
    private GnewsClient gNewsClient;

    @Autowired
    private NewsReaderRepository _newsReaderRepository;

    public String fetchNews(String userName) {

        String pref = null;

        NewsReaderUser newsReaderUser = _newsReaderRepository.findByUserName(userName);
        List<NewsPreferences> preferenceList = newsReaderUser.getNewsPreferences();

        for(int i=0; i<preferenceList.size(); i++){

            if(i==0){
                pref = preferenceList.get(i).getReaderPref();
            }else{
                pref = pref.concat(" AND ").concat(preferenceList.get(i).getReaderPref());
            }

        }
        System.out.println(pref);


        return gNewsClient.getNews(pref, apiKey, "en", 10);
    }
}
