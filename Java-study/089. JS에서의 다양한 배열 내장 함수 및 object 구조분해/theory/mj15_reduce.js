/*
reduce
reduce 함수는 잘 사용 할 줄 알면 정말 유용한 내장 함수입니다. 
 
 reduce(함수)
 reduce(함수, 초깃값)
 
 위 함수는
 (accumulator, current) => 누적계산결과
 (accumulator, current, index, array) => 누적계산결과
     - index: 현재 current 가 몇번째 인지 
     - array: 함수를 실행하는 배열 자신 
 
만약 여러분이 주어진 배열에 대하여 총합을 구해야 하는 상황이 왔다고 가정해봅시다.
 
 
 
이렇게 구현을 할 수 있을텐데요
*/

let numbers = [1, 2, 3, 4, 5];
let result, sum;

// .forEach() 사용
sum = 0;
numbers.forEach((n) => (sum += n));
console.log(sum);

// reduce 사용
sum = numbers.reduce((acc, cur) => acc + cur);
console.log(sum); // 15
// [1, 2, 3, 4, 5]
//  ↘ ↓
//     3, 3, 4, 5
//     ↘ ↓
//        6, 4, 5
//        ↘ ↓
//          10, 5
//           ↘ ↓
//             15

sum = numbers.reduce((accumulator, current) => (accumulator += current), 0);
console.log(sum);

console.log();

// 초깃값 사용
sum = numbers.reduce((acc, cur) => acc + cur, 0);
console.log(sum);
// [0, 1, 2, 3, 4, 5]   초깃값 0 부터 시작
//   ↘↓
// [   1, 2, 3, 4, 5]
//      ↘↓
// [      3, 3, 4, 5]
//         ↘↓
// [         6, 4, 5]
//            ↘↓
// [           10, 5]
//               ↘↓
// [              15]

sum = numbers.reduce((accumulator, current) => (accumulator += current), 5);
console.log(sum);

console.log();

// reduce 가 진행되는 중관과정 확인해보기
sum = numbers.reduce((acc, cur, index, array) => {
  console.log(`${index} : ${acc} + ${cur} [${array}]`);

  return acc + cur;
});
console.log(sum);
// 1 : 1 + 2 [1,2,3,4,5]
// 2 : 3 + 3 [1,2,3,4,5]
// 3 : 6 + 4 [1,2,3,4,5]
// 4 : 10 + 5 [1,2,3,4,5]
// 15

sum = numbers.reduce((acc, cur, index, array) => {
  console.log(`${index} : ${acc} + ${cur} [${array}]`);

  return acc + cur;
}, 0);
console.log(sum);
// 0 : 0 + 1 [1,2,3,4,5]
// 1 : 1 + 2 [1,2,3,4,5]
// 2 : 3 + 3 [1,2,3,4,5]
// 3 : 6 + 4 [1,2,3,4,5]
// 4 : 10 + 5 [1,2,3,4,5]
// 15

// 도전
// reduce 를 사용하여 평균 구하기
console.log();
// ❗❗❗❗❗나누기 5씩한 값을 누적하여 덧셈하기때문에 결국엔 평균 값이 나옴...
let avg = numbers.reduce((acc, cur, index, arr) => acc + cur / arr.length, 0);
console.log('avg =', avg);

console.log();
avg = numbers.reduce((acc, cur, index, array) => {
  // 마지막 원소인 경우만 평균을 리턴하면 된다.
  if (index === array.length - 1) {
    return (acc + cur) / array.length;
  }
  return acc + cur;
});
console.log('avg =', avg);

//------------------------------------------------
//  원래 reduce : n -> 1
//  그러나 다음의 동작도 가능하다.

// 각각의 원소에 x 2을 한 배열 구하기 (응? 이건 map 아닌가?)
result = numbers.reduce((acc, cur) => {
  acc.push(cur * 2);
  return acc;
}, []);

// []
// [2]
// [2, 4]
// [2, 4, 6]
// [2, 4, 6, 8]
// [2, 4, 6, 8, 10]

console.log(result);

// reduce() 강력하다 !
