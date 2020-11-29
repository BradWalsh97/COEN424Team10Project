package com.teamten.sizzle.service;

import com.teamten.sizzle.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface RecipesService {

    ArrayList<Recipe> getRecipes(Integer amount);

    void storeABunchOfRecipesInDb();

    List<Recipe> getAllSavedRecipes();

    int getNextRecipeIndex();

    void addNewRecipe(String username, int cookBookId, Recipe recipe);

    ArrayList<Recipe> getRecipeByQuery(String query);

    ArrayList<Recipe> getRecipesByIngredients(String ingredients, String badIngredients);
}
