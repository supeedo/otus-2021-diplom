package ru.shop.controllers.user;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.CategoryDTO;
import ru.shop.service.CategoryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RestControllerAdvice
@Tag(name = "Categories", description = "Product Category controller methods")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @Operation(summary = "Retrieving all categories", tags = "category",
            description = "Lets get a list of all categories")
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
    @Operation(summary = "Getting a category by id", tags = "category")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    @Operation(summary = "Adding a new category", tags = "category")
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
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            categoryService.createNewCategory(categoryDTO);
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/{id}")
    @Operation(summary = "Category update", tags = "category")
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
    public String updateCategoryById(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Removal of category", tags = "category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category delete successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Category not deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}