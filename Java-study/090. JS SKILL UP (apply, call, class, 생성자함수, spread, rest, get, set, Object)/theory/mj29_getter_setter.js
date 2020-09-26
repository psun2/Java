/* object 의 getter, setter
 
getter 함수: 특정 값을 조회할때마다
setter 함수: 특정 값을 설정할때마다
 
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/get
 
ES 5.1 에 최초 정의
https://www.ecma-international.org/ecma-262/5.1/#sec-11.1.5
 
ES 6 에서 계산된 프로퍼티 이름이 추가
https://www.ecma-international.org/ecma-262/6.0/#sec-method-definitions
 
 
JS 에서 매우 유용
 
*/

const numbers = {
  _a: 1,
  _b: 2,

  // getter 함수
  get sum() {
    // get 키워드로 시작
    console.log('sum getter 함수가 실행됩니다');
    // 반드시 무엇인가 리턴해주도록 하자.
    return this._a + this._b; // return 이 없으면 undefined
  },
  get a() {
    return this._a;
  },
  get b() {
    return this._b;
  },
};
// numbers.sum(); // TypeError: numbers.sum is not a function at Object.<anonymous>

// getter 함ㅅ누는 아래와 같이 '조회' 하려 할때 사용합니다.
// 해당 코드가 호출됩니다.
console.log(numbers.sum); // 3
numbers._b = 5;
console.log(numbers.sum); // 6
console.log(numbers._b); // 5

// setter 함수
const dog = {
  _name: '멍멍이',

  // setter
  set name(value) {
    // 반드시 parameter 가 설정되 있도록 합니다.
    console.log('이름이 바뀝니다..', value);
    this._name = value;
  },

  get name() {
    return this._name;
  },
};

// setter 는 값을 'write/update' 동작을 할때 호출됩니다.
console.log(dog.name); // 멍멍이
dog.name = '흰둥이'; // setter name() 호출
console.log(dog.name); // 흰둥이
// dog.name('갈둥이'); // TypeError: dog.name is not a function at Object.<anonymous>

//---------------------------------------

const numbers2 = {
  _a: 1,
  _b: 2,
  sum: 3,

  calculate() {
    console.log('calculate() 호출');
    this.sum = this._a + this._b;
  },

  get a() {
    return this._a;
  },
  get b() {
    return this._b;
  },
  set a(value) {
    this._a = value;
    this.calculate(); // sum 값 업데이트
  },
  set b(value) {
    this._b = value;
    this.calculate(); // sum 값 업데이트
  },
};

console.log(numbers2.sum);
numbers2.a = 5;
numbers2.b = 7;
console.log(numbers2.sum);

// 여러번 조회해도 이전에 이미 계산된 값을
// 조회하는 것이기 때문에 재계산을 하지 않는다.
console.log(numbers2.sum);
console.log(numbers2.sum);

// 다르게 동작하게 하는 경우
const number3 = {
  _a: 1,
  _b: 2,
  get sum() {
    console.log('sum() 호출 : 연산수행');
    return this._a + this._b;
  },
};

console.log(number3.sum);
number3._a = 100;
number3._b = 200;
console.log(number3.sum);
console.log(number3.sum);
