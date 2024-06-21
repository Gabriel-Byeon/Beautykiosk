package com.beauty.Beautykiosk.Goods;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 고유 번호

    @Column(length = 200)
    private String name;    // 이름

    @Lob
    private String image; // 이미지 데이터를 저장하는 필드

    @Column(columnDefinition = "TEXT")
    private String effect;  // 효능,효과

    private Integer number; // 가격

    private String age; // 나이대 // 추가해야됨

}
