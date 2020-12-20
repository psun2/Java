package com.lec.security.domain;

import com.lec.security.domain.query.TokenSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TokenDAO {

	@Insert(TokenSQL.CREATE_NEW_TOKEN)
	@Options(flushCache = Options.FlushCachePolicy.TRUE)
	int createNewToken(TokenVO tokenVo);

	@Update(TokenSQL.UPDATE_TOKEN)
	@Options(flushCache = Options.FlushCachePolicy.TRUE)
	int updateToken(String series, String tokenValue, Date lastUsed);

	@Select(TokenSQL.GET_TOKEN_FOR_SERIES)
	TokenVO getTokenForSeries(String seriesId);

	@Delete(TokenSQL.REMOVE_USER_TOKENS)
	@Options(flushCache = Options.FlushCachePolicy.TRUE)
	int removeUserTokens(String username);

}
