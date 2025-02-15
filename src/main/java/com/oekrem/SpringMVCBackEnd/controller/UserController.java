package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<CreateUserRequest> addUser(@RequestBody CreateUserRequest createUserRequest){
        userService.addUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest){
        userService.updateUser(id, updateUserRequest);
        return ResponseEntity.ok(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
