package com.devconnect.controller;

import com.devconnect.model.Message;
import com.devconnect.model.User;
import com.devconnect.repository.MessageRepository;
import com.devconnect.repository.UserRepository;
import com.devconnect.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestHeader("Authorization") String token, @RequestBody Message message) {
        String email = jwtService.extractEmail(token.substring(7));
        User sender = userRepository.findByEmail(email).orElseThrow();
        message.setSender(sender);
        return ResponseEntity.ok(messageRepository.save(message));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Message>> getMyMessages(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractEmail(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(messageRepository.findBySenderIdOrReceiverId(user.getId(), user.getId()));
    }
}
