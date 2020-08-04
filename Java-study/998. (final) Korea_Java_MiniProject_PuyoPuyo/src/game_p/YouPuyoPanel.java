package game_p;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YouPuyoPanel extends JPanel {

	PuyoGameInfo info;
	ImageIcon icon;
	ArrayList<JLabel> puyos;
	boolean chk;

	public YouPuyoPanel() {
		// TODO Auto-generated constructor stub

		this.chk = false;
		this.icon = new ImageIcon("./img/background.png");

		setLayout(null);
		setBackground(Color.green);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(icon.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	void painting(MeGameInfo info) {

		// System.out.println("paint 진입"); // 진입 확인

		if (info != null) {

			removeComponent(); // 전에껄 지우고

			this.puyos = new ArrayList<JLabel>(); // 새로 업데이트

			for (Puyo puyo : info.puyos) {

				JLabel lb = new JLabel(new ImageIcon("./img/" + puyo.color + "-48.png"));

				lb.setName(puyo.color);
				lb.setBounds(puyo.x, puyo.y, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
				add(lb);
				puyos.add(lb);

//				System.out.println("lb : " + lb);
//				System.out.println("puyo.y : " + puyo.color + " : " + puyo.y);
//				System.out.println(lb.getName() + " : " + lb.getY()); // 좌표 업데이트가 안됨

			}

			this.info.score.setText("점수 : " + info.score + "점");
			this.info.combo.setText("연쇄 : " + info.combo + "연쇄");
			this.info.second.setText("경과 시간 : " + info.second + "s");

			setVisible(false);
			setVisible(true);

		}

	}

	void removeComponent() {

		if (this.puyos != null) {

			for (JLabel puyoLb : this.puyos) {
				remove(puyoLb);
			}

			setVisible(false);
			setVisible(true);

		}
	}

	@Override
	public void paint(Graphics g) { // 게임이 끝났을시 투명도를 설정
		super.paint(g);
		if (!this.chk)
			return;
		BufferedImage img = (BufferedImage) createImage(getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
		g2.drawImage(img, 0, 0, null);
	}

}
