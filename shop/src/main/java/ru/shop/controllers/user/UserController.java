package ru.shop.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.shop.domain.ProductDTO;
import ru.shop.domain.UserDTO;
import ru.shop.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RestControllerAdvice
@Tag(name = "Users", description = "User controllers methods")
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "New User Registration", tags = "user",
            description = "Allows you to register a new user who has access to product orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get all Users information",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))),
            @ApiResponse(responseCode = "404",
                    description = "User not created",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    @Operation(summary = "Receipt of all users", tags = "user",
            description = "Lets get a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get all users information",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Users not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public List<UserDTO> getAllUser(Pageable pageable) {
        return userService.getAllUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Receiving the user by id", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get user information",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    @Operation(summary = "Adding a new user manually", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Add new user",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not added",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
        if (userDTO != null) {
            userService.createUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping("/")
    @Operation(summary = "User update", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not updated",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    public ResponseEntity<UserDTO> updateUserById(@RequestBody @Valid UserDTO userDTO) {
        if (userDTO != null) {
            userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Removal of user", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User delete successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
