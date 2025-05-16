package com.newsAggregator.newsAggregator.controller;
import com.newsAggregator.newsAggregator.config.JwtUtill;
import com.newsAggregator.newsAggregator.constant.NewsAggregatorConstant;
import com.newsAggregator.newsAggregator.dto.NewsReaderUserDTO;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import com.newsAggregator.newsAggregator.exception.UserNotFoundException;
import com.newsAggregator.newsAggregator.services.AuthTokenServices;
import com.newsAggregator.newsAggregator.services.NewsReaderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/news")
public class NewsReaderController {

    @Autowired
    private NewsReaderServices _newsReaderServices;

    @Autowired
    private AuthTokenServices _authTokenServices;

    @PostMapping("/register")
    public NewsReaderUser registerNewUser(@RequestBody NewsReaderUserDTO newsReaderDto){

        //Create the user
        NewsReaderUser newsReaderUser =  _newsReaderServices.registerUser(newsReaderDto);

        //Let's prepare to generate the logic to validate user
        String genToken  = UUID.randomUUID().toString();
        String appURL = NewsAggregatorConstant.appUrl.concat("verifyToken?genToken=").concat(genToken);
        System.out.println("Please verify your email by clicking the url "+appURL);

        //Create the user token
        _authTokenServices.registerVerificationToken(newsReaderUser,genToken);
        return newsReaderUser;
    }

    @GetMapping("/login")
    public String loginUser(@RequestParam String userName, @RequestParam String password) throws UserNotFoundException {

        _newsReaderServices.loginUser(userName, password);
        String genToken = JwtUtill.generateToken(userName);
        System.out.println("Token = [" + genToken + "]");
        return "User logged in successfully with key " +genToken;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e){
        ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
