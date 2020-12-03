//함수 파라미터에서의 rest
//rest 를 함수파라미터에서 사용 할 수도 있습니다.
 
// 예) 우리가 파라미터로 넣어준 모든 값들을
// 합해주는 함수를 만들기
 
{
    function sum(a, b, c, d, e, f, g) {
      return a + b + c + d + e + f + g;
    }
    // 호출시 매개변수가 다 차있으면 작동은 하겠지만...
    console.log(sum(1, 2, 3, 4, 5, 6, 7))

    // 만약 하나라도 빠지면?
    console.log(sum(1, 2, 3, 4, 5, 6))  // NaN
}
  
{
    // 호출시 매개변수가 빠질 경우를 대비하야...
    function sum(a, b, c, d, e, f, g) {
        let sum = 0;
        if(a) sum += a;
        if(b) sum += b;
        if (c) sum += c;
        if (d) sum += d;
        if (e) sum += e;
        if (f) sum += f;
        if (g) sum += g;
        return sum;
    }
    // ... 매개변수 빠져도 동작은 한다...
    console.log(sum(1, 2, 3, 4, 5, 6))
}

// rest 사용
{
    // 매개변수로 넘어온 것들을 하나의 배열로 받음.
    function sum(...rest){
        console.log(rest)  // ★타입은 배열★

        // reduce 를 사용하여 합산 구하기
        return rest.reduce((acc, cur) => acc + cur)
    }

    const result = sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    console.log(result)
}

















