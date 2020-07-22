package puyopuyo;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Puyo extends JLabel {

	PuyoFrame frame;
	String img;

	public Puyo(PuyoFrame frame, String img, Color color) {
		// TODO Auto-generated constructor stub

		this.img = img;
		this.frame = frame;
		// setIcon(new ImageIcon(img));
		setText(img);
		setName(img);
		setBackground(color);
		setOpaque(true);
	}

	void move() {

		Runnable thread = new Runnable() {

			int step = 5;
			boolean meChk = true;
			boolean youChk = true;

			@Override
			public void run() {
				// TODO Auto-generated method stub

				Puyo.this.frame.chk = false;
				while (Puyo.this.getY() != 200) {

					try {

						Thread.sleep(1000);

						for (Puyo me : Puyo.this.frame.puyos) {

							if (me.equals(Puyo.this)) {
								if (me.getY() < 200) { // 임시값
									me.setLocation(me.getX(), me.getY() + step);
									break;
								}
							}

						}
						
				

						System.out.println(Puyo.this.frame.chk);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				Puyo.this.frame.chk = false;
			}
		};
		frame.threadPool.submit(thread);
	}

	void bomb() { // 뿌요가 터져서 빈공간이 생긴 경우

	}

	void meet() { // 다른 뿌요를 만난경우

	}

	void end() { // y축의 끝을 만난 경우

	}

}
