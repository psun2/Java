// 자료형 : data type

//  https://developer.mozilla.org/ko/docs/Web/JavaSCript/Data_structures

// 동적타이핑 (dynamic type)
// 변수는 고정타입이 없다

let whatever = 'Mark';  // string 타입
console.log(typeof whatever);
whatever = 37;   // number 타입
console.log(typeof whatever );
whatever = true;  // boolen 타입
console.log(typeof whatever );

// runtime 에 개발자가 모르는 어떤 타입의 값이
// 들어올수 있다  주의!

// 데이터 타입
// 1.기본타입 (primitive)
//  -- boolean
//  -- null
//  -- undefined
//  -- number
//  -- string
//  -- symbol (ES6 에 추가)
//
// 2. 객체 (object)
//   ..

//-----------------------------
// boolean 타입
// true 혹은 false 값
{
    console.log('\nboolean...')

    const b1 = true
    const b2 = false

    console.log(`b1 = ${b1}`, typeof b1)
    console.log(`b2 = ${b2}`, typeof b2)

    const b3 = new Boolean(false)  // false 값을 갖는 Boolean 객체 object
    console.log(`b3 = ${b3}`, typeof b3)

    if(b2) console.log(b2, '참입니다')
     else  console.log(b2, '거짓입니다')

    if(b3) console.log(b3, '참입니다')
     else  console.log(b3, '거짓입니다')

    if(b2 == b3)
        console.log(' == 참입니다')
    else   
        console.log(' == 거짓입니다')

    if(b2 === b3)
        console.log(' === 참입니다')
    else   
        console.log(' === 거짓입니다')


}

//-------------------------------------------------
// symbol
// ES6 부터 출현
// https://developer.mozilla.org/ko/docs/Glossary/Symbol

// '고유'한 값을 만들어낼때 사용

console.log('\nsymbol')
{
    const a = Symbol()    // new 를 사용하면 안된다.
    const b = Symbol(37)  // Symbol(값)
    const c = Symbol('Mark')
    const d = Symbol('Mark') // c, d 는 같은 것이 아니다!
                            // 같은 인자로 생성되었어도
                            // symbol은 '고유'한 객체로 
                            // 생성된다.

    console.log(`a =`, a, typeof a)
    console.log(`b =`, b, typeof b)
    console.log(`c =`, c, typeof c)
    console.log(`d =`, d, typeof d)

    console.log(c == d)  // false
    console.log(c === d) // false


}





























