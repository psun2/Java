package exam;

public class Pow {

	static int pow(int num, int length) {

		System.out.println("length" + length);

//		int digit = (length - 1);
//		int digit = length;
//		System.out.println("digit" + digit);

		int result = 1;

		if (length == 1)
			return result;
		
		result = num *  pow(num, (length - 1));

		return result;

	}

	public static void main(String[] args) {

		System.out.println("dd => " + pow(10, 4));

		System.out.println("1".length());
		System.out.println("1990".length());
		
		System.out.println(Integer.parseInt("09"));

	}

}
