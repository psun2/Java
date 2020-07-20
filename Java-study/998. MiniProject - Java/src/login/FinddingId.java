package login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FinddingId extends JFrame {

	public ArrayList<String> data = new ArrayList<String>();
	GameDAO dao = new GameDAO();
	GameDTO dto = new GameDTO();

	LoginTest jt = new LoginTest();
	JTextField nameText;
	JTextField idText;
	JTextField birthText;
	JTextField emailText;
	JButton closeBtn;
	JButton findBtn;

	public FinddingId() {
		getContentPane().setLayout(null);
		setSize(455, 615);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel lbJoinId = new JLabel("�̸�");
		lbJoinId.setBounds(47, 63, 74, 27);
		getContentPane().add(lbJoinId);

		JLabel lbJoinId_1 = new JLabel("���̵�");
		lbJoinId_1.setBounds(47, 105, 74, 27);
		getContentPane().add(lbJoinId_1);

		JLabel lbJoinId_1_1 = new JLabel("�������");
		lbJoinId_1_1.setBounds(47, 147, 74, 27);
		getContentPane().add(lbJoinId_1_1);

		JLabel lbJoinId_1_1_1 = new JLabel("�̸���");
		lbJoinId_1_1_1.setBounds(47, 189, 74, 27);
		getContentPane().add(lbJoinId_1_1_1);

		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(172, 65, 209, 27);
		getContentPane().add(nameText);

		idText = new JTextField();
		idText.setColumns(10);
		idText.setBounds(172, 105, 209, 27);
		getContentPane().add(idText);

		birthText = new JTextField();
		birthText.setColumns(10);
		birthText.setBounds(172, 147, 209, 27);
		getContentPane().add(birthText);

		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(172, 189, 209, 27);
		getContentPane().add(emailText);

		closeBtn = new JButton("����");
		closeBtn.setBounds(227, 262, 129, 29);
		getContentPane().add(closeBtn);
		closeBtn.addActionListener(ActBtn);

		findBtn = new JButton("ã��");
		findBtn.setBounds(81, 262, 129, 29);
		getContentPane().add(findBtn);
		findBtn.addActionListener(ActBtn);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	ActionListener ActBtn = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeBtn) {
				dispose();
				jt.setVisible(true);
			}
			if (e.getSource() == findBtn) {
				String id = idText.getText();
				System.out.println(id);
				String name = nameText.getText();
				String birth = birthText.getText();
				String mail = emailText.getText();
//				if(id==null || )
				dto.setId(id);
				dto.setName(name);
				dto.setBirth(birth);
				dto.setEmail(mail);
				System.out.println(new GameDAO().find(id, name, birth, mail));

				JOptionPane.showMessageDialog(null, dto.getName());

//				if(dao.find(id, name, birth, mail)== null) {
//					JOptionPane.showMessageDialog(null, "����");
//				}else {
//					JOptionPane.showMessageDialog(null, dto);
//					
//				}
//				if(dao.detail("id")!=null)
//					System.out.println(new GameDAO().detail(id));

//				dto.setId(idText.getText());
//				dto.setName(nameText.getText());
//				dto.setBirth(birthText.getText());
//				dto.setEmail(emailText.getText());

//				System.out.println(new GameDAO().find(id, name, birth, mail));
//				System.out.println(new GameDAO().detail(id));
//				if(){
//					JOptionPane.showMessageDialog(null, dto);
//				}else{
//					JOptionPane.showMessageDialog(null, dto);

			}

		}

	};

	public static void main(String[] args) {
		new FinddingId();

	}
}
