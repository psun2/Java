/*
클래스
클래스라는 기능은 C++, Java, C#, PHP 등의 
다른 프로그래밍 언어에는 있는 기능인데 
 
자바스크립트에는 없었기 때문에 
예전 자바스크립트 (ES5) 에서는 
클래스 문법이 따로 없었기 때문에 위에서 작성한 코드처럼 
객체 생성자 함수를 사용하여 비슷한 작업을 구현해왔습니다.
 
ES6 에서부터는 class 라는 문법이 추가되었는데요, 
우리가 객체 생성자로 구현했던 코드를 조금 더 명확하고, 
깔끔하게 구현 할 수 있게 해줍니다. 
 
추가적으로, 상속도 훨씬 쉽게 해줄 수 있습니다.
 
  https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Classes
*/

class Animal {
  // 생성자
  constructor(type, name, sound) {
    this.type = type;
    this.name = name;
    this.sound = sound;
  }

  // 메소드 정의
  // 자동으로 prototype 에 등록 됩니다.
  say() {
    console.log(this.sound);
  }
}

let dog = new Animal('개', '멍멍이', '멍멍');
let cat = new Animal('고양이', '야옹이', '야옹');

dog.say();
cat.say();
console.log(dog);
console.log(cat);

console.log();
// 클래스 상속!
class Dog extends Animal {
  constructor(name, sound, height) {
    // super() 호출, 부모생성자 먼저 호출해야 합니다.
    // JS 는 super() 즉 부모를 부르는(.call())은 반드시 명시 되어야 합니다.
    super('개', name, sound);
    this.height = height;
  }
}

class Cat extends Animal {
  constructor(name, sound, height) {
    super('고양이', name, sound);
    this.height = height;
  }

  grrr() {
    console.log(this.name, '그르릉 거리다');
  }
}

dog = new Dog('멍멍이!', '멍멍!', 132.2);
cat = new Cat('야옹이~~', '야옹~~', 99.25);

dog.say();
cat.say();

cat.grrr();

console.log(dog);
console.log(cat);
