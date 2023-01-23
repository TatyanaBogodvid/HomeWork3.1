package com.example.recipes.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int ingredientQuantity;
    private String measureUnit;

    @Override
    public String toString() {
        return name + " - " + ingredientQuantity + " " + measureUnit;
    }
}

