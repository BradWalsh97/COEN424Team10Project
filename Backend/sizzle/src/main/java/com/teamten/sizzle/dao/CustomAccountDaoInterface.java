package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;

public interface CustomAccountDaoInterface {
    void addNewRecipeToUser(String user, int cookBookId,Recipe recipe);
    void removeRecipeFromUserAccount();
    void addNewCookBook(String user, CookBook cookBook);
    void removeCookBook(String user, int cookBookId);
}
