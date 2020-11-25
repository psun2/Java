package com.lec.sts12_validation;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts12_validation.beans.WriteDTO;

@Controller
@RequestMapping("board")
public class BoardController implements com.lec.sts12_validation.Controller{

	@Autowired
	@Qualifier("logger")
	private Logger logger;
	
	@Autowired
	@Qualifier("vaildator")
	private Validator boardvalidator;
	
	// formData 검증
	@RequestMapping("write")
	public void boardWrite() {}
	
	@RequestMapping("writeOk")
	public String boardWriteOk(@ModelAttribute("w") @Valid WriteDTO dto, // @Validated 사용안됨.. @Valid 사용
			BindingResult bindingResult // <-- validator 가 유효성 검사를 한 객체
			) {
		
		// Command Object 로서의 역할을 하는 WriteDTO 는 uid 가 int 형인데...
		// Spring이 injection 해줄때 int로 변환하여 주입합니다.
		// 이과정에 parsing이 될수 없는 문자열이 들어 온다면 에러가 나는데
		// 이때 BindingResult 객체가 유효성 검사한 결과를 볼수 있습니다.
		logger.info("유효성 검사 결과: uid: {}, name: {}", dto.getUid(), dto.getName());
		logger.info("WriteDTO dto: {}", dto);
		logger.info("BindingResult: {}", bindingResult);
		// INFO : com.lec.sts12_validation.Controller - BindingResult: org.springframework.validation.BeanPropertyBindingResult: 0 errors
		logger.error("에러 개수: {}", bindingResult.getErrorCount());
		
		// showError(bindingResult);
		
		String page = "board/writeOk";
		
		if(bindingResult.hasErrors()) page = "board/write";
		
		logger.info("writeOk 최종페이지: {}", page);
		
		return page;
		
	}
	
	// public void showError(Errors errors) {
	// Errors : BindingResult 의 부모
	public void showError(BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			
			logger.error("에러 개수: {}", bindingResult.getErrorCount());
			logger.error("\t[field]|\t[code]");
			
			List<FieldError> errorList = bindingResult.getFieldErrors();
			
			errorList.forEach((e) -> {
				logger.error("\t{}|\t{}", e.getField(), e.getCode());
			});
		} else {
			logger.info("에러 없음");
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		// @Autowired를 사용하려면 
		// BoardValidator 객체가 스프링이 관리하는 빈이어야 합니다.
		// 또한 Validator을 implements 했띠 때문에
		// Validator type이 됩니다.

		binder.setValidator(boardvalidator);

	}
	
}
