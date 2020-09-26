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
    name: '슬라임',
  };

  const cuteSlime = {
    name: '슬라임',
    attribute: 'cute', // property 추가
  };

  const purpleCuteSlime = {
    name: '슬라임',
    attribute: 'cute',
    color: 'purple', // property 추가
  };

  console.log(slime);
  console.log(cuteSlime);
  console.log(purpleCuteSlime);
  //   { name: '슬라임', attribute: 'cute', color: 'purple' }
}

// 기존의 객체에서 만들어진 속성을 사용하여
// 새로운 객체를 만들때 spread 를 사용하면 편리
// ...  : spread 연산자
console.log();
{
  const slime = {
    name: '슬라임',
  };

  const cuteSlime = {
    ...slime, // deep copy
    attribute: 'cute', // property 추가
  };

  const purpleCuteSlime = {
    ...cuteSlime, // deep copy
    color: 'purple', // property 추가
  };

  console.log(slime);
  console.log(cuteSlime);
  console.log(purpleCuteSlime);
  //   { name: '슬라임', attribute: 'cute', color: 'purple' }

  console.log(slime === cuteSlime); // false : 다른 객체 입니다.

  const greenCuteSlime = {
    ...purpleCuteSlime, // 이미 color 값이 있지만...
    color: 'Green', // color 값을 덮어 씌웁니다.
  };
  console.log(greenCuteSlime);
  // { name: '슬라임', attribute: 'cute', color: 'Green' }

  const pinkCuteSlime = {
    color: 'Pink', // 나중에 spread
    ...greenCuteSlime,
    // 나중에 덮어쓴 값이 color 값이 된다.
    // === Green
  };
  console.log(pinkCuteSlime);
  //   { color: 'Green', name: '슬라임', attribute: 'cute' }
}

// 만약 spread 를
// 사용하지 않으면 어떨까?
console.log();
{
  const slime = {
    name: '슬라임',
  };
  console.log(slime);
  // { name: '슬라임' }

  const cuteSlime = slime; // reference 참조 가 됩니다.
  cuteSlime.attribute = 'cute';
  console.log(cuteSlime);
  // { name: '슬라임', attribute: 'cute' }

  const purpleCuteSlime = cuteSlime; // reference 참조 가 됩니다.
  purpleCuteSlime.color = 'purple';
  console.log(purpleCuteSlime);
  // { name: '슬라임', attribute: 'cute', color: 'purple' }

  console.log();
  console.log(slime);
  // { name: '슬라임', attribute: 'cute', color: 'purple' }
  console.log(cuteSlime);
  // { name: '슬라임', attribute: 'cute', color: 'purple' }
  console.log(purpleCuteSlime);
  // { name: '슬라임', attribute: 'cute', color: 'purple' }
  console.log(slime === purpleCuteSlime); // true
  // 결국 같은 객체다.
  // reference 참조가 되어 결국 shallcopy 가 됩니다.
}

// spread 연산자는 배열에서도 사용 할 수 있다.
{
  const animals = ['개', '고양이', '참새'];
  const anotherAnimals = [...animals, '비둘기'];

  console.log('animals =', animals);
  console.log('anotherAnimals =', anotherAnimals);
  // anotherAnimals = [ '개', '고양이', '참새', '비둘기' ]

  const anotherAnimals2 = animals.concat(['비둘기']);
  console.log('anotherAnimals2 =', anotherAnimals2);
  // anotherAnimals2 = [ '개', '고양이', '참새', '비둘기' ]
}

// spread 연산자 여러번 사용 가능.
{
  const numbers = [1, 2, 3, 4, 5];
  const newNumbers = [...numbers, 1000, ...numbers];
  console.log(newNumbers);
  const newNewNumbers = [...numbers, ...newNumbers, 999, 9999];
  console.log(newNewNumbers);
}
