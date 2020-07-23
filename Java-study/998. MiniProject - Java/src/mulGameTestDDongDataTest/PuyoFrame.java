package mulGameTestDDongDataTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PuyoFrame extends JFrame {

	final int width = 806;
	final int height = 679;

	MePuyoPanel me;
	YouPuyoPanel you;
	JPanel center;
	JButton exitRoom;
	JToggleButton startGame;
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

		me = new MePuyoPanel(id);
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);
		addKeyListener(new ActionKey(me));

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		new GameSend(socket, this).start();
		new GameReceive(socket, this).start();
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
				System.out.println("소켓이 닫치니 ?");
			}

		}

	}

	public static void main(String[] args) {
		new PuyoFrame("내 아이디");
	}

}
