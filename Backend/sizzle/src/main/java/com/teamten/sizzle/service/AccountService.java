package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.AccountDao;
import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        account = accountDao.findAccountByUsername(account.getUsername());
        accountDao.delete(account);
        return true;
    }

    public void addNewCookBook(String user, CookBook cookBook) {
        Account account = accountDao.findAccountByUsername(user);
        cookBook.setRecipeIds(new int[0]);
        List<CookBook> cookBookList = account.getCookBooks();
        if (cookBookList == null || cookBookList.size() == 0) {
            cookBook.setId(0);
        } else {
            cookBookList.sort((c1, c2) -> c2.getId() - c1.getId());
            cookBook.setId(cookBookList.get(0).getId() + 1);
        }
        accountDao.addNewCookBook(user, cookBook);
    }

    public void removeCookBook(String user, int cookBookId) {
        accountDao.removeCookBook(user, cookBookId);
    }

    public void addRecipeToUser(String user, int cookBookId, int recipe) {
        if (!accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipe))
            accountDao.addNewRecipeToUser(user, cookBookId, recipe);
    }


    public void removeRecipeFromCookBook(String user, int cookBookId, int recipeId) {
        if (accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipeId))
            accountDao.removeRecipeFromCookBook(user, cookBookId, recipeId);
    }

    public boolean checkRecipeSaved(String user, int cookBookId, int recipeId) {
        return accountDao.cookBookContainsRecipeWithId(user, cookBookId, recipeId);
    }

    public void updateUserEmail(String user, String newEmail) {
        accountDao.updateUserEmail(user, newEmail);
    }

    public void updateUserPassword(String user, String newPassword) {
        accountDao.updateUserPassword(user, newPassword);
    }
}
