// reduce 응용
// 도전!
// 배열안의 알파벳은 각각 몇개가 있을까?
let alphabets = ['a', 'a', 'a', 'b', 'c', 'c', 'd', 'e'];
// 결과 => { a: 3, b: 1, c: 2, d: 1, e: 1 }
// 힌트 alphabets.reduce() 사용;

let count = alphabets.reduce((acc, cur) => {
  //   if (acc[cur]) acc[cur] += 1;
  //   else acc[cur] = 1;
  acc[cur] = acc[cur] ? (acc[cur] += 1) : (acc[cur] = 1);

  return acc;
}, {});
console.log(count); // { a: 3, b: 1, c: 2, d: 1, e: 1 }

/*
    {}, a, a, a, b, c, c, d, e
    
    {a : 1}, a, a, b, c, c, d, e

    {a : 2}, a, b, c, c, d, e

    {a : 3}, b, c, c, d, e

    {a : 3, b : 1}, c, c, d, e

    {a : 3, b : 1, c : 1}, c, d, e

    {a : 3, b : 1, c : 2}, d, e

    {a : 3, b : 1, c : 2, d : 1}, e

    {a : 3, b : 1, c : 2, d : 1, e : 1}
*/
