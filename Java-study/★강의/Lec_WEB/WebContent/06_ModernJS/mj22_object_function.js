
// object 안의 함수 정의하는 다양한 방법들.


// ES6 에선 object 에서 메소드 정의를 위한 단축구문이 도입
//  
//  ex ) foo : function() {}  
//         => foo() {}   단축구문
// 
//  https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/Method_definitions


const dog = {
    name : "멍멍이"
    , sound : "왈왈!"

    // 방법1
    , say1 : function aaa(){
        console.log(`say1: ${this.sound}`)
    }

    // 방법 2 : 이름없는(anonymous) 함수
    , say2 : function(){
        console.log(`say2: ${this.sound}`)
    }

    // 방법3 : 메소드 정의 단축구문 (ES6)
    , say3(){
        console.log(`say3: ${this.sound}`)
    }


    // 방법 4 : 화살표함수 
    , say4 : () => {
        console.log(`say4: ${this.sound}`) // 동작안한다? 왜?
    }

    // 이유
    // function 키워드로 만든 함수에서의 this는
    // 자기가 속해있는, 객체를 가리키는데..
    // 화살표 함수의 경우
    // this를 자기가 속해 있는 곳으로
    // 연결하지 않습니다. (binding 발생하지 않는다.)
    // 화살표 함수는 this 가 뭔지 모릅니다!
}

dog.say1()
// dog.aaa()  // TypeError: dog.aaa is not a function

dog.say2()
dog.say3()
dog.say4()  // undefined

const cat = {
    name: "야옹이"
    , sound: "냐앙~"
}

cat.say1 = dog.say1

console.log()
dog.say1()
cat.say1()

const catSay = cat.say1
catSay()  // this에 연결(binding) 된게 없다. !







