package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends MongoRepository<Account, String> {

    public Account findAccountByUsername(String username);
}
