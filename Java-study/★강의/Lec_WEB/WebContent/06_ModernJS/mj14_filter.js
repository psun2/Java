// filter(함수)
//  특정 조건을 만족하는 원소들만으로 새로운 배열 생성
// 매개변수인 함수를 Truthy/Falsy 를 리턴해야 한다

let todos = [
    {
      id: 1,
      text: "자바스크립트 입문",
      done: true
    },
    {
      id: 2,
      text: "함수 배우기",
      done: true
    },
    {
      id: 3,
      text: "객체와 배열 배우기",
      done: true
    },
    {
      id: 4,
      text: "배열 내장함수 배우기",
      done: false
    }
  ];
  
  //
  
  // done 값이 false 인 것만 뽑아내려면 (filtering!)
// [ { id: 4, text: '배열 내장함수 배우기', done: false } ]


// .forEach() 사용
let result = []
todos.forEach(e => {
    if(!e.done) result.push(e)
})
console.log(result)

console.log()

result = todos.filter(todo => !todo.done)  // 
console.log(result)

console.log()
// done 값이 '참' 인것만 filtering  하기
result = todos.filter(todo => todo.done)  
console.log(result)


// id 값이 짝수인 것만 filtering  하기
result = todos.filter(todo => !(todo.id % 2))  
console.log(result)










