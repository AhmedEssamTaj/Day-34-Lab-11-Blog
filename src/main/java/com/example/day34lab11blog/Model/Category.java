package com.example.day34lab11blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "category name cannot be empty")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;

    public @NotEmpty(message = "category name cannot be empty") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "category name cannot be empty") String name) {
        this.name = name;
    }
}
