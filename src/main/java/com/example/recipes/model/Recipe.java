package com.example.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int cookingTime;
    private String measureUnitTime;
    private List<Ingredient> ingredients;
    private List<String> steps;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n")
                .append("Время приготвления: ").append(cookingTime).append(" ").append(measureUnitTime).append("\n")
                .append("Ингредиенты : ").append("\n");
        for(Ingredient ingredient : ingredients){
            stringBuilder.append("• ").append(ingredient).append("\n");
        }
        stringBuilder.append("Инструкция приготвления: ").append("\n");
        int counter = 1;
        for(String step : steps){
            stringBuilder.append(counter++).append(". ").append(step).append("\n");
        }
        return  stringBuilder.toString();
    }
}
