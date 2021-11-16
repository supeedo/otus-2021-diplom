package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@Tag(name = "Категории", description = "методы контроллера Категорий товара")
public class CategoryController {

    @GetMapping("/category/")
    public String getAllCategory() {
        return "Hello";
    }

    @GetMapping("/category/{id}")
    public String getCategoryById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/category/")
    public String addCategory(){
        return null;
    }

    @PostMapping("/category/{id}")
    public String updateCategoryById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/category/{id}")
    public String deleteCategoryById(@PathVariable String id){
        return null;
    }
}