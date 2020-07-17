package lastPuyo2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//public class Puyo extends Thread {
public class Puyo {

	public static final int puyoSize = 50;

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

		// randomTest();

		JLabel result = new JLabel(new ImageIcon("./img/" + colors[i] + "-48.png"));

		// System.out.println("./img/" + colors[i] + "-48.png"); // ./img/blue-48.png

		result.setName(colors[i]);

		// System.out.println(result.getName()); // blue

		return result;
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//		while (true) {
//
//			// System.out.println("Lb.getY() : " + Lb.getY());
//			// System.out.println("y : " + y);
//
//			if (panel.endGame) // 게임이 끝나서 더이상 업데이트를 할 필요 없음
//				return;
//
//			if (Lb.getX() != x || Lb.getY() != y) {
//				this.x = Lb.getX();
//				this.y = Lb.getY();
//				// System.out.println("좌표 업데이트 완료");
//			}
//
//		}
//
//	}

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

}
