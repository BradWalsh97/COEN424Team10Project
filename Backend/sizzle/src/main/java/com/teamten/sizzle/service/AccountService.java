package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.AccountDao;
import com.teamten.sizzle.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public boolean addUser(Account account) {
        accountDao.save(account);
        return true;
    }

    public boolean authenticateUser(Account account) {
        Account acc = accountDao.findAccountByUsername(account.getUsername());
        return acc.getPassword().equals(account.getPassword());
    }

    public boolean deleteUser(Account account) {
        accountDao.delete(account);
        return true;
    }
}
