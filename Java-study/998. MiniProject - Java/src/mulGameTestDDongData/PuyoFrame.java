package mulGameTestDDongData;

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
	Socket socket;
	String host;
	int port;

	public PuyoFrame(String id) {
		// TODO Auto-generated constructor stub

		System.out.println("Frame 시작 == 게임 시작");
		init();
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		me = new MePuyoPanel(id, socket);
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		addKeyListener(new ActionKey(me));

		new GameReceive(socket, this).start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			this.port = 7777;
			this.socket = new Socket(host, port);
			System.out.println("host : " + host); // 서버 접속 확인!!!
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
		new PuyoFrame("내 아이디");
	}

}
