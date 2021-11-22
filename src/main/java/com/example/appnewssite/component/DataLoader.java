package com.example.appnewssite.component;

import com.example.appnewssite.entity.RoleLavozim;
import com.example.appnewssite.entity.User;
import com.example.appnewssite.entity.enums.PermissionHuquq;
import com.example.appnewssite.repository.RoleLavozimRepository;
import com.example.appnewssite.repository.UserRepository;
import com.example.appnewssite.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.appnewssite.entity.enums.PermissionHuquq.*;

@Component
public class DataLoader implements CommandLineRunner {//bu class loader(yuklovchi ) dagi malumotlar
    //CommandLineRunner bu programma run bolganda  buyruqlarni shu yerda berish imkonini beradigan interface
    //programmada har doim bitta admin yoki bitta
    // director qushib beridh kerak default xolatida qushib berish kk qolganlarni qushib
    // berish un kerak boladi bu class
//  dataloader =>  malumotlarni default xolatida db ga yozuchi class

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleLavozimRepository roleLavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;


    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            PermissionHuquq[] huquqs = PermissionHuquq.values();


            RoleLavozim admin = roleLavozimRepository.save(new RoleLavozim(
                    AppConstants.ADMIN,
                    Arrays.asList(huquqs),
                    "Sestime egasi")
            );
            RoleLavozim user = roleLavozimRepository.save(new RoleLavozim(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi")
            );
            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));

        }

    }
}
