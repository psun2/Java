package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Puyo implements Comparable<Puyo> {
//public class Puyo  {

	private static final long serialVersionUID = 1L;

	public static final int PUYOSIZE = 50; // 뿌요 사이즈
	public static final int PANG = 4; // 터질때 필요한 갯수
	// public static final int JUM = 10; // 터질때 필요한 갯수

	int x, y;

	boolean stopChk; // 뿌요가 더이상 갈 곳이 없을때 false 초기값 true

	JLabel Lb;
	PuyoPanel panel;

	String name;

	public Puyo(PuyoPanel panel) {
		// TODO Auto-generated constructor stub
		this.panel = panel;
		init();
//		start();

	}

	void init() {
		this.stopChk = true;
		this.Lb = label();
		this.name = Lb.getName();
	}

	JLabel label() {

		String[] colors = { "blue", "green", "red", "yellow", "ninja" };
		int i = (int) (Math.random() * 5);
//		int i = 0;

		// randomTest();

		JLabel result = new JLabel(new ImageIcon("./img/" + colors[i] + "-48.png"));

		// JLabel result = new JLabel(new ImageIcon("./img/" + colors[i] + ".png"));

		result.setName(colors[i]);

		// System.out.println(result.getName()); // blue

		return result;
	}

	@Override
	public String toString() {
		return "Puyo [name=" + name + "]";
	}

	void randomTest() { // 골고루 잘 돌아가는거 확인

		while (true) {

			int i = (int) (Math.random() * 4);
			System.out.println(i);

			if (i == 3)
				return;

		}

	}

	@Override
	public int compareTo(Puyo o) {
		// TODO Auto-generated method stub

		int res = o.Lb.getY() - Lb.getY();

		if (res == 0)
			res = Lb.getX() - o.Lb.getX();

		return res;
	}

}