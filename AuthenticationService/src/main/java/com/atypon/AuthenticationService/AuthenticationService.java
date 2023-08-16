package com.atypon.AuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        return "hdawwas".equals(username) && "hrmad".equals(password);
    }
}
