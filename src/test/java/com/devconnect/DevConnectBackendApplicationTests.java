package com.devconnect;

import com.devconnect.model.User;
import com.devconnect.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class DevConnectBackendApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRegistration() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("encodedpass");
        userRepository.save(user);

        User savedUser = userRepository.findByEmail("test@example.com").orElse(null);
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
    }
}
