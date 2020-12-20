package com.ltns.rest_area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.service.SearchService;

@Controller
@RequestMapping("/restarea")
public class RestAreaController {

	@Autowired
	private SearchService searchService;

	// 휴게소로 검색
	@GetMapping("/{listSort}/{routeName}/{destination}/{orderBy}")
	public AjaxList getList(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy) {

		AjaxList result = moreList(listSort, routeName, destination, orderBy, 0);
		return result;
	}

	// 추가 리스트 호출(아래로 스크롤) pageNo 이후부터 10개 추가
	@GetMapping("/{listSort}/{routeName}/{destination}/{orderBy}/{lastIndex}")
	public AjaxList moreList(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy, @PathVariable int lastIndex) {

		// response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		//dto 데이터
		List<DTO> list=null;


		// 페이징 관련 세팅 값들
		int totalCnt=0;	//총 dto 갯수

		int numOfRows=10; 	//한 페이지에 리스트할 dto 갯수
		int totalPage=0;	//총 페이지 갯수

		int pagenationPage=10;	//페이지네이션에 표시할 페이지 갯수
		
		int from=lastIndex+1;
		try {
			switch (listSort) {
			case "ra":
				//전체 휴게소 갯수
				totalCnt=searchService.raCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeRaDTOs(routeName,destination,orderBy, from,numOfRows);
				break;
				
				
			case "gs":
				//전체 휴게소 갯수
				totalCnt=searchService.gsCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeGsDTOs(routeName,destination,orderBy, from,numOfRows);
				break;
				
				
			case "fm":
				//전체 휴게소 갯수
				totalCnt=searchService.fmCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeFmDTOs(routeName,destination,orderBy, from,numOfRows);
				break;
			}
			
			if(list==null) {
				message.append("[리스트할 데이터가 없습니다]");
			}else {
				status="OK";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : "+e.getMessage()+"]");
		}
		
		AjaxList result=new AjaxList();
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list!=null) {
			result.setCount(list.size());
			result.setList(list);
		}
		result.setPageNo(0);//필요없다 페이지네이션이 아닌 무한스크롤 방식이기 때문
		result.setTotalPage(totalPage);
		result.setNumOfRows(numOfRows);
		result.setTotalCnt(totalCnt);
		
		result.setPagenationPage(pagenationPage);

		return result;
	}

}
