package an.adminLogin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class an_adminLoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private JLabel result;

	an_adminLoginControl control; // �α��� ��Ʈ�� Ŭ����.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					an_adminLoginView frame = new an_adminLoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public an_adminLoginView() {
		init();
	}

	public an_adminLoginView(an_adminLoginControl control) {
		this.control = control;
		init();

	}

	void init() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		control = new an_adminLoginControl(this); // �����ڸ� ������Ѵ�.
		setBounds(500, 200, 869, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("User :");
		panel_2.add(lblNewLabel);

	
		password = new JPasswordField(10);
		panel_2.add(password);

		String  passwordnumber = new AdminLogDAO().detail("������").getAD_PW();
		
		JButton btnNewButton = new JButton("�α���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strPw = String.valueOf(password.getPassword());
				if (strPw.equals(passwordnumber)) {
					result.setText("�α����� �Ǿ����ϴ�");
					result.setForeground(Color.blue);

					control.loginViewClose();// �α��� �����Ǹ�, an_adminLoginControl Ŭ������ loginViewClose() �޼ҵ� ����

				} else {
					result.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					result.setForeground(Color.RED);
				}

			}
		});
		panel_2.add(btnNewButton);

		result = new JLabel(" ");
		panel_2.add(result);

	}

}
