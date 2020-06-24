package theory.layout;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Card extends JFrame {

	// CardLayout 은 겹치는 Border와 달리
	// Z축으로 쌓입니다.
	// slider을 구성할때 유리한 정렬 기법입니다.

	public Card(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CardLayout card = new CardLayout();
		setLayout(card);

		Color[] colors = { new Color(255, 0, 0), new Color(255, 94, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(26, 35, 126), new Color(95, 0, 255) };

		String index = "빨주노초파남보";

		for (int i = 0; i < colors.length; i++) {
			JPanel pp = new JPanel();
			pp.setBackground(colors[i]);
			add(pp, index.substring(i, i + 1));
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		new CardLayout(hgap, vgap).show(parent, name);

		int i = 0;

		while (true) {
			try {
				Thread.sleep(100);
				card.show(getContentPane(), index.substring(i, i + 1));
				i++;
				i %= index.length();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

public class CardLayoutMain {

	public static void main(String[] args) {

		new Card("CardLayout");

	}

}
