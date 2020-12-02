package com.lec.rest;

public class CommonResult {
	
	// 클라이언트 측에서 보내는 정보
//	  Key : VALUE
//    "uid": 15,
//    "id": "tjddjs90",
//    "password": "비밀번호"
	
////////////////////////////////////////////////////////////////////////////////
	// RequestBody 와 결정적인 차이점!!!!!!!!!!!!!!!!!!!!
	// http://localhost:8080/rest/rest/?uid=15&id=tjddjs90&password=비밀번호
	// URL로 모는 정보가 넘어갑니다......
////////////////////////////////////////////////////////////////////////////////
	
	// 서버측에서 보내주는 결과물
//	{
//	    "status": "OK",
//	    "message": "반환 성공",
//	    "dto": {
//	        "uid": 15,
//	        "id": "tjddjs90",
//	        "password": "비밀번호"
//	    }
//	}

}
