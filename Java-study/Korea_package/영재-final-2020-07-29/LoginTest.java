package login_p;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;
import lobby_p.Lobby_Main;
import java.awt.Font;

public class LoginTest extends JFrame implements DDongInter {

	boolean login_chk = false;
	GameUserDTO dto = new GameUserDTO();
	GameUserDAO dao = new GameUserDAO();

	Container contentPane;
	JTextField idText;
	JPasswordField pwText;
	ArrayList<String> idstr;

	DDongData ddos;
	JLabel lbId;
	JLabel lbPw;
	JButton btnLogin;
	JButton btnJoin;
	JButton btnFindingInfo;
	JButton btnCloseGame;
	LobbyDAO lodao;
	LobbyDTO lodto;
	String ids,ids2,ids3;

	public ClientNetWork cn;

	boolean lochk;

	public LoginTest() {

		setTitle("��������"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		contentPane = getContentPane();
//      setSize(455, 615);
		setSize(600, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		lbId = new JLabel("ID");
		lbId.setFont(new Font("����", Font.BOLD, 25));
		lbId.setBounds(130, 202, 74, 44);
		getContentPane().add(lbId);

		lbPw = new JLabel("PW");
		lbPw.setFont(new Font("����", Font.BOLD, 26));
		lbPw.setBounds(130, 246, 74, 44);
		getContentPane().add(lbPw);

		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setFont(new Font("����", Font.PLAIN, 25));
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(236, 321, 107, 29);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(actBut);

		btnJoin = new JButton("ȸ������");
		btnJoin.setBackground(Color.LIGHT_GRAY);
		btnJoin.setBounds(108, 379, 107, 35);
		getContentPane().add(btnJoin);
		btnJoin.addActionListener(actBut);

		btnFindingInfo = new JButton("���� ã��");
		btnFindingInfo.setBackground(Color.LIGHT_GRAY);
		btnFindingInfo.setBounds(214, 379, 154, 35);
		getContentPane().add(btnFindingInfo);

		idText = new JTextField();
		idText.setFont(new Font("����", Font.BOLD, 25));
		idText.setBounds(236, 206, 213, 36);
		getContentPane().add(idText);

		idText.setColumns(10);

		pwText = new JPasswordField();
		pwText.setBounds(236, 256, 213, 35);
		getContentPane().add(pwText);
		btnFindingInfo.addActionListener(actBut);
		pwText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnCloseGame = new JButton("������");
		btnCloseGame.setBackground(Color.LIGHT_GRAY);
		btnCloseGame.setBounds(367, 379, 107, 35);
		getContentPane().add(btnCloseGame);
		btnCloseGame.addActionListener(actBut);

		idstr = new ArrayList<String>();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void roomchk() {
		
		GameRoomDTO gdto= new GameRoomDTO();
		GameRoomDAO gdao = new GameRoomDAO();
		gdto = gdao.roomdPepleetail(idText.getText());
		ids2 = gdto.getUser1();
		ids3 = gdto.getUser2();
	}
	
	void robichk() {
		lodao = new LobbyDAO();
		lodto = new LobbyDTO();
		lodto = lodao.detail(idText.getText());
		ids = lodto.getId();

	}

	Socket soc;
	ActionListener actBut = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnLogin) {
				try {

					if (dao.Login(idText.getText(), pwText.getText())) {
						roomchk();
						robichk();
						
					if (idText.getText().equals(ids) 
						|| idText.getText().equals(ids2)
					    || idText.getText().equals(ids3)) {
						JOptionPane.showMessageDialog(null, "�̹��������Դϴ�");
						idText.setText("");
						pwText.setText("");
						idText.setFocusable(true);
					} else {
						JOptionPane.showMessageDialog(null, "�α��� ����");

						new LobbyDAO().insert(idText.getText());
						cn = new ClientNetWork(idText.getText());
						Lobby_Main mm = new Lobby_Main(cn);

						ddos = new DDongData();
						ddos.type = "�κ�";
						cn.send(ddos);

						dispose();
					}

					} else if (dao.Login(idText.getText(), pwText.getText()) == false) {

						JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ Ȯ�����ּ���");
					}

				} catch (Exception e1) {

				}
			}

			if (e.getSource() == btnJoin) {
				dispose();
				new JoinTest();

			}

			if (e.getSource() == btnFindingInfo) {
				dispose();
				new FinddingId();

			}

			if (e.getSource() == btnCloseGame) {
				System.exit(0);
			}
		}
	};

	public static void main(String[] args) {

		new LoginTest();

	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub

	}

}