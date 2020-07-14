package test;

import javax.swing.JLabel;

public class WhileFunctionTest {

	GamePanel a;

	public WhileFunctionTest(GamePanel a) {
		// TODO Auto-generated constructor stub

		this.a = a;
		System.out.println(a.getName());

		test();
	}

	void test() {

		while (true) {
			JLabel asd = new JLabel("안보여");
			asd.setBounds(100, 100, 100, 100);
			a.add(asd);
		}
	}

}
