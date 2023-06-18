package com.example.demofake;

import org.springframework.data.repository.Repository;
import com.example.demofake.UserAccount;

public interface UserRepository extends Repository<UserAccount, Long>{
	UserAccount findByUsername(String username);
}