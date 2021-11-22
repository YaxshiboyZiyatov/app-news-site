package com.example.appnewssite.entity;

import com.example.appnewssite.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Commit extends AbsEntity {// bu class maqola un
    //text bolsa db da text bolib saqlaydi chunki text kop bolishi mn


    @Column(nullable = false, columnDefinition = "text")//text bolsa db da text bolib saqlaydi chunki text kop bolishi mn
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;//maqola


}
