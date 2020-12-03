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
const data = [10, 20, 30, 40, 50]
const len = data.length
const doggy = {
    name: "흰둥이",
    sound: "멍멍",
    age: 2
}
let i, v


// 1. for() 사용
let result = []
for(let i = 0; i < len; i++){
    result.push(data[i] * 2)
}
console.log(result)

// 2. for .. in 사용
result = []
for(i in data){   // i <- 0, 1, 2 ...
    result.push(data[i] * 2)
}
console.log(result)

// 3. for .. of 사용
result = []
for(v of data){  // v <- 10, 20, 30 ...
    result.push(v * 2)
}
console.log(result)

// iterable  한 객체에 대해서 반복 가능
let itr = "boo";
for(let ch of itr){
    console.log(ch)
}

// DOM객체
// ex)  document.getElementsByTagName(...)


// 4. .forEach() 사용
result = []
data.forEach(element => {
    result.push(element * 2)
})
console.log(result)