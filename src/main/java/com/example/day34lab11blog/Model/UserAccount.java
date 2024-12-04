package com.example.day34lab11blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, message = "size length must be at least 4")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "CHAR_LENGTH(username) > 4")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email must be a valid email format")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @Column(updatable = false)
    @CreationTimestamp
    private String registrationDate;

    public @NotEmpty(message = "username cannot be empty") @Size(min = 4, message = "size length must be at least 4") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "username cannot be empty") @Size(min = 4, message = "size length must be at least 4") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "password cannot be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password cannot be empty") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "email cannot be empty") @Email(message = "email must be a valid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email cannot be empty") @Email(message = "email must be a valid email format") String email) {
        this.email = email;
    }
}
