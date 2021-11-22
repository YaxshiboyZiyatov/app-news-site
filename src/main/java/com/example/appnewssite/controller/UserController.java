package com.example.appnewssite.controller;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.UserDTO;
import com.example.appnewssite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
