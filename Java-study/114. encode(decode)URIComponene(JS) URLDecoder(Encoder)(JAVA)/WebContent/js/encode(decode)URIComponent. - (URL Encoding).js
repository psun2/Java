// MDN : https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent

// encodeURIComponent() 함수는 URI의 특정한 문자를 UTF-8로 인코딩해 하나, 둘, 셋, 혹은 네 개의 연속된 이스케이프 문자로 나타냅니다. (두 개의 대리 문자로 이루어진 문자만 이스케이프 문자 네 개로 변환됩니다.)

console.log(`결과물 : https://www.naver.com/search?=${encodeURIComponent("박성언")}`);
// 결과물 : https://www.naver.com/search?=%EB%B0%95%EC%84%B1%EC%96%B8

// Escape :
// 값을 에러 없이 제대로 전달하기 위해 제어 문자로 인식될 수 있는 특정 문자 왼쪽에 슬래시를 붙이거나 URI(URL) 혹은 HTML 엔티티
// 등으로 인코딩하여 제어 문자가 아닌 일반 문자로 인식시켜 에러 또는 에러를 이용한 부정행위를 방지한다. 예를 들어 따옴표를 그대로 전달하면
// 에러가 발생하지만 이스케이프(\' 또는 \")를 한 후 전달하면 전달받는 쪽에서 이스케이프 해제 함수를 사용하지 않아도 에러 없이
// 이스케이프가 해제된 문자열을 전달받을 수 있다.

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// MDN :
// https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/decodeURIComponent

// decodeURIComponent() 함수는 encodeURIComponent 나 비슷한 방법으로 생성된 Uniform Resource
// Identifier(URI) 컴포넌트를 해독합니다.

console
		.log(decodeURIComponent("결과물 : https://www.naver.com/search?=%EB%B0%95%EC%84%B1%EC%96%B8"));

// 결과물 : https://www.naver.com/search?=박성언
