package com.example.appnewssite.entity;

import com.example.appnewssite.entity.enums.PermissionHuquq;
import com.example.appnewssite.entity.enums.RoleTypesLavozimTurlari;
import com.example.appnewssite.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RoleLavozim extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;//ADMIN, USER, CUSTOM

//    @Enumerated(value = EnumType.STRING)
//    private RoleTypesLavozimTurlari roleTypesLavozimTurlari;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<PermissionHuquq> permissionHuquqList;

    @Column(length = 500)
    private String description;


//    public <T> RoleLavozim(String admin, List<T> asList, String sestime_egasi) {
//    }
}
