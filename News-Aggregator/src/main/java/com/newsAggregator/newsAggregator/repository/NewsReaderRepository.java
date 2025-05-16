package com.newsAggregator.newsAggregator.repository;
import com.newsAggregator.newsAggregator.entity.NewsReaderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsReaderRepository extends JpaRepository<NewsReaderUser, Long> {

    NewsReaderUser findByUserName(String userName);
}
