package ru.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@Tag(name = "Заказы", description = "методы контроллера Заказов товара")
public class OrderController {

    @GetMapping("/order/")
    public String getAllOrder() {
        return "Hello";
    }

    @GetMapping("/order/{id}")
    public String getOrderById(@PathVariable String id) {
        return id;
    }

    @PutMapping("/order/")
    public String addOrder(){
        return null;
    }

    @PostMapping("/order/{id}")
    public String updateOrderById(@PathVariable String id, @RequestBody String orderDTO){
        return null;
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrderById(@PathVariable String id){
        return null;
    }
}
