package lobby_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;
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

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import game_p.GameRoom_GUI;
import game_p.PuyoFrame;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;
import lobby_p.RankMain_GUI;

public class Lobby_Main_Clear extends JFrame implements DDongInter {

	public ClientNetWork cn;
	DDongData dData;

	JTextArea chatArea; // ��ȭ ������� �ԷµǴ� â
	JTextField wrArea; // �޼��� �Է�â
	JScrollPane js;
	JPanel roomList;

	String ttt;
	UserList_Main userList;

	private JPanel contentPane;

	class UserList_Main extends JPanel {

		String id;
		JTable lobbyT;
		JScrollPane sp2;

		public UserList_Main() {

			setBounds(0, 0, 636, 902);
			setLayout(null);

			JLabel lobbyList = new JLabel("�κ�");
			lobbyList.setBounds(0, 0, 261, 50);
			lobbyList.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
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
			lobbyT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
			sp2 = new JScrollPane(lobbyT);
			sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp2.setBounds(0, 50, 261, 550);
			add(sp2);

		}
	}

	public Lobby_Main_Clear(ClientNetWork cn) {

		this.cn = cn;
		cn.ddInter = this;

		setBounds(300, 50, 1130, 950);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		userList = new UserList_Main();
		userList.setBounds(830, 30, 261, 600);
		userList.setBackground(Color.CYAN);

		// == ȭ�� ���κ� =====================

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
		chatArea.setBounds(30, 640, 782, 228);
		chatArea.setBackground(Color.MAGENTA);
		contentPane.add(chatArea);
		// -- ä��â �� -------

		// -- ��ŷ ��ư -------
		JButton rankBtn = new JButton("��ŷ");
		rankBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
		rankBtn.setBounds(830, 640, 261, 130);
		rankBtn.setBackground(Color.PINK);
		contentPane.add(rankBtn);

		rankBtn.addActionListener(new RankBtnAction());
		// -- ��ŷ ��ư �� -------

		// -- ������ ��ư -------
		JButton exitBtn = new JButton("��������");
		exitBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
		exitBtn.setBounds(830, 778, 261, 190);
		exitBtn.setBackground(Color.lightGray);
		contentPane.add(exitBtn);

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LobbyDAO().delete(cn.id);
				dispose();
			}
		});
		// -- ������ ��ư -------

		// == ȭ�� �Ʒ��κ� �� =====================

		LobbyDAO dao = new LobbyDAO();

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// dao.delete(cn.id); // Ŭ��ũ�� ������ �κ񸮽�Ʈ ��񿡼� �����ǰ� �����ϱ�

		DDongData data = new DDongData();
		data.type = "�κ�";
		cn.send(data);

	}

	void roomBtn() { // ���ư
		userList.init();
		if (js != null) {
			roomList.removeAll();
			js.removeAll();
			contentPane.remove(js);
		}

		roomList = new JPanel();
		roomList.setBounds(30, 30, 780, 1200);
		roomList.setLayout(null);
		js = new JScrollPane(roomList);
		js.setBounds(30, 30, 780, 600);
		js.setLayout(null);
		contentPane.add(js);

		for (int i = 1; i < 11; i++) {

			Integer roomN = i;
			int btnX = 0;
			int btnY = 0;

			if (i % 3 == 1) {

				btnY += 200 * (i / 3);
			} else if (i % 3 == 2) {
				btnX = 260;
				btnY += 200 * (i / 3);
			} else if (i % 3 == 0) {
				btnX = 520;
				btnY += 200 * (i / 3 - 1);
			}

			JButton roombtn = new JButton();
			roombtn.setBounds(btnX, btnY, 260, 200);
			roombtn.setBackground(Color.yellow);
			JLabel btnName = new JLabel();
			btnName.setFont(new Font("�������", Font.BOLD, 15));
			ttt = "<html>NO. " + roomN + " (0 / 2)<br> >>�游��� << </html>";
			btnName.setText(ttt);
			roombtn.add(btnName);
			js.add(roombtn);

			// ���� ������Ʈ ������ �۵� �� ����
			if (new GameRoomDAO().detailroom(roomN).getUser1() != null
					&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

				roombtn.setBackground(Color.cyan);
				ttt = "<html>NO. " + roomN + "  (1 / 2)<br> >> �����ϱ� << </html>";
				btnName.setText(ttt);

			} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
					&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

				roombtn.setBackground(Color.MAGENTA);
				ttt = "<html>NO. " + roomN + " (2 / 2)<br>-- �ο��� ���� �� �ֽ��ϴ� --</html>";
				btnName.setText(ttt);
			}

			// ���ư�� ��������
			roombtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					GameRoomDTO dto = new GameRoomDTO();

					dData = new DDongData();
//               dData.dst = new ArrayList<String>();

					if (new GameRoomDAO().detailroom(roomN).getUser1() == null
							&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

						roombtn.setBackground(Color.cyan);
						ttt = "<html>NO. " + roomN + " (1 / 2)<br> >> �����ϱ� << </html>";
						btnName.setText(ttt);

						System.out.println(roomN + "���� ������ ���� ���� ��������");
						dto.setNo(roomN);
						dto.setUser1(cn.id);

						new GameRoomDAO().modifyUser1(dto);

						new LobbyDAO().delete(cn.id);

//                  System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");

						GameRoom_GUI goGame = new GameRoom_GUI(cn.id, roomN); // ���⼭ ���ӻ���
						goGame.cn = cn;
						cn.ddInter = goGame;

						dData.type = "����"; //
						cn.send(dData);
						System.out.println(dData.type.toString() + "," + dData.toString() + "���� �ι� �������� Ÿ��,������1");

						dispose();

					} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
							&& new GameRoomDAO().detailroom(roomN).getUser2() == null) {

						roombtn.setBackground(Color.MAGENTA);
						ttt = "<html>NO. " + roomN + "(2 / 2)<br>-- �ο��� ���� �� �ֽ��ϴ� --</html>";
						btnName.setText(ttt);

						System.out.println(roomN + "���� ������ 1�� �ִ°��� ��������");

						dto.setNo(roomN);
						dto.setUser2(cn.id);

						new GameRoomDAO().modifyUser2(dto);

						new LobbyDAO().delete(cn.id);

						GameRoom_GUI goGame = new GameRoom_GUI(cn.id, roomN); // ���⼭ ���ӻ���
						goGame.cn = cn;
						cn.ddInter = goGame;

						dData.type = "����"; // ���ӹ濡�� DB������Ʈ ������ �˷��ֱ� ���ؼ� ���
						cn.send(dData);
						System.out.println(dData.type.toString() + "," + dData.toString() + "���� �ι� �������� Ÿ��,������1");
						dispose();

					} else if (new GameRoomDAO().detailroom(roomN).getUser1() != null
							&& new GameRoomDAO().detailroom(roomN).getUser2() != null) {

						System.out.println(roomN + "���� �ִ°��� ��������");
						JOptionPane.showMessageDialog(null, "���� �� á���ϴ�.\n�ٸ����� �̿��� �ּ���!!");
					}

				}
			});
		}
		js.setVisible(false);
		js.setVisible(true);
	}

	// == ��ŷ��ư ������ ===
	class RankBtnAction implements ActionListener { // ��ŷ ��ư ������

		public RankBtnAction() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFrame rankPop = new JFrame();
			rankPop.setBounds(500, 75, 536, 700);
			rankPop.setTitle("��ŷ");
			rankPop.add(new RankMain_GUI());
			rankPop.setVisible(true);
		}
	}
	// == ��ŷ��ư ������ �� ===

	class ChatUser extends JPanel {

		public ChatUser() { // ������

			try {

				setBounds(0, 0, 782, 228);
				setLayout(null);

				// chatArea - ��ȭâ
				chatArea = new JTextArea(); // ��ȭâ
				chatArea.setEnabled(false); // ��ȭ�� �ԷµǴ� �����̹Ƿ� �ؽ�Ʈ�� �Է����� ���ϰ� ���´�
				JScrollPane js = new JScrollPane(chatArea);
				js.setBounds(0, 0, 782, 188);
				chatArea.setBackground(new Color(250, 250, 250));
				chatArea.setFont(new Font("�����ٸ����", Font.BOLD, 16));
				chatArea.setForeground(Color.black);
				add(js);
				// == ��ȭâ swing ==============

				// wrArea - �޼��� �Է�â
				wrArea = new JTextField();
				wrArea.setBounds(0, 188, 782, 40);
				wrArea.setFont(new Font("�����ٸ����", Font.BOLD, 16));
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
							System.out.println("ä�� ����");

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

		System.out.println("Tq" + dd);

		if (dd.type.equals("ä��")) {

			System.out.println(dd.data.toString() + " - ����Ʈ���ִ°��ε� ������Ÿ�Թ���");
			chatArea.append("[" + dd.src + "]  " + dd.data + "\n"); // ���⼭ �������µ�
			// �о� �� ������ ��ȭâ�� �Է����ְ� �ٹٲ��� ���ش�
			chatArea.setCaretPosition(chatArea.getDocument().getLength());
			// ��ũ���� �� ������ ��ȭ�ʿ� ��ġ���ش�

		} else if (dd.type.equals("�κ�") || dd.type.equals("����")) {
			System.out.println("����� �κ�");
			roomBtn();
		}

	}
}