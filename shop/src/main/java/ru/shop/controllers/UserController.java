package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RestControllerAdvice
@Tag(name = "Пользователи", description = "методы контроллера Пользователей")
public class UserController {

    @GetMapping("/")
    public String getAllUser() {
        return "Hello";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/")
    public String addUser(){
        return null;
    }

    @PostMapping("/{id}")
    public String updateUserById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable String id){
        return null;
    }
}
