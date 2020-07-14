package puyopuyo;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Puyo2 extends JPanel { // 뿌요 라벨 2개씩 한 쌍을 가지고 있는 뿌요 프레임

	JLabel me; // (왼쪽뿌요)
	JLabel you; // (오른쪽 뿌요)
	int width; // 뿌요 하나의 가로 사이즈
	int height; // 뿌요 하나의 세로 사이즈

	// me 를 기준으로 모양이 변경될때 you의 위치만 바뀜

	public Puyo2() {
		// TODO Auto-generated constructor stub

		this.width = 50;
		this.height = 50;

		setLayout(null);
		setBackground(Color.blue);

		this.me = new JLabel("me");
		me.setBounds(0, 0, width, height);
		me.setBackground(Color.black);
		me.setOpaque(true);

		this.you = new JLabel("you");
		you.setBounds(0, me.getSize().height, width, height);
		you.setBackground(Color.cyan);
		you.setOpaque(true);

		add(me);
		add(you);

	}

	public void move() { // 일단 보류

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		};

	}

}
