function Numbers(_a, _b) {
  this._a = _a;
  this._b = _b;
  this._sum = this._a + this._b;
}

Numbers.prototype.calculator = function () {
  this._sum = this._a + this._b;
};
Numbers.prototype.getA = function () {
  return this._a;
};
Numbers.prototype.getB = function () {
  return this._b;
};
Numbers.prototype.setA = function (value) {
  this._a = value;
  this.calculator();
};
Numbers.prototype.setB = function (value) {
  this._b = value;
  this.calculator();
};
Numbers.prototype.getSum = function () {
  return this._sum;
};

const numbers = new Numbers(1, 2);
console.log(numbers);
// Numbers { _a: 1, _b: 2, _sum: 3 }

console.log(numbers.getA()); // 1
numbers.setA(10);
console.log(numbers.getA()); // 10
