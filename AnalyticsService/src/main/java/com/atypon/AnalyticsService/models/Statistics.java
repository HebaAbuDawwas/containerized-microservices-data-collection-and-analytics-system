package com.atypon.AnalyticsService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    private String id;

    private String username;
    private int sum;
    private double average;
    private int maxMarks;
    private int minMarks;

    public Statistics(String username, int sum, double average, int maxMarks, int minMarks) {
        this.username = username;
        this.sum = sum;
        this.average = average;
        this.maxMarks = maxMarks;
        this.minMarks = minMarks;
    }
}
