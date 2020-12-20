package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override // 전체 유저 검색
    public List<DTO> selectAll() {
        return sqlSession.getMapper(UserDAO.class).selectAll();
    }

    @Override // 유저고유값, 유저아이디, 닉네임 을 반영한 select 동적 쿼리
    public List<DTO> selectByObject(Object obj) {
        UserDTO user = (UserDTO) obj;
        return sqlSession.getMapper(UserDAO.class).selectByObject(user);
    }

    // 회원 가입
    @Override
    public int insertByObject(Object obj) {
        UserDTO user = (UserDTO) obj;
        return sqlSession.getMapper(UserDAO.class).insertByObject(user);
    }

    @Override
    public int insertAllByDTOs(List<DTO> dtos) {
        return 0;
    }

    // 업데이트
    @Override // nickname password 을 반영한 update 동적 쿼리
    public int updateByObject(Object obj) {
        UserDTO user = (UserDTO) obj;
        return sqlSession.getMapper(UserDAO.class).updateByObject(user);
    }

    @Override
    public int updateAllByDTOs(List<DTO> dtos) {
        return 0;
    }

    @Override
    public int update_(String a, String s, String d, String e, String dd) {
        return 0;
    }

    // 회원 탈퇴
    @Override
    public int deleteByObject(Object obj) {
        UserDTO user = (UserDTO) obj;
        return sqlSession.getMapper(UserDAO.class).deleteByObject(user);
    }

    @Override
    public List<DTO> selectByInt(int i) {
        return null;
    }

    @Override
    public List<DTO> selectByString(String s) {
        return null;
    }

    @Override
    public List<DTO> selectByDTO(DTO dto) {
        return null;
    }


    @Override
    public int insertByDTO(DTO dto) {
        return 0;
    }

    @Override
    public int test(String s) {
        return 0;
    }

    @Override
    public int inset_(String s, String d, String e, String dd) {
        return 0;
    }

    @Override
    public int updateByDTO(DTO dto) {
        return 0;
    }


    @Override
    public int deleteByInt(int i) {
        return 0;
    }

    @Override
    public int deleteByString(String str) {
        return 0;
    }

    @Override
    public int selectCnt() {
        return 0;
    }

    @Override
    public int selectCntByInt(int i) {
        return 0;
    }

    @Override
    public int selectCntByString(String str) {
        return 0;
    }

    @Override
    public int selectCntByObject(Object obj) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
