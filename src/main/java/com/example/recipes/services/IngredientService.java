package com.example.recipes.services;

import com.example.recipes.model.Ingredient;

public interface IngredientService {
    public long addIngredient(Ingredient ingredient);
    public Ingredient getIngredient(Long id);


    Ingredient getAllIngredient();

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(Long id);

    void deleteAllIngredient();
}
