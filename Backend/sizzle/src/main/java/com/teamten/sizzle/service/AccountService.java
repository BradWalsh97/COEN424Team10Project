package com.teamten.sizzle.service;

import com.teamten.sizzle.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public boolean addUser(Account account){
        return true;
    }

    public boolean authenticateUser(Account account){
        return false;
    }

    public boolean deleteUser(Account account){
        return false;
    }
}
