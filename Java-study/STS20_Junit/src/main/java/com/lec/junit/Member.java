package com.lec.junit;

import lombok.Data;

@Data
public class Member {

    private int age;

    public Member(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("나이는 0보다 커야합니다.");
        }
        this.age = age;
    }

}
