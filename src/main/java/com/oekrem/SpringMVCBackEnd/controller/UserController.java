package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Mapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Manages User Operations")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class),
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing parameters)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins can get users")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(userService.findAll(page, size));
    }

    @Operation(summary = "Get User By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid User id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can access"),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER, authentication.name)")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Get User By Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Email format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can access"),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @GetMapping("/email")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#email, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER, authentication.name)")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserResponseByEmail(email));
    }


    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing user details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only admins can create a user"),
            @ApiResponse(responseCode = "409", description = "Conflict, User could not be created")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> addUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(createUserRequest));
    }

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing user details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only admins and owners can update"),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER ,authentication.name)")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest updateUserRequest
    ){
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
    }

    @Operation(summary = "Patch User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing user details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can patch"),
            @ApiResponse(responseCode = "404", description = "User Not Found")
    })
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER ,authentication.name)")
    public ResponseEntity<UserResponse> patchUser(
            @PathVariable Long id,
            @RequestBody PatchUserRequest patchUserRequest
    ){
        return ResponseEntity.ok(userService.patchUser(id, patchUserRequest));
    }

    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid user id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can delete"),
            @ApiResponse(responseCode = "404", description = "Order Not Found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER ,authentication.name)")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
