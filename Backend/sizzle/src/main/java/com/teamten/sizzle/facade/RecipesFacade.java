package com.teamten.sizzle.facade;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RecipesFacade {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spoonacularApi}")
    private String apiKey;

    public ArrayList<Recipe> getRecipesByIngredients(Integer amount) {
        String url = "https://api.spoonacular.com/recipes/random?apiKey="+apiKey+"&number="+amount;
        System.out.println(url);
        String results = restTemplate.getForObject(url, String.class);
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();

        ArrayList<Recipe> recipes = new ArrayList<>();

        for (JsonElement recipe:jsonObject.getAsJsonArray("recipes")) {
            JsonObject json = new JsonParser().parse(recipe.toString()).getAsJsonObject();

            String title = json.get("title").getAsString();
            String summary = json.get("summary").getAsString();
            String instructions = json.get("instructions").getAsString();
            String image = json.get("image").getAsString();

            recipes.add(new Recipe(title, summary, instructions, image));
        }

        return recipes;
    }

}


