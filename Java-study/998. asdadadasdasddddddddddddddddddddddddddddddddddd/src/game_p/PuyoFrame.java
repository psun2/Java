package game_p;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import game_p.GameRoom_GUI.ExitBtn;
import game_p.GameRoom_GUI.ReadyBtn;

public class PuyoFrame extends JFrame implements DDongInter {

	public ClientNetWork cn;

	final int width = 806;
//	final int height = 679;
	final int height = 679 + 50;

	MePuyoPanel me;
	YouPuyoPanel you;

	JPanel state;

	JLabel meLb, youLb;

	String meId, enemyId;

	ExecutorService threadPool;

	public PuyoFrame(String meId, String enemyId) {
		// TODO Auto-generated constructor stub

		this.meId = meId;
		this.enemyId = enemyId;
		this.threadPool = Executors.newCachedThreadPool();

		System.out.println("Frame 시작 == 게임 시작");
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		me = new MePuyoPanel(this);
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);
		addKeyListener(new ActionKey(me));

		int labelH = 50;
		this.meLb = new JLabel("ME");
		meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		meLb.setHorizontalAlignment(JLabel.CENTER);
		add(meLb);

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		this.youLb = new JLabel("YOU");
		youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		youLb.setHorizontalAlignment(JLabel.CENTER);
		add(youLb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

//		update();

	}

//	void update() {
//
//		System.out.println("update");
//
//		Runnable thread = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				while (true) {
//
//					// System.out.println("asdasdasdasdasd");
//
//					if (me.data.data != null) {
//
//						// System.out.println("업데이트 진입");
//						try {
//							cn.send(me.data);
////                  Thread.sleep(33);
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//
//			}
//		};
//		this.threadPool.submit(thread);
//
//	}
//	void update() {
//
//		System.out.println("update");
//
//		Runnable thread = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				while (true) {
//
//					// System.out.println("asdasdasdasdasd");
//
//					if (me.data.data != null) {
//
//						// System.out.println("업데이트 진입");
//						try {
//							cn.send(me.data);
////                  Thread.sleep(33);
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//
//			}
//		};
//		this.threadPool.submit(thread);
//
//	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub

		if (dd.type.equals("게임중") && dd.src.equals(enemyId)) {
			you.paint((MeGameInfo) dd.data);
		}

//		
//		if (dd.type.equals("게임")) {
//
//			if (dd.src.equals(cn.id)) {
//				this.data = dd;
//				for (String id : dd.dst) {
//					if (!cn.id.equals(id))
//						this.enemy = id;
//				}
//			}
//
//			if (this.enemy != null && dd.src.equals(enemy)) {
//				you.paint((MeGameInfo) dd.data);
//			}
//
//		}

	}

	class ExitBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			PuyoFrame.this.dispose();

		}

	}
	
	public static void main(String[] args) {
		new PuyoFrame("asdds", "adad"); 
	}

}