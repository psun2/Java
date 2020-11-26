package com.lec.sts13_jdbc.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lec.sts13_jdbc.beans.BoardDTO;

@Component
public class BoardControllerValidator implements Validator { // @Valid 어노테이션에 동작합니다.

	@Autowired
	@Qualifier("logger")
	Logger logger;

	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("supports({}), BoardDTO.class.isAssignableFrom: {}", clazz, BoardDTO.class.isAssignableFrom(clazz));
		return BoardDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate({})", target);

		BoardDTO dto = (BoardDTO) target;

		// uid => 작성자
//		if(dto.getUid() == 0) {
//			errors.rejectValue("uid", "파싱에러");
//		}

//		if(dto.getName() == null || dto.getName().trim().isEmpty()) {
//			errors.rejectValue("name", "파싱에러");
//		}
//		if(dto.getSubject() == null || dto.getSubject().trim().isEmpty()) {
//			errors.rejectValue("subject", "파싱에러");
//		}
//		if(dto.getContent() == null || dto.getContent().trim().isEmpty()) {
//			errors.rejectValue("content", "파싱에러");
//		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "공백 거부");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "공백 거부");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "공백 거부");

	}

}
