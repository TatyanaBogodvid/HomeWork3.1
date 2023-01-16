package com.example.recipes.controllers;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipeService;
import com.example.recipes.services.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "RecipeController", description = "API для рецептов")
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipesService;
    private ValidateService validateService;

    public RecipeController(RecipeService recipesService, ValidateService validateService) {
        this.recipesService = recipesService;
        this.validateService = validateService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта", description = "Добавление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры рецепта")

    })
    public ResponseEntity<Long> createRecipe(@RequestBody Recipe recipe){
        if (validateService.isNotValid(recipe)){
            return ResponseEntity.badRequest().build();
        }
        long id = recipesService.addRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }

    @Operation(summary = "Поиск рецепта по номеру", description = "Поиск рецепта по номеру")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поиск прошёл успешно"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")

    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> recipeGetById(@PathVariable long id){
        Recipe recipe = recipesService.getRecipe(id);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @Operation(summary = "Получение всего списка рецептов", description = "Получение всего списка рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поиск прошёл успешно"),
            @ApiResponse(responseCode = "404", description = "Рецепты не найдены")
    })
    @GetMapping
    public ResponseEntity<Recipe> getAllRecipe(){
        if (recipesService.getAllRecipe() != null) {
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @Operation(summary = "Изменение рецепта", description = "Изменение рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")

    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipesService.editRecipe(id, newRecipe);
        if(recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @Operation(summary = "Удаление рецепта", description = "Удаление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")

    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id){
        if(recipesService.deleteRecipe(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удаление всех рецептов", description = "Удаление всех рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепты успешно удалены"),
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAllRecipe(){
        recipesService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }

}
