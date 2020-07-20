package lang_p;

public class MathMain {

	public static void main(String[] args) {

		System.out.println(Math.PI);
		System.out.println(Math.E);

		// 올림
		System.out.println(Math.ceil(123.456));
		System.out.println(Math.ceil(-123.456));

		// 버림
		System.out.println(Math.floor(123.456));
		System.out.println(Math.floor(-123.456));

		// 반올림
		System.out.println(Math.round(123.456));
		System.out.println(Math.round(123.789));
		System.out.println(Math.round(-123.456));
		System.out.println(Math.round(-123.789));

		// 절대값 => absolut
		System.out.println(Math.abs(123));
		System.out.println(Math.abs(-123));

		// max
		System.out.println(Math.max(10, 20));

		// min
		System.out.println(Math.min(10, 20));

		// 삼각함수
		System.out.println(Math.sin(30));
		// -0.9880316240928618
		System.out.println(Math.sin(30 * Math.PI / 180));
		// 0.49999999999999994
		System.out.println(Math.cos(30 * Math.PI / 180));
		// 0.8660254037844387
		System.out.println(Math.tan(30 * Math.PI / 180));
		// 0.5773502691896257

		System.out.println(Math.exp(3));

		System.out.println(Math.log(3));

		// pow
		System.out.println(Math.pow(2, 4));

		// sqrt
		System.out.println(Math.sqrt(2)); // 루트2

		// random
		System.out.println(Math.random());
		// 0 < 난수 < 1
		System.out.println((int) (Math.random() * 3 + 1));
		// 1 ~ 3 까지의 랜덤 수
	}

}
