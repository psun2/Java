// 우선 함 보자
// 함수 '정의 먼저' 하고 '호출을 나중'
function hello1(){
    console.log('hello1')
}
hello1() // 


// 호출코드가 정의코드 보다 앞에 있다면 과연?

hello2()
function hello2(){
    console.log('hello2 실행될까요?')
}

// 변수의 경우는 ?

// 1)
// console.log('val_1 =', val_1)

// 2)
console.log('val_2 =', val_2)
var val_2

// 3)
console.log('val_3 =', val_3)
var val_3 = 10

// 4)
val_4++
console.log('val_4 =', val_4)
var val_4 = 20

// 5)
val_5 = 20
val_5++
console.log('val_5 =', val_5)
var val_5 = 40

//--------------------------------------------------------------------------------------

/*
    변수(함수) 선언이 위로 올라오는 증상   
    이러한 현상을 hoisting 이라 하며, 
   

   hoisting 
     아래에 있는 '선언'을(만) 끌어올린다.

   hoisting 때문에 동작의 오류처럼 보이는 증상 겪게 됨

   Hoisting
    https://developer.mozilla.org/ko/docs/Glossary/Hoisting

    'hoising' 현상은 JavaScript 처음부터 있어왔던 증상이다.
    그러나, 위 'hoisting' 이라는 용어 자체는 
    ES2015(ES6) 이전에는 사용되지 않았음
*/

/*
    var 는 hoisting 발생  → 많은 혼란을 야기 시켰다.
    let, const 는 hoisting 이 발생 안한다.
*/

console.log()

{
    console.log(`name = ${name}`)
    name = 'Mark'
    console.log(`name = ${name}`)
    var name
}

{
    // let, var 는 선언하기 전에는 사용 불가. hoisting 없다.
    //console.log(name)
    const name = "Mark"
}

// 함수 안에서는 ?
function myFunction(){
    console.log('myFunction() 호출')

    // 1. let, const 의 경우
    //console.log(name);  // 에러 
    const name = 'Mark';

    // 2. var 의 경우
    console.log(`nick = ${nick}`); // 에러는 아니다, undefined 가 나올뿐.
    var nick = 'Flash';
}
myFunction()

console.log()

{
    console.log(`age = ${age}`)
    let age = 30
    console.log(`age = ${age}`)
}