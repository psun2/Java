function Numbers(_a, _b) {
  this._a = _a;
  this._b = _b;
  this.getA = function () {
    return this._a;
  };
  this.getB = function () {
    return this._b;
  };
  this.setA = function (value) {
    this._a = value;
  };
  this.setB = function (value) {
    this._b = value;
  };
}

const numbers = new Numbers(1, 2);
console.log(numbers);
// Numbers {
//     _a: 1,
//     _b: 2,
//     getA: [Function],
//     getB: [Function],
//     setA: [Function],
//     setB: [Function]
//   }

console.log(numbers.getA()); // 1
numbers.setA(10);
console.log(numbers.getA()); // 10
