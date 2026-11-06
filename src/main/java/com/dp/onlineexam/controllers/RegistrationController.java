package com.dp.onlineexam.controllers;

import com.dp.onlineexam.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {
    private final JdbcTemplate jdbcTemplate;
    final String sql = """
                INSERT INTO users (email, password, phone_number, address)
                VALUES (?, ?, ?, ?)
                """;
    public RegistrationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("JDBC Template has been created "+this.jdbcTemplate);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Welcome to Spring Boot REST API!";
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {

        jdbcTemplate.update(
                sql,
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAddress()
        );

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");

        return ResponseEntity.ok(response);
    }
}