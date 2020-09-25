/*
  주어진 배열 numbers 에서
  3 보다 큰 원소들로만 이루어진 배열을 만들어서 출력
 */

const numbers = [1, 2, 3, 4, 5, 6, 7];
const leng = numbers.length;

let result = [];
// 1. for
for (let i = 0; i < leng; i++) {
  numbers[i] > 3 ? result.push(numbers[i]) : numbers[i];
}
console.log('for :', result);

// 2. for .. in
result = [];
for (index in numbers) {
  numbers[index] > 3 ? result.push(numbers[index]) : numbers[index];
}
console.log('for .. in :', result);

// 3. for .. of
result = [];
for (value of numbers) {
  value > 3 ? result.push(value) : value;
}
console.log('for .. of :', result);

// 4. forEach
result = [];
numbers.forEach((num) => (num > 3 ? result.push(num) : num));
console.log('forEach :', result);
