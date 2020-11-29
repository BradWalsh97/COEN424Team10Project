package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface CustomAccountDao {
    void addNewRecipeToUser(String user, int cookBookId, int recipe);

    void removeRecipeFromCookBook(String user, int cookBookId, int recipeId);

    void addNewCookBook(String user, CookBook cookBook);

    void removeCookBook(String user, int cookBookId);

    boolean cookBookContainsRecipeWithId(String user, int cookBookId, int recipeId);

    void updateUserEmail(String user, String email);

    void updateUserPassword(String user, String password);

    boolean userExistsWithUsername(String username);

    Account getCookBooksForUser(String user);

    ArrayList<Recipe> getRecipeByTitleMatch(String query);
}
