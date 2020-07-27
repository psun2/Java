package test;

public class Test {

	public static void main(String[] args) {

		AAA a = new BBB().detail();

		System.out.println(a);

		System.out.println("-------------------------------------");

		int size = 50;
		int i = 0;
		int z = 0;
		for (int j = 0; j < 6; j++) {
			System.out.println("z : " + z);
			System.out.println("i : " + i);
			z++;
			i += size;
			System.out.println("-------------------------------------");
		}

	}

}
