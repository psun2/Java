package com.lec.jdbc_refactoring.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WriteDTO {

    private long uid;
    private String subject;
    private String content;
    private String name;
    private long viewcnt;
    private Timestamp regDate; // java.sql

}
