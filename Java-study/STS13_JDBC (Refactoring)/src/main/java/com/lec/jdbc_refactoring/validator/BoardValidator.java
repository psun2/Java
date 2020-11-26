package com.lec.jdbc_refactoring.validator;

import com.lec.jdbc_refactoring.domain.WriteDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return WriteDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WriteDTO dto = (WriteDTO) target;

        long uid = dto.getUid();
        if (uid == 0) { // 나의 요구 사항에서 uid가 0인 경우는 없음 그러므로, 파싱 에러
            errors.rejectValue("uid", "파싱에러");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "빈 문자열 거부");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "빈 문자열 거부");

    }

}
