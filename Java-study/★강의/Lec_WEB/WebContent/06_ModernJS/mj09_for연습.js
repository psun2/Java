// 주어진 배열 numbers 에서
// 3보다 큰 원소들로만 이루어진 배열을 만들어서 출력
// => [4, 5, 6, 7]

const numbers = [1, 2, 3, 4, 5, 6, 7];

// 1. for
let result = []
for (let i = 0; i < numbers.length; i++) {
    if (numbers[i] > 3) {
        result.push(numbers[i])
    }
}
console.log(result)

// 2. for .. in
result = []
for (i in numbers) { // i <- 0, 1, 2 ...
    if (numbers[i] > 3) {
        result.push(numbers[i])
    }
}
console.log(result)

// 3. for .. of
result = []
for (v of numbers) { // v <- 10, 20, 30 ...
    if (v > 3) {
        result.push(v)
    }
}
console.log(result)

// 4. .forEach()
result = []
numbers.forEach(element => {
    if (element > 3)
        result.push(element)
})
console.log(result)