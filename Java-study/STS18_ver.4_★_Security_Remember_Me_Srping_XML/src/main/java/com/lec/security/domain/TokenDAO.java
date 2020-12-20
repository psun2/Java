package com.lec.security.domain;

import java.util.Date;

public interface TokenDAO {

	int createNewToken(TokenVO tokenVo);

	int updateToken(String series, String tokenValue, Date lastUsed);

	TokenVO getTokenForSeries(String seriesId);

	int removeUserTokens(String username);

}
