package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    private final UserService userService;
    
    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }
    
    @PostConstruct
    public void initData() {
        logger.info("Initializing sample data...");
        
        try {
            // Check if data already exists
            if (userService.getUserCount() > 0) {
                logger.info("Data already exists. Skipping initialization.");
                return;
            }
            
            // Create sample users
            User user1 = new User("john_doe", "john.doe@example.com", "John", "Doe");
            User user2 = new User("jane_smith", "jane.smith@example.com", "Jane", "Smith");
            User user3 = new User("bob_wilson", "bob.wilson@example.com", "Bob", "Wilson");
            User user4 = new User("alice_brown", "alice.brown@example.com", "Alice", "Brown");
            User user5 = new User("charlie_davis", "charlie.davis@example.com", "Charlie", "Davis");
            
            // Save users
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);
            userService.createUser(user5);
            
            // Deactivate one user for testing
            User savedUser = userService.getUserByUsername("charlie_davis").orElse(null);
            if (savedUser != null) {
                userService.deactivateUser(savedUser.getId());
            }
            
            logger.info("Sample data initialized successfully. Created {} users.", userService.getUserCount());
            logger.info("Active users: {}", userService.getActiveUsers().size());
            
        } catch (Exception e) {
            logger.error("Error initializing sample data", e);
        }
    }
}
