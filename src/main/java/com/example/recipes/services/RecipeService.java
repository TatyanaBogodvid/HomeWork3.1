package com.example.recipes.services;

import com.example.recipes.model.Recipe;
import org.springframework.lang.Nullable;

public interface RecipeService {
    public long addRecipe(Recipe recipe);

    public Recipe getRecipe(Long id);

    Recipe getAllRecipe();

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(Long id);

    void deleteAllRecipe();

    @Nullable
    byte[] download();
}
