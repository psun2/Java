package lobby_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import game_p.WaitRoomFrame;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;

public class Lobby_Main extends JFrame implements DDongInter, WindowListener {

	public ClientNetWork cn;
	DDongData dData;

	JTextArea textArea; // ��ȭ ������� �ԷµǴ� â
	JTextField wrArea; // �޼��� �Է�â

	JScrollPane js;
	JPanel roomList; // �渮��Ʈ
	String ttt;

	UserList_Main userList;

	private JPanel contentPane;

	public Lobby_Main(ClientNetWork cn) {

		this.cn = cn;
		cn.ddInter = this;

		setSize(806, 700);
		setLocationRelativeTo(null);
		setTitle("��������"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// == ȭ�� ���κ� =====================

		// -- �������� ����Ʈ -------
		userList = new UserList_Main();
		userList.setBounds(605, 20, 180, 420);
		// -- �������� ����Ʈ �� -------

		// -- �� ����Ʈ -------
		roomBtn();
		// -- �� ����Ʈ �� -------

		// -- �������� ����Ʈ -------
		contentPane.add(userList);
		// -- �������� ����Ʈ �� -------

		// == ȭ�� ���κ� �� =====================

		// == ȭ�� �Ʒ��κ� =====================

		// -- ä��â -------
		JPanel chatArea = new ChatUser();
		chatArea.setBounds(20, 455, 570, 200);
		chatArea.setBackground(Color.MAGENTA);
		contentPane.add(chatArea);
		// -- ä��â �� -------

		// -- ��ŷ ��ư -------
		JButton rankBtn = new JButton("��ŷ");
		rankBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
		rankBtn.setBounds(605, 455, 180, 115);
		rankBtn.setBackground(Color.PINK);
		contentPane.add(rankBtn);

		rankBtn.addActionListener(new RankBtnAction());
		// -- ��ŷ ��ư �� -------

		// -- ������ ��ư -------
		JButton exitBtn = new JButton("��������");
		exitBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
		exitBtn.setBounds(605, 579, 180, 75);
		exitBtn.setBackground(Color.lightGray);
		contentPane.add(exitBtn);

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LobbyDAO().delete(cn.id);

				dData = new DDongData();
				dData.type = "�κ�";
				cn.send(dData);

				System.exit(0);
			}
		});
		// -- ������ ��ư -------

		// == ȭ�� �Ʒ��κ� �� =====================

		LobbyDAO dao = new LobbyDAO();

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

//      addWindowListener(this);
	}

	void roomBtn() { // ���ư

		if (js != null) {

			roomList.removeAll();
			js.removeAll();
			contentPane.remove(js);
		}

		userList.init();

		roomList = new JPanel();
		roomList.setPreferredSize(new Dimension(550, 840));
		// roomList.setBounds(30,30,770,1200);
		js = new JScrollPane(roomList);
		js.setBounds(20, 20, 570, 420);
		roomList.setLayout(null);
		contentPane.add(js);

		for (int i = 1; i < 19; i++) {

			Integer roomN = i;
			int btnX = 0;
			int btnY = 0;

			if (i % 3 == 1) {

				btnY += 140 * (i / 3);
			} else if (i % 3 == 2) {
				btnX = 184;
				btnY += 140 * (i / 3);
			} else if (i % 3 == 0) {
				btnX = 368;
				btnY += 140 * (i / 3 - 1);
			}

			JButton roombtn = new JButton();
			roombtn.setBounds(btnX, btnY, 184, 140);
			roombtn.setBackground(Color.yellow);
			JLabel btnName = new JLabel();
			btnName.setFont(new Font("����", Font.BOLD, 14));
			ttt = "<html>NO. " + roomN + " (0 / 2)<br><br> >> �游��� << </html>";
			btnName.setText(ttt);
			roombtn.add(btnName);
			roomList.add(roombtn);

			// ���� ������Ʈ ������ �۵� �� ����
			if (new GameRoomDAO().detailroom(roomN).getUser1() != null
					&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

				roombtn.setBackground(Color.cyan);
				ttt = "<html>NO. " + roomN + "  (1 / 2)<br><br> >> �����ϱ�</html>";
				btnName.setText(ttt);

			} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
					&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

				roombtn.setBackground(Color.MAGENTA);
				ttt = "<html>NO. " + roomN + " (2 / 2)<br><br> - ����Ұ� - </html>";
				btnName.setText(ttt);
			} else if (new GameRoomDAO().detailroom(roomN).getUser1() == null
					&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

				roombtn.setBackground(Color.cyan);
				ttt = "<html>NO. " + roomN + "  (1 / 2)<br><br> >> �����ϱ�</html>";
				btnName.setText(ttt);
			}

			// ���ư�� ��������
			roombtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					GameRoomDTO dto = new GameRoomDTO();

					dData = new DDongData();

					if (new GameRoomDAO().detailroom(roomN).getUser1() == null
							&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

						roombtn.setBackground(Color.cyan);
						ttt = "<html>NO. " + roomN + " (1 / 2)<br><br> >> �����ϱ� </html>";
						btnName.setText(ttt);

						dto.setNo(roomN);
						dto.setUser1(cn.id);

						new GameRoomDAO().modifyUser1(dto);

						new LobbyDAO().delete(cn.id);

						// System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� ��������
						// Ÿ��,������1");

						WaitRoomFrame goGame = new WaitRoomFrame(cn.id, roomN); // ���⼭ ���ӻ���
						goGame.cn = cn;
						cn.ddInter = goGame;

						dData = new DDongData();
						dData.type = "����"; //
						cn.send(dData);

						dispose();

					} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
							&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

						roombtn.setBackground(Color.MAGENTA);
						ttt = "<html>NO. " + roomN + "(2 / 2)<br><br> - ����Ұ� - </html>";
						btnName.setText(ttt);

						dto.setNo(roomN);
						dto.setUser2(cn.id);

						new GameRoomDAO().modifyUser2(dto);

						new LobbyDAO().delete(cn.id);

						WaitRoomFrame goGame = new WaitRoomFrame(cn.id, roomN); // ���⼭ ���ӻ���
						goGame.cn = cn;
						cn.ddInter = goGame;

						dData = new DDongData();
						dData.type = "����"; // ���ӹ濡�� DB������Ʈ ������ �˷��ֱ� ���ؼ� ���
						cn.send(dData);

						dispose();

					} else if (new GameRoomDAO().detailroom(roomN).getUser1() == null
							&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

						roombtn.setBackground(Color.MAGENTA);
						ttt = "<html>NO. " + roomN + "(2 / 2)<br><br> - ����Ұ� - </html>";
						btnName.setText(ttt);

						dto.setNo(roomN);
						dto.setUser1(cn.id);

						new GameRoomDAO().modifyUser1(dto);

						new LobbyDAO().delete(cn.id);

						// System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� ��������
						// Ÿ��,������1");

						WaitRoomFrame goGame = new WaitRoomFrame(cn.id, roomN); // ���⼭ ���ӻ���
						goGame.cn = cn;
						cn.ddInter = goGame;

						dData = new DDongData();
						dData.type = "����"; //
						cn.send(dData);

						dispose();

					} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
							&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

						JOptionPane.showMessageDialog(null, "���� �� á���ϴ�.\n�ٸ����� �̿��� �ּ���!!");
					}

				}
			});
		}

		js.setVisible(false);
		js.setVisible(true);
	}

	class UserList_Main extends JPanel {

		String id;
		JTable lobbyT;
		JScrollPane sp2;

		public UserList_Main() {

			setBounds(0, 0, 180, 420);
			setBackground(Color.white);
			setLayout(null);

			JLabel lobbyList = new JLabel("�κ� ���� ����");
			lobbyList.setBounds(0, 0, 180, 25);
			lobbyList.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
			lobbyList.setHorizontalAlignment(JLabel.CENTER);
			add(lobbyList);
		}

		void init() {

			if (sp2 != null) {

				lobbyT.removeAll();
				sp2.removeAll();
			}

			ArrayList<LobbyDTO> arr = new LobbyDAO().list();

			Object[] index = { "ID" };
			Object[][] lobbyL = new Object[arr.size()][1];

			for (int i = 0; i < arr.size(); i++) {

				lobbyL[i][0] = arr.get(i).getId();
			}

			lobbyT = new JTable(lobbyL, index);
			lobbyT.setEnabled(false);
			lobbyT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
			sp2 = new JScrollPane(lobbyT);
			sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp2.setBounds(0, 30, 180, 390);
			add(sp2);

		}
	}

	// == ��ŷ��ư ������ ===
	class RankBtnAction implements ActionListener { // ��ŷ ��ư ������

		public RankBtnAction() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFrame rankPop = new JFrame();
			rankPop.setSize(520, 570);
			rankPop.setLocationRelativeTo(null);
			rankPop.setTitle("��ŷ");
			rankPop.setIconImage(new ImageIcon("./img/logo.png").getImage());
			rankPop.getContentPane().add(new RankMain_GUI());
			rankPop.setVisible(true);
		}
	}
	// == ��ŷ��ư ������ �� ===

	class ChatUser extends JPanel {

		public ChatUser() { // ������

			try {

				setBounds(0, 0, 570, 100);
				setLayout(null);

				// chatArea - ��ȭâ
				textArea = new JTextArea(); // ��ȭâ
				textArea.setEnabled(false); // ��ȭ�� �ԷµǴ� �����̹Ƿ� �ؽ�Ʈ�� �Է����� ���ϰ� ���´�
				JScrollPane js = new JScrollPane(textArea);
				js.setBounds(0, 0, 570, 165);
				textArea.setBackground(new Color(250, 250, 250));
				textArea.setFont(new Font("����", Font.BOLD, 15));
				textArea.setForeground(Color.black);
				add(js);
				// == ��ȭâ swing ==============

				// wrArea - �޼��� �Է�â
				wrArea = new JTextField();
				wrArea.setBounds(0, 165, 570, 35);
				// wrArea.setBackground(Color.magenta);
				wrArea.setFont(new Font("����", Font.BOLD, 16));
				add(wrArea);
				// == �޼��� �Է�â swing ==============

				wrArea.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						try {

							dData = new DDongData();
							dData.type = "ä��";
							dData.data = wrArea.getText();
							cn.send(dData);

							wrArea.setText(""); // �޼����� �Է��� �޼��� â�� ����ش�
							wrArea.setFocusable(true); // Ŀ���� �Ǿ����� �����ش�

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // == ChatUser_test() �� =====================
	}

	@Override
	public void reciver(DDongData dd) {

		if (dd.type.equals("ä��")) {

			textArea.append(" [" + dd.src + "]  " + dd.data + "\n"); // ���⼭ �������µ�
			// �о� �� ������ ��ȭâ�� �Է����ְ� �ٹٲ��� ���ش�
			textArea.setCaretPosition(textArea.getDocument().getLength());
			// ��ũ���� �� ������ ��ȭ�ʿ� ��ġ���ش�

		} else if (dd.type.equals("�κ�") || dd.type.equals("����")) {

			if (dd.dst == null)
				roomBtn();
		}

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}