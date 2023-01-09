package com.example.recipes.controllers;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createIngredient(@RequestBody Ingredient ingredient){
        long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

}
