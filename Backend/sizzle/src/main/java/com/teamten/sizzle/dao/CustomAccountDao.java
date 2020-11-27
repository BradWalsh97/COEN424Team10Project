package com.teamten.sizzle.dao;

import com.teamten.sizzle.model.CookBook;

public interface CustomAccountDao {
    void addNewRecipeToUser(String user, int cookBookId, int recipe);

    void removeRecipeFromCookBook(String user, int cookBookId, int recipeId);

    void addNewCookBook(String user, CookBook cookBook);

    void removeCookBook(String user, int cookBookId);

    boolean cookBookContainsRecipeWithId(String user, int cookBookId, int recipeId);

    void updateUserEmail(String user, String email);

    void updateUserPassword(String user, String password);

    boolean userExistsWithUsername(String username);
}
