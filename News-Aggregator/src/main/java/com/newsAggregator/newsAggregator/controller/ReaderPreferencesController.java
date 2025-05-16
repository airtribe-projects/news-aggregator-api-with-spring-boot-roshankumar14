package com.newsAggregator.newsAggregator.controller;

import com.newsAggregator.newsAggregator.entity.NewsPreferences;
import com.newsAggregator.newsAggregator.exception.PreferenceNotFoundException;
import com.newsAggregator.newsAggregator.exception.UserNotFoundException;
import com.newsAggregator.newsAggregator.exception.UserPreferenceAlExist;
import com.newsAggregator.newsAggregator.services.PreferenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/news")
public class ReaderPreferencesController {

    @Autowired
    private PreferenceServices _prefServices;

    @GetMapping("/preference")
    public List<NewsPreferences> getTheUserPreferences(@RequestParam (value = "userName" , required = true) String userName) throws PreferenceNotFoundException {

        return _prefServices.getPreferences(userName);
    }


    @PutMapping("/preference")
    public NewsPreferences updateTheUserPreferences(@RequestParam String userName , @RequestParam String preference) throws UserNotFoundException, UserPreferenceAlExist {
        return _prefServices.updatePref(userName, preference);
    }

    @ExceptionHandler(PreferenceNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(PreferenceNotFoundException e){
        ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(UserPreferenceAlExist.class)
    public ResponseEntity handleUserPreferenceAlExist(UserPreferenceAlExist e){
        ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
