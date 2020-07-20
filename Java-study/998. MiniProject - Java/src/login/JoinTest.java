package login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class JoinTest extends JFrame {

	public ArrayList<String> data = new ArrayList<String>(); //id, pw, name, email, birth ���� ���� �ؼ� main���� �Ѱ���

	GameDTO dto = new GameDTO();
	GameDAO dao = new GameDAO();

	String[] regex = { 
			"^[a-zA-Z0-9_]{5,12}$", // ���̵� ����� ���ڸ� 5���ڿ��� 12���� ����
			"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // ��������Ư������ ���� 8�� �̻�
			"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // ��������Ư������ ���� 8�� �̻�
			"^[��-�R]{2,4}$", // �̸��ѱ� 2~4��
			"^[0-9_]{1,6}$", // 
			"^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$" // �̸��� ��ȿ�� �˻�
	};

	String idc = "[a-zA-Z0-9]*(([a-zA-Z][0-9]*)|([0-9][a-zA-Z]*))";

	public String id;			// id ��Ƶ� ����
	public String pw;			// pw ��Ƶ� ����
	public String pw2;			// pwȮ�ζ� 
	public String name;		// �̸�
	public String email;		// ����
	public String birth;		// ����

	JTextField JoinIdText = null;
	JPasswordField passwordField;
	JPasswordField passwordField_1;
	JTextField JoinBirthText;
	JTextField JoinNameText;
	JTextField JoinEmailText;
	JFrame jf;

	JButton btnCancelMake;	//���� ��ư
	JButton btnMakeMember;	//ȸ�����Խ� �Ϸ� ��ư
	JButton btnIdChk;		//���̵� �ߺ� �˻�
	

	public JoinTest() {	// ȸ�� ���� guiâ

		setTitle("ȸ������");
		getContentPane().setLayout(null);
		setSize(455, 615);				
		setLocationRelativeTo(null);	//gui ȭ�� �� �߾����� ������
		getContentPane().setLayout(null);
		setResizable(false);	//gui ������ ���� �Ұ�

		JLabel lbJoinId = new JLabel("���̵�");
		lbJoinId.setBounds(17, 30, 74, 27);
		getContentPane().add(lbJoinId);

		
		JLabel lbJoinPw = new JLabel("��й�ȣ");
		lbJoinPw.setBounds(17, 72, 74, 27);
		getContentPane().add(lbJoinPw);

		JLabel lbJoinPwC = new JLabel("��й�ȣ Ȯ��");
		lbJoinPwC.setBounds(17, 114, 122, 27);
		getContentPane().add(lbJoinPwC);
		
		JLabel lbName = new JLabel("�̸�");
		lbName.setBounds(17, 164, 74, 27);
		getContentPane().add(lbName);


		JLabel lbJoinBirth = new JLabel("�������");
		lbJoinBirth.setBounds(17, 206, 74, 27);
		getContentPane().add(lbJoinBirth);

		JLabel lbJoinEmail = new JLabel("�̸���");
		lbJoinEmail.setBounds(17, 248, 74, 27);
		getContentPane().add(lbJoinEmail);

		JoinIdText = new JTextField();
		JoinIdText.setBounds(142, 32, 187, 27);
		JoinIdText.setColumns(10);
		getContentPane().add(JoinIdText);

		passwordField = new JPasswordField();
		passwordField.setBounds(142, 74, 187, 27);
		getContentPane().add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(142, 114, 187, 27);
		getContentPane().add(passwordField_1);

		JoinNameText = new JTextField();
		JoinNameText.setColumns(10);
		JoinNameText.setBounds(142, 164, 187, 27);
		getContentPane().add(JoinNameText);

		JoinBirthText = new JTextField();
		JoinBirthText.setColumns(10);
		JoinBirthText.setBounds(142, 206, 187, 27);
		getContentPane().add(JoinBirthText);

		JoinEmailText = new JTextField();
		JoinEmailText.setColumns(10);
		JoinEmailText.setBounds(142, 248, 187, 27);
		getContentPane().add(JoinEmailText);

		btnCancelMake = new JButton("����");
		btnCancelMake.setBackground(Color.LIGHT_GRAY);
		btnCancelMake.setBounds(235, 290, 129, 29);
		getContentPane().add(btnCancelMake);
		btnCancelMake.addActionListener(butAct);

		btnMakeMember = new JButton("ȸ������");
		btnMakeMember.setBackground(Color.LIGHT_GRAY);
		btnMakeMember.setBounds(89, 290, 129, 29);
		getContentPane().add(btnMakeMember);
		btnMakeMember.addActionListener(butAct);

		btnIdChk = new JButton("�ߺ�Ȯ��");
		btnIdChk.setBackground(Color.LIGHT_GRAY);
		btnIdChk.setBounds(346, 30, 86, 27);
		getContentPane().add(btnIdChk);
		btnIdChk.addActionListener(butAct);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	
	

	ActionListener butAct = new ActionListener() { // �׼Ǹ���Ʈ

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCancelMake) { // ���� ��ư ���� �� �α��� â���� ȭ�� ��ȯ

				dispose();

				LoginTest jt = new LoginTest();
				jt.setVisible(true);
			}

			if (e.getSource() == btnIdChk) { // �ߺ� �˻�
				
					String jt = JoinIdText.getText().trim();
					
					if("".equals(jt) || !Pattern.matches(regex[0], jt)) {
						JOptionPane.showMessageDialog(null, " ���̵� Ȯ�� ���ּ���");
					}else if(dao.idd(jt)==0) {
						JOptionPane.showMessageDialog(null, JoinIdText.getText() + " �ߺ��Դϴ�.");
					}else if(dao.idd(jt)==1 && Pattern.matches(regex[0], jt)) {
						JOptionPane.showMessageDialog(null, JoinIdText.getText() + " ��밡���մϴ�.");
					}
				
			}
			
				
//				System.out.println(new GameDAO().idC(JoinIdText.getText()));
//				
//				if(JoinIdText.getText().trim().equals("")){;
//					System.out.println(JoinIdText.getText().trim().equals(""));
//		           }else{
//		               
//		              if(dao.idC((JoinIdText.getText())) != null){ //�ߺ��ƴϴ�.(��밡��)
//		            	  JOptionPane.showMessageDialog(null, JoinIdText.getText()+"�� ��밡���մϴ�.");  
//		            	  
//		              }else{ //�ߺ��̴�.
//		            	  JOptionPane.showMessageDialog(null,JoinIdText.getText()+"�� �ߺ��Դϴ�.");
//		            	  
//		                 
////		            	  JoinIdText.setText("");//text�ڽ������
//		            	  
//		              }
//				}
		           
		       
	

			if (e.getSource() == btnMakeMember) {	//ȸ�� ���� 
				id = JoinIdText.getText();	
				pw = passwordField.getText();
				pw2 = passwordField_1.getText();
				name = JoinNameText.getText();
				birth = JoinBirthText.getText();
				email = JoinEmailText.getText();
				
				String[] joinChk = { id, pw, pw2, name, birth, email }; // ��ȿ�� �˻縦 ���� �迭��
				String[] pppChk = { "id�� Ȯ�� ���ּ���", "��й�ȣ�� Ȯ���� �ּ���", "��й�ȣ�� Ȯ���� �ּ���", "�̸��� Ȯ���� �ּ���", "������ Ȯ���� �ּ���",
						"email�� Ȯ���� �ּ���" }; // ��������

				while (true) { //while ���� ������ ��ȿ�� �˻� ����
					int i = 0;	//id�� ��ȿ�� �˻� �迭, ���� �˸��޼��� �迭 �ε����� ǥ�� �� ����
					int j = -1;
					if(true) {
					if (Pattern.matches(regex[i], joinChk[i])) { //������ ��ġ�ϸ� data�� ����
//						data.add(id);
						dto.setId(id);
					} else { //��ġ ���� ���� �� idȮ�� �޼��� â 
						JOptionPane.showMessageDialog(null, pppChk[i]);
						j--;
						break;
					}

					if (Pattern.matches(regex[i], joinChk[i])) { //������ ��ġ�ϸ� data�� ����
						
					} else { //��ġ ���� ���� �� idȮ�� �޼��� â 
						JOptionPane.showMessageDialog(null, pppChk[i]);
						break;
					}
					i++;	//id ����� i�� �������� ���� �迭 ���� ����
					if (Pattern.matches(regex[i], joinChk[i])) {
						if (pw2.equals(pw))
//							data.add(pw);
						dto.setPw(pw);
					} else {
						JOptionPane.showMessageDialog(null, pppChk[i]);
						break;
					}
					i++;
					if (Pattern.matches(regex[i], joinChk[i])) {
//						data.add(name);
						dto.setName(name);
					} else {
						JOptionPane.showMessageDialog(null, pppChk[i]);
						break;
					}
					i++;

					if (Pattern.matches(regex[i], joinChk[i])) {
//						data.add(birth);
						dto.setBirth(birth);
					} else {
						JOptionPane.showMessageDialog(null, pppChk[i]);
						break;
					}
					i++;
					
					if (Pattern.matches(regex[i], joinChk[i])) {
//						data.add(email);
						dto.setEmail(email);
					} else {
						JOptionPane.showMessageDialog(null, pppChk[i]);
						break;
					}
					}

					System.out.println(data); //�Ϸ�� �����Ͱ� Ȯ��
					System.out.println(new GameDAO().insert(dto)); //db��  insert 
					JOptionPane.showMessageDialog(null, name + "�� ȸ������ �Ϸ�");
					break;

				}

			}

		}
	};


	public static void main(String[] args) {
		new JoinTest();

	}
}
	

