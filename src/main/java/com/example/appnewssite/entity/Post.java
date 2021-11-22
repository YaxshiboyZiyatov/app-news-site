package com.example.appnewssite.entity;

import com.example.appnewssite.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post extends AbsEntity {// bu class maqola un
    //text bolsa db da text bolib saqlaydi chunki text kop bolishi mn

    @Column(nullable = false, columnDefinition = "text") //text bolsa db da text bolib saqlaydi chunki text kop bolishi mn
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @Column(nullable = false, columnDefinition = "text")
    private String url;


}
