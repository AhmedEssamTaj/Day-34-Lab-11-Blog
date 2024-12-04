package com.example.day34lab11blog.Controller;

import com.example.day34lab11blog.Model.Category;
import com.example.day34lab11blog.Model.Comment;
import com.example.day34lab11blog.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("comment added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid Comment comment, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body("comment updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body("comment deleted successfully");
    }

}
