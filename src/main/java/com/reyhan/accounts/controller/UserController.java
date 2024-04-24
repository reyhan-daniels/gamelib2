package com.reyhan.accounts.controller;

import com.reyhan.accounts.dto.LoginDTO;
import com.reyhan.accounts.dto.UserInfoDTO;
import com.reyhan.accounts.model.User;
import com.reyhan.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = userService.authenticateUser(loginDTO);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current-user")
    @PreAuthorize("hasRole('USER')")
    public void getCurrentUser() {
        userService.getCurrentUser();
    }

}
