package com.dp.onlineexam.controllers;

import com.dp.onlineexam.models.User;
import com.dp.onlineexam.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenciationController {
    private final UserService userService;

    public AuthenciationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenciateUser")
    public ResponseEntity<Boolean> registerUser(@RequestBody User user) {
        try {
            boolean IsUserRegistered = userService.IsAuthenciatedUser(user);
            if( IsUserRegistered )
            {  return ResponseEntity.ok(true); }
            else
            {  return ResponseEntity.ok(false); }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
