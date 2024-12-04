package com.example.day34lab11blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "userId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "postId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotNull(message = "content cannot be null")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;

//    @Column(columnDefinition = "timestamp not null")
@Column(updatable = false)
@CreationTimestamp
    private LocalDateTime commentDate;

    public @NotNull(message = "userId cannot be null") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "userId cannot be null") Integer userId) {
        this.userId = userId;
    }

    public @NotNull(message = "postId cannot be null") Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull(message = "postId cannot be null") Integer postId) {
        this.postId = postId;
    }

    public @NotNull(message = "content cannot be null") String getContent() {
        return content;
    }

    public void setContent(@NotNull(message = "content cannot be null") String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
