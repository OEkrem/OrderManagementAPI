package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateUserRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
