package theory;

public class DataType {

	public static void main(String[] args) {

		// 1 byte

		boolean bo1 = true;
		boolean bo2 = false;

		System.out.println("boolean 자료형은 1 byte 로 true 와 false 로 구분 되어 있습니다.");
		System.out.println(bo1);
		System.out.println(bo2);

		byte b = 1;
		System.out.println("byte 는 정수형 자료형으로 1byte의 크기를 가지고 있으며, -128 ~ 127 까지 표현이 가능합니다. ");
		System.out.println(b);

		// 2 byte

		short sh = -32768;
		short sh2 = 32767;
		char ch = 'a';

		System.out.println("short 는 정수형 자료형으로 2byte의 크기를 가지고 있으며, -32768 ~ 32767 까지 표현이 가능합니다. ");
		System.out.println(sh + ", " + sh2);
		System.out.println("ch 는 정수형 자료형으로 2byte의 크기를 가지고 있습니다. ");
		System.out.println(ch);

		// 4 byte

		int i = -2147483648;
		int i2 = 2147483647;

		float fl = 3.14F;
		float f2 = 3.14f;
//		- f 또는 -F 붙여 주지 않으면 double형 데이터로 인식하기에 꼭 붙여 줘야합니다.

		System.out.println("int 는 정수형 자료형으로 4byte의 크기를 가지고 있으며, -2147483648 ~ 2147483647 까지 표현이 가능합니다. ");
		System.out.println(i + ", " + i2);
		System.out.println("float 는 실수형 자료형으로 4byte 의 크기를 가지고 있으며, -f 또는 -F 를 붙이지 않으면 double 형으로 받아 들이니 주의합니다.");
		System.out.println(fl);
		System.out.println(f2);

		// 8 byte

		long lo = -9223372036854775808l;
		long lo2 = 9223372036854775807L;
//		- l 또는 -L 붙여 주지 않으면 int형 데이터로 인식하기에 꼭 붙여 줘야합니다.

		double du = 7.7864456;

		System.out.println(
				"long 는 정수형 자료형으로 8byte의 크기를 가지고 있으며, -9223372036854775808 ~ 9223372036854775807 까지 표현이 가능합니다. ");
		System.out.println("long 자료형은 8byte 의 크기를 가지고 있으며, -l 또는 -L 를 붙이지 않으면 int 형으로 받아 들이니 주의합니다.");
		System.out.println(lo);
		System.out.println(lo2);
		System.out.println("double 는 실수형 자료형으로 8byte의 크기를 가지고 있습니다.");
		System.out.println(du);
	}

}
