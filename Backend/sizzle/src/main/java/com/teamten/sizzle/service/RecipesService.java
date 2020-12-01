package com.teamten.sizzle.service;

import com.teamten.sizzle.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface RecipesService {

    ArrayList<Recipe> getRecipes(Integer amount);

    void storeABunchOfRecipesInDb();

    List<Recipe> getAllSavedRecipes();

    int getNextRecipeIndex();

    Recipe addNewRecipe(String username, int cookBookId, Recipe recipe, MultipartFile image);

    ArrayList<Recipe> getRecipeByQuery(String query);

    ArrayList<Recipe> getRecipesByIngredients(String ingredients, String badIngredients);

    List<Recipe> getRecipesByCookbookId(String user, int cookbookId);
}
