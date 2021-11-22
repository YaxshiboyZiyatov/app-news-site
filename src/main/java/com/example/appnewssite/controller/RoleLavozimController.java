package com.example.appnewssite.controller;

import com.example.appnewssite.aop.HuquqniTekshirish;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDTO;
import com.example.appnewssite.service.RoleLavozimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleLavozimController {
    @Autowired
    RoleLavozimService roleLavozimService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addLavozim(@Valid @RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleLavozimService.roleAdd(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @HuquqniTekshirish(huquq = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@Valid @PathVariable Long id,
                                     @RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleLavozimService.editLavozim(id, roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
