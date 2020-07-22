package server;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFrame extends JFrame {

	final int width = 306;
	final int height = 680;
	Client client;

	public ExecutorService threadPool;

	public TestFrame() {
		// TODO Auto-generated constructor stub

		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(new GridLayout()); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		this.threadPool = Executors.newCachedThreadPool();

		client = new Client();

		JButton btn1 = new JButton("btn1");
		JButton btn2 = new JButton("btn2");

		add(btn1);
		add(btn2);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("btn1 눌림");

				ObjectSend os = new ObjectSend(client.socket);

				os.sender(new TestSendClass());
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("btn2 눌림");
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		sendThread();
	}

	void sendThread() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new ObjectRecive(client.socket);
			}
		};

		threadPool.submit(thread);

	}

	public static void main(String[] args) {
		new TestFrame();
	}

}
