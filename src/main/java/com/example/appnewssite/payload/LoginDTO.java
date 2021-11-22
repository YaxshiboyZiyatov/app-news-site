package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {


    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "password bo'sh bo'lmasin")
    private String password;



}
