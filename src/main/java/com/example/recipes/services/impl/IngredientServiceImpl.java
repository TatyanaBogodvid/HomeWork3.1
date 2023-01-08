package com.example.recipes.services.impl;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long generateId = 1L;

    @Override
    public void addIngredient(Ingredient ingredient){
        ingredients.put(generateId, ingredient);
        generateId++;
    }

    @Override
    public Ingredient getIngredient(Long id){
        return ingredients.get(id);
    }
}
