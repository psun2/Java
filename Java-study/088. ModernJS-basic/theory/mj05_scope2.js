const value = 'hello1'; // global scope

function myFunction() {
  console.log('myFunction: ');
  console.log(value); // "hello1"
}
myFunction();

function otherFunction() {
  console.log('otherFunction: ');
  //   console.log(value); // let이 선언됨가 동시에
  // value는 글로벌 스코프가 아닌 블럭 스코프 내의 value 를 reference 합니다.

  //   let value = 'bye';
  // ReferenceError: Cannot access 'value' before initialization
}
otherFunction();

//------------------------------------------------------

function myFunctiuon2() {
  const value = 'bye!';
  const anotherValue = 'world';

  function functionInside() {
    console.log('functionInside()');
    console.log(value);
    console.log(anotherValue);
  }
  functionInside();
}
myFunctiuon2();

console.log(value); // hello1

console.log();

function myFunction3() {
  const value = 'bye!';
  if (true) {
    const value = 'world';
    console.log('block scope value =', value); // world
  }
  console.log('function scope value =', value); // bye!
}
myFunction3();

console.log();

function myFunction4() {
  var value = 'bye!';
  if (true) {
    var value = 'world';
    console.log('block scope value =', value); // world
  }
  console.log('function scope value =', value); // world ❗❗
}
myFunction4();

// 이러한 이유 때문에 var 보다는
// const, let 사용을 권장합니다.
