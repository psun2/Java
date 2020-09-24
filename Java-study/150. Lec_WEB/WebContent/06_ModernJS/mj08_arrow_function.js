/*
    화살표 함수 Arrow function (ES6 도입)
    함수를 정의하는 또 다른 방법
 
    [구문]
        (param1, param2, …, paramN) => { statements }
        (param1, param2, …, paramN) => expression
        // 다음과 동일함:  => { return expression; }
    
    [공식]    
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/%EC%95%A0%EB%A1%9C%EC%9A%B0_%ED%8E%91%EC%85%98
 
    ★ 화살표 함수는 this 가 바인딩(binding) 되지 않는다
*/

const add = (a, b) => {
  return a + b;
};

let sum = add(1, 2);

console.log(`sum = ${sum}`);

// 매개변수 하나일때는 괄호 생략 가능
// 현재 프리티어 설정으로 인하여 괄호가 계속 생성
// 크롬에서 확인해 보자 !
const hello1 = (name) => {
  console.log(name);
};
hello1('아이언맨');

const hello2 = (name, age) => {
  console.log('hello2: ', name, age);
};
hello2('Susan', 23);

// return 값이 없는 화살표 함수는
// 더 간략히 정의 가능
const add2 = (a, b) => a + b; // return a + b;
sum = add2(10, 20);
console.log(`sum = ${sum}`);

const add3 = function (a, b) {
  return a + b;
};

/*
    화살표 함수의 'this' 와
    function 으로 만든 함수의 'this' 는
    의미가 다르다!
    
    이는 나중에 다루어 보겠습니다.!
  */
// ★ 화살표 함수는 this 가 바인딩(binding) 되지 않는다
