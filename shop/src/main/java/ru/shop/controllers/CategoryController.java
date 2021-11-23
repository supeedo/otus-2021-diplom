package ru.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.CategoryDTO;
import ru.shop.service.CategoryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RestControllerAdvice
@Tag(name = "Категории", description = "методы контроллера Категорий товара")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @Operation(summary = "Получение всех категорий", tags = "category", description = "Позволяет получить список всех категорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get all categories information",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по id", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get  category information",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public CategoryDTO getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/")
    @Operation(summary = "Добавление новой категории", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Add new category",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Category not added",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        if(categoryDTO != null){
            categoryService.createNewCategory(categoryDTO);
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    @Operation(summary = "Обновление категории", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Category not updated",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public String updateCategoryById(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление категории", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category delete successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Category not updated",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public void deleteCategoryById(@PathVariable String id){
//        return null;
    }
}