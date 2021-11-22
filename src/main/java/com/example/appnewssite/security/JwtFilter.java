package com.example.appnewssite.security;


import com.example.appnewssite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;

    //JPA AUDITING BU USERLAR NIMA ISH QILISHINI YOZIB QOYADI SHUNING UN KK
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {


        //REQUESTDAN TOKENNI OLISH //Authorization bilan olish standar kelishilgan qoida
        String authorization = httpServletRequest.getHeader("Authorization");

        //TOKEN BORLIGINI VA TOKENNI BOSHLANISH BEARER EKANLIGINI TEKSHIRISH
        if (authorization != null && authorization.startsWith("Bearer")) {
            //AYNAN TOKENNO O'ZINI QIRQIB OLDIM
            authorization = authorization.substring(7);
            //TOKENNI ISCHIDAN USERNAME NI OLDIK
            String email = jwtProvider.getUsernameFromToken(authorization);
            //TOKENNI VLEDATION DAN OTKAZDIK (TOKEN BUZU;MAGANLIGINI, MUDDATI OTMAGANLIGINI VA H.K)
            if (email != null) {
                //USERNAME ORQALI USERDETAILS NI OLDIK
                UserDetails userDetails = authService.loadUserByUsername(email);
                //USERDETAILS ORQALI AUTHENTICATION YARATIM OLDIK
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                //sestemega mana shu user kirdi
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
