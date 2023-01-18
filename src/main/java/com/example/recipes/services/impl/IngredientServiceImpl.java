package com.example.recipes.services.impl;

import com.example.recipes.model.Ingredient;
import com.example.recipes.services.FilesService;
import com.example.recipes.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final FilesService filesService;
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long generateId = 1L;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public long addIngredient(Ingredient ingredient){
        ingredients.put(generateId, ingredient);
        saveToFile();
        return generateId++;
    }

   /* @PostConstruct
    private void init() {
        readFromFile();
    }*/

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
            saveToFile();
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
        ingredients = new TreeMap<Long, Ingredient>();
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile(){
        String json = filesService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
