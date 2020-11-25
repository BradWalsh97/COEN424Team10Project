package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.RecipeDao;
import com.teamten.sizzle.facade.RecipesFacade;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultRecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesFacade recipesFacade;
    @Autowired
    private RecipeDao recipeDao;
    private int latestRecipeId = -1;

    public ArrayList<Recipe> getRecipes(Integer amount) {
        return recipesFacade.getRecipesByIngredients(amount);
    }

    public void storeABunchOfRecipesInDb() {
        ArrayList<Recipe> recipes = recipesFacade.getRecipesByIngredients(10);
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

    public void addNewRecipe(String username, Recipe recipe){
        recipe.setId(getNextRecipeIndex());
        recipeDao.save(recipe);

    }

}
