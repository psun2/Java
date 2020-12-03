// 조건문 더 스마트하게 쓰기
// 특정 값이 여러 값중 하나인지 확인해야 할 때
// 만약, 여러분이 특정 값이 여러 값 중 하나인지 확인을 해야 하는 상황이 생겼다고 해봅시다.
// 그러면, 이러한 시도를 할 수도 있을 것입니다.

let isAnimal = function(text){
    return (
        text === "고양이" ||  text ==="개" || 
            text === "거북이" || text === "너구리"
    )
}
console.log(isAnimal("개"))
console.log(isAnimal("노트북"))

// 배열안에 넣고 .includes 함수 사용
isAnimal = function(name){
    const animals = ["고양이", "개", "거북이", "너구리"]
    return animals.includes(name)  // || 연산자 사용하는거에 비해 깔끔.
}
console.log(isAnimal("개"))
console.log(isAnimal("노트북"))

// 화살표 함수 사용
isAnimal = name => ["고양이", "개", "거북이", "너구리"].includes(name)
console.log(isAnimal("개"))
console.log(isAnimal("노트북"))

// 코드가 짧다고 무조건 좋은것이 아니라..
// 코드가 짧아도 읽고, 무슨 동작인지 쉽고 
// 명확하게 보여야 비로서 좋은 코드다

//-----------------------------------------
//값에 따라 다른 결과물을 반환 해야 할 때
// 이번에는 주어진 값에 따라
// 다른 결과물을 반환해야 할 때 사용 할 수 있는 유용한 팁을 알아보겠습니다.
// 예를 들어서, 동물 이름을 받아오면,
// 동물의 소리를 반환하는 함수를 만들고 싶다고 가정해보겠습니다

let getSound = function(animal) {
    if (animal === "개") return "멍멍!";
    if (animal === "고양이") return "야옹~";
    if (animal === "참새") return "짹짹";
    if (animal === "비둘기") return "구구 구 구";
    return "...?";
  
    // 위 코드는 그닥 좋은 코드가 아니다..
    // 계속 if.. if...
  };

  console.log(getSound("개"))
  console.log(getSound("비둘기"))
  console.log(getSound("닝겐"))

  // swtich- case 를 사용하는 경우
  
  getSound = function(animal) {
    switch (animal) {
      case "개":
        return "멍멍!";
      case "고양이":
        return "야옹~";
      case "참새":
        return "짹짹";
      case "비둘기":
        return "구구 구 구";
      default:
        return "...?";
    }
    // switch 도 그닥. ㅋ드가 깔끔하진 않다
  };
  
  console.log(getSound("개")); // 멍멍!
  console.log(getSound("비둘기")); // 구구 구 구

// SCE 사용
getSound = function(animal){
    const sounds = {
        개: "멍멍",
        고양이: "야옹~",
        참새: "짹짹",
        비둘기: "구구"
    }
    // SCE 사용!
    return sounds[animal] || "...?"
}

console.log(getSound("개")); // 멍멍!
console.log(getSound("비둘기")); // 구구 구 구
console.log(getSound("김연지"))