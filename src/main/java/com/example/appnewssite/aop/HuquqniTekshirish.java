package com.example.appnewssite.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)// bu ananation qachon va qayerda  ishlasin bu method ustida ishlasin dedim
@Retention(RetentionPolicy.RUNTIME)// bu qachon ishlashini aytayapdi yani runtime bolganda ishlasin dedim
public @interface HuquqniTekshirish {
    String huquq();
}
