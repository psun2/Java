// 함수 정의 매개변수 VS 호출시 인자의 개수

function add(a, b) {
  console.log(`add(${a}, ${b}) 호출`);
  return a + b;
}

let sum;

sum = add(1, 2);
console.log('sum =', sum); // sum = 3

sum = add(10, 20, 50); // ?? 호출시 인자가 더 많은 경우
console.log('sum =', sum); // sum = 30

sum = add(10); // ?? 호출시 인자가 더 적은 경우
// b 는 undefined 가 됩니다.
// 10 + undefined 가 되어
// NaN 이 출력 되게 됩니다.
console.log('sum =', sum); // sum = NaN

function add(a, b) {
  // hoisting 이 일어나 버립니다.
  // 오버로딩(Overloading) : 같은 이름의 메소드를 여러 개 가지면서 매개변수의 유형과 개수가 다르도록 하는 기술
  // (parameter 의 개수를 다르게 설정합니다.)

  // 오버라이딩(Overriding) : 상위 클래스가 가지고 있는 메소드를 하위 클래스가 재정의 해서 상요한다.
  // (메소드의 내용을 재정의 합니다.)

  console.log(`add(${a}, ${b}) 호출2 허걱`);
  return a + b;
}
sum = add(100, 200);
console.log('sum =', sum);
// function 은 hoisting 이 발생한다!!!!!!!!!!!!!

console.log();
// 함수 만드는 방법
{
  // 방법1: function + 이름있는 함수
  function hello1() {
    console.log('hello1');
  }
  hello1();
  console.log(hello1, typeof hello1);

  // 방법2: var + 이름없는(anonymous) 함수
  var hello2 = function () {
    console.log('hello2');
  };
  hello2();

  // 방법3: {let, const} + 이름없는(anonymous) 함수
  let hello3 = function () {
    console.log('hello3');
  };
  hello3();
}

// 블럭 밖
console.log('\n 블럭 밖 호출');
hello1(); // hello1
hello2(); // hello2
// hello3(); // Error let, const => block scope

console.log();
// 두가지 방법의 차이점은?
{
  function hello4() {
    console.log('hello4');
  }
  hello4();

  hello5(); // 문제없이 실행됩니다.! hoisting 발생
  function hello5() {
    console.log('hello5');
  }
}

console.log();
{
  // #1 function 사용
  console.log('hello6 :', hello6); // hello6 : [Function: hello6]
  function hello6() {
    console.log('hello6');
  }
  hello6();

  // #2  익명함수(이름없는 함수) 사용하는 경우.
  console.log('hello7 :', hello7); // hello7 : undefined
  //   hello7(); // TypeError: hello7 is not a function at Object.<anonymous>
  // 타입 에러 정의는 되어있지만 선언이 안되어 있음
  var hello7 = function () {
    console.log('hello7');
  };

  // #3 const 의 경우는?
  //   hello8(); // ReferenceError: Cannot access 'hello8' before initialization at Object.<anonymous>
  // 레퍼런스 에러 값을 참조 할 수 없음... 즉 정의자체가 안되있음 hello8을 몰라요...
  const hello8 = function () {
    console.log('hello8');
  };
}
