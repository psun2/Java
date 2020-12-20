package com.ltns.rest_area.controller.restcontroller.auth.user;

import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.user.AjaxList;
import com.ltns.rest_area.domain.user.UserDTO;
import com.ltns.rest_area.service.user.UserSrvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserSrvice userSrvice;

    @PostMapping("/joinLookup") // 회원가입시 중복체크 컨트롤러
    public ResponseEntity<AjaxResult> joinLookup(@Valid UserDTO user, BindingResult bindingResult) {

        String message = user.getMessage();

        AjaxResult result = new AjaxResult();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // parameter 검증 과정중 에러가 있다면
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // parameter의 유효성에 맞다면 핵심 로직 실행
            try {
                List<UserDTO> users = userSrvice.findSelector(user);
                switch (message) {
                    case "checkId":
                        if (users.size() == 1) {
                            result.setCount(users.size());
                            result.setMessage(users.get(0).getUm_username() + " 아이디 중복");
                        } else {
                            result.setStatus("OK");
                            result.setMessage("중복되는 아이디가 존재 하지 않습니다.");
                        }
                        break;
                    case "checkNick":
                        if (users.size() == 1) {
                            result.setCount(users.size());
                            result.setMessage(users.get(0).getUm_nickname() + " 닉네임 중복");
                        } else {
                            result.setStatus("OK");
                            result.setMessage("중복되는 닉네임이 존재 하지 않습니다.");
                        }
                        break;
                }
            } catch (Exception e) {
                log.error("트랜잭션 에러 : {}", e.getMessage());
                result.setMessage("트랜잭션 에러");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if

        return new ResponseEntity<>(result, HttpStatus.OK);
    } // end joinLookup()

    @PostMapping("/auth/user/join") // 회원 가입 컨트롤러
    public ResponseEntity<AjaxResult> join(@Valid UserDTO user, BindingResult bindingResult) {

        AjaxResult result = new AjaxResult();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            try {
                int count = userSrvice.insertUser(user);
                result.setCount(count);
                result.setStatus("OK");
                result.setMessage("회원가입에 성공했습니다.");
            } catch (Exception e) {
                log.error("트랜잭션 에러 : {}", e.getMessage());
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if

        return new ResponseEntity<>(result, HttpStatus.OK);
    } // end join()

} // end UserController

