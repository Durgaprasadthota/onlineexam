package com.dp.onlineexam.controllers;

import com.dp.onlineexam.models.User;
import com.dp.onlineexam.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// Autheciation controller to authenciate users
@RestController
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userexists")
    public ResponseEntity<Boolean> isAuthenticatedUser(@RequestBody User user) {
        try {
            boolean IsUserRegistered = userService.isAuthenticatedUser(user);
            if( IsUserRegistered )
            {  return ResponseEntity.ok(true); }
            else
            {  return ResponseEntity.ok(false); }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
