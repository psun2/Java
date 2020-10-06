// JAVA
//     non-static -> instance 변수, instance 메소드
//     static     -> 클래스 변수, 클래스 메소드
//                  1. 단 하나 생성, 메소드영역에 생성
//                       프로그램 시작시 생성 (클래스 로딩)
//                  2. 인스턴스 없이(new없이 사용가능)
//                        ?? 클래스이름.static변수/메소드
//                        System.out.println()
//                  3. 모든 인스턴스가 공유.

//-----------------------------------------------------------
// getter & getter
// 일반적으로 클래스 '내부적으로만' 사용하는 변수명을
// _name 과 같이 언더바로 작명하는 경우 많음
// _ 언더바로 시작하는 변수명에 readonly 동작시킬수 있는 것이 있슴.
// 이런 용도의 멤버변수를 외부에서 접근할때 getter, setter를 통해 접근

class A {
  age = 37;
  static _age = 37;

  static hello() {
    console.log(A._age);
  }
}

console.log(A);
console.log(A.age); // undefined
console.log(A._age); // 37
A.hello(); // 37

console.log();

class B {
  age = 37; // non-static

  static hello() {
    console.log(this.age);
  }
}
console.log(B);
console.log(B.age); // undefined
B.hello(); // undefined

console.log(new B().age); // 37

// console.log(new B().hello());

//new B().hello();  // 에러!  으잉?
//                     TypeError: (intermediate value).hello is not a function
//                     static 은 new 생성된 객체로 사용 불가!
//                     ★static 은 객체에 소속된 멤버가 아니다★

console.log();

// static name <-- 클래스 이름을 뜻한다!
class C {
  nick = '브라운';
  static age = 20;
  static name = '찰리';
}
console.log(C); // [class: C] { age: 20 }
console.log(C); // [class: 찰리] { age: 20, name: '찰리' }
