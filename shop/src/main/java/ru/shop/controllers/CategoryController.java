package ru.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RestControllerAdvice
@Tag(name = "Категории", description = "методы контроллера Категорий товара")
public class CategoryController {

    @GetMapping("/")
    @Operation(summary = "Получение всех категорий", tags = "category", description = "Позволяет получить список всех категорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of All the Participants",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class ) )}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public List<CategoryDTO> getAllCategory() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по id", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found" )
    })
    public CategoryDTO getCategoryById(@PathVariable String id) {
        return new CategoryDTO();
    }

    @PutMapping("/")
    @Operation(summary = "Добавление новой категории", tags = "category")
    @ResponseStatus(HttpStatus.OK)
    public void addCategory(){
    }

    @PostMapping("/{id}")
    @Operation(summary = "Обновление категории", tags = "category")
    @ResponseStatus(HttpStatus.OK)
    public String updateCategoryById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление категории", tags = "category")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategoryById(@PathVariable String id){
        return null;
    }
}