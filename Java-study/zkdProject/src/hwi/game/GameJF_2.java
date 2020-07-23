package hwi.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class GameJF_2 extends JFrame implements ReceiverObjFromClient {

	private JPanel contentPane;

	ArrayList<TeethBtn> jb = new ArrayList<TeethBtn>();

	DefaultClient dc;

	TeethBtn[] btn;

	String me,you;

	JLabel lblNewLabel;

	boolean trun = true;

	int index = -1;

//	int index = (int)(Math.random()*jb.size());

	class TeethBtn extends JButton implements ActionListener {

		int i;

		public TeethBtn(int x, int y, int width, int height) {
			super();

			setBounds(x, y, width, height);
			setIcon(new ImageIcon("img/이빨.png"));
			setContentAreaFilled(false);
			setVisible(true);
			addActionListener(this);
			setLayout(new BorderLayout(0, 0));
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(i);

			if (trun) {
				String msg = i + "";
				if (index == i) {
					System.out.println("펑~~~~~~~~~~~~~~~~~~~~~`");
					msg = "펑";
					// 내가 짐
					lblNewLabel.setIcon(new ImageIcon("img/[크기변환]악어종료.png"));

					// 진거 후속 처리
					for (int i = 0; i < btn.length; i++) {

						btn[i].setEnabled(false);
					}
				} else {

					setIcon(new ImageIcon("img/이빨빠짐.png"));
					setContentAreaFilled(true);
					setEnabled(false);

				}

				dc.sendMessage(msg, "game", you);

				trun = false;

			} else {
				System.out.println("기다려!");
			}

		}

	}

	@Override
	public void getMsgObjectFromClient(MessageObject mo) {

		String ri = (String) mo.getMessageMain();
		System.out.println("내가 받는다 확씨!!! " + ri);
		if (ri.equals("펑")) {
			System.out.println("내가 이김");

			/// 이긴 화면 보여줘
			lblNewLabel.setIcon(new ImageIcon("img/승리.png"));

			/// 이긴거 후속 처리
			for (int i = 0; i < btn.length; i++) {

				btn[i].setEnabled(false);
			}

		} else {

			int ti = Integer.parseInt(ri);

			btn[ti].setIcon(new ImageIcon("img/이빨빠짐.png"));
			btn[ti].setContentAreaFilled(true);
			btn[ti].setEnabled(false);

			trun = true;
		}
	}

	public GameJF_2(int index, DefaultClient dc, String me,String you) {
		
		this.index = index;
	

		this.dc = dc;
		this.me = me;
		this.you = you;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/[크기변환]악어.png"));
		lblNewLabel.setBounds(0, 0, 743, 561);
		contentPane.add(lblNewLabel);

		btn = new TeethBtn[] { new TeethBtn(193, 106, 71, 64), new TeethBtn(196, 111, 55, 57),
				new TeethBtn(283, 99, 63, 64), new TeethBtn(378, 99, 63, 69), new TeethBtn(463, 106, 63, 64),
				new TeethBtn(532, 137, 46, 48), new TeethBtn(549, 197, 46, 32), new TeethBtn(532, 241, 46, 38),
				new TeethBtn(532, 288, 61, 50), new TeethBtn(549, 350, 46, 48), new TeethBtn(507, 396, 63, 64),
				new TeethBtn(432, 414, 71, 64), new TeethBtn(328, 429, 71, 64), new TeethBtn(235, 429, 71, 64),
				new TeethBtn(171, 403, 61, 57), new TeethBtn(134, 350, 46, 57), new TeethBtn(134, 288, 71, 64),
				new TeethBtn(134, 241, 46, 48), new TeethBtn(121, 195, 51, 48), new TeethBtn(134, 132, 61, 64) };

		for (int i = 0; i < btn.length; i++) {
			jb.add(btn[i]);
			contentPane.add(btn[i]);
			btn[i].i = i;

		}

		index = (int) (Math.random() * jb.size());
		System.out.println("index:" + index);

//		JButton gameGetOut = new JButton("게임 나가기");
//		contentPane.add(gameGetOut, BorderLayout.SOUTH);
//
//		gameGetOut.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// 서버로 같이 끈겨야하나?
//				dispose();// 게임 나가기 버튼을 누르면 게임 창이 꺼지게 하기.
//
//			}
//		});

		setVisible(true);

	}

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// GameJF_2 frame = new GameJF_2();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	// s
}
