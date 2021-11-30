package ru.shop.controllers.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.UserDTO;
import ru.shop.service.UserService;

@RestController
@RequestMapping("/user")
@RestControllerAdvice
@Tag(name = "Users", description = "User controllers methods")
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registrationUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "Registration";
    }

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
