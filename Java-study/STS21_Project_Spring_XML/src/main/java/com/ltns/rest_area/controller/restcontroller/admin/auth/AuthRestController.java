package com.ltns.rest_area.controller.restcontroller.admin.auth;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.user.AjaxList;
import com.ltns.rest_area.domain.user.AuthDTO;
import com.ltns.rest_area.domain.user.UserAuthDTO;
import com.ltns.rest_area.service.user.AuthService;
import com.ltns.rest_area.validator.user.AuthValidation;

@RestController
@RequestMapping("/admin/auth")
public class AuthRestController {

    @Autowired
    AuthService authSerivece;

    @InitBinder
    public void intBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new AuthValidation()); // validation setting
    } // end intBinder()

    @PostMapping("/find/all") // userMember 와 auth 테이블 을 조인시켜 조회(All)
    public ResponseEntity<AjaxList> findUserAuth() {

        AjaxList result = new AjaxList();
        result.setStatus("FAIL");

        try {
            List<UserAuthDTO> users = null;

            users = authSerivece.finAll();
            result.setCount(users.size());
            result.setListAll(users);
            result.setStatus("OK");
            result.setMessage("트랜잭션 성공");

        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("트랜잭션 오류");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        } // end try

        return new ResponseEntity<>(result, HttpStatus.OK);

    } // end findUserAuth()

    @PostMapping("/find/username") // userMember 와 auth 테이블 을 조인시켜 조회(username)
    public ResponseEntity<AjaxList> findUserAuthByUsername(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {

        AjaxList result = new AjaxList();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터에 에러가 있나 검증
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터에 에러가 없고 필요한 값이 모두 잘 들어 왔다면

            try {

                List<UserAuthDTO> users = authSerivece.findByUsername(userAuth.getUsername());
                result.setCount(users.size());
                result.setListAll(users);
                result.setStatus("OK");
                result.setMessage("트랜잭션 성공");

            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if

        return new ResponseEntity<>(result, HttpStatus.OK);

    } // end findUserAuthByUsername()

    @PostMapping("/find/uid") // auth table 을 조회
    public ResponseEntity<AjaxList> findUserAuthByUid(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {

        AjaxList result = new AjaxList();
        result.setStatus("FAIL");
        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터에 에러가 있나 검증
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터에 에러가 없고 필요한 값이 모두 잘 들어 왔다면

            try {

                List<AuthDTO> users = authSerivece.findByUid(userAuth.getUid());
                result.setCount(users.size());
                result.setListAll(users);
                result.setStatus("OK");
                result.setMessage("트랜잭션 성공");

            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if
        return new ResponseEntity<>(result, HttpStatus.OK);

    } // end findUserAuthByUid()


    @PostMapping("/insert") // 권한 추가 컨트롤러
    public ResponseEntity<AjaxResult> addAuthority(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {
        AjaxResult result = new AjaxResult();
        result.setStatus("FAIL");

        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터에 에러가 있나 검증
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터에 에러가 없고 필요한 값이 모두 잘 들어 왔다면
            try {
                int insert = authSerivece.insertAuthByUsername(userAuth);

                if (insert == 1) {
                    result.setCount(insert);
                    result.setStatus("OK");
                    result.setMessage("트랜잭션 성공");
                } else {
                    result.setMessage("권한 추가 실패");
                    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                } // end if

            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if
        return new ResponseEntity<>(result, HttpStatus.OK);
    } // end addAuthority()

    @PutMapping("/update") // 권한 업데이트 (어떠한 유저의 어떤 권한을 어떻게 업데이트 할 것인가 1 : N 테이블 이기 때문에)
    public ResponseEntity<AjaxList> updateAuthority(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {
        AjaxList result = new AjaxList();
        result.setStatus("FAIL");

        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터에 에러가 있나 검증
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터에 에러가 없고 필요한 값이 모두 잘 들어 왔다면

            try {

                int update = authSerivece.updateByUid(userAuth);

                if (update == 1) {
                    List<UserAuthDTO> users = authSerivece.findByUsername(userAuth.getUsername());
                    result.setCount(users.size());
                    result.setListAll(users);
                    result.setStatus("OK");
                    result.setMessage("트랜잭션 성공");
                } else {
                    result.setMessage("권한 업데이트 실패");
                    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
                } // end if

            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("트랜잭션 오류");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if
        return new ResponseEntity<>(result, HttpStatus.OK);
    } // end updateAuthority()

    @DeleteMapping("/delete") // 권한 삭제 (어떠한 유저의 어떤 권한을 삭제 할것인가? 1 : N이기때문에...)
    public ResponseEntity<AjaxResult> deleteByUsername(@Valid UserAuthDTO userAuth, BindingResult bindingResult) {

        AjaxResult result = new AjaxResult();
        result.setStatus("FAIL");

        // List<String> msgs = new ArrayList<String>();
        StringBuffer msgs = new StringBuffer();

        if (bindingResult.hasErrors()) { // 파라미터에 에러가 있나 검증
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(msg -> {
                // msgs.add(msg.getDefaultMessage());
                msgs.append(msg.getDefaultMessage());
            });
            // result.setMessage(msgs.get(0));
            result.setMessage(msgs.toString());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else { // 파라미터에 에러가 없고 필요한 값이 모두 잘 들어 왔다면
            try {
                int delete = authSerivece.deleteByUsername(userAuth);
                result.setCount(delete);
                result.setStatus("OK");
                result.setMessage("트랜잭션 성공");

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } // end try
        } // end if

        return new ResponseEntity<>(result, HttpStatus.OK);
    } // end deleteByUsername()

} // end AuthRestController
