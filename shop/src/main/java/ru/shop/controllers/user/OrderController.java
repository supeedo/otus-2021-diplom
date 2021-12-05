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
import ru.shop.domain.OrderDTO;
import ru.shop.service.CategoryService;
import ru.shop.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RestControllerAdvice
@Tag(name = "Orders", description = "Product order controller methods")
public class OrderController {

    private final OrderService orderService;

    public OrderController(@Qualifier("orderServiceImpl") OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    @Operation(summary = "Retrieving all order", tags = "order",
            description = "Lets get a list of all order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get all order information",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public List<OrderDTO> getAllCategory() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting a order by id", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get  order information",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public OrderDTO getCategoryById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_USER')")
    @PutMapping("/")
    @Operation(summary = "Adding a new order", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Add new category",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Order not added",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<OrderDTO> addCategory(@RequestBody @Valid OrderDTO orderDTO) {
        if (orderDTO != null) {
            orderService.createNewOrder(orderDTO);
            return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/{id}")
    @Operation(summary = "Order update", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Order not updated",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<OrderDTO> updateCategoryById( @RequestBody @Valid OrderDTO orderDTO) {
        if (orderDTO != null) {
            orderService.updateOrder(orderDTO);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Removal of order", tags = "order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order delete successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Order not deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}