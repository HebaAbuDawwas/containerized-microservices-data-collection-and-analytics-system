package com.atypon.AnalyticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping ("/calculate")
    @ResponseBody
    public ResponseEntity<String> calculateStatistics(@RequestParam String username) {
        analyticsService.calculateAndSaveStatistics(username);
        return ResponseEntity.ok("Statistics calculated and saved.");
    }
}
