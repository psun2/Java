
{
    console.log("상속을 사용하지 않는 경우")

    function Dog(name, sound){
        this.type = "개"
        this.name = name
        this.sound = sound
    }

    function Cat(name, sound){
        this.type = "고양이"
        this.name = name
        this.sound = sound
    }

    Dog.prototype.say = function(){
        console.log(this.sound)
    }

    Cat.prototype.say = function(){
        console.log(this.sound)
    }

    let dog = new Dog("멍멍이", "멍멍")
    let cat = new Cat("야옹이", "야옹")

    dog.say()
    cat.say()

    // 동일한 코드가 중복되는 상황 -->
    //   상속을 사용해보자
}

//-------------------------------------
console.log('\n상속을 사용 하는 경우');
function Animal(type, name, sound){
    this.type = type
    this.name = name
    this.sound = sound
}

Animal.prototype.say = function(){
    console.log(this.sound)
}

Animal.prototype.sharedValue = 1

// 생성자 상속
// Animal.call 을 호출!
//여기서 첫번째 인자에는 this 를 넣어주어야 하고,
// 그 이후에는 Animal 객체 생성자 함수에서 필요로 하는 파라미터를 넣어주어야 합니다.

// 추가적으로 prototype 을 공유해야 하기 때문에
// 상속받은 객체 생성자 함수를 만들고 나서 prototype 값을 Animal.prototype 으로 설정해주었습니다.

// Dog() 와 Cat() 을 Animal() 이
// 가지고 있던것을 재사용함

function Dog(name, sound){
    Animal.call(this, "개", name, sound)
    // .call(this, 상속하는 부모 객체 생성자 파라미터들)
}
Dog.prototype = Animal.prototype


function Cat(name, sound){
    Animal.call(this, "고양이", name, sound)
}
Cat.prototype = Animal.prototype

let dog = new Dog("멍멍이", "멍멍")
let cat = new Cat("야옹이", "야옹")

dog.say()
cat.say()

console.log(dog.sharedValue)
console.log(cat.sharedValue)

Dog.prototype.sharedValue = 100

console.log(dog.sharedValue)
console.log(cat.sharedValue)

console.log(dog)
console.log(cat)


