package com.example.appnewssite.controller;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.LoginDTO;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.security.JwtProvider;
import com.example.appnewssite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.userRegister(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

     @PostMapping("/login")// tokenni oldim
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {

         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                         loginDTO.getPassword()));
        User user=(User) authentication.getPrincipal();
         String token = jwtProvider.generateToken(user.getUsername(), user.getRoleLavozim());
        return ResponseEntity.ok(token);
    }


}
