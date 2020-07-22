package login;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginTest extends JFrame {
	GameDTO dto = new GameDTO();
	GameDAO dao = new GameDAO();
	ArrayList<String> data;

	Container contentPane;
	private JTextField idText;
	private JPasswordField pwText;

	public LoginTest() {

		setTitle("��������");
		contentPane = getContentPane();
		setSize(455, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel lbId = new JLabel("���̵�");
		lbId.setBounds(69, 206, 74, 44);
		getContentPane().add(lbId);

		JLabel lbPw = new JLabel("��й�ȣ");
		lbPw.setBounds(69, 246, 74, 44);
		getContentPane().add(lbPw);

		JButton btnLogin = new JButton("�α���");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(182, 297, 107, 29);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (dao.Login(idText.getText(), pwText.getText()) == 0)
					JOptionPane.showMessageDialog(null, "�α��� ����");
				if (dao.Login(idText.getText(), pwText.getText()) == 1)
					JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ Ȯ�����ּ���");

			}
		});

		// ȸ������ ��ư Ŭ���� ȸ������â guiâ ��
		JButton btnJoin = new JButton("ȸ������");
		btnJoin.setBackground(Color.LIGHT_GRAY);
		btnJoin.setBounds(39, 337, 107, 19);
		getContentPane().add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JoinTest jt = new JoinTest();

				jt.setVisible(true);

			}
		});

		JButton btnFindingInfo = new JButton("���̵�/��й�ȣã��");
		btnFindingInfo.setBackground(Color.LIGHT_GRAY);
		btnFindingInfo.setBounds(143, 337, 154, 19);
		getContentPane().add(btnFindingInfo);

		idText = new JTextField();
		idText.setBounds(160, 215, 166, 27);
		getContentPane().add(idText);

		idText.setColumns(10);

		pwText = new JPasswordField();
		pwText.setBounds(160, 255, 166, 27);
		getContentPane().add(pwText);
		btnFindingInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FinddingId fd = new FinddingId();

				fd.setVisible(true);

			}
		});

		// ���� ��ư ���� �� ����
		JButton btnCloseGame = new JButton("����");
		btnCloseGame.setBackground(Color.LIGHT_GRAY);
		btnCloseGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCloseGame.setBounds(295, 337, 107, 19);
		getContentPane().add(btnCloseGame);
		btnCloseGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCloseGame)
					System.exit(0);

			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new LoginTest();

	}
}
