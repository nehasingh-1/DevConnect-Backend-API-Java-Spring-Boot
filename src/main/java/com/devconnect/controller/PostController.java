package com.devconnect.controller;

import com.devconnect.model.Post;
import com.devconnect.model.User;
import com.devconnect.repository.PostRepository;
import com.devconnect.repository.UserRepository;
import com.devconnect.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postRepository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token, @RequestBody Post post) {
        String email = jwtService.extractEmail(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow();
        post.setUser(user);
        return ResponseEntity.ok(postRepository.save(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postRequest) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return ResponseEntity.ok(postRepository.save(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok("Post deleted");
    }
}
