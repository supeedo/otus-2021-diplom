package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@Tag(name = "Товары", description = "методы контроллера Товара")
public class ProductController {

    @GetMapping("/product/")
    public String getAllProduct() {
        return "Hello";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/product/")
    public String addProduct(){
        return null;
    }

    @PostMapping("/product/{id}")
    public String updateProductById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductById(@PathVariable String id){
        return null;
    }
}
