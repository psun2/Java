package com.lec.spring;

import lombok.Data;

@Data // getter, setter, 기본생성자, toString, equals(), hashCode()
public class Student {
    private String name; // 이름
    private int age; // 나이
    private Score score; // 성적, reference 타입

    public Student() {
        super();
        System.out.println("Student() 생성");
    }

    public Student(String name, int age, Score score) {
        super();
        System.out.printf("Student(%s, %d, %s) 생성\n", name, age, score.toString());
        this.name = name;
        this.age = age;
        this.score = score;
    }

}
