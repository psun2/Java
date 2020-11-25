package com.lec.sts12_validation;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lec.sts12_validation.beans.WriteDTO;

@Component(value = "vaildator")
public class BoardValidator implements Validator { // @Valid 어노테이션에 동작합니다.

	
	@Autowired
	@Qualifier("logger")
	private Logger logger;	


	// initBinder 실행시 실행됩니다.
	// 일단 검사하고자 하는 클래스가 맞는 지 확인 합니다.
	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("supports() 실행");

		logger.info("supports({})", clazz.getName());

		// 검증할 객체의 클래스 타입인지 확인(WriteDTO 타입인지 확인)
		logger.info("WriteDTO.class.isAssignableFrom(clazz): {}", WriteDTO.class.isAssignableFrom(clazz));
		return WriteDTO.class.isAssignableFrom(clazz);
	}

	// 주어진 객체(target)에 유효성 검사를 하고
	// 유효성 검사에 오류가 있는경우, 주어진 객체에 이 오류들을 errors에 등록
	// errors => bindingResult 의 상위 부모 객체
	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate() 실행");

		WriteDTO dto = (WriteDTO) target;
		
		// 1. uid 가 0을 허용 하지 않는다면...
		// parsing error 시 0으로 치환되므로 uid 값이 0이라면 error 발생 한 것입니다.
		int uid = dto.getUid();
		if(uid == 0) {
			logger.error("uid error");
			errors.rejectValue("uid", "uid 에러"); // 첫번째 인자 uid 는 반드시 
			// dto의 필드 값과 일치 해야 합니다.
			// 정확히 말씀드리면 setter and getter 와 일치해야합니다.
		}
		
		// 2. uid 가 0도 허용한다는 가정하에 ...
		List<FieldError> errorList = errors.getFieldErrors();
		errorList.forEach((e) -> {
			if (e.getField().equals("uid")) {
				logger.error("uid 오류 {}, {}", e.getField(), e.getCode());
				errors.rejectValue("uid", "uid 에러"); // 첫번째 인자 uid 는 반드시
				// dto의 필드 값과 일치 해야 합니다.
				// 정확히 말씀드리면 setter and getter 와 일치해야합니다.
				return;
			}
		});

		String name = dto.getName();
		if(name == null || name.trim().isEmpty()) {
			logger.error("name: {}", name);
			errors.rejectValue("name", "name 은 공백을 허용 하지 않습니다.");
		}
		
		// reject If Empty Or Whitespace : 비어 있거나 공백 인 경우 거부
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "subject 비어 있거나 공백 인 경우 거부");
	}

}
