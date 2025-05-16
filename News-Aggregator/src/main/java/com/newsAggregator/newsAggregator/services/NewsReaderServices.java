package com.newsAggregator.newsAggregator.services;

import com.newsAggregator.newsAggregator.dto.NewsReaderUserDTO;
import com.newsAggregator.newsAggregator.entity.NewsPreferences;
import com.newsAggregator.newsAggregator.exception.UserNotFoundException;
import com.newsAggregator.newsAggregator.repository.NewsReaderRepository;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsReaderServices{

    @Autowired
    private NewsReaderRepository _newsReaderRepo;

    @Autowired
    private PasswordEncoder _passEncoder;


    public NewsReaderUser registerUser(NewsReaderUserDTO newsReaderUserDto){
        NewsReaderUser newsReaderUserEnt = new NewsReaderUser();
        newsReaderUserEnt.setPassword(_passEncoder.encode(newsReaderUserDto.getPassword()));
        newsReaderUserEnt.setUserName(newsReaderUserDto.getUserName());
        newsReaderUserEnt.setEmailId(newsReaderUserDto.getEmailId());
        newsReaderUserEnt.setLanguage(newsReaderUserDto.getLanguage());
        newsReaderUserEnt.setEnable(false);

        List<NewsPreferences> preferences = newsReaderUserDto.getNewsPreferences().stream()
                .map(category -> {
                    NewsPreferences pref = new NewsPreferences();
                    pref.setReaderPref(category.toLowerCase());
                    pref.setNewsReaderUser(newsReaderUserEnt); // maintain the bidirectional relationship
                    return pref;
                })
                .collect(Collectors.toList());

        newsReaderUserEnt.setNewsPreferences(preferences);
        return _newsReaderRepo.save(newsReaderUserEnt);
    }

    public NewsReaderUser loginUser(String userName, String password) throws UsernameNotFoundException, UserNotFoundException {
        NewsReaderUser loggedInUser = _newsReaderRepo.findByUserName(userName);
        if(loggedInUser == null){
            throw new UserNotFoundException("User name not exist");
        }

        if(!loggedInUser.isEnable()){
            throw new UserNotFoundException("Your account is not verified. Please check your email for a verification link or request a new one to continue");
        }

        if(!_passEncoder.matches(password, loggedInUser.getPassword())){
            throw new UserNotFoundException("Please provide a valid password");
        }

        return loggedInUser;
    }
}
