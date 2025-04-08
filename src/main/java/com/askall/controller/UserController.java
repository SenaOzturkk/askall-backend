package com.askall.controller;

import com.askall.dto.ApiResponse;
import com.askall.modal.Question;
import com.askall.modal.User;
import com.askall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "User List successfully", users);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createUser(@RequestBody User user) {
        ApiResponse<Object> response = userService.createUser(user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable UUID userId) {
        Optional<User> deletedUser = userService.deleteUser(userId);

        if (deletedUser.isPresent()) {
            ApiResponse<Object> response = ApiResponse.success(HttpStatus.OK, "User deleted successfully", deletedUser.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = ApiResponse.error(HttpStatus.OK, "User not found: " + userId);
            return ResponseEntity.ok(response);
        }
    }



    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getUserById(@PathVariable UUID userId) {
        Optional<User> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "User found successfully", existingUser.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response =  ApiResponse.success(HttpStatus.OK, "User not found: ", null);
            return ResponseEntity.ok(response);
        }
    }


}
