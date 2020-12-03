// for of : iterable 한 객체에 사용  ES6
// for in : property,  객체(object) 에 대해 사용

// iterable 한 객체?  (크게 두가지)
//  1. iterable 한 프로토콜을 자체적으로 내장한 객체들
//     ex) 배열
//  2. 커스텀하게 iterable 한 프로토콜을 가지게 설계한 객체들
//

for(const i of [1, 2, 3]){
    console.log('i =', i)
}

for(const i in {a: 1, b: 2, c: 3}){
    console.log('i =', i)
}
// 마치 {a: 1, b: 2, c: 3} 안에 있는 a,b,c 만 나오는 듯하지만

// prototype 에 있는 것도 나올수 있기 때문에
// 의도치 않은 결과를 내뱉을수도 있다.
// (런타임 환경에 따라서 for..in.. 은 다르게 동작할수 있다)
Object.prototype.test = function(){}
console.log()
for(const i in {a: 1, b: 2, c: 3}){
    console.log('i =', i)
}

