//------------------------------------------
//  함수의 기본 파라미터 (default function parameter)
//  https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/Default_parameters


function add(a, b = 0){
    console.log('a =',a, ', b =', b)
    return a + b
}

let sum;

sum = add(1, 2)
console.log('sum =', sum)

sum = add(10)
console.log('sum =', sum)

console.log

let calcCircleArea = function(r){
    return Math.PI * r * r
}

console.log(calcCircleArea(10))

console.log(calcCircleArea())  // # NaN

calcCircleArea = function(r = 1){
    return Math.PI * r * r
}

console.log(calcCircleArea())  //?

// SCE로 사용 가능.
calcCircleArea = function(r){
    r = r || 10
    return Math.PI * r * r
}
console.log(calcCircleArea())

// 화살표 함수도 default parameter 사용 가능
calcCircleArea = (r = 100) => Math.PI * r * r;
console.log(calcCircleArea())