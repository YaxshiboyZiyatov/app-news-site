package com.example.appnewssite.payload;

import com.example.appnewssite.entity.RoleLavozim;
import com.example.appnewssite.entity.enums.PermissionHuquq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {

    @NotBlank//probelni ham hisobga olmaydi
    private String name;

    private String description;

    @NotEmpty// qushishga majbur
    private List<PermissionHuquq> permissionHuquqList;
}
