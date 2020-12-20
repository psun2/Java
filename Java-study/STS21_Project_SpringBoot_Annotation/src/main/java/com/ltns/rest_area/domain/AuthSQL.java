package com.ltns.rest_area.domain;

public class AuthSQL {

    // 권한 생성
    public static final String INSERT_AUTH = "INSERT INTO auth(um_uid) VALUES (#{uid})";

    // 권한 추가 설정
    public static final String INSERT_ADD_AUTH = "INSERT INTO auth VALUES (#{addAuthority}, #{uid})";

    // 권한 삭제 (회원 탈퇴시 모든 권한 삭제)
    public static final String DELETE_AUTH_BY_UID = "DELETE FROM auth WHERE um_uid = #{uid}";

    // 권한 삭제: 어떠한 유저의 어떠한 권한을 삭제할 것인가?
    public static final String DELETE_MODIFY_AUTH_BY_UID_AND_AUTHORITY = "DELETE FROM auth\n" +
            "        WHERE um_uid=#{uid}\n" +
            "        AND authority=#{authority}";

    // 권한 수정
    public static final String UPDATE_AUTHORITY_BY_UID_AND_AUTHORITY = "  UPDATE auth \n" +
            "        SET authority = #{nextAuthority} \n" +
            "        WHERE um_uid = #{uid} \n" +
            "        AND authority = #{prevAuthority}";

    // 전체 권한 findAll (userView)
    public static final String SELECT_USERVIEW = "SELECT * FROM userView";

    // 권한 find (userView)
    public static final String SELECT_USERVIEW_BY_USERNAME = "SELECT * FROM userView WHERE username = #{username}";

    // 권한 find uid (auth table)
    public static final String SELECT_AUTH_BY_UID = "SELECT * FROM auth WHERE um_uid=#{uid}";

}
