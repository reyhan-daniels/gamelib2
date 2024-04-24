package com.reyhan.accounts.service;

import com.reyhan.accounts.controller.UserController;
import com.reyhan.accounts.dto.LoginDTO;
import com.reyhan.accounts.exception.UserAlreadyExistsException;
import com.reyhan.accounts.model.User;
import com.reyhan.accounts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        logger.info("Registering user: {}", user.getUsername());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.error("Username already exists");
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("User registered successfully");
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Authentication authenticateUser(LoginDTO loginDTO) {
        logger.info("Authenticating user: {}", loginDTO.getUsername());
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null) {
            logger.info("User found: {}", user.getUsername());
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {

                UserDetails userDetails = convertToUserDetails(user);

                System.out.println(userDetails);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                System.out.println(SecurityContextHolder.getContext().getAuthentication());

                logger.info("User authenticated successfully");
                return authentication;
            }
        }
        logger.error("User authentication failed");
        return null;
    }

    private UserDetails convertToUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    public void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println(authentication);
        }
        else {
            logger.error("No user logged in");
        }
    }
}
