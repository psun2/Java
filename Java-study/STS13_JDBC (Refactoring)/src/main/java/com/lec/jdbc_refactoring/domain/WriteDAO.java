package com.lec.jdbc_refactoring.domain;

import java.util.List;

public interface WriteDAO {
    List<WriteDTO> readAll();

    int create(WriteDTO dto);

    List<WriteDTO> readyAndViewcntIncrease(long uid);

    List<WriteDTO> readByUid(long uid);

    int update(WriteDTO dto);

    int delete(long uid);
}
