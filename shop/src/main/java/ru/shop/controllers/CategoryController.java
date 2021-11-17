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
    @Operation(summary = "Получение всех категорий", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Found the categories",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "500", description = "Server error"
                    //дописать контент ошибок и схему
            ),
            @ApiResponse(
                    responseCode = "404", description = "Category not found"
            )
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategory() {
        return new ArrayList<>();
    }



    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found" )
    })
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable String id) {
        return new CategoryDTO();
    }






    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void addCategory(){
    }





    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCategoryById(@PathVariable String id, @RequestBody String categoryDTO){
        return null;
    }





    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategoryById(@PathVariable String id){
        return null;
    }
}