/*
  함수 인자와 spread
 
  파라미터(parameter) : 함수에서 받아오는 변수
  인자(argument)  : 함수 호출시 보내는 값
*/
{
  function myFunction(a) {
    // 여기서 a 는 파라미터
    console.log(a);
  }

  myFunction('hello world'); // 여기서 'hello world' 는 인자
}

{
  function sum(...rest) {
    console.log(rest);
    return rest.reduce((acc, cur) => (acc += cur), 0);
  }

  let result = sum(10, 20, 30, 40, 50);
  console.log(result);

  const numbers = [10, 20, 30, 40, 50, 60];
  result = sum(numbers); // [ [ 10, 20, 30, 40, 50, 60 ] ]
  // 이때 당시 배열로 넣었기때문에 이중배열 형태의 파라미터 취급을 합니다.
  console.log(result); // 010,20,30,40,50,60

  // 배열 안의 원소를 호출 인자로 보내주고 싶다면 ???
  result = sum(
    numbers[0],
    numbers[1],
    numbers[2],
    numbers[3],
    numbers[4],
    numbers[5],
  );
  // 매우 불편 합니다.
  console.log(result); // 210
}

{
  function sum(...rest) {
    console.log(rest);
    return rest.reduce((acc, cur) => (acc += cur), 0);
  }

  const numbers = [10, 20, 30, 40, 50, 60];

  // ❗❗❗ 호출시 함수 인자에 spread 사용 가능 ❗❗❗
  const result = sum(...numbers);
  console.log(result);
}

//----------------------------------------------
// 함수에 n 개의 숫자들이 파라미터로 주어졌을 때,
// 그 중 가장 큰 값을 알아내세요.
function max(...rest) {
  // 1.
  // return rest.reduce((acc, cur) => (acc > cur ? acc : cur), 0);
  // return rest.reduce((acc, cur) => (acc > cur ? acc : cur), rest[0]);

  const max = Math.max.apply(null, rest); // 최대값
  const min = Math.min.apply(null, rest); //최소값
  return max;
  // apply 함수를 이용하면 배열을 파라미터로 전달 할 수 있다.
  // 이 함수의 구문은 거의 call() 구문과 유사합니다.
  // 근본적인 차이점은  call() 은 함수에 전달될 인수 리스트를 받는데 비해,
  // apply() 는 인수들의 단일 배열을 받는다는 점입니다.
  // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Function/apply

  // 2.
  // let result = 0;
  // rest.forEach((value) => {
  //   if (value > result) result = value;
  // });
  // return result;
}

const data = [1, 2, 3, 4, 5, 10, 5, 6, 7];
const result = max(...data);
console.log(result); // 10
