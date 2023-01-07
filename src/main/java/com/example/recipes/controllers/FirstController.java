package com.example.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String start(){
        return "Приложение запущено!";
    }

    @GetMapping("/info")
    public String info(){
        return "Богодвид Татьяна; "+"\n"+
        "Recipes; "+"\n"+
        "02.01.2023; "+"\n"+
        "Хранение, добавление и удаление рецептов.";
    }
}

