package com.teamten.sizzle.service;

import com.teamten.sizzle.facade.RecipesFacade;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DefaultRecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesFacade recipesFacade;

    public ArrayList<Recipe> getRecipes(Integer amount) {
        return recipesFacade.getRecipesByIngredients(amount);
    }
}
