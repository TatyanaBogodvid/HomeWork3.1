package com.example.recipes.controllers;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/api/getById")
    public Ingredient ingredient(@RequestParam long id){
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/api/new")
    public Ingredient createIngredient(@RequestParam Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
        return ingredient;
    }

}
