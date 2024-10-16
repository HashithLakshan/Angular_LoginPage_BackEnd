package com.example.demo.service.serviceImpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashing {

    public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    // Matching the DB userHashPassword & UserPassword
    public boolean checkPassword(String userPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(userPassword, hashedPassword);
    }

    // Hash the User Password
    public String hashPassword(String userPassword) {
        return bCryptPasswordEncoder.encode(userPassword);
    }
}
