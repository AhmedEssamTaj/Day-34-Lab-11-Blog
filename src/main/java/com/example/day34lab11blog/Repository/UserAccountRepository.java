package com.example.day34lab11blog.Repository;

import com.example.day34lab11blog.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findUserAccountByUserId(Integer userId);
}
