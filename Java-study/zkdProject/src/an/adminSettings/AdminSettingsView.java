package an.adminSettings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSettingsView extends JFrame {

	JPanel contentPane;
	JPasswordField passwordField;
	JPasswordField passwordField_1;
	AdminSettingsControl settingscontrol;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//an_adminSettingsView frame = new an_adminSettingsView();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param settingscontrol 
	 */
	public AdminSettingsView(AdminSettingsControl settingscontrol) {
		
		this.settingscontrol = settingscontrol;
		
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("���� ��� ��ȣ");
		panel.add(lblNewLabel);
		
		passwordField = new JPasswordField(10);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("�� ��й�ȣ    ");
		panel_5.add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField(10);
		panel_5.add(passwordField_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			//	new an_adminSettingsView(settingscontrol);
				
				// ���� ��й�ȣ���� ���ο� ��й�ȣ�� �ٲ��ִ� �޼ҵ带 ���� �����ش�.
				
				
			}
		});
		panel_1.add(btnNewButton);
		setVisible(true);	
	}

}
