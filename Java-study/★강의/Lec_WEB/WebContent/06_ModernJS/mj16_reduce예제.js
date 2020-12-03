// reduce 응용
// 도전!
// 배열안의 알파벳은 각각 몇개가 있을까?
let alphabets = ["c", "a", "a", "b", "c", "a", "d", "e"];
// 결과 => { a: 3, b: 1, c: 2, d: 1, e: 1 }
// 힌트 alphabets.reduce() 사용;

let count = alphabets.reduce((acc, cur) => {
    if(acc[cur]){
        acc[cur] += 1 // 기존에 존재 했다면 +1 증가
    } else {
        acc[cur] = 1 // 첫등장
    }
    return acc;
}, {})
console.log(count) // { a: 3, b: 1, c: 2, d: 1, e: 1 }
/*
    {}, c, a, a, b, c, a, d, e

     {c:1}, a, a, b, c, a, d, e

     {c:1, a:1}, a, b, c, a, d, e

        {c:1, a:2}
*/

