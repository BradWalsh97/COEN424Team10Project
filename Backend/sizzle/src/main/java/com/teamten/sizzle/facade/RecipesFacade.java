package com.teamten.sizzle.facade;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamten.sizzle.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class RecipesFacade {

    @Autowired
    private RestTemplate restTemplate;

    private int apiKeyId = 0;

    private String[] apiKeys = {"e1ea8e0b803b48e3a48b95732783e0ef", "122b3ec99c6c4081bf38f22b878f8dcb", "9eb28a6adf5f4147a1382e2965d2b34c", "1c39e95e7c6548deb69114bc4bf96b40", "4ff79984454e4be584f4274bda81f99b", "87b5fe20b6c742b2a72db78fe610c98b"};

    public ArrayList<Recipe> getRandomRecipe(Integer amount) {
        String url = "https://api.spoonacular.com/recipes/random?apiKey=" + apiKeys[(apiKeyId++ % apiKeys.length)] + "&number=" + amount;
        System.out.println(url);
        String results = restTemplate.getForObject(url, String.class);
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();

        ArrayList<Recipe> recipes = new ArrayList<>();

        for (JsonElement recipe : jsonObject.getAsJsonArray("recipes")) {
            JsonObject json = new JsonParser().parse(recipe.toString()).getAsJsonObject();

            int id = json.get("id").getAsInt();
            String title = json.get("title").getAsString();
            String summary = json.get("summary").getAsString();
            String image = json.get("image").getAsString();
            String instructions = json.get("instructions").getAsString();

            recipes.add(new Recipe(id, title, summary, instructions, image, false, true));
        }

        return recipes;
    }


    public ArrayList<Recipe> getRecipeByQuery(String query) {
        String url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + apiKeys[(apiKeyId++ % apiKeys.length)] + "&query=" + query + "&addRecipeInformation=true&number=3";
        String results = restTemplate.getForObject(url, String.class);
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();
        JsonArray jsonResultsArray = jsonObject.getAsJsonArray("results");
        ArrayList<Recipe> recipes = this.getRecipesByJsonResultsArray(jsonResultsArray);
        return recipes;
    }

    public ArrayList<Recipe> getRecipesByIngredients(String ingredients, String badIngredients) {
        System.out.println(ingredients + " " + badIngredients);
        ArrayList<Integer> recipeIds = this.getRecipeIdsByIngredients(ingredients, badIngredients);
        ArrayList<Recipe> recipes = this.getRecipesByIdsBulk(recipeIds);
        return recipes;
    }

    private ArrayList<Recipe> getRecipesByIdsBulk(ArrayList<Integer> recipeIds) {
        String rIds = "";
        for (Integer recipeId : recipeIds) {
            rIds += recipeId.toString() + ",";
        }
        if (!rIds.isEmpty()) {
            rIds = rIds.substring(0, rIds.length() - 1);
            System.out.println("rIds " + rIds);
            String url = "https://api.spoonacular.com/recipes/informationBulk?apiKey=" + apiKeys[(apiKeyId++ % apiKeys.length)] + "&ids=" + rIds;
            String results = restTemplate.getForObject(url, String.class);
            JsonArray jsonResults = new JsonParser().parse(results).getAsJsonArray();
            ArrayList<Recipe> recipes = this.getRecipesByJsonResultsArray(jsonResults);
            return recipes;

        } else
            return new ArrayList<Recipe>();
    }

    private ArrayList<Integer> getRecipeIdsByIngredients(String ingredients, String badIngredients) {
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + apiKeys[(apiKeyId++ % apiKeys.length)] + "&ingredients=" + ingredients + "&number=15";
        String results = restTemplate.getForObject(url, String.class);
        JsonArray recipes = new JsonParser().parse(results).getAsJsonArray();

        HashMap<String, Boolean> badIngredientsMap = buildBadIngredientsMap(badIngredients);

        ArrayList<Integer> recipeIdsList = new ArrayList<>();

        for (JsonElement recipe : recipes) {
            JsonObject jsonRecipe = new JsonParser().parse(recipe.toString()).getAsJsonObject();
            JsonArray missedIngredients = jsonRecipe.get("missedIngredients").getAsJsonArray();
            JsonArray usedIngredients = jsonRecipe.get("usedIngredients").getAsJsonArray();
            String title = jsonRecipe.get("title").getAsString();
            int id = jsonRecipe.get("id").getAsInt();
            System.out.println(id + " " + title);

            JsonArray allIngredients = new JsonArray();
            allIngredients.addAll(missedIngredients);
            allIngredients.addAll(usedIngredients);

            boolean addRecipe = true;
            for (JsonElement i : allIngredients) {
                JsonObject jsonIngredient = new JsonParser().parse(i.toString()).getAsJsonObject();
                String name = jsonIngredient.get("name").getAsString();
                if (badIngredientsMap.containsKey(name)) {
                    addRecipe = false;

                    break;
                }
            }
            if (addRecipe)
                recipeIdsList.add(id);
        }
        return recipeIdsList;
    }

    private HashMap<String, Boolean> buildBadIngredientsMap(String badIngredients) {
        HashMap<String, Boolean> badIngredientsMaps = new HashMap<>();
        List<String> items = Arrays.asList(badIngredients.split("\\s*,\\s*"));
        for (String item : items) {
            badIngredientsMaps.put(item.toLowerCase(), true);
        }
        return badIngredientsMaps;
    }

    private ArrayList<Recipe> getRecipesByJsonResultsArray(JsonArray results) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String i = "<ol>";
        for (JsonElement recipe : results) {
            JsonObject jsonRecipe = new JsonParser().parse(recipe.toString()).getAsJsonObject();

            String title = jsonRecipe.get("title").getAsString();
            String summary = jsonRecipe.get("summary").getAsString();
            String image = jsonRecipe.get("image").getAsString();
            int id = jsonRecipe.get("id").getAsInt();
            //System.out.println(title);

            JsonArray instructions = jsonRecipe.get("analyzedInstructions").getAsJsonArray();

            for (JsonElement instruction : instructions) {
                JsonObject jsonInstruction = new JsonParser().parse(instruction.toString()).getAsJsonObject();
                JsonArray steps = jsonInstruction.get("steps").getAsJsonArray();

                for (JsonElement step : steps) {
                    JsonObject jsonStep = new JsonParser().parse(step.toString()).getAsJsonObject();
                    String s = "<li>" + jsonStep.get("step").getAsString() + "</li>";
                    //System.out.println(s);
                    i += s;
                }
            }
            i += "</ol>";

            recipes.add(new Recipe(id, title, summary, i, image, false, true));
        }
        return recipes;
    }
}


