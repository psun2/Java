// 우선 함 보자
// 함수 '정의 먼저' 하고 '호출을 나중'
function hello1() {
  console.log('hello1');
}
hello1();

// 호출코드가 정의코드 보다 앞에 있다면 과연?
hello2();
function hello2() {
  console.log('hello2 실행될까요?');
}

// 변수의 경우는 ?

// 1)
// console.log('val_1 =', val_1); // 에러]

// 2)
console.log('val_2 =', val_2);
var val_2;

// 3)
console.log('val_3 =', val_3); // undefined
var val_3 = 10;

// 4)
val_4++; // 선언전 증가
console.log('val_4 =', val_4); // NaN
// undefined 인 상태에서 ++ 연산을 시킬때 산술연산이 되지 않으므로, NaN
var val_4 = 20;

// 5)
val_5 = 20;
val_5++; // 선언전 증가
console.log('val_5 =', val_5);
var val_5 = 40;

//--------------------------------------------------------------------------------------

/*
    변수(함수) 선언이 위로 올라오는 증상   
    이러한 현상을 hoisting 이라 하며, 
   
 
   hoising 
     아래에 있는 '선언'을(만) 끌어올린다.
 
   hoising 때문에 동작의 오류처럼 보이는 증상 겪게 됨
 
   Hoisting
    https://developer.mozilla.org/ko/docs/Glossary/Hoisting
 
    'hoising' 현상은 JavaScript 처음부터 있어왔던 증상이다.
    그러나, 위 'hoisting' 이라는 용어 자체는 
    ES2015(ES6) 이전에는 사용되지 않았음
*/

/*
    var 는 hoisting 발생  → 많은 혼란을 야기 시켰다.
    let, const 는 hoisting 이 발생 안한다.
*/

console.log();

{
  //   console.log(`name = ${name}`);
  //   name = 'Mark'; // ReferenceError: name is not defined

  //   console.log(`name = ${name}`);
  //   let name = 'Mark'; // ReferenceError: Cannot access 'name' before initialization

  console.log(`name = ${name}`); // name = undefined
  var name = 'Mark';
}

{
  // let, var 는 선언하기 전에는 사용 불가. hoisting 없다.
  //   console.log(name);
  //   const name = 'Mark'; // ReferenceError: Cannot access 'name' before initialization
}

// 함수 안에서는 ?
function myFunction() {
  console.log('myFunction() 호출');

  // 1. let, const 의 경우
  //console.log(name);  // 에러
  const name = 'Mark';

  // 2. var 의 경우
  console.log(`nick = ${nick}`); // 에러는 아니다, undefined 가 나올뿐.
  var nick = 'Flash';
}
myFunction();

console.log();

{
  console.log(`age = ${age}`);
  var age = 30;
  console.log(`age = ${age}`);
}
