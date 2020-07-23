package an.adminLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ChangePWFrame extends JFrame {

	private JPanel contentPane;
	JPasswordField password;
	an_adminLoginControl control;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangePWFrame(an_adminLoginControl control) {
		this.control = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("비밀번호 변경 :");
		panel_2.add(lblNewLabel, BorderLayout.WEST);

		password = new JPasswordField(10);
		panel_2.add(password, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("변경");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String adminname = "관리자";
				String strPw = String.valueOf(password.getPassword());
				System.out.println(strPw);
				control.changePW(strPw);

			}
		});
		panel_2.add(btnNewButton, BorderLayout.EAST);
		setVisible(true);
	}

}
