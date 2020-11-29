package com.lec.mybatis.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BoardDAO {


    @Select("SELECT" +
            " wr_uid \"uid\"," +
            " wr_subject subject," +
            " wr_content content," +
            " wr_name name," +
            " wr_viewcnt viewcnt," +
            " wr_regdate regdate" +
            " FROM" +
            " test_write" +
            " ORDER BY" +
            " wr_uid DESC")
    List<BoardDTO> getListAll();

    @Insert(" INSERT INTO" +
            " test_write (wr_uid, wr_subject, wr_content, wr_name, wr_regdate)" +
            " VALUES" +
            " (test_write_seq.nextval, #{subject}, #{content}, #{name}, SYSDATE)")
    @Options(useGeneratedKeys = true, keyColumn = "wr_uid", keyProperty = "uid")
    @Flush
    int write(BoardDTO dto);

    // MyBatis 는 두개의 트랜잭션을 사용 할 수 없습니다.
    // List<WriteDTO> readByUid(long uid);

    // 조회수 증가
    @Update("  UPDATE" +
            " test_write" +
            " SET" +
            " wr_viewcnt = wr_viewcnt + 1" +
            " WHERE" +
            " wr_uid = #{uid}")
    int viewCnt(long uid);

    @Select("SELECT" +
            " wr_uid \"uid\"," +
            " wr_subject subject," +
            " wr_content content," +
            " wr_name name," +
            " wr_viewcnt viewcnt," +
            " wr_regdate regdate" +
            " FROM" +
            " test_write" +
            " WHERE" +
            " wr_uid = #{uid}")
    List<BoardDTO> selectByUid(long uid);

    @Update("UPDATE" +
            " test_write" +
            " SET" +
            " wr_subject = #{board.subject}," +
            " wr_content = #{board.content}" +
            " WHERE" +
            " wr_uid = #{board.uid}")
    int update(@Param("board") BoardDTO dto);

    @Delete(" DELETE FROM" +
            " test_write" +
            " WHERE" +
            " wr_uid = #{uid}")
    int deleteByUid(long uid);

}
