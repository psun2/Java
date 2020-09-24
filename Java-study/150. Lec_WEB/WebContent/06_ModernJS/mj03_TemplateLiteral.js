/*
    Template Literal 문법 (ES6 등장)
    ES6 에는 문자열 조합을
    편리하게 할수 있는 방법 제공
        '이전에는 Template String이라 불렸지만
        'ES2015 명세서 부터는 Template Literal 이라 부름
    
    backtick (`) 문자 사용한 문자열 포맷팅
        문자열 안에서 ${변수}
        문자열 안에서 ${값}
        ..
 
    공식]
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Template_literals        
*/
function sayHello(name) {
  console.log('Hello, ' + name + '!');
}
sayHello('아이언맨');

function sayHello2(name) {
  console.log(`Hello, ${name}!`);
}
sayHello2('스파이더맨');
