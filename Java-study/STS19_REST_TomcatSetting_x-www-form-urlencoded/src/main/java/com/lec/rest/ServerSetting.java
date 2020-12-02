package com.lec.rest;

public class ServerSetting {

	// POST, PUT, DELETE
	// 메소드 이용시 이용불가 에러

	// WHY?
	// 톰캣은 내부적으로 내부 보안을 위하여 GET이외의 모든 메소드는 허용을 하지 않습니다.
	// 이를 허용 하기 위하여 톰캣은 설정을 변경해야합니다.

	// server => server.xml
	// 기존: <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1"
	// redirectPort="8443"/>
	// 변경후: <Connector URIEncoding="UTF-8" connectionTimeout="20000"
	// parseBodyMethods="POST,PUT,DELETE" port="8080" protocol="HTTP/1.1"
	// redirectPort="8443"/>
	
	// 핵심: parseBodyMethods="POST,PUT,DELETE"

}
