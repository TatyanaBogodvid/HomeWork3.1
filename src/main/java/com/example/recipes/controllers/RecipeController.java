package com.example.recipes.controllers;

import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipesService;

    public RecipeController(RecipeService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> recipegetById(@PathVariable long id){
        Recipe recipe = recipesService.getRecipe(id);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createRecipe(@RequestBody Recipe recipe){
        long id = recipesService.addRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }
}
