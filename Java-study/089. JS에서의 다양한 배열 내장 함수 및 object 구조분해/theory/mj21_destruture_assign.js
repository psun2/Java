// Destructuring Assigment (ES6 에서 등장) 문법
// '비구조화할당' 혹은 '구조분해할당' 이라고 번역이 됨.
// 객체변수.속성  <-- 좀더 편리하게 다룰수 있다.
// '비구조화할당 문법'
//   다른말로 '객체 구조 분해'

// 공식
// https://www.ecma-international.org/ecma-262/6.0/#sec-destructuring-assignment
// https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment
// 추가 참조
// https://velog.io/@public_danuel/destructuring-assignment
// 비구조화 할당, 잘 사용하면 코드 깔끔해진다

const ironMan = {
  name: '스타크',
  actor: '로다주',
  alias: '아이언맨',
};

const captainAmerica = {
  name: '스티브',
  actor: '크리스에반스',
  alias: '캡틴그놈',
};

//---------------------------------------------
// 객체의 구조분해
{
  let { actor, name } = ironMan;
  console.log(`배우: ${actor}, 이름: ${name}`);

  // 해당 property 명으로 해야한다.
  // 그렇지 않으면 undefined
  let { ooo, qqq } = captainAmerica;
  console.log(`배우: ${ooo}, 이름: ${qqq}`);
}

{
  // object 정의한 순서대로 나열할 필요는 없다
  let { alias, name, age } = ironMan;
  console.log(`별칭: ${alias}, 이름: ${name}, 나이: ${age}`);
}

console.log();

{
  // 함수의 파라미터에서도 비구조화 할당
  let print = function ({ a, b }) {
    console.log(`print(a=${a}, b=${b}) 호출`);
  };
  let data = { a: 10, b: 20 };
  print(data); // 호출시점에서 구조분해 발생!
  // print(a=10, b=20) 호출

  print(ironMan);
  // print(a=undefined, b=undefined) 호출

  data = { a: 10 };
  print(data);
  // print(a=10, b=undefined) 호출

  console.log();
}

// 위와 같이
// 분해할 값이 없을때 기본값을 적용하려면?

// 1. SCE
print = function ({ a, b }) {
  b = b || 'default 값: 2'; // b의 기본값은 2
  console.log(`print(a=${a}, b=${b}) 호출`);
};
print({ a: 10 });

// 2. default parameter 사용 (제대로 된 방법!)
print = function ({ a, b = 2 }) {
  console.log(`print(a=${a}, b=${b}) 호출`);
};
print({ a: 10 }); // print(a=10, b=2) 호출

print({ a: 10, b: 25 }); // print(a=10, b=25) 호출

// default parameter 는 함수에서만 사용할수 있는 것은 아니다.
console.log();
let { c, d } = { c: 111, e: 222 };
// console.log(`c=${c}, d=${d}, e=${e}`); // Error
console.log(`c=${c}, d=${d}`); // c=111, d=undefined

let { e, f = 30 } = { c: 111, e: 222 };
console.log(`e=${e}, f=${f}`); // e=222, f=30

//----------------------------------------------------------------------
console.log();

// 비구조화 할당시 이름 바꾸기
// 이번에는, 비구조화 할당을 하는 과정에서
// 선언 할 값의 이름을 바꾸는 방법을 알아보겠습니다.
{
  const animal = {
    name: '멍멍이',
    type: '개',
  };

  let nickname = animal.name;

  // 이를 비구조화 할당으로 하면 아래와 같이 가능
  // 이름을 바꾸어 줄수도 있다!

  let { name: nickname2 } = animal;
  // animal.name 을 nickname2 에 할당
  // 원래변수이름: 새로운변수운이름
  console.log('nickname2 =', nickname2);
}

//--------------------------------------
//배열 비구조화 할당
//비구조화 할당은 객체에만 할 수 있는 것이 아닙니다.
// 배열에서 할 수 있어요.
{
  console.log('\n배열의 비구조화 할당');

  const array = [1, 2];

  let [one, two] = array; // 배열원소 순서대로 구조분해
  console.log(one, two); // 1 2

  let [a, b, c] = array;
  console.log(a, b, c); // 1 2 undefined

  let [x, y, z = 3] = array;
  console.log(x, y, z); // 1 2 3

  // 배열 비구조화 할당 사용하면 변수값 교환(swap) 가능!
  let aaa = 10,
    bbb = 30;

  // temp = aaa;
  // aaa = bbb;
  // bbb = temp;

  console.log(`aaa=${aaa}, bbb=${bbb}`);
  [aaa, bbb] = [bbb, aaa]; // 변수값 교환
  console.log(`aaa=${aaa}, bbb=${bbb}`);
}

{
  //--------------------------
  // 깊은 값 비구조화 할당
  // 객체의 깊숙한 곳에 들어있는 값을 꺼내는 방법
  // Nested object and array destructuring
  //  https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment#Nested_object_and_array_destructuring

  console.log('\n깊은값 비구조화 할당');

  const deepObject = {
    state: {
      information: {
        name: 'frogteam',
        languages: ['korean', 'english', 'chinese'],
      },
    },
    value: 5,
  };

  //  name, languages, value 값들을 밖으로 꺼내주고 싶다면
  // 어떻게 해야 할까요? 이럴땐 두가지 해결 방법이 있는데요

  // 1. 비구조화할당 문법을 두번사용
  const { name, languages } = deepObject.state.information;
  const { value } = deepObject;

  console.log(name, languages, value);

  // object 를 아래와 같이 정의해도 된다!
  // ES6 의 object-shorthand 문법 (단축 속성명)이라고 부릅니다. (이름은 굳이 알아둘 필요는 없습니다..!)
  // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Object_initializer

  const extracted = {
    name, // name : name
    languages, // languages : languages
    value, // value : value
  };
  console.log(extracted);

  // 2. object-shorthand + 구조 분해
  {
    // const {
    //   state: {
    //     information: { name: value },
    //   },
    // } = deepObject;
    // console.log('value =', value);
    // const name = value;
    // console.log('name =', name);
    const {
      state: {
        information: { name, languages },
      },
      value,
    } = deepObject;

    const extracted = {
      name,
      languages,
      value,
    };

    console.log(extracted);
  }

  {
    const {
      state: {
        information: {
          name,
          languages: [firstLang, secondLang, thirdLang],
        },
      },
      value,
    } = deepObject;

    const extracted = {
      name,
      languages,
      firstLang,
      secondLang,
      thirdLang,
      value,
    };

    console.log(extracted);
  }

  // 코드가 더 더러워진 느낌... 가능하다는 것만 알아두자.
  // 차라리 1번째 방법처럼 여러번 걸쳐 구조분해하는게 낳을수도 있다.
  // 상황에 따라, 선호도에 따라 사용
}

console.log('\n[프로그램 종료]\n\n\n\n\n\n\n\n\n\n\n\n');
