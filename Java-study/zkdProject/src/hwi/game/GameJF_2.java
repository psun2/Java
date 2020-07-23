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
			setIcon(new ImageIcon("img/�̻�.png"));
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
					System.out.println("��~~~~~~~~~~~~~~~~~~~~~`");
					msg = "��";
					// ���� ��
					lblNewLabel.setIcon(new ImageIcon("img/[ũ�⺯ȯ]�Ǿ�����.png"));

					// ���� �ļ� ó��
					for (int i = 0; i < btn.length; i++) {

						btn[i].setEnabled(false);
					}
				} else {

					setIcon(new ImageIcon("img/�̻�����.png"));
					setContentAreaFilled(true);
					setEnabled(false);

				}

				dc.sendMessage(msg, "game", you);

				trun = false;

			} else {
				System.out.println("��ٷ�!");
			}

		}

	}

	@Override
	public void getMsgObjectFromClient(MessageObject mo) {

		String ri = (String) mo.getMessageMain();
		System.out.println("���� �޴´� Ȯ��!!! " + ri);
		if (ri.equals("��")) {
			System.out.println("���� �̱�");

			/// �̱� ȭ�� ������
			lblNewLabel.setIcon(new ImageIcon("img/�¸�.png"));

			/// �̱�� �ļ� ó��
			for (int i = 0; i < btn.length; i++) {

				btn[i].setEnabled(false);
			}

		} else {

			int ti = Integer.parseInt(ri);

			btn[ti].setIcon(new ImageIcon("img/�̻�����.png"));
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
		lblNewLabel.setIcon(new ImageIcon("img/[ũ�⺯ȯ]�Ǿ�.png"));
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

//		JButton gameGetOut = new JButton("���� ������");
//		contentPane.add(gameGetOut, BorderLayout.SOUTH);
//
//		gameGetOut.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// ������ ���� ���ܾ��ϳ�?
//				dispose();// ���� ������ ��ư�� ������ ���� â�� ������ �ϱ�.
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
