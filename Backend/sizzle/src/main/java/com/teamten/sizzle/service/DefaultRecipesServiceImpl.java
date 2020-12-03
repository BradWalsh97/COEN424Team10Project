package com.teamten.sizzle.service;

import com.teamten.sizzle.dao.AccountDao;
import com.teamten.sizzle.dao.RecipeDao;
import com.teamten.sizzle.exceptions.CookbookNotFoundException;
import com.teamten.sizzle.facade.AmazonS3Facade;
import com.teamten.sizzle.facade.RecipesFacade;
import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DefaultRecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesFacade recipesFacade;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AmazonS3Facade amazonS3Facade;
    @Value("${aws.bucket}")
    private String bucket;

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

    public Recipe addNewRecipe(String username, int cookBookId, Recipe recipe, MultipartFile image) {
        recipe.setId(getNextRecipeIndex());
        String imageUrl = amazonS3Facade.uploadImageToS3Bucket(image, bucket);
        recipe.setImage(imageUrl);
        recipeDao.save(recipe);
        accountDao.addNewRecipeToUser(username, cookBookId, recipe.getId());
        return recipe;
    }

    public ArrayList<Recipe> getRecipeByQuery(String query) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Recipe> recipesFromApi = recipesFacade.getRecipeByQuery(query);
        ArrayList<Recipe> recipesFromDb = accountDao.getRecipeByTitleMatch(query);
        ArrayList<Recipe> allRecipes = new ArrayList<>();
        allRecipes.addAll(recipesFromApi);
        allRecipes.addAll(recipesFromDb);

        HashMap<Integer, Boolean> uniqueMap = new HashMap<>();

        for (Recipe r:allRecipes) {
            if (!uniqueMap.containsKey(r.getId())) {
                recipes.add(r);
                uniqueMap.put(r.getId(), true);
            }
        }

        return recipes;
    }

    @Override
    public ArrayList<Recipe> getRecipesByIngredients(String ingredients, String badIngredients) {
        return recipesFacade.getRecipesByIngredients(ingredients, badIngredients);
    }

    public List<Recipe> getRecipesByCookbookId(String user, int cookbookId) {
        Account account = accountDao.findAccountByUsername(user);
        CookBook cookBook = null;
        ArrayList<Recipe> recipes = null;
        for (CookBook c : account.getCookBooks()) {
            if (c.getId() == cookbookId) {
                cookBook = c;
                break;
            }
        }
        if (cookBook != null) {
            if (cookBook.getRecipeIds() != null) recipes = accountDao.getRecipesByIds(cookBook.getRecipeIds());

        } else {
            throw new CookbookNotFoundException("Cookbook of id " + cookbookId + " was not found");
        }
        return recipes;
    }

}
