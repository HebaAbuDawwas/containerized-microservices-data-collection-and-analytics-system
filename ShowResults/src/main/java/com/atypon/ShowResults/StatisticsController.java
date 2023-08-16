package com.atypon.ShowResults;

import com.atypon.ShowResults.models.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("/show-results")
    public String showStatistics(@RequestParam String username, @RequestParam String password, Model model) {
        String authServiceURL = "http://authentication-app:8081/authenticate";
        String analyticsServiceURL = "http://analytics-app:8082/calculate";
        try {
            ResponseEntity<String> responseAuth = restTemplate.getForEntity(authServiceURL + "?username=" + username + "&password=" + password, String.class);
            ResponseEntity<String> responseAnalytics = restTemplate.getForEntity(analyticsServiceURL + "?username=" + username, String.class);

            if (responseAuth.getStatusCode() == OK && responseAnalytics.getStatusCode() == OK) {
                Optional<Statistics> statistics = statisticsService.getStatisticsByUsername(username);

                if (statistics.isPresent()) {
                    model.addAttribute("statistics", statistics.get());
                    return "result";
                } else {
                    return "no-result";
                }
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                System.out.println("Authentication failed: Invalid credentials");
                return "login";
            }
        }

        return "login";
    }

}