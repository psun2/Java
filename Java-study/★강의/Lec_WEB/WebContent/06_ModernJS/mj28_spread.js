/*
  함수 인자와 spread
 
  파라미터(parameter) : 함수에서 받아오는 변수
  인자(argument)  : 함수 호출시 보내는 값
*/
{
    function myFunction(a) {
      // 여기서 a 는 파라미터
      console.log(a);
    }
   
    myFunction("hello world"); // 여기서 'hello world' 는 인자
}

{
    function sum(...rest){
        return rest.reduce((acc, cur) => acc + cur)
    }

    console.log(sum(10, 20, 30, 40))

    const numbers = [10, 20, 30, 40, 50, 60]
    console.log(sum(numbers))

    // 배열 안의 원소를 호출인자로 보내주고 싶다면???
    const result = sum(
        numbers[0],
        numbers[1],
        numbers[2],
        numbers[3],
        numbers[4],
        numbers[5]
    )
    // 매우 불편한다.
    console.log(result)

}

{
    function sum(...rest){
        return rest.reduce((acc, cur) => acc + cur)
    }

    const numbers = [10, 20, 30, 40, 50, 60]

    // ★ 호출시 함수 인자에 spread 사용 가능 ★
    const result = sum(...numbers)
    console.log(result)
}

//------------------------------------------
// 함수에 n 개의 숫자들이 파라미터로 주어졌을 때, 
// 그 중 가장 큰 값을 리턴하세요

function max(...numbers){
    return numbers.reduce(
        (acc, cur) => (cur > acc ? cur : acc),
        numbers[0]
        );
}

const data = [1, 2, 3, 4, 10, 5, 6, 7]
const result = max(...data)  // data 가 인자로 넘겨져야 한다
console.log(result)   // 10

























   
  
  