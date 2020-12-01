package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.AccountDao;
import com.teamten.sizzle.facade.AmazonS3Facade;
import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(Account account) {
        if (accountDao.userExistsWithUsername(account.getUsername())) return false;
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDao.save(account);
        return true;
    }

    public boolean authenticateUser(Account account) {
        if (checkUserExist(account.getUsername())) {
            Account acc = accountDao.findAccountByUsername(account.getUsername());
            return passwordEncoder.matches(account.getPassword(), acc.getPassword());
        } else {
            return false;
        }
    }

    public boolean deleteUser(Account account) {
        if (checkUserExist(account.getUsername())) {
            account = accountDao.findAccountByUsername(account.getUsername());
            accountDao.delete(account);
            return true;
        } else {
            return false;
        }
    }

    public CookBook addNewCookBook(String user, CookBook cookBook) {
        Account account = accountDao.findAccountByUsername(user);
        List<CookBook> cookBookList = account.getCookBooks();
        if (cookBookList == null || cookBookList.size() == 0) {
            cookBook.setId(0);
        } else {
            cookBookList.sort((c1, c2) -> c2.getId() - c1.getId());
            cookBook.setId(cookBookList.get(0).getId() + 1);
        }
        accountDao.addNewCookBook(user, cookBook);
        return cookBook;
    }

    public void removeCookBook(String user, int cookBookId) {
        if (!accountDao.userExistsWithUsername(user)) return;
        accountDao.removeCookBook(user, cookBookId);
    }

    public void addRecipeToUser(String user, int cookBookId, int recipe) {
        if (!accountDao.userExistsWithUsername(user)) return;
        if (!accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipe)) {
            accountDao.addNewRecipeToUser(user, cookBookId, recipe);
        }
    }


    public void removeRecipeFromCookBook(String user, int cookBookId, int recipeId) {
        if (!accountDao.userExistsWithUsername(user)) return;
        if (accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipeId))
            accountDao.removeRecipeFromCookBook(user, cookBookId, recipeId);
    }

    public boolean checkRecipeSaved(String user, int cookBookId, int recipeId) {
        if (!accountDao.userExistsWithUsername(user)) return false;
        return accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipeId);
    }

    public void updateUserEmail(String user, String newEmail) {
        accountDao.updateUserEmail(user, newEmail);
    }

    public void updateUserPassword(String user, String currentPassword, String newPassword) {
        Account account = accountDao.findAccountByUsername(user);
        if (passwordEncoder.matches(currentPassword, account.getPassword())) {
            newPassword = passwordEncoder.encode(newPassword);
            accountDao.updateUserPassword(user, newPassword);
        }
    }

    public boolean checkUserExist(String user) {
        return accountDao.userExistsWithUsername(user);
    }

    public Account getCookBooksForUser(String user){
        return accountDao.getCookBooksForUser(user);
    }


}


