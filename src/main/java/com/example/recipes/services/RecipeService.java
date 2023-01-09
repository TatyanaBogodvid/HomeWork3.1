package com.example.recipes.services;

import com.example.recipes.model.Recipe;

public interface RecipeService {
    public long addRecipe(Recipe recipe);

    public Recipe getRecipe(Long id);
}
