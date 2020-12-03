// 정의 매개변수 vs 호출시 인자의 개수

function add(a, b){
    console.log(`add(${a}, ${b}) 호출`)
    return a + b
}

let sum

sum = add(1, 2)
console.log('sum =', sum)

sum = add(10, 20, 50) // ?? 호출시 인자가 더 많은 경우
console.log('sum =', sum)

sum = add(10)  // ?? 호출시 인자가 더 적은 경우
console.log('sum =', sum) // NaN


function add(a, b){
    console.log(`add(${a}, ${b}) 호출2 허걱`)
    return a + b
}

sum = add(100, 200)
console.log('sum =', sum)

// function 은 hoisting 이 발생한다!!!!!!! 

console.log()
// 함수 만드는 방법
{
    // 방법1 : function + 이름있는 함수
    function hello1(){
        console.log('hello1')
    }
    hello1()
    console.log(hello1, typeof hello1)

    // 방법2 : var + 이름없는 함수
    var hello2 = function(){
        console.log('hello2')
    }
    hello2()

    // 방법3 : let, const + 이름없는 함수
    const hello3 = function(){
        console.log('hello3')
    }
    hello3()    
}

// 블럭밖
console.log('\n블럭 밖 호출')
hello1()
hello2()
// hello3()  let, const 는 block scope

console.log()
// 두가지 방법의 차이점은?
{
    function hello4(){
        console.log('hello4');
    }
    hello4()
 
    hello5()  // 문제없이 실행된다! hoisting!
    function hello5(){
        console.log('hello5');
    }
}
 
console.log();
{  
    console.log(hello6)   // [Function: hello6]
    console.log(hello7)   // undefined

    // #1 function 사용
    function hello6(){
        console.log('hello6');
    }
    hello6();

    // #2  익명함수(이름없는 함수) 사용하는 경우.
    //hello7(); //  에러
    var hello7 = function(){
        console.log('hello7');
    }
 
    // #3
    // const 의 경우는?
    //hello8()  // 에러
    const hello8 = function(){
        console.log('hello8');
    }
}
 














