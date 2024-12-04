package com.example.day34lab11blog.Controller;

import com.example.day34lab11blog.ApiResponse.ApiResponse;
import com.example.day34lab11blog.Model.UserAccount;
import com.example.day34lab11blog.Service.UserAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserAccountController {


    private final UserAccountService userAccountService;

    public UserAccountController (UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }
    @GetMapping("/get-all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userAccountService.getAllUserAccounts());
    }
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid UserAccount userAccount, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        userAccountService.addUserAccount(userAccount);
        return ResponseEntity.status(200).body("user added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid UserAccount userAccount, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userAccountService.updateUserAccount(id, userAccount);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userAccountService.deleteUserAccount(id);
        return ResponseEntity.status(200).body("user deleted successfully");
    }


}
