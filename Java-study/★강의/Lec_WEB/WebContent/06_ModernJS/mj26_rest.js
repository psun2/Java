/*
rest
rest는 생김새는 spread 랑 비슷한데, 역할이 매우 다릅니다.
 
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/rest_parameters
 
rest는 객체, 배열, 그리고 함수의 파라미터에서 사용이 가능합니다.
*/
 
// 객체에서의 rest
 
{
    const purpleCuteSlime = {
      name: "슬라임",
      attribute: "cute",
      color: "purple"
    };

    // 객체 비구조화할당을 하면서 ...rest 를 넣으면
    const {color, ...rest} = purpleCuteSlime
    console.log(color) // purple
    console.log(rest) // color 를 제외한 나머지 것들

    // 이름이 꼭 rest가 아니어도 됨.
    const {attribute, ...slime} = rest
    console.log(attribute)
    console.log(slime)    //{ name: '슬라임' }

    const {color: c, ...r} = purpleCuteSlime
    console.log(c)
    console.log(r)

}


// spread 가ㅣ 다른 객체, 배열에 퍼뜨리는 역할(?)을 한다면,
// rest 는 나머지들(?) 모아오는 역할.
console.log()

// 배열에서 rest 된다.
{
    const numbers = [0, 1, 2, 3, 4, 5, 6]

    const [one, ...rest] = numbers
    console.log(one)
    console.log(rest) // index 0 번째를 제외한 나머지들 모아옴

    const [One, Two, ...r] = numbers
    console.log(One)
    console.log(Two)
    console.log(r)

    // ...rest 는 반드시 마지막에 와야 한다
    //const[...Rest, last] = numbers  // SyntaxError: Rest element must be last element

}



console.log("\n[프로그램 종료]\n\n\n\n")














  
  