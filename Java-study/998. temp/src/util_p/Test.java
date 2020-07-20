package util_p;

class Cal {

	void ppp() {
		System.out.println("dd");
	}

}

class Cal2 {
	Cal aa;

	public Cal2(Cal aa) {
		this.aa = aa;
	}
}

public class Test {

	public static void main(String[] args) {

		Cal aa;

		for (int i = 0; i < 1; i++) {
			new Cal(aa);
			Cal2[] arr = { new Cal2(aa), };
		}

		aa.ppp();

	}

}
