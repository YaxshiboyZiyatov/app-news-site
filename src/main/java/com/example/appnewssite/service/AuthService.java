package com.example.appnewssite.service;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exceptions.ResourceNotFoundException;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RegisterDTO;
import com.example.appnewssite.repository.RoleLavozimRepository;
import com.example.appnewssite.repository.UserRepository;
import com.example.appnewssite.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleLavozimRepository roleLavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse userRegister(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("parollar mos emas", false);

        if (userRepository.existsByUsername(registerDTO.getUsername()))
            return new ApiResponse("Bu username avval ruyxatdan o'tgan", false);
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleLavozimRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("lavozim", "name", AppConstants.USER)),
                true

        );
        userRepository.save(user);

        return new ApiResponse("Muvaffaqiyatli yo'yxatdan o'tdingiz", true);

    }

    public UserDetails findByEmail(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(username + " Topilmadi"));
    }
}
