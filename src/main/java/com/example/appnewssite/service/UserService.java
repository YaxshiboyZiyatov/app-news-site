package com.example.appnewssite.service;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.payload.UserDTO;
import com.example.appnewssite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ApiResponse addUser(UserDTO userDTO) {
        return null;

    }
}
