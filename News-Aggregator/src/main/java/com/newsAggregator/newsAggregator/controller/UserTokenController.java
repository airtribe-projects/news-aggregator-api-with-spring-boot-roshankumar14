package com.newsAggregator.newsAggregator.controller;

import com.newsAggregator.newsAggregator.services.AuthTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/news")
public class UserTokenController {

    @Autowired
    private AuthTokenServices _authTokenServices;

    @PostMapping("/verifyToken")
    public String verifyUserToken(@RequestParam String genToken){
        boolean isUserVerified  = _authTokenServices.verifyToken(genToken);

        if(isUserVerified){
            _authTokenServices.enableUser(genToken);
        }else{
            System.out.println("User not verified, invalid token");
        }
        return "User verified successfully";
    }
}
