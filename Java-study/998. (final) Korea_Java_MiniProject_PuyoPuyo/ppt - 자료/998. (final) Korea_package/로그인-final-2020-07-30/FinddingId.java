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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	
	   int monthL=0;
	   int dayL=0;
	   int yearL=0;
	   int days=1;
	   int lastday;
	   
	   boolean truechk = false;
	
	
	
	
	
	
	public FinddingId() {
		getContentPane().setLayout(null);
		setTitle("���� ã��");
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		setSize(600, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		

		JLabel lbJoinId_1_1_1 = new JLabel("�̸���");
		lbJoinId_1_1_1.setBounds(138, 291, 74, 27);
		getContentPane().add(lbJoinId_1_1_1);

		
		
	      Calendar calders = Calendar.getInstance();
	      
	      JComboBox year= new JComboBox();  //��
	      year.setBounds(245, 250, 74, 23);
	      for (int i = 1920; i <= 2020; i++) {
	    	  year.addItem(i);
	      }
	      
	      JComboBox month = new JComboBox(); //��
	      month.setBounds(340, 250, 52, 23);
	      for (int i = 1; i <=12; i++) {
	    	  month.addItem(i);
	      }      
	      
	      JComboBox day = new JComboBox(); //��
	      day.setBounds(410, 250, 46, 23);
	      
	      for (int i = 1; i <=31; i++) {
	    	  day.addItem(i);
	      }      
	      JLabel lblNewLabel_2 = new JLabel("��");
	      lblNewLabel_2.setBounds(325, 250, 21, 27);
	      getContentPane().add(lblNewLabel_2);
	      
	      JLabel lblNewLabel_2_1 = new JLabel("��");
	      lblNewLabel_2_1.setBounds(395, 250, 21, 27);
	      getContentPane().add(lblNewLabel_2_1);
	      
	      JLabel lblNewLabel_2_1_1 = new JLabel("��");
	      lblNewLabel_2_1_1.setBounds(465, 250, 21, 27);
	      getContentPane().add(lblNewLabel_2_1_1);
	      
	      getContentPane().add(year);
	      getContentPane().add(month);
	      getContentPane().add(day);
	      
	      
	      
	      month.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  yearL = (int) year.getItemAt(year.getSelectedIndex());
				  monthL = (int) month.getItemAt(month.getSelectedIndex());
				  calders.set(yearL, monthL-1,days);
//		
//				  System.out.println("�ش�⵵: "+calders.get(Calendar.YEAR));
//				  System.out.println("�ش��: "+(calders.get(Calendar.MONTH)+1)); //�����ٶ� +1�� �Ͽ� ����������� ����
//				  System.out.println("������ ��(���� ��¥ ���� �ִ��): "+calders.getActualMaximum(Calendar.DAY_OF_MONTH));
				  lastday = calders.getActualMaximum(Calendar.DAY_OF_MONTH);
				  
				  
				  
			}
	      });
	      
	      
	      day.addActionListener(new ActionListener() {
	     	@Override
	  		public void actionPerformed(ActionEvent e) {
	      		dayL = (int) day.getItemAt(day.getSelectedIndex());
	      		System.out.println(dayL);
	      		
	      		if(dayL>lastday) {
	      			System.out.println("��Ŀ");
	      		}else {
	      			truechk = true;
	      		}
	      		
	      	}
	  	});

		

		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(245, 206, 209, 27);
		getContentPane().add(nameText);

		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(245, 291, 209, 27);
		getContentPane().add(emailText);

		closeBtn = new JButton("����");
		closeBtn.setBounds(305, 416, 129, 29);
		getContentPane().add(closeBtn);
		closeBtn.addActionListener(actBtn);

		findBtn = new JButton("ã��");
		findBtn.setBounds(159, 416, 129, 29);
		getContentPane().add(findBtn);
		
		JLabel lblNewLabel = new JLabel("\uC774\uB984");
		lblNewLabel.setBounds(138, 212, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_1.setBounds(138, 254, 57, 15);
		getContentPane().add(lblNewLabel_1);
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
				String name = nameText.getText().trim();
				String birth =yearL+"��"+monthL+"��"+dayL+"��";
				String mail = emailText.getText().trim();
				
				while (true) {
					GameUserDTO dto = new GameUserDTO();

					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���");
						break;
					} else {
						dto.setName(name);
					}
					
					if (!truechk) {
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