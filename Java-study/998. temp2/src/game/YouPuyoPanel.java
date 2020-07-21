package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YouPuyoPanel extends JPanel {

	Socket socket;
	ObjectInputStream ois;
	PuyoGameInfo info;
	ImageIcon icon;

	ExecutorService threadPool;

	public YouPuyoPanel(Socket socket) {
		// TODO Auto-generated constructor stub

		System.out.println("--- you  생성");

		this.icon = new ImageIcon("./img/background.png");

		setLayout(null);
		setBackground(Color.green);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

		this.socket = socket;
		this.threadPool = Executors.newCachedThreadPool();

		System.out.println("--- you   생성 끝");

		recever();

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(icon.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	public void recever() {

		System.out.println("recever 진입");

		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
			// System.out.println("you : ois " + ois); // 여기까지 작동 확인
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {

					while (true) {

						if (ois != null) {
							paint((MeGameInfo) ois.readObject());
							// System.out.println(ois.readObject());
							// System.out.println("서버로 부터 정보를 얻어 오는데 성공");
						}

						Thread.sleep(1000);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
						try {
							if (ois != null)
								ois.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}

			}
		};

		this.threadPool.submit(thread);
		// TestServer.threadPool.submit(thread);
		System.out.println("recever 종료");

	}

	void paint(MeGameInfo info) {

		// System.out.println("paint 진입"); // 진입 확인

		if (info != null) {

			for (Puyo puyo : info.puyos) {

				JLabel lb = new JLabel(new ImageIcon("./img/" + puyo.color + "-48.png"));
				lb.setName(puyo.color);
				lb.setBounds(puyo.x, puyo.y, puyo.PUYOSIZE, puyo.PUYOSIZE);
				add(lb);

				// System.out.println("lb : " + lb);
				// System.out.println("puyo.y : " + puyo.color + " : " + puyo.y);
				// System.out.println(lb.getName() + " : " + lb.getY()); // 좌표 업데이트가 안됨

			}
		}
	}

}
