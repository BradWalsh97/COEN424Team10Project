package com.teamten.sizzle.controller;

import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/new")
    public boolean addNewUser(@RequestBody Account account) {
        return accountService.addUser(account);
    }

    @GetMapping("/authenticate")
    public boolean authenticateUser(@RequestBody Account account) {
        return accountService.authenticateUser(account);
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody Account account) {
        return accountService.deleteUser(account);
    }

}
