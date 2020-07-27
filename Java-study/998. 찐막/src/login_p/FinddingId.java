package login_p;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import login_p.LoginTest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FinddingId extends JFrame{

	String id, pw, name, birth, email;
	GameUserDAO dao = new GameUserDAO();
	GameUserDTO dto = new GameUserDTO();
	ArrayList<String> fId = new ArrayList<String>();



	//LoginTest jt = new LoginTest();
	JTextField nameText;
	JTextField idText;
	JTextField birthText;
	JTextField emailText;
	JButton closeBtn;
	JButton findBtn;
	public FinddingId() {
		getContentPane().setLayout(null);
		setTitle("���� ã��");
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		setSize(455, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel lbJoinId = new JLabel("�̸�");
		lbJoinId.setBounds(47, 75, 74, 27);
		getContentPane().add(lbJoinId);

		JLabel lbJoinId_1_1 = new JLabel("�������");
		lbJoinId_1_1.setBounds(47, 120, 74, 27);
		getContentPane().add(lbJoinId_1_1);

		JLabel lbJoinId_1_1_1 = new JLabel("�̸���");
		lbJoinId_1_1_1.setBounds(47, 160, 74, 27);
		getContentPane().add(lbJoinId_1_1_1);

		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(172, 75, 209, 27);
		getContentPane().add(nameText);

		birthText = new JTextField();
		birthText.setColumns(10);
		birthText.setBounds(172, 120, 209, 27);
		getContentPane().add(birthText);

		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(172, 160, 209, 27);
		getContentPane().add(emailText);

		closeBtn = new JButton("����");
		closeBtn.setBounds(227, 262, 129, 29);
		getContentPane().add(closeBtn);
		closeBtn.addActionListener(actBtn);

		findBtn = new JButton("ã��");
		findBtn.setBounds(81, 262, 129, 29);
		getContentPane().add(findBtn);
		findBtn.addActionListener(actBtn);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	ActionListener actBtn = new ActionListener() {


		ArrayList<GameUserDTO> pwF = new ArrayList<GameUserDTO>();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeBtn) {
				dispose();
				new LoginTest();
			}

			if (e.getSource() == findBtn) {
				//String id = idText.getText().trim();
				String name = nameText.getText().trim();
				String birth = birthText.getText().trim();
				String mail = emailText.getText().trim();

				while (true) {
					GameUserDTO dto = new GameUserDTO();

					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���");
						break;
					} else {
						dto.setName(name);
					}

					if (birth.equals("")) {
						JOptionPane.showMessageDialog(null, "������ �Է��� �ּ���");
						break;
					} else {
						dto.setBirth(birth);
					}

					if (mail.equals("")) {
						JOptionPane.showMessageDialog(null, "������ �Է��� �ּ���");
						break;
					} else {
						dto.setEmail(mail);
					}

					pwF.add(new GameUserDAO().find(name, birth, mail));

					String findname = pwF.get(0).getName();
					String findId = pwF.get(0).getId();
					String findPw = pwF.get(0).getPw();

					if (findname != null && findId != null && findPw != null) {
						JOptionPane.showMessageDialog(null,
								findname + "���� ���̵� : " + findId + " ��й�ȣ : " + findPw + " �Դϴ�.");
						pwF.clear();
					} else {
						JOptionPane.showMessageDialog(null, "��ȿ���� ���� ���� �Դϴ�.");
						pwF.clear();
					}

					System.out.println(pwF);
					break;

				}

			}

		}

	};

	public static void main(String[] args) {
		new FinddingId();

	}
}