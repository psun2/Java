package game;

import java.awt.Color;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoFrame extends JFrame {

	final int width = 606;
	final int height = 680;
	MePuyoPanel me;
	YouPuyoPanel you;


	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		init();
		System.out.println("Frame �떆�옉 == 寃뚯엫 �떆�옉");
		setSize(width, height); // �봽�젅�엫 �궗�씠利�
		setLocationRelativeTo(null); // �봽�젅�엫 �떆�옉�떆 紐⑤땲�꽣 以묒븰�뿉 異쒕젰
		setResizable(false); // �봽�젅�엫 �궗�씠利� 議곗젅 �븷 �닔 �뾾�쓬
		getContentPane().setLayout(null); // �젅�씠2�븘�썐
		setTitle("肉뚯슂肉뚯슂"); // ���씠��
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // ���씠��諛� 濡쒓퀬 �꽕�젙
		getContentPane().setBackground(Color.blue);

		me = new MePuyoPanel();
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		addKeyListener(new ActionKey(me));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �봽�젅�엫 �떕湲� �샃�뀡
		setVisible(true); // �봽�젅�엫�쓣 蹂댁뿬以�

	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			this.port = 7777;
			this.socket = new Socket(host, port);
			System.out.println("host : " + host); // �꽌踰� �젒�냽 �솗�씤!!!
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				if (socket != null && !socket.isClosed())
					socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
