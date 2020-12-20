package com.ltns.rest_area.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class MemberSQL {

    public static final String FIND_ALL = "SELECT * FROM userMember";

    // 어노테이션에서의 동적 쿼리문
    public String findSelector(UserDTO user) {

        SQL sql = new SQL();

        sql.SELECT("*");
        sql.FROM("userMember");

        long uid = user.getUm_uid();
        String username = user.getUm_username();
        String nickname = user.getUm_nickname();

        if (uid != 0) {
            sql.WHERE("um_uid = #{um_uid}");

        } else if (username != null && !username.isEmpty()) {
            sql.WHERE("um_username = #{um_username}");
        } else {
            sql.WHERE("um_nickname = #{um_nickname}");

        }

        log.info("SELECT QUERY : {}", sql.toString());

        return sql.toString();
    }

    // xml 파일에서의 동적 query 문
    public static final String FIND_SELECTOR = "SELECT * FROM userMember\n" +
            "        WHERE\n" +
            "        <choose>\n" +
            "            <when test=\"um_uid != null and um_uid != ''\">\n" +
            "                um_uid = #{um_uid}\n" +
            "            </when>\n" +
            "            <when test=\"um_username != null and um_username != ''\">\n" +
            "                um_username = #{um_username}\n" +
            "            </when>\n" +
            "            <otherwise>\n" +
            "                um_nickname = #{um_nickname}\n" +
            "            </otherwise>\n" +
            "        </choose>";

    public static final String INSERT_USER = "INSERT INTO userMember (um_uid, um_username, um_password, um_nickname)\n" +
            "    \tVALUES (SEQ_USERMEMBER_UM_UID.NEXTVAL, #{um_username}, #{um_password}, #{um_nickname})";

    public static final String DELETE_USER = "DELETE FROM userMember WHERE um_username = #{um_username}";

    // 어노테이션에서의 동적 쿼리문
    public String updateUserByUsername(UserDTO user) {

        SQL sql = new SQL();

        sql.UPDATE("userMember");

        String username = user.getUm_username();
        String password = user.getUm_password();
        String nickname = user.getUm_nickname();

        if (nickname != null && !nickname.isEmpty() && password == null || password.isEmpty()) {
            sql.SET("um_nickname = #{um_nickname}");
        } else if (password != null && !password.isEmpty() && nickname == null || nickname.isEmpty()) {
            sql.SET("um_password = #{um_password}");
        } else {
            sql.SET("um_nickname = #{um_nickname}", "um_password = #{um_password}");
        }

        sql.WHERE("um_username = #{um_username}");

        log.info("UPDATE QUERY : {}", sql.toString());

        return sql.toString();
    }

    // xml 파일에서의 동적 query 문
    public static final String UPDATE_USER = "UPDATE userMember SET\n" +
            "        <choose>\n" +
            "            <when test=\"um_nickname != null && um_nickname != '' && um_password == null || um_password == ''\">\n" +
            "                um_nickname = #{um_nickname}\n" +
            "            </when>\n" +
            "            <when test=\"um_password != null && um_password != '' && um_nickname == null || um_nickname == ''\">\n" +
            "                um_password = #{um_password}\n" +
            "            </when>\n" +
            "            <otherwise>\n" +
            "                um_nickname = #{um_nickname}, um_password = #{um_password}\n" +
            "            </otherwise>\n" +
            "        </choose>\n" +
            "        WHERE um_username = #{um_username}";

}
