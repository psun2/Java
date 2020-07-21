package newasd;

import java.awt.Color;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI extends JFrame {

	final int width = 606;
	final int height = 680;

	Socket socket;
	String host;
	int port;

	MeGUI me;
	YouGUI you;

	Game info;

	public static ExecutorService threadPool;

	public GUI() {
		// TODO Auto-generated constructor stub

		init();
		// setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setSize(width, height);
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		me = new MeGUI(socket, info);
		me.setBounds(0, 0, info.size * 6, info.size * 13);
		add(me);

		you = new YouGUI(socket, info);
		you.setBounds(info.size * 6, 0, info.size * 6, info.size * 13);
		add(you);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌
	}

	void init() {

		info = new Game();

		GUI.threadPool = Executors.newCachedThreadPool();

		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			int port = 7777;
//			socket = new Socket(host, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new GUI();
	}

}
