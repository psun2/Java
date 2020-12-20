package com.lec.mybatis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TestResult {

    private String status;
    private long count;
    private String message;

    @JsonProperty("data")
    private List list; // 이때 Map 으로 결과물을 보내주면 Json 배열이 아닌 Object 의 형태로 반환합니다.

}
