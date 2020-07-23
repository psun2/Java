package lobby;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import lobby.RankMain_GUI;
import lobby.UserList_Main;


public class Lobby_Main_Clear extends JFrame implements DDongInter{

	
	public ClientNetWork cn;
//	String user = "anjdijs";
	DDongData dData;

	JTextArea chatArea; // ��ȭ ������� �ԷµǴ� â
	JTextField wrArea; // �޼��� �Է�â	

//	JButton roombtn;
//	Integer roomN;
	String ttt;

	private JPanel contentPane;


	public Lobby_Main_Clear() {
		
		
		setBounds(300, 50, 1130, 950);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// == ȭ�� ���κ� =====================

		// -- �� ����Ʈ -------
		roomBtn();
		//		 -- �� ����Ʈ �� -------


		// -- �������� ����Ʈ -------
		JPanel userList = new UserList_Main();
		userList.setBounds(830, 30, 261, 600);
		userList.setBackground(Color.CYAN);
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
		rankBtn.setBounds(830, 640, 261, 228);
		rankBtn.setBackground(Color.PINK);
		contentPane.add(rankBtn);

		rankBtn.addActionListener(new RankBtnAction());
		// -- ��ŷ ��ư �� -------

		// == ȭ�� �Ʒ��κ� �� =====================



		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void roomBtn() {

		JPanel roomList = new JPanel();
		roomList.setBounds(30,30,780,1200);
		roomList.setLayout(null);
		JScrollPane js = new JScrollPane(roomList);
		js.setBounds(30, 30, 780, 600);
		js.setLayout(null);
		contentPane.add(js);


		for (int i = 1; i < 11; i++) {

			Integer roomN = i;
			int btnX = 0;
			int btnY = 0;
			
			if(i%3==1) {

				btnY += 200*(i/3);
			}else if(i%3==2) {
				btnX = 260;
				btnY += 200*(i/3);
			}else if(i%3==0) {
				btnX = 520;
				btnY += 200*(i/3-1);
			}
			
			JButton roombtn = new JButton();
			roombtn.setBounds(btnX, btnY, 260, 200);
			roombtn.setBackground(Color.yellow);
			JLabel btnName = new JLabel();
			btnName.setFont(new Font("�������",Font.BOLD, 15));
			ttt = "<html>NO. " + roomN + " (0 / 2)<br> >>�游��� << </html>";
			btnName.setText(ttt);
			roombtn.add(btnName);
			js.add(roombtn);


			// ���� ������Ʈ ������ �۵� �� ����
			if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
					new GameRoomDAO().detailroom(roomN).getUser2()==null) {

				roombtn.setBackground(Color.cyan);
				ttt = "<html>NO. " + roomN + "  (1 / 2)<br> >> �����ϱ� << </html>";
				btnName.setText(ttt);

			} else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
					new GameRoomDAO().detailroom(roomN).getUser2()!=null) {

				roombtn.setBackground(Color.MAGENTA);
				ttt = "<html>NO. " + roomN + " (2 / 2)<br>-- �ο��� ���� �� �ֽ��ϴ� --</html>";
				btnName.setText(ttt);
			}


			roombtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					GameRoomDTO dto = new GameRoomDTO();

					if(new GameRoomDAO().detailroom(roomN).getUser1()==null &&
							new GameRoomDAO().detailroom(roomN).getUser2()==null) {
						
						roombtn.setBackground(Color.cyan);
						ttt = "<html>NO. " + roomN + " (1 / 2)<br> >> �����ϱ� << </html>";
						btnName.setText(ttt);
						
						
						System.out.println(roomN+"���� ������ ���� ���� ��������");
						dto.setNo(roomN);
						dto.setUser1(cn.id);

						new GameRoomDAO().modifyUser1(dto);
						
						new LobbyDAO().delete(cn.id);
						
						
//						JFrame goGame = new PuyoFrame();
//						dispose();

						dData = new DDongData();
						dData.type = "�κ�";
						System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");
						cn.send(dData);
						
						dData = new DDongData();
						dData.dst = new ArrayList<String>();
						dData.type = "����";
						dData.dst.add(cn.id);
						System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");
						cn.send(dData);
						


					} else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
							new GameRoomDAO().detailroom(roomN).getUser2()==null) {

						roombtn.setBackground(Color.MAGENTA);
						ttt = "<html>NO. " + roomN + "(2 / 2)<br>-- �ο��� ���� �� �ֽ��ϴ� --</html>";
						btnName.setText(ttt);

						System.out.println(roomN+"���� ������ 1�� �ִ°��� ��������");
						
						dto.setNo(roomN);
						dto.setUser2(cn.id);

						new GameRoomDAO().modifyUser2(dto);
						
						new LobbyDAO().delete(cn.id);
						
//						JFrame goGame = new PuyoFrame();
//						dispose();

						dData = new DDongData();
						dData.type = "�κ�";
						System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");
						cn.send(dData);
						
						dData = new DDongData();
						dData.type = "����";
						dData.dst.add(cn.id);
						cn.send(dData);
						System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������2");
						
					} else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
							new GameRoomDAO().detailroom(roomN).getUser2()!=null) {
						
						System.out.println(roomN+"���� �ִ°��� ��������");
						JOptionPane.showMessageDialog(null, "���� �� á���ϴ�.\n�ٸ����� �̿��� �ּ���!!");
					}

				}
			});
		}
	}


	// == ��ŷ��ư ������ ===
	class RankBtnAction implements ActionListener{ // ��ŷ ��ư ������

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
							dData.data= wrArea.getText();
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

	
	public static void main(String[] args) {
		new Lobby_Main_Clear();
	}

	@Override
	public void reciver(DDongData dd) {
		
		if(dData.type.equals("ä��")) {
			System.out.println(dData.type.toString()+" - ����Ʈ���ִ°��ε� ������Ÿ�Թ���");

			chatArea.append(dData.data+"\n"); // ���⼭ �������µ�
			// �о� �� ������ ��ȭâ�� �Է����ְ� �ٹٲ��� ���ش�
			chatArea.setCaretPosition(chatArea.getDocument().getLength());
			// ��ũ���� �� ������ ��ȭ�ʿ� ��ġ���ش�

		} else if(dData.type.equals("�κ�")) {

			roomBtn();
		}
		
	}
}
