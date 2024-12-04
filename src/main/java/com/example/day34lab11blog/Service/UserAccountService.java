package com.example.day34lab11blog.Service;

import com.example.day34lab11blog.ApiResponse.ApiException;
import com.example.day34lab11blog.Model.UserAccount;
import com.example.day34lab11blog.Repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public  UserAccountService (UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public void addUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public void updateUserAccount(Integer id,UserAccount userAccount) {

        UserAccount tempUserAccount = userAccountRepository.findUserAccountByUserId(id);
        if (tempUserAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        tempUserAccount.setUsername(userAccount.getUsername());
        tempUserAccount.setPassword(userAccount.getPassword());
        tempUserAccount.setEmail(userAccount.getEmail());
        userAccountRepository.save(tempUserAccount);
    }

    public void deleteUserAccount(Integer id) {
        UserAccount tempUserAccount = userAccountRepository.findUserAccountByUserId(id);
        if (tempUserAccount == null) {
            throw new ApiException("no user with this id was found");
        }
        userAccountRepository.delete(tempUserAccount);
    }

}


