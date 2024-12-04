package com.example.day34lab11blog.Controller;

import com.example.day34lab11blog.ApiResponse.ApiResponse;
import com.example.day34lab11blog.Model.Post;
import com.example.day34lab11blog.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("post added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("post updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("post deleted successfully"));
    }

    // endpoint to get all posts by userId
    @GetMapping("/get-userid/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable Integer userId) {

        return ResponseEntity.status(200).body(postService.getPostsByUserId(userId));
    }

    // endpoint to get post by title
    @GetMapping("/get-title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    // endpoint to get posts that contiains this word in the title
    @GetMapping("/get-word-title/{title}")
    public ResponseEntity getPostsByWordInTitle (@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostsContainTitle(title));
    }

    // endpoint to get posts withn date range
    @GetMapping("/get-date-range/{start}/{end}")
    public ResponseEntity getPostsByDateRange (@PathVariable LocalDateTime start,@PathVariable LocalDateTime end) {
        return ResponseEntity.status(200).body(postService.getPostsByDateRange(start, end));
    }

    // endpoint to get posts by category id
    @GetMapping("/get-category/{categoryId}")
    public ResponseEntity getPostsByCategory (@PathVariable Integer categoryId) {
        return ResponseEntity.status(200).body(postService.getPostsByCategoryId(categoryId));
    }

    // endpoint to get the number of posts by a user
    @GetMapping("/get-count/{id}")
    public ResponseEntity getNumberOfPostsById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostsByCategoryId(id));
    }

    // endpoint to get the most recent post by user
    @GetMapping("/get-recent/{userId}")
    public ResponseEntity getMostRecentPostByUser (@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.getRecentPostByUserId(userId));
    }
}
