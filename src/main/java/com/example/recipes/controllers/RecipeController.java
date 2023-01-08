package com.example.recipes.controllers;

import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/api/getById")
    @ResponseBody
    public Recipe recipegetById(@RequestParam long id){
        return recipesService.getRecipe(id);
    }

    @PostMapping("/api/new")
    public Recipe createRecipe(@RequestParam Recipe recipe){
        recipesService.addRecipe(recipe);
        return recipe;
    }
}
