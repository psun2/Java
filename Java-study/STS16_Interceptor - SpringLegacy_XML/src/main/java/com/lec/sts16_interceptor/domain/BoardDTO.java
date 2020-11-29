package com.lec.sts16_interceptor.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDTO {

    private long uid;
    private String subject;
    private String content;
    private String name;
    private long viewcnt;
    private Timestamp regDate; // java.sql

}
