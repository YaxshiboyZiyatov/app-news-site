package com.example.appnewssite.entity;

import com.example.appnewssite.entity.enums.PermissionHuquq;
import com.example.appnewssite.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {//UserDetails userni detallarni yig'uvchi interface
//Permission & => ruxsat yani qila oladigan ishlar yani huquq
//Role => lavozim
    //Role yani lavozimlar lar => dynamic boladi
    // Permission yani qila oladigan ishlar  => static boladi
    //lavozimlarni pemission larga biriktiriib ketadi

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//har qanday userni lavozimi bolsin lavozimsia odam bolmasin
    private RoleLavozim roleLavozim;

    private boolean enabled;// tizimga kira olsinmi yoki yoqmi tekshirish un

    private boolean accountNonExpired = true;//true doim tizimga kira oladi

    private boolean accountNonLocked = true;//true doim tizimga kira oladi

    private boolean credentialsNonExpired = true;//true doim tizimga kira oladi

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {// bu method mana shu userni huquqlarini qaytaardigan mehtod
        List<PermissionHuquq> permissionHuquqList = this.roleLavozim.getPermissionHuquqList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();//set bitta object ni dublikat qilishni oldini oladi
        for (PermissionHuquq permissionHuquq : permissionHuquqList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissionHuquq.name()));
        }
//         for (PermissionHuquq permissionHuquq : permissionHuquqList) {
//
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return permissionHuquq.name();
//                }
//            });
//        }
//
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, RoleLavozim roleLavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.roleLavozim = roleLavozim;
        this.enabled = enabled;
    }
}
