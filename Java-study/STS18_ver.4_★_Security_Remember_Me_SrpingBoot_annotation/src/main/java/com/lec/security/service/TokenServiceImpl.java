package com.lec.security.service;

import com.lec.security.domain.TokenDAO;
import com.lec.security.domain.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public int createNewToken(TokenVO tokenVo) {
        return tokenDAO.createNewToken(tokenVo);
    }

    @Override
    public int updateToken(String series, String tokenValue, Date lastUsed) {
        return tokenDAO.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public TokenVO getTokenForSeries(String seriesId) {
        return tokenDAO.getTokenForSeries(seriesId);
    }

    @Override
    public int removeUserTokens(String username) {
        return tokenDAO.removeUserTokens(username);
    }
}
