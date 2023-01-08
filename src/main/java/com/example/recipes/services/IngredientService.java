package com.example.recipes.services;

import com.example.recipes.model.Ingredient;

public interface IngredientService {
    public void addIngredient(Ingredient ingredient);
    public Ingredient getIngredient(Long id);
}
