package com.example.recipes.controllers;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "IngrediemtController", description = "API для ингредиентов")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;
    private ValidateService validateService;

    public IngredientController(IngredientService ingredientService, ValidateService validateService) {
        this.ingredientService = ingredientService;
        this.validateService = validateService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента", description = "Добавление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры ингредиента")

    })
    public ResponseEntity<Long> createIngredient(@RequestBody Ingredient ingredient){
        if (validateService.isNotValid(ingredient)){
            return ResponseEntity.badRequest().build();
        }
        long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @Operation(summary = "Поиск игредиента по номеру", description = "Поиск игредиента по номеру")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поиск прошёл успешно"),
            @ApiResponse(responseCode = "404", description = "Игредиент не найден")

    })
    @GetMapping("{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Получение всего списка игредиентов", description = "Получение всего списка рингредиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поиск прошёл успешно"),
            @ApiResponse(responseCode = "404", description = "Ингредиенты не найдены")

    })
    @GetMapping
    public ResponseEntity<Ingredient> getAllIngredient(){
        if (ingredientService.getAllIngredient() != null) {
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @Operation(summary = "Изменение ингредиента", description = "Изменение ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден")

    })
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editRecipe(@PathVariable long id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredientService.editIngredient(id, newIngredient);
        if(ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент успешно удален"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден")

    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id){
        if(ingredientService.deleteIngredient(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удаление всех ингредиентов", description = "Удаление всех ингредиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Игредиенты успешно удалены"),
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteAllIngredient(){
        ingredientService.deleteAllIngredient();
        return ResponseEntity.ok().build();
    }



}
