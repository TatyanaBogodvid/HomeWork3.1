package com.example.recipes.services.impl;

import com.example.recipes.model.Ingredient;
import com.example.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long generateId = 1L;

    @Override
    public long addIngredient(Ingredient ingredient){
        ingredients.put(generateId, ingredient);
        return generateId++;
    }

    @Override
    public Ingredient getIngredient(Long id){
        Ingredient ingredient = ingredients.get(id);
        if(ingredient != null){
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient getAllIngredient(){
        for(Ingredient ingredient : ingredients.values()){
            if(ingredient != null) {
                return ingredient;
            }
        }
        return  null;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient){
        if(ingredients.containsKey(id)){
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return  null;
    }

    @Override
    public boolean deleteIngredient(Long id){
        if(ingredients.containsKey(id)){
            ingredients.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllIngredient(){
        ingredients = new TreeMap<>();
    }
}
