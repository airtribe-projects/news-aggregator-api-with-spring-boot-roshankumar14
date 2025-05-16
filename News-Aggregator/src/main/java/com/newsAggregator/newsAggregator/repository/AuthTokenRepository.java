package com.newsAggregator.newsAggregator.repository;
import com.newsAggregator.newsAggregator.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<UserToken, Long> {

   UserToken findByGenToken(String tokenId);
}
