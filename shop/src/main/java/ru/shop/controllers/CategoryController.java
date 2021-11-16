package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
@Tag( name = "Тестовое имя контроллера", description = "Тестовое описание контроллера")
public class CategoryController {

    @GetMapping("/")
//    @ApiOperation("Описание метода")
    public String getCategories(){
        return "Hello";
    }
}
