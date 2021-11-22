package com.example.appnewssite.entity.template;

import com.example.appnewssite.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)// updatable = false=> buni hech kim uzgartira olmasin va
    // nullable = false=> vaqti yaratilmasa bolmasin
    @CreationTimestamp// qachon ruhxatdan otsa aftamat ushu vaqtni olib quyadi yani faqat abekt yaratilgan vaqtni oladi
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp// qachon update bolsa aftamat ushu vaqtni olib quyadi yani faqat abekt update vaqtni oladi
    private Timestamp updatedAt;

    @JoinColumn(updatable = false)// qachoni mening yaratgan type class type bolsa JoinColumn dan foydalanaman
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    //fetch bu abektni chaqirish bunga obektni olib kelish degani
    //fetch ni ikki xili bor LAZY va EAGER
    //LAZY biz uni faqat chaqirsak malummotlr omboridan olib kelsin yani qachon murojat qilsak olib kladi
    //EAGER fetch ni defould EAGER yani doim olib keladi yani qachoni maqolani uzini olsam ham kim yaratganini olib keladi
    private User createdBy; // bu objectni kim qushdi

    @LastModifiedDate
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy; // bu objectni kim tahrirladi

}
