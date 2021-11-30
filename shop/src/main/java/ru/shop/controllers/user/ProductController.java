package ru.shop.controllers.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RestControllerAdvice
@Tag(name = "Товары", description = "методы контроллера Товара")
public class ProductController {

    @GetMapping("/")
    public String getAllProduct() {
        return "Hello";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/")
    public String addProduct(){
        return null;
    }

    @PostMapping("/{id}")
    public String updateProductById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable String id){
        return null;
    }
}
