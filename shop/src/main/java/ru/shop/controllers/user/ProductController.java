package ru.shop.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.CategoryDTO;
import ru.shop.domain.ProductDTO;
import ru.shop.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RestControllerAdvice
@Tag(name = "Products", description = "Product controller methods")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    @Operation(summary = "Receipt of all products", tags = "product",
            description = "Lets get a list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get all products information",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Products not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public List<ProductDTO> getAllProduct(Pageable pageable) {
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Receiving the product by id", tags = "product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get product information",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    @Operation(summary = "Adding a new product", tags = "product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Add new product",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Product not added",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        if (productDTO != null) {
            productService.createNewProduct(productDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/")
    @Operation(summary = "Product update", tags = "product")
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
    public ResponseEntity<ProductDTO> updateProductById(@RequestBody @Valid ProductDTO productDTO) {
        if (productDTO != null) {
            productService.updateProduct(productDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Removal of product", tags = "product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product delete successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Product not deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
