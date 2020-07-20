package game;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

public class YouPuyoPanel extends JPanel {

//	MyPuyoPanel you;
	PuyoGameInfo info;
	ObjectInputStream ois;

	ExecutorService threadPool;

//	public YouPuyoPanel(MyPuyoPanel you) {
		public YouPuyoPanel() {
		// TODO Auto-generated constructor stub

		//this.you = you;

		threadPool = Executors.newCachedThreadPool();
		
//		try {
//			ois = 
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		setBackground(Color.CYAN);
		setLayout(null);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

	}

	void updateInfo() {

	}

	void receiver() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {
					updateInfo(); // 상대편의 info 정보를 업데이트
				}

			}
		};

		threadPool.submit(thread);

	}

}
