package com.example.recipes.services.impl;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.IngredientService;

import java.util.Map;
import java.util.TreeMap;

public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long generateId = 1L;


    public void addIngredient(Ingredient ingredient){
        ingredients.put(generateId, ingredient);
        generateId++;
    }
    public Ingredient getIngredient(Long id){
        return ingredients.get(id);
    }
}
