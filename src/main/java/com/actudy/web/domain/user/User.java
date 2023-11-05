package com.actudy.web.domain.user;

import com.actudy.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;


    /**
     * @Enumerated(EnumType.STRING)
     * 1. JPA로 데이터 저장 시 Enum 값의 형태 지정
     * 2. default로 int형이 저장
     * 3. 숫자로 저장될 시 의미를 즉시 파악하기 어려움
     * 4. 문자열로 저장하여 파악이 쉽게끔 하는 것을 권장
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
