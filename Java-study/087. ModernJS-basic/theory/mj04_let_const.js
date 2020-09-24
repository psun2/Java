// JS 애서 선언하기 위한 키워드
// var, let, const 3가지가 있다.

// 여전히 var 사용 가능.
var v1 = 100;
console.log('v1 =', v1);

v2 = 200; // var 없이도 선언 가능.
console.log(`v2 = ${v2}`);

var v1 = 300; // 동일 이름의 변수 중복 선언 가능
console.log(`v1 = ${v1}`);

// ★ var 는 '오늘날 js' 에서는 그닥 권장하지 않습니다.

/*
   ES6 부터 let, const 소개.  (IE9, IE10 와 같은 구형브라우저는 지원안함)
    변수 선언 --> let
    상수 선언 --> const
 
 
    let 
        동일이름 변수 중복 선언 불가
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/let
            ↑ 처음이라면 함 보자.
 
    const
        한번 선언 + 초기화 하면 값 변경 불가.
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/const
 
    이들은 block scope 를 가진다.
        { ... }
*/

let value = 177;
console.log(`value = ${value}`);

// let value = 277; // 에러] 동일이름 변수 선언 불가
// SyntaxError: Identifier 'value' has already been declared
// console.log(`value = ${value}`);

// var value = 10;
// SyntaxError: Identifier 'value' has already been declared
// console.log(`value = ${value}`);

value = 277;
console.log(`value = ${value}`);

let value2;
console.log(`value2 = ${value2}`); // 초기화 안되어 있으면 에러가 아님.
// console.log(`value3 = ${value3}`); // 선언되지 않은 변수 에러

// 상수 선언
const C1 = 1;
console.log(`C1 = ${C1}`);

// C1 = 2; // 에러] 상수값 변경 불가
// TypeError: Assignment to constant variable.
// console.log(`C1 = ${C1}`);

// const C2; // 에러] 상수는 반드시 선언시 초기화 해야 한다.
// SyntaxError: Missing initializer in const declaration
// console.log(`C2 = ${C2}`);

/*
final int n = 10;
final int m;
 자바에서는 맴버 변수일때 error
 하지만 지역 변수일시 나중에 초기화 가능
*/

// const 는 object 에서도 가능
const MY_OBJECT = { key: 'value' };
console.log(`MY_OBJECT = ${MY_OBJECT}`); // MY_OBJECT = [object Object]
console.log(`MY_OBJECT = ${JSON.stringify(MY_OBJECT)}`); // MY_OBJECT = {"key":"value"}

// MY_OBJECT 는 변경할수 없으나
// 그 안의 key: value 는 변경할수 있다.
// MY_OBJECT = { name: 'john' }; // 에러]
MY_OBJECT.key = 'other value';
console.log(`MY_OBJECT = ${JSON.stringify(MY_OBJECT)}`);
// ❣ object 내용물을 변경못하게 하려면 Object.freeze() 를 사용

const MY_ARRAY = [];
MY_ARRAY.push('A');
console.log(`MY_ARRAY = ${MY_ARRAY}`);
