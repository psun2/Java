package com.lec.security.domain.query;

public class TokenSQL {

    public static final String CREATE_NEW_TOKEN = "\tINSERT \n" +
            "\tINTO custom_persistent_logins \n" +
            "\tVALUES (#{username}, #{series}, #{tokenValue}, SYSDATE)";

    public static final String UPDATE_TOKEN = "\tUPDATE \n" +
            "\t\tcustom_persistent_logins\n" +
            "\tSET \n" +
            "\t\tseries = #{series},\n" +
            "\t\ttokenValue = #{tokenValue},\n" +
            "\t\tlastUsed = SYSDATE\n" +
            "\tWHERE series = #{series}";

    public static final String GET_TOKEN_FOR_SERIES = "\tSELECT\n" +
            "\t\tusername, series, tokenValue, lastUsed\n" +
            "\tFROM\n" +
            "\t\tcustom_persistent_logins\n" +
            "\tWHERE \n" +
            "\t\tseries = #{seriesId}";

    public static final String REMOVE_USER_TOKENS = "\tDELETE \n" +
            "\tFROM \n" +
            "\t\tcustom_persistent_logins\n" +
            "\tWHERE\n" +
            "\t\t username = #{username}";

}
