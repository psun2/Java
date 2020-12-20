package com.ltns.rest_area.validator.user;

import com.ltns.rest_area.domain.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("supports({})", clazz.getName());

        // 검증할 객체의 클래스 타입인지 확인 (WriteDTO 타입인지 확인)
        return UserDTO.class.isAssignableFrom(clazz);
    } // end supports()

    @Override
    public void validate(Object target, Errors errors) {
        // 주어진 객체(target)에 유효성 검사를 하고
        // 유효성 검사에 오류가 있는 경우, 주어진 객체에 이 오류들을  errors에 등록.

        UserDTO user = (UserDTO) target; // parameter 로 받아온 유저객체 (회원 가입 폼)

        String message = user.getMessage(); // 어떠한 상황에서의 검증인지

        long uid = user.getUm_uid();

        String username = user.getUm_username();

        String nickname = user.getUm_nickname();

        String password = user.getUm_password();

        String passwordCheck = user.getPasswordCheck();

        if (message == null || message.equals("")) { // 동작 메시지가 없는 경우 리턴
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "empty", "동작 메시지를 추가해주세요");
            return;
        }

        switch (message) {

            case "checkId": // 회원 가입에서의 아이디 중복 체크 전에 일단 아이디의 유효성 일치여부 확인
                checkId(username, errors);
                break;

            case "checkPw": // 회원 정보 수정시 필요
                checkPw(password, errors);
                break;

            case "checkNick": // 회원 가입에서의 닉네임 중복 체크 전에 일단 닉네임의 유효성 일치여부 확인
                checkNick(nickname, errors);
                break;

            case "updatePw": // 비밀번호 수정 (username 필수)
                username(errors);
                checkPw(password, errors);
                reCheckPw(password, passwordCheck, errors);
                break;

            case "updateNick": // 닉네임 수정 (username 필수)
                username(errors);
                checkNick(nickname, errors);
                break;

            case "updatePwAndNick": // 비밀번호와 닉네임 수정 (username 필수)
                username(errors);
                checkPw(password, errors);
                reCheckPw(password, passwordCheck, errors);
                checkNick(nickname, errors);
                break;

            case "deleteUser": // 회원 삭젝시 (username 필수)
                username(errors);
                break;

            case "insertUser": // 회원시 다시한번 유효성 검증
                checkId(username, errors);
                checkPw(password, errors);
                reCheckPw(password, passwordCheck, errors);
                checkNick(nickname, errors);
                break;

            case "findByUid": // uid 로 검색시
                uid(uid, errors);
                break;

            case "findByUsername": // username 으로 검색시
                username(errors);
                break;

            case "findByNickname": // nickname 으로 검색시
                nickname(errors);
                break;

            default:
                break;

        } // end switch

    } // end validate()

    void username(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_username", "empty", "아이디 값이 공백입니다.");
    } // end username()

    void uid(long uid, Errors errors) {
        if (uid == 0)
            errors.rejectValue("um_uid", "validation", new Long[]{uid}, "uid 값이 없습니다.");
    } // end uid()

    void nickname(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_nickname", "empty", "닉네임 값이 공백입니다.");
    } // end nickname()

    void password(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "um_password", "empty", "비밀번호 값이 공백입니다.");
    } // end password()

    void passwordCheck(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "empty", "비밀번호 체크 값이 공백입니다.");
    } // end passwordCheck()

    void checkId(String username, Errors errors) { // 아이디 검증
        username(errors);
        if (username != null && !username.equals("")) {
            String regex = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
            if (!inspect(regex, username)) {
                errors.rejectValue("um_username", "validation", new String[]{username}, "아이디(이메일) 형식이 아닙니다.");
            }
        }
    } // end checkId()

    void checkPw(String password, Errors errors) { // 비밀번호 검증
        password(errors);
        if (password != null && !password.equals("")) {
            String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
            if (!inspect(regex, password)) {
                errors.rejectValue("um_password", "validation", new String[]{password},
                        "비밀번호는 특수문자, 대문자, 소문자, 숫자를 포함한 8 ~ 20 자리입니다.");
            }
        }
    } // end checkPw()

    void reCheckPw(String password, String passwordCheck, Errors errors) {
        passwordCheck(errors);
        if (passwordCheck != null && !passwordCheck.equals("")) {
            if (!password.equals(passwordCheck))
                errors.rejectValue("passwordCheck", "validation", new String[]{passwordCheck},
                        "비밀번호가 서로 다릅니다.");
        }
    } // end reCheckPw()

    void checkNick(String nickname, Errors errors) { // 닉네임 검증
        nickname(errors);
        if (nickname != null && !nickname.equals("")) {
            String regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
            if (!inspect(regex, nickname))
                errors.rejectValue("um_nickname", "validation", new String[]{nickname}, "허용하는 범위에 맞지 않습니다.");
        }
    } // end checkNick()

    boolean inspect(String regex, String input) { // 정규식 패턴 매치

        if (Pattern.matches(regex, input)) return true; // 정규식에 통과한다면 true 를 반환

        return false; // 통과하지 못했다면 false 반환

    } // end inspect()

} // end UserValidation
