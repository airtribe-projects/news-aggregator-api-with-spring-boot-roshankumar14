package com.newsAggregator.newsAggregator.services;

import com.newsAggregator.newsAggregator.entity.NewsPreferences;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import com.newsAggregator.newsAggregator.exception.PreferenceNotFoundException;
import com.newsAggregator.newsAggregator.exception.UserNotFoundException;
import com.newsAggregator.newsAggregator.exception.UserPreferenceAlExist;
import com.newsAggregator.newsAggregator.repository.NewsReaderRepository;
import com.newsAggregator.newsAggregator.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServices {

    @Autowired
    private PreferenceRepository _prefRepository;

    @Autowired
    private NewsReaderRepository _newsReaderRepository;

    public List<NewsPreferences> getPreferences(String UserName) throws PreferenceNotFoundException {

        NewsReaderUser newsReaderUser = _newsReaderRepository.findByUserName(UserName);
        List<NewsPreferences> preferencesList = _prefRepository.findByNewsReaderUserUserId(newsReaderUser.getUserId());

        if(preferencesList.isEmpty()){
            throw new PreferenceNotFoundException("There is no preference selected yet");
        }
        return preferencesList;
    }

    public List<NewsPreferences> getAllPreferences() throws PreferenceNotFoundException {
        List<NewsPreferences> preferencesList = _prefRepository.findAll();
        if(preferencesList.isEmpty()){
            throw new PreferenceNotFoundException("There is no preference selected yet");
        }
        return preferencesList;
    }

    public NewsPreferences updatePref(String userName, String preference) throws UserNotFoundException, UserPreferenceAlExist {
        NewsReaderUser newsReaderUser = _newsReaderRepository.findByUserName(userName);
        List<NewsPreferences> newsPreferencesList = _prefRepository.findByNewsReaderUserUserId(newsReaderUser.getUserId());

        for(NewsPreferences newsPreferences : newsPreferencesList){
            if(newsPreferences.getReaderPref().equalsIgnoreCase(preference)){
                throw new UserPreferenceAlExist("User provided preference already exist");
            }
        }

        NewsPreferences newsPreferences = new NewsPreferences();
        newsPreferences.setReaderPref(preference.toLowerCase());
        newsPreferences.setNewsReaderUser(newsReaderUser);
        return _prefRepository.save(newsPreferences);
    }
}
