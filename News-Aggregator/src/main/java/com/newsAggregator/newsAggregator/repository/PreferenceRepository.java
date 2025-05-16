package com.newsAggregator.newsAggregator.repository;

import com.newsAggregator.newsAggregator.entity.NewsPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<NewsPreferences, Long> {
    List<NewsPreferences> findByNewsReaderUserUserId(Long userId);
}
