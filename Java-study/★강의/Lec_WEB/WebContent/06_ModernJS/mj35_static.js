//---------------------------------------------
// static 변수, 함수
// 클래스의 변수와 함수에 적용되는 키워드
//  new 없이도 사용가능하다.  -->  클래스이름.이름

class A {
    static age = 37;
    static hello() {
        console.log(A.age);  // 
    }
}

console.log(A);
console.log(A.age);
A.hello();

console.log()

class B{
    age = 37; // non-static

    static hello(){
        console.log(this.age)
    }

}

console.log(B)
console.log(B.age)
B.hello()

console.log(new B().age)

//console.log(new B().hello())
//new B().hello();  // 에러!  으잉?
            // TypeError: (intermediate value).hello is not a function
            // static 은 new 생성된 객체로 사용 불가!
            // ★static 은 객체에 소속된 멤버가 아니다★



// static name <-- 클래스 이름을 뜻한다!
 class C {
    nick = '브라운'
    static age = 20
    static name = '찰리'
 }

 console.log(C)






