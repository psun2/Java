package com.lec.mybatis.domain;

import org.apache.ibatis.annotations.Insert;

public interface AA {

    @Insert("INSERT INTO test_ticket VALUES('아', 3)")
    int selectCard();
}
