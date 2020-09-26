//------------------------------------------
//  함수의 기본 파라미터 (default function parameter)
//  https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/Default_parameters

{
  function add(a, b) {
    console.log('a =', a, ', b =', b);
    return a + b;
  }

  let sum;

  sum = add(1, 2);
  console.log('sum =', sum);
  // a = 1 , b = 2
  // sum = 3

  sum = add(1);
  console.log('sum =', sum);
  // a = 1 , b = undefined
  // sum = NaN
}

console.log();

{
  function add(a, b = 0) {
    console.log('a =', a, ', b =', b);
    return a + b;
  }

  let sum;

  sum = add(1);
  console.log('sum =', sum);
  // a = 1 , b = 0
  // sum = 1
}

console.log();

let calcCircleArea = function (r) {
  // let 으로 정의하여 hoisting 발생이 안됩니다.
  // function 정의시엔 hoistring 발생되어 최종으로 정의된
  // 함수를 사용합니다.
  return Math.PI * Math.pow(r, 2);
};

console.log(calcCircleArea(10)); // 314.1592653589793

console.log(calcCircleArea()); // NaN

calcCircleArea = function (r = 1) {
  return Math.PI * Math.pow(r, 2);
};
console.log(calcCircleArea()); // 3.141592653589793

// SCE 사용 가능
calcCircleArea = function (r) {
  r = r || 10;
  return Math.PI * Math.pow(r, 2);
};
console.log(calcCircleArea()); // 314.1592653589793

// 화살표 함수도 default parameter 사용 가능
calcCircleArea = (r = 100) => Math.PI * Math.pow(r, 2);
console.log(calcCircleArea()); // 31415.926535897932
