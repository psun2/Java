package oops;

class Calc {

	int plus(int a, int b) {
		System.out.println("덧셈 함수가 실행됩니다. \n");
		return a + b;
	}

	int minus(int a, int b) {
		System.out.println("뺄셈 함수가 실행됩니다. \n");
		return a - b;
	}

	int multiply(int a, int b) {
		System.out.println("곱셈 함수가 실행됩니다. \n");
		return a * b;
	}

	int division(int a, int b) {
		System.out.println("나눗셈 함수가 실행됩니다. \n");
		return a / b;
	}
}

public class Calculator {

	public static void main(String[] args) {

		Calc claculator = new Calc();

		int plus = claculator.plus(10, 30);
		System.out.println(plus);

		int minus = claculator.minus(10, 30);
		System.out.println(minus);

		int multiply = claculator.multiply(10, 30);
		System.out.println(multiply);

		int division = claculator.division(10, 30);
		System.out.println(division);

	}

}
