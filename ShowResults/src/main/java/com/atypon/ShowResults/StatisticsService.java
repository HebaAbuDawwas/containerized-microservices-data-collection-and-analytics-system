package com.atypon.ShowResults;

import com.atypon.ShowResults.models.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatisticsService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Statistics> getStatisticsByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return Optional.ofNullable(mongoTemplate.findOne(query, Statistics.class));
    }
}