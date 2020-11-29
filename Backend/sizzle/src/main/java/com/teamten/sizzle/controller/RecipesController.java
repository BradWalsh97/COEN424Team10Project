package com.teamten.sizzle.controller;

import com.teamten.sizzle.model.Recipe;
import com.teamten.sizzle.service.DefaultRecipesServiceImpl;
import com.teamten.sizzle.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @GetMapping("random/{amount}")
    public ResponseEntity<ArrayList<Recipe>> getRecipes(@PathVariable Integer amount) {

        ArrayList<Recipe> recipes = recipesService.getRecipes(amount);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("store")
    public void storeABunchOfRecipe() {
        recipesService.storeABunchOfRecipesInDb();
    }


    @GetMapping("/get")
    public List<Recipe> getAllSaveRecipes() {
        return recipesService.getAllSavedRecipes();
    }


    @GetMapping("/getIndex")
    public int getBigRecipeIndex() {
        return recipesService.getNextRecipeIndex();
    }

    @PostMapping("/newRecipe/{user}/{cookBook}")
    public void addNewRecipe(@PathVariable String user, @PathVariable int cookBook, @RequestBody Recipe newRecipe) {
    }

}
