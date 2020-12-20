package com.lec.security.domain.query;

public class UserSQL {

    public static final String FIND_BY_USERNAME = "\t\tSELECT \n" +
            "   \t\t\tusername, password, enabled, authority  \n" +
            "\t\tFROM security_user \n" +
            "\t\tWHERE username = #{username}";

    public static final String FIND_BY_USERDETAIL = "\t\tSELECT \n" +
            "   \t\t\tusername, password, enabled, authority  \n" +
            "\t\tFROM security_user \n" +
            "\t\tWHERE username = #{username}";

}
