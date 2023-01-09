package com.example.recipes.services.impl;

import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long generateId = 1L;

    @Override
    public long addRecipe(Recipe recipe){
        recipes.put(generateId, recipe);
        return generateId++;
    }

    @Override
    public Recipe getRecipe(Long id){
        Recipe recipe = recipes.get(id);
        if(recipe != null){
            return  recipe;
        }
        return null;
    }
}
