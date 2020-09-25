/*
  JS 는 for 가 복잡하게 많다.
    1. for()
    2. for...in 사용 : 'property(key/인덱스)' 를 반복한다.  주로 '객체(object)' 에 사용
    3. for...of 사용 : '값' 을 반복한다.  (ES6 등장)
        iterable 한 객체에 사용 (Array, Map, Set, String, TypedArray, arguments 객체 등을 포함)
        iterable 한 객체?  (크게 두가지)
            a. iterable 한 프로토콜을 자체적으로 내장한 객체들
                ex) 배열
            b. 커스텀하게 iterable 한 프로토콜을 가지게 설계한 객체들
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/for...of
 
 
    4. 배열.forEach(함수) : 주어진 함수를 배열요소 각각에 대해 실행
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach
*/

// 주어진 배열의 값을 x 2 한 배열 만들기
const data = [10, 20, 30, 40, 50];
const len = data.length;
const doggy = {
  name: '흰둥이',
  sound: '멍멍',
  age: 2,
};
let i, v;

// 1. for() 사용
let result = [];
for (let i = 0; i < len; i++) {
  result.push(data[i] * 2);
}
console.log('for', result);

// 2. for .. in 사용
result = [];
for (let index in data) {
  result.push(data[index] * 2);
}
console.log('for .. in', result);

// 3. for .. of 사용
result = [];
for (let value of data) {
  result.push(value * 2);
}
console.log('for .. of', result);

// iterable 한 객체에 대해서 반복 가능
let itr = 'boo';
for (let value of itr) {
  console.log(value);
}

// DOM 객체
// ex) document.getElementsByTagName(...);

// 4. .forEach() 사용
result = [];
data.forEach((index) => {
  result.push(index * 2);
});
console.log('forEach', result);

// 5. map
result = data.map((index) => index * 2);
console.log('map', result);

// for of : iterable 한 객체에 사용  ES6
// for in : property,  객체(object) 에 대해 사용

// iterable 한 객체?  (크게 두가지)
//  1. iterable 한 프로토콜을 자체적으로 내장한 객체들
//     ex) 배열
//  2. 커스텀하게 iterable 한 프로토콜을 가지게 설계한 객체들
//
for (const i of [1, 2, 3]) {
  console.log('i =', i);
}

for (const i in { a: 1, b: 2, c: 3 }) {
  console.log('i =', i);
}
// 마치 {a: 1, b: 2, c: 3} 안에 있는 a,b,c 만 나오는 듯하지만

// prototype 에 있는 것도 나올수 있기 때문에
// 의도치 않은 결과를 내뱉을수도 있다.
// (런타임 환경에 따라서 for..in.. 은 다르게 동작할수 있다)

Object.prototype.test = function () {};
console.log();
for (const i in { a: 1, b: 2, c: 3 }) {
  console.log('i =', i);
}
// i = a
// i = b
// i = c
// i = test // prototype으로 등록 된 것이 나올수 있습니다.

for (i in doggy) {
  console.log(i, doggy[i]);
}
