/*
splice
splice 는 배열에서 특정 항목을 제거할 때 사용합니다.
splice() 는 제거된 값들의 배열 리턴함
*/

let numbers = [10, 20, 30, 40];

// 위 배열에서 30을 지우려 한다면?
numbers.splice(2, 1); // 배열 인덱스 2 부터 1개를 제거
// ❗❗❗❗ 원본 numbers 가 변화되었다
console.log('numbers =', numbers); // [ 10, 20, 40 ]

numbers = [10, 20, 30, 40];
numbers.splice(2, 2); // 인덱스 2부터 2개를 제거
console.log('numbers =', numbers); // [ 10, 20 ]

console.log();

// splice() 는 '제거된 값' 들의 배열을 리턴
numbers = [10, 20, 30, 40];
let spliced = numbers.splice(2, 2); // 인덱스 2부터 2개를 제거
console.log('spliced =', spliced); // [ 30, 40 ]
console.log('numbers =', numbers); // [ 10, 20 ]

console.log();
/*
slice
slice 는 splice 랑 조금 비슷한데요, 
배열을 잘라낼 때 사용하는데, 
중요한 점은 '기존의 배열'은 건드리지 않는 다는 것입니다.
*/
numbers = [10, 20, 30, 40];
let sliced = numbers.slice(1, 3); // 인덱스 1부터 3 전까지
console.log('sliced =', sliced); // [ 20, 30 ]
// ❗❗❗❗ 원본데이터 변화 없다!
console.log('numbers =', numbers); // [ 10, 20, 30, 40 ]
