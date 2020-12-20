package com.lec.security.service;

import com.lec.security.domain.TokenVO;

import java.util.Date;

public interface TokenService {

    int createNewToken(TokenVO tokenVo);

    int updateToken(String series, String tokenValue, Date lastUsed);

    TokenVO getTokenForSeries(String seriesId);

    int removeUserTokens(String username);

}
