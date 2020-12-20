package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthDAOImpl implements AuthDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override // 등급 insert
    public int insertByObject(Object obj) {
        long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).insertByObject(uid);
    }

    @Override // <!-- 권한 추가 설정 -->
    public int insertByDTO(DTO dto) {

        UserAuthDTO userAuth = (UserAuthDTO) dto;

        return sqlSession.getMapper(AuthDAO.class).insertByDTO(userAuth);

    }

    @Override // 회원 탈퇴시 등급 제거 또는 uid 값으로 등급 제거 (foreign key => uid)
    public int deleteByObject(Object obj) {
        long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).deleteByObject(uid);
    }

    @Override  // 권한 삭제 (어떠한 유저의 어떤 권한을 삭제 할 것인가?)
    public int deleteByUserAuth(UserAuthDTO userAuthDTO) {
        return sqlSession.getMapper(AuthDAO.class).deleteByUserAuth(userAuthDTO);
    }

    @Override // 권한 등급 조정 (유저의 uid 와 이전에 가지고 있던 권한, 바뀔 권한)
    public int updateByObject(Object obj) {
        UserAuthDTO userAuth = (UserAuthDTO) obj;
        return sqlSession.getMapper(AuthDAO.class).updateByObject(userAuth);
    }

    @Override   // auth 테이블 조회
    public List<DTO> selectByObject(Object obj) {
        long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).selectByObject(uid);
    }

    @Override  // userView 를 조회 findAll
    public List<DTO> selectAll() {
        return sqlSession.getMapper(AuthDAO.class).selectAll();
    }

    @Override   // userView 를 조회 findByUsername
    public List<DTO> selectByString(String s) {
        return sqlSession.getMapper(AuthDAO.class).selectByString(s);
    }

    @Override
    public int insertAllByDTOs(List<DTO> dtos) {
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
    public List<DTO> selectByInt(int i) {
        return null;
    }

    @Override
    public List<DTO> selectByDTO(DTO dto) {
        return null;
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
    public int updateAllByDTOs(List<DTO> dtos) {
        return 0;
    }

    @Override
    public int update_(String a, String s, String d, String e, String dd) {
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
    public int deleteAll() {
        return 0;
    }


}
