package com.example.recipes.services.impl;

import com.example.recipes.model.Recipe;
import com.example.recipes.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final FilesServiceRecImpl filesService;
    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long generateId = 1L;

    public RecipeServiceImpl(FilesServiceRecImpl filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public long addRecipe(Recipe recipe){
        recipes.put(generateId, recipe);
        saveToFile();
        return generateId++;
    }

    @Override
    public Recipe getRecipe(Long id){
        Recipe recipe = recipes.get(id);
        if(recipe != null){
            return  recipe;
        }
        return null;
    }

    @Override
    public Recipe getAllRecipe(){
        for(Recipe recipe : recipes.values()){
            if(recipe != null) {
                return recipe;
            }
        }
        return  null;
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe){
        if(recipes.containsKey(id)){
            recipes.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return  null;
    }

    @Override
    public boolean deleteRecipe(Long id){
        if(recipes.containsKey(id)){
            recipes.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllRecipe(){
        recipes = new TreeMap<>();
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile(){
        String json = filesService.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
