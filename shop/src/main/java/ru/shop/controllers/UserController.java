package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@Tag(name = "Пользователи", description = "методы контроллера Пользователей")
public class UserController {

    @GetMapping("/user/")
    public String getAllUser() {
        return "Hello";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/user/")
    public String addUser(){
        return null;
    }

    @PostMapping("/user/{id}")
    public String updateUserById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable String id){
        return null;
    }
}
