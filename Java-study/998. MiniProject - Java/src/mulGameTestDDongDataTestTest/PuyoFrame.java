package mulGameTestDDongDataTestTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;

public class PuyoFrame extends JFrame implements DDongInter {

	ClientNetWork cn;

	final int width = 806;
	final int height = 679;

	MePuyoPanel me;
	YouPuyoPanel you;

	DDongData data;

	ExecutorService threadPool;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub

//		cn = new ClientNetWork("아이디다");
		this.data = new DDongData();
		this.threadPool = Executors.newCachedThreadPool();

		System.out.println("Frame 시작 == 게임 시작");
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		me = new MePuyoPanel();
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);
		addKeyListener(new ActionKey(me));

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		update();

	}

	void update() {

		System.out.println("update");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					// System.out.println("asdasdasdasdasd");

//					if (me.meInfo != null) {

					// System.out.println("업데이트 진입");
					try {
						data.src = "아이디";
						data.type = "게임";
						data.data = me.meInfo;
//						System.out.println(data.type);
//						System.out.println(me.meInfo);
						cn.send(PuyoFrame.this.data);
//						Thread.sleep(33);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					}
				}

			}
		};
		this.threadPool.submit(thread);

	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub

		System.out.println("리시브 확인 ㅓ모엄노ㅓㅇ모나ㅓ오머ㅏ롸ㅓ몰나ㅓ모나ㅓ로마ㅓ노라ㅓ");

		// System.out.println("asdasdadasdasd" + dd);

//		if (dd.type.equals("게임") && !dd.src.equals(cn.id)) {
//			this.data = dd;
//		you.paint((MeGameInfo) dd.data);
//		}

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
