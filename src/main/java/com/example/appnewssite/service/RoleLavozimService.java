package com.example.appnewssite.service;

import com.example.appnewssite.entity.RoleLavozim;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDTO;
import com.example.appnewssite.repository.RoleLavozimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleLavozimService {

    @Autowired
    RoleLavozimRepository roleLavozimRepository;

    public ApiResponse roleAdd(RoleDTO roleDTO) {
        if (roleLavozimRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("bu user qushilgan", false);

        RoleLavozim roleLavozim = new RoleLavozim(
                roleDTO.getName(),
                roleDTO.getPermissionHuquqList(),
                roleDTO.getDescription());

        roleLavozimRepository.save(roleLavozim);

        return new ApiResponse("Saqlandi", true);


    }

    public ApiResponse editLavozim(Long id, RoleDTO roleDTO) {
        return new ApiResponse("salom", true);
    }
}
