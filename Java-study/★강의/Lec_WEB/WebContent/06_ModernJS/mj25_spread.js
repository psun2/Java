/*
spread 와 rest
ES6 에서 도입된 spread 와 rest 

spread
객체 혹은 배열을 펼칠수있습니다.
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Spread_syntax

기존 객체를 '복사'하고, 그리고 뭔가 더 추가하고자 할때 사용
*/
{
    const slime = {
        name: "슬라임"
    }

    const cuteSlime = {
        name: "슬라임",
        attribute: "cute"  // property 추가
    }

    const purpleCuteSlime = {
        name: "슬라임",
        attribute: "cute",
        color: "purple"    // property 추가
    }

    console.log(slime)
    console.log(cuteSlime)
    console.log(purpleCuteSlime)
}

// 기존의 객체에서 만들어진 속성을 사용하여
// 새로운 객체를 만들때 spread 를 사용하면 편리
// ...  : spread 연산자
console.log();
{
    const slime = {
        name: "슬라임"
    }
    
    const cuteSlime = {
        ...slime,
        attribute: "cute"
    }

    const purpleCuteSlime = {
        ...cuteSlime,
        color: "purple"
    }

    console.log(slime)
    console.log(cuteSlime)
    console.log(purpleCuteSlime)

    console.log(slime === cuteSlime) // false : 다른 객체다


    const greenCuteSlime = {
        ...purpleCuteSlime, // 이미 color 값이 있지만..
        color: "Green"   // color 값을 덮어쓴다 
    }
    console.log(greenCuteSlime)

    const pinkCuteSlime = {
        color: "Pink",
        ...purpleCuteSlime  // 나중에 spread
        // 나중에 덮어쓴 값이 color 값이 된다.
    }
    console.log(pinkCuteSlime)

}

// 만약 spread 를
// 사용하지 않으면 어떨까?
console.log();
{
  const slime = {
    name: "슬라임"
  };
  console.log(slime)

  const cuteSlime = slime;  // cuteSlime 은 slime 을 '참조' 하는 형태
  cuteSlime.attribute = "cute"
  console.log(cuteSlime)

  const purpleCuteSlime = cuteSlime;
  purpleCuteSlime.color = "purple"
  console.log(purpleCuteSlime)

  console.log()
  console.log(slime)
  console.log(cuteSlime)
  console.log(purpleCuteSlime)

  console.log(slime === cuteSlime)  // true
        // 결국 같은 객체다

}

console.log()
// sperad 연산자는 배열에서도 사용 할 수 있다.
{
    const animals = ["개", "고양이", "참새"]
    const anotherAnimals = [...animals, "비둘기"]

    console.log(animals)
    console.log(anotherAnimals)

    const anotherAnimals2 = animals.concat(['비둘기'])
    console.log(anotherAnimals2)
}

// spread 연산자 여러번 사용 가능.
{
    const numbers = [1, 2, 3, 4, 5]
    const newNumbers = [...numbers, 1000, ...numbers]
    console.log(newNumbers)
}










