package com.lec.sts13_jdbc.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lec.sts13_jdbc.beans.BoardUID;

@Component
public class BoardControllerUidValidator implements Validator{ // @Valid 어노테이션에 동작합니다.

	@Autowired
	@Qualifier("logger")
	Logger logger;
	
	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("supports({}), BoardUID.class.isAssignableFrom(clazz): {}", clazz, BoardUID.class.isAssignableFrom(clazz));
		return BoardUID.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate({})", target);
		
		BoardUID uid = (BoardUID) target;
		
		// uid => 글 번호
		if(uid.getUid() == 0) {
			errors.rejectValue("uid", "파싱에러");
		}

	}

}
