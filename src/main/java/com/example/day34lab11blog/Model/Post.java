package com.example.day34lab11blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "categoryId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotEmpty(message = "title cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "content cannot be empty")
    @Column(columnDefinition = "varchar(350) not null")
    private String content;

    @NotNull(message = "userId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    //    @Column(columnDefinition = "timestamp not null")
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime publishDate;

    public @NotNull(message = "userId cannot be null") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "userId cannot be null") Integer userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "content cannot be empty") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "content cannot be empty") String content) {
        this.content = content;
    }

    public @NotEmpty(message = "title cannot be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "title cannot be empty") String title) {
        this.title = title;
    }

    public @NotNull(message = "categoryId cannot be null") Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = "categoryId cannot be null") Integer categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
