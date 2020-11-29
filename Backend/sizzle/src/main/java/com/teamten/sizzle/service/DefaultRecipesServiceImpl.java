package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.AccountDao;
import com.teamten.sizzle.dao.RecipeDao;
import com.teamten.sizzle.facade.RecipesFacade;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultRecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesFacade recipesFacade;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private AccountDao accountDao;

    private int latestRecipeId = -1;

    public ArrayList<Recipe> getRecipes(Integer amount) {
        return recipesFacade.getRandomRecipe(amount);
    }

    public void storeABunchOfRecipesInDb() {
        ArrayList<Recipe> recipes = recipesFacade.getRandomRecipe(10);
        Recipe recipe;
        for (int i = 0; i < recipes.toArray().length; i++) {
            recipe = recipes.get(i);
            recipe.setId(i + 1);
            recipeDao.save(recipe);
        }
    }

    public List<Recipe> getAllSavedRecipes() {
        return recipeDao.findAll();
    }

    public int getNextRecipeIndex() {
        if (latestRecipeId == -1) {
            latestRecipeId = recipeDao.findIds().get(0).getId();
        }
        return ++latestRecipeId;
    }

    public void addNewRecipe(String username, int cookBookId, Recipe recipe) {
        recipe.setId(getNextRecipeIndex());
        recipeDao.save(recipe);
        accountDao.addNewRecipeToUser(username, cookBookId, recipe.getId());
    }

    public ArrayList<Recipe> getRecipeByQuery(String query) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Recipe> recipesFromApi = recipesFacade.getRecipeByQuery(query);
        ArrayList<Recipe> recipesFromDb = accountDao.getRecipeByTitleMatch(query);

        recipes.addAll(recipesFromApi);
        recipes.addAll(recipesFromDb);
        return recipes;
    }

    @Override
    public ArrayList<Recipe> getRecipesByIngredients(String ingredients, String badIngredients) {
        return recipesFacade.getRecipesByIngredients(ingredients, badIngredients);
    }

}
