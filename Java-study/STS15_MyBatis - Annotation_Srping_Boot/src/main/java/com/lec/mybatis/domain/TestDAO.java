package com.lec.mybatis.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO {

    @Select("SELECT * FROM mybatis WHERE data1 = #{unm}")
    List<TestDTO> getTest(@Value("num") long data1);
}
