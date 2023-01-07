package com.example.recipes.services.impl;

import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipesService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipeServiceImpl implements RecipesService {

    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long generateId = 1L;


    public void addRecipe(Recipe recipe){
        recipes.put(generateId, recipe);
        generateId++;
    }
    public Recipe getRecipe(Long id){
        return recipes.get(id);
    }
}
