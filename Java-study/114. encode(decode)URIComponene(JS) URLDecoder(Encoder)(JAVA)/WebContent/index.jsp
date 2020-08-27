<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1">
<title>114. encodeURIComponene(JS) URLDecoder(JAVA)</title>
</head>
<body>

	<h1>encodeURIComponene(JS)</h1>
	<div>
		<a
			href="https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent">(출처)
			MDN</a>
	</div>
	<p>encodeURIComponent() 함수는 URI의 특정한 문자를 UTF-8로 인코딩해 하나, 둘, 셋, 혹은 네
		개의 연속된 이스케이프 문자로 나타냅니다. (두 개의 대리 문자로 이루어진 문자만 이스케이프 문자 네 개로 변환됩니다.)</p>

	<P>Escape : 값을 에러 없이 제대로 전달하기 위해 제어 문자로 인식될 수 있는 특정 문자 왼쪽에 슬래시를
		붙이거나 URI(URL) 혹은 HTML 엔티티 등으로 인코딩하여 제어 문자가 아닌 일반 문자로 인식시켜 에러 또는 에러를
		이용한 부정행위를 방지한다. 예를 들어 따옴표를 그대로 전달하면 에러가 발생하지만 이스케이프(\' 또는 \")를 한 후
		전달하면 전달받는 쪽에서 이스케이프 해제 함수를 사용하지 않아도 에러 없이 이스케이프가 해제된 문자열을 전달받을 수 있다.</P>


	<xmp>console.log( `결과물 :
	https://www.naver.com/search?=&sect;{encodeURIComponent("박성언")}`);</xmp>
	<p>결과물 : https://www.naver.com/search?=%EB%B0%95%EC%84%B1%EC%96%B8</p>

	<hr />

	<h1>decodeURIComponene(JS)</h1>
	<div>
		<a
			href="https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/decodeURIComponent">(출처)
			MDN</a>
	</div>
	<p>decodeURIComponent() 함수는 encodeURIComponent 나 비슷한 방법으로 생성된
		Uniform Resource Identifier(URI) 컴포넌트를 해독합니다.</p>

	<xmp>console .log(decodeURIComponent("결과물 :
	https://www.naver.com/search?=%EB%B0%95%EC%84%B1%EC%96%B8"));</xmp>
	<p>결과물 : https://www.naver.com/search?=박성언</p>


	<hr />
	<hr />
	<hr />

	<h1>URLDecoder(JAVA)</h1>
	<xmp> try { String str = "한글 문자열!@#english";
	System.out.println(java.net.URLDecoder.decode(str, "UTF-8")); // 한글
	문자열!@#english } catch (UnsupportedEncodingException e) { // TODO
	Auto-generated catch block e.printStackTrace(); } </xmp>

	<hr />

	<h1>URLEncoder(JAVA)</h1>
	<xmp> try { String str = "한글 문자열!@#english";
	System.out.println(java.net.URLEncoder.encode(str, "UTF-8")); //
	%ED%95%9C%EA%B8%80+%EB%AC%B8%EC%9E%90%EC%97%B4%21%40%23english } catch
	(Exception e) { // TODO: handle exception } </xmp>
</body>
</html>