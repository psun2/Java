package com.ltns.rest_area.config.validator;

import com.ltns.rest_area.domain.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
public class Config implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("supports({})", clazz.getName());

        // 검증할 객체의 클래스 타입인지 확인 (WriteDTO 타입인지 확인)
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 주어진 객체(target)에 유효성 검사를 하고
        // 유효성 검사에 오류가 있는 경우, 주어진 객체에 이 오류들을  errors에 등록.

        UserDTO user = (UserDTO) target; // parameter 로 받아온 유저객체 (회원 가입 폼)

        String message = user.getMessage(); // 어떠한 상황에서의 검증인지

        if (message.equals("insertUser")) { // 회원 가입일 경우
            String regex = null; // 정규식을 담을 string

            // Test case 참조 Test 통과
            String username = user.getUm_username().trim();
            if (username != null && !username.equals("")) {
                regex = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
                if (!inspect(regex, username)) { // 유효성에 통과 실패
                    errors.rejectValue("um_username", "validation", new String[]{username}, "아이디(이메일) 형식이 아닙니다.");
                }
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty");
            }

            String password = user.getUm_password().trim();
            if (password != null && !password.equals("")) {
                regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
                if (!inspect(regex, password)) {
                    errors.rejectValue("um_password", "validation", new String[]{password}, "비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
                } else {
                    String passwordCheck = user.getPasswordCheck();
                    if (!password.equals(passwordCheck)) // 유효성은 통과 되었으나 비밀번호가 서로 다른 경우
                        errors.rejectValue("passwordCheck", "validation", new String[]{passwordCheck}, "비밀번호가 서로 다릅니다.");
                }
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty");
            }

            String nickname = user.getUm_nickname().trim();
            if (nickname != null && !nickname.equals("")) {
                regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
                if (!inspect(regex, nickname))
                    errors.rejectValue("um_nickname", "validation", new String[]{nickname}, "허용하는 범위에 맞지 않습니다.");
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty");
            }
        } else if (message.equals("findByUid")) { // user 의 고유값으로 조회할때
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_uid", "empty");
        } else if (message.equals("findByUsername")) { // user 의 id 값으로 조회할때
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty");
        } else if (message.equals("findByNickname")) { // user 의 닉네임 값으로 조회
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty");
        } else { // 그 외의 경우
            return;
        }


    }

    boolean inspect(String regex, String input) {

        if (Pattern.matches(regex, input)) return true; // 정규식에 통과한다면 true 를 반환

        return false; // 통과하지 못했다면 false 반환

    }

}
