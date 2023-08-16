package com.atypon.EnterDataWebApp;


import com.atypon.EnterDataWebApp.models.MarksEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class DataEntryController {

    private final MarksEntryRepository marksEntryRepository;
    private final RestTemplate restTemplate;


    @Autowired
    public DataEntryController(MarksEntryRepository marksEntryRepository, RestTemplate restTemplate) {
        this.marksEntryRepository = marksEntryRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String showDataEntryForm() {
        return "data-entry";
    }

    @PostMapping("/submit-marks")
    public String submitMarks(@RequestParam String username, @RequestParam String password, @RequestParam int[] marks) {


        String authServiceURL = "http://authentication-app:8081/authenticate";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(authServiceURL + "?username=" + username + "&password=" + password, String.class);

            if (response.getStatusCode() == OK) {
                System.out.println("\n\n\nDONE\n\n\n");
                for (int mark : marks) {
                    MarksEntry entry = new MarksEntry(username, mark);
                    marksEntryRepository.save(entry);
                }
                return "welcome";
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                System.out.println("Authentication failed: Invalid credentials");
                return "data-entry";
            }
        }


        return "data-entry";
    }

}


