package com.sesac.sesacspring.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // class Student(){} 와 같은 빈 생성자가 필수로 필요하다!
// Entity : DB 의 필드와 변수의 연관관계가 정의된 친구
// 즉, DB 테이블에 대응되는 하나의 클래스
@Builder // 필드가 전체 다 들어있는 생성자가 필수로 필요하다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Setter // 언제든지 값을 바꿀 수 있음!
public class Student {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // = auto_increment
    private int id;
    // = id int primary key auto_increment
    // SEQUENCE 전략 : 새로운 오브젝트를 만들어서 id 를 부여하는 방법 ( mysql 에서 사용 x )
    // TABLE : SEQUENCE 전략을 흉내낸 전략 -> 모든 DBMS 에서 사용 가능! (억지로 만든 기능이기 때문에 성능이 안좋아서 사용 거의 안함!)

    @Column(name = "name", nullable = false, length = 20)
    // = name varchar(20) not null
    private String name;

    @Column(columnDefinition = "TEXT")
    private String nickname;

    // enum : 선택지 만드는 경우!
    @Column
    @Enumerated(EnumType.STRING)
    private LoginType type;
    public enum LoginType {
        GOOGLE, KAKAO
    }

}

