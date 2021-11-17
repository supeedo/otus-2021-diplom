package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RestControllerAdvice
@Tag(name = "Заказы", description = "методы контроллера Заказов товара")
public class OrderController {

    @GetMapping("/")
    public String getAllOrder() {
        return "Hello";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/")
    public String addOrder(){
        return null;
    }

    @PostMapping("/{id}")
    public String updateOrderById(@PathVariable String id, @RequestBody String orderDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable String id){
        return null;
    }
}
