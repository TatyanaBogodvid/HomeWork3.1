package com.example.recipes.services;

import com.example.recipes.model.Recipe;

public interface RecipesService {
    public void addRecipe(Recipe recipe);

    public Recipe getRecipe(Long id);
}
