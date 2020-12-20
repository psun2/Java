package com.ltns.rest_area.validator.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ltns.rest_area.domain.user.UserAuthDTO;

public class AuthValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 검사전 해당 클래스가 맞다면 
		// 검사 진행
		return UserAuthDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAuthDTO userAuth = (UserAuthDTO) target;

		long uid = userAuth.getUid();

		String message = userAuth.getMessage();

		if (message == null || message.equals("")) { // 행동 메시지가 없으면 아무런 동작을 안하게 합니다.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "empty", "동작 메시지를 추가해주세요");
			return;
		}

		switch (message) {
		case "uid": // uid 로 검색시 uid 는 필수 요소 입니다.
			if (uid == 0)
				errors.rejectValue("uid", "validation", new Long[] { uid }, "uid 값이 없습니다.");
			break;
		case "username": // username 로 검색시 username 는 필수 요소 입니다.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "delete": // delete 명령이 들어올때 어떠한 유저의 어떠한 권한을 삭제 시킬지 알아야합니다.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authority", "empty", "authority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "insert": // 어떠한 유저에게 어떠한 권한을 줄것인가 알아야 합니다.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addAuthority", "empty", "addAuthority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			break;
		case "update": // 어떠한 유저의 어떤 권한을 업데이트 시킬 것이지 알아야 합니다.
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "아이디 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prevAuthority", "empty", "prevAuthority 값이 공백입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nextAuthority", "empty", "nextAuthority 값이 공백입니다.");
			break;

		default:
			break;
		}
	}

}
