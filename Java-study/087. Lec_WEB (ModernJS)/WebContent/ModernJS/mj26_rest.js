/*
rest
rest는 생김새는 spread 랑 비슷한데, 역할이 매우 다릅니다.
 
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/rest_parameters
 
rest는 객체, 배열, 그리고 함수의 파라미터에서 사용이 가능합니다.
*/

// 객체에서의 rest

{
  const purpleCuteSlime = {
    name: '슬라임',
    attribute: 'cute',
    color: 'purple',
  };

  // 객체의 비구조화할당을 하면서 ...rest
  // rest문법일 뿐이지 ...이후 이름은 중요하지 않습니다.
  // 그저 변수 명입니다.
  const { color, ...rest } = purpleCuteSlime;
  console.log(color); // purple
  console.log(rest); // { name: '슬라임', attribute: 'cute' }

  // rest 는 나머지 것들로 만들어진 value 입니다.

  // 이름이 꼭 rest가 아니어도 됨.
  const { attribute, ...slime } = rest;
  console.log(attribute); // cute
  console.log(slime); // { name: '슬라임' }

  //   const {color: c, ...rest:other} = purpleCuteSlime;
  // ...rest:other  => rest 문법은 이름 변경 불가
  const { color: c, ...r } = purpleCuteSlime;
  console.log(c); // purple
  console.log(r); // { name: '슬라임', attribute: 'cute' }
}

// spread 가 다른 객체, 배열에 퍼뜨리는 역할(?) 을 한다면,
// rest 는 나머지들(?)을 모아오는 역할을 수행합니다.
console.log();

// 배열에서 rest 됩니다.
{
  const numbers = [0, 1, 2, 3, 4, 5, 6, 7];

  const [one, ...rest] = numbers;
  console.log(one); // 0
  console.log(rest); // [1, 2, 3, 4, 5, 6, 7]
  // index 0번째를 제외한 나머지들을 배열의 형태로 모아옵니다.

  const [One, Two, ...r] = numbers;
  console.log(One); // 0
  console.log(Two); // 1
  console.log(r); // [ 2, 3, 4, 5, 6, 7 ]
}

// 배열에서의 rest 문법
console.log();
{
  const animals = ['개', '고양이', '참새'];

  const [dog, ...any] = animals;
  console.log(dog); // 개
  console.log(any); // [ '고양이', '참새' ]
}

console.log('\n[프로그램 종료]\n\n\n\n\n\n\n');
