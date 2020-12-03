package com.teamten.sizzle.controller;
import com.teamten.sizzle.model.Recipe;
import com.teamten.sizzle.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private RecipesService recipesService;

    @GetMapping("/{query}")
    public ResponseEntity<ArrayList<Recipe>> searchRecipe(@PathVariable String query) {
        ArrayList<Recipe> recipes = recipesService.getRecipeByQuery(query);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<ArrayList<Recipe>> searchRecipe(@RequestParam("ingredients") String ingredients,
                                                          @RequestParam("badIngredients") String badIngredients) {
        ArrayList<Recipe> recipes = recipesService.getRecipesByIngredients(ingredients, badIngredients);
        return ResponseEntity.ok(recipes);
    }
}
