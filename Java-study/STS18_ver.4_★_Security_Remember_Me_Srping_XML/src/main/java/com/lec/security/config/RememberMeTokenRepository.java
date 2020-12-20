package com.lec.security.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.lec.security.domain.TokenVO;
import com.lec.security.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RememberMeTokenRepository implements PersistentTokenRepository {
// spring security remember-me custom 객체

	@Autowired
	TokenService tokenService;

	public RememberMeTokenRepository() {
		log.info("RememberMeTokenRepository 빈등록(remember-me customizing)");
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// remember-me check box 를 받았을시 호출

		log.info("createNewToken Parameter 검증(PersistentRememberMeToken token) : {}", token);

		log.info("createNewToken Parameter 검증 (token.getUsername()) : {}", token.getUsername());
		log.info("createNewToken Parameter 검증 (token.getSeries()) : {}", token.getSeries());
		log.info("createNewToken Parameter 검증 (token.getTokenValue()) : {}", token.getTokenValue());

		// 토큰정보를 가진 커스텀 객체 생성
		TokenVO tokenVo = new TokenVO(token);

		 // 트랜잭션 구간
        try {

            int  result = tokenService.createNewToken(tokenVo);
            
            if (result != 1) {
               
                log.info("트랜잭션 성공(토큰생성) : {}", result);
            } else {
                log.error("트랜잭션 결과 : {}", result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("트랜잭션 에러 메시지 : {}", e.getMessage());
        } // end try

	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// ✔✔언제 어떻게 호출되어 어떤 업데이트를 진행하는지 알 수 없습니다.
		
		log.info("updateToken  Parameter 검증(String series) : {}", series);
		log.info("updateToken  Parameter 검증(String tokenValue, Date lastUsed) : {}, {}", tokenValue, lastUsed);

		 // 트랜잭션 구간
        try {

            int result = tokenService.updateToken(series, tokenValue, lastUsed);

            if (result != 1) {
                log.info("트랜잭션 성공(토큰업데이트) : {}", result);
            } else {
                log.error("트랜잭션 결과 : {}", result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("트랜잭션 에러 메시지 : {}", e.getMessage());
        } // end try
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// 로그인이 되어있을때 ('remember-me')token(cookie, session)이 있을시
		// 페이지 진입시 호출 (즉 로그인이 되어있나 검문)

		log.info("getTokenForSeries Parameter 검증(String seriesId) : {}", seriesId);

		TokenVO tokenVo = null;
		
		// 트랜잭션 구간
		try {

			tokenVo = tokenService.getTokenForSeries(seriesId);
			if (tokenVo != null) {
				log.info("트랜잭션 성공(토큰생성) : {}", tokenVo);
			} else {
				log.error("트랜잭션 결과 : {}", tokenVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("트랜잭션 에러 메시지 : {}", e.getMessage());
		}

		return new PersistentRememberMeToken(tokenVo.getUsername(), tokenVo.getSeries(), tokenVo.getTokenValue(),
				tokenVo.getLastUsed());
	}

	@Override
	public void removeUserTokens(String username) {
		// 로그 아웃시 호출

		log.info("removeUserTokens Parameter 검증(String username) : {}", username);

		// 트랜잭션 구간
		try {

			int result = tokenService.removeUserTokens(username);
			if (result != 1) {
				log.info("트랜잭션 성공(토큰생성) : {}", result);
			} else {
				log.error("트랜잭션 결과 : {}", result);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("트랜잭션 에러 메시지 : {}", e.getMessage());
		}
	}

}
