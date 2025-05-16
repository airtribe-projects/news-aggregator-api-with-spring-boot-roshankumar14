package com.newsAggregator.newsAggregator.services;

import com.newsAggregator.newsAggregator.entity.UserToken;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import com.newsAggregator.newsAggregator.repository.AuthTokenRepository;
import com.newsAggregator.newsAggregator.repository.NewsReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuthTokenServices {

    @Autowired
    private AuthTokenRepository _authTokenRepo;

    @Autowired
    private NewsReaderRepository _newsReaderRepo;

    public boolean verifyToken(String tokenId){
        UserToken authToken = _authTokenRepo.findByGenToken(tokenId);
        if (authToken == null) return false;
        return true;
    }

    public void registerVerificationToken(NewsReaderUser user, String token) {
        UserToken authTokenEnt = new UserToken();
        authTokenEnt.setNewsReaderUser(user);
        authTokenEnt.setGenToken(token);

        //Set the expiry days for the token
        Instant now = Instant.now();
        Instant future = now.plusSeconds(24 * 60 * 60); // add 24 hours in seconds
        Date datePlus24Hours = Date.from(future);
        authTokenEnt.setExpDate(datePlus24Hours);

        //Create the token
        _authTokenRepo.save(authTokenEnt);
    }

    public void enableUser(String tokenId) {
        UserToken authToken = _authTokenRepo.findByGenToken(tokenId);
        NewsReaderUser newsReaderUser = authToken.getNewsReaderUser();
        newsReaderUser.setEnable(true);
        _newsReaderRepo.save(newsReaderUser);
        _authTokenRepo.delete(authToken);
    }
}
