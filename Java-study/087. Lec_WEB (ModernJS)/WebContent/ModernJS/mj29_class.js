class Numbers {
  constructor(_a, _b) {
    this._a = _a;
    this._b = _b;
    this._sum = this._a + this._b;
  }
  calculation() {
    console.log('덧셈 실행');
    this._sum = this._a + this._b;
  }

  get a() {
    return this._a;
  }
  get b() {
    return this._b;
  }
  get sum() {
    return this._sum;
  }
  set a(value) {
    this._a = value;
    this.calculation();
  }
  set b(value) {
    this._b = value;
    this.calculation();
  }
}

const numbers = new Numbers(1, 2);
console.log(numbers);
// Numbers { _a: 1, _b: 2 }
console.log(numbers.sum); // 3

numbers.a = 10;
numbers.b = 20;

console.log(numbers.a); // 10
console.log(numbers.b); // 20

numbers.calculation(); // 덧셈 실행

console.log(numbers.sum); // 30
