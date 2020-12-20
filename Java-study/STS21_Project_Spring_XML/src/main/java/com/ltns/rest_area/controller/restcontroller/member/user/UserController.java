package com.ltns.rest_area.controller.restcontroller.member.user;

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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member/user")
public class UserController {

    @Autowired
    private UserSrvice userSrvice;

    @PostMapping("/lookUp") // 회원 조회
    public ResponseEntity<AjaxList> lookup(@Valid UserDTO user, BindingResult bindingResult) {

        AjaxList result = new AjaxList();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // parameter 검증 과정에서 에러가 있다면...
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // parameter 검증 과정에서 에러가 없다면...
            try {

                List<UserDTO> users = userSrvice.findSelector(user);

                result.setCount(users.size());
                result.setListAll(users);
                result.setStatus("OK");
                result.setMessage("트랜잭션 성공");

            } catch (Exception e) {
                log.error("트랜잭션 에러 : {}", e.getMessage());
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if
        return new ResponseEntity<>(result, HttpStatus.OK);

    } // enn lookup()

    @PutMapping("/modify") // 회원 정보 수정
    public ResponseEntity<AjaxList> modify(@Valid UserDTO user, BindingResult bindingResult) {

        String message = user.getMessage();

        AjaxList result = new AjaxList();
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
                List<UserDTO> users = null;
                int update = userSrvice.updateSelector(user);
                if (update == 1) { // 업데이트가 되었다면
                    users = userSrvice.findSelector(user);
                    result.setCount(users.size());
                    result.setListAll(users);
                    result.setStatus("OK");
                    result.setMessage("트랜잭션 성공");
                } else { // 없데이트가 되지 않았다면
                    result.setMessage("삭제실패");
                    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } catch (Exception e) {
                log.error("트랜잭션 에러 : {}", e.getMessage());
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if
        return new ResponseEntity<>(result, HttpStatus.OK);

    } // end modify()

    @DeleteMapping("/delete") // 회원 탈퇴
    public ResponseEntity<AjaxResult> delete(@Valid UserDTO user, BindingResult bindingResult) {

        AjaxResult result = new AjaxResult();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터 검증 과정에서 에러가 있다면 ...
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터가 정상 적이라면 ...
            try {
                int delete = userSrvice.deleteByUesrname(user); // 회원 삭제

                if (delete == 1) { // 진짜로 삭제가 되었다면 ..
                    result.setCount(delete);
                    result.setStatus("OK");
                    result.setMessage("트랜잭션 성공(유저 삭제)");
                } else { // 삭제가 되지 않았다면
                    result.setMessage("삭제실패");
                    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } catch (Exception e) {
                log.error("트랜잭션 에러 : {}", e.getMessage());
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if

        return new ResponseEntity<>(result, HttpStatus.OK);
    } // delete()

} // UserController
