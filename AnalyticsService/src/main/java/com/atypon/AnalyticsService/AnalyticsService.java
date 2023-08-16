package com.atypon.AnalyticsService;

import com.atypon.AnalyticsService.models.MarksEntry;
import com.atypon.AnalyticsService.models.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final MarksEntryRepository marksEntryRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AnalyticsService(MarksEntryRepository marksEntryRepository, MongoTemplate mongoTemplate) {
        this.marksEntryRepository = marksEntryRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void calculateAndSaveStatistics(String username) {
        List<MarksEntry> marksEntries = marksEntryRepository.findByUsername(username);

        if (!marksEntries.isEmpty()) {
            int sum = marksEntries.stream().mapToInt(MarksEntry::getMarks).sum();
            double average = (double) sum / marksEntries.size();
            int maxMarks = marksEntries.stream().mapToInt(MarksEntry::getMarks).max().orElse(0);
            int minMarks = marksEntries.stream().mapToInt(MarksEntry::getMarks).min().orElse(0);


            Query query = Query.query(Criteria.where("username").is(username));
            Statistics existingStatistics = mongoTemplate.findOne(query, Statistics.class);
            Statistics updatedStatistics = new Statistics(username, sum, average, maxMarks, minMarks);
            if (existingStatistics != null) {
                updatedStatistics.setId(existingStatistics.getId());
            }

            mongoTemplate.save(updatedStatistics);


        }
    }
}
