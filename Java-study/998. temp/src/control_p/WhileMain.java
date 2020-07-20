package control_p;

public class WhileMain {

	public static void main(String[] args) {

		int i = 0; // 초기값
		while (i < 10) { // 조건
			System.out.println("while" + i);
			i++; // 증감
		}

		int sum = 0;
		i = 1;
		while (i <= 100) {
			sum += i;
			i++;
		}
		System.out.println(sum);

		int a = 10, b = 30;

		System.out.println(a + " , " + b);

		a = b;
		b = a;

		System.out.println(a + " , " + b);

		a = 10;
		int buf = a;
		a = b;
		b = buf;

		System.out.println(a + " , " + b + " , " + buf);

	}

}
