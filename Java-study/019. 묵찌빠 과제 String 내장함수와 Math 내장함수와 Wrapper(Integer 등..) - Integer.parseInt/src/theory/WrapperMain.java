package theory;

public class WrapperMain {

	public static void main(String[] args) {

		// 문자열을 int 형태로 변환
		System.out.println("Integer.parseInt(\"int형으로 바꿀 숫자형태의 문자열\")");
		int i = Integer.parseInt("123456");

		Integer ii = new Integer(123);
		int iii = ii.intValue();
		Integer iv = 123;
		System.out.println(i + 100);
		// 100은 숫자로 변환된 것을 나타내기 위해서 더해준 예제
		System.out.println(ii);
		System.out.println(iii);
		System.out.println(iv);

		// 문자열을 byte 형태로 변환
		byte bb = Byte.parseByte("123");
		short sh = Short.parseShort("123");
		long ll = Long.parseLong("123");

		float ff = Float.parseFloat("123.456");
		double dd = Double.parseDouble("123.456");

		boolean bo = Boolean.parseBoolean("true");
		System.out.println("bb : " + bb);
		System.out.println("sh : " + sh);
		System.out.println("ll : " + ll);

		System.out.println("ff : " + ff);
		System.out.println("dd : " + dd);

		System.out.println("bo : " + bo);

		// num 숫자를 binary로 변환후, 1의 수를 반환
		// 2진수로 변화후 1의 갯수만을 세어 숫자를 반환
		System.out.println();
		System.out.println("Integer.bitCount(2진수로 바꿀 수) => 인자로 받는 수를 2진수로 바꾸어 1의 갯수를 세어 반환 합니다.");
		System.out.println(Integer.bitCount(4)); // 이진수 => 100
		System.out.println(Integer.bitCount(5)); // 이진수 => 101
		System.out.println(Integer.bitCount(6)); // 이진수 => 110
		System.out.println(Integer.bitCount(7)); // 이진수 => 111

		System.out.println();
		System.out.println("intValue() 와 toString() 의 차이 => 숫자형태와 문자 형태로 return 합니다.");
		System.out.println(ii.intValue() + 100); // 223 => 숫자
		System.out.println(ii.toString() + 100); // 123100 => 문자

		System.out.println();
		System.out.println(
				"Integer.toBinaryString(2진수로 바꿀 값), Integer.toOctalString(8진수로 바꿀 값), Integer.toHexString(16진수로 바꿀 값) => 해당 값을 문자열로 반환 합니다.");
		System.out.println(Integer.toBinaryString(12) + "문자임"); // 2진수
		System.out.println(Integer.toOctalString(12) + "문자임"); // 8진수
		System.out.println(Integer.toHexString(12) + "문자임"); // 16진수

	}

}