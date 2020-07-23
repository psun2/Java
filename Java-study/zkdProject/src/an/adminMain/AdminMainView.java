package an.adminMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import an.adminSettings.AdminSettingsControl;
import an.sales.SalesControl;
import an.tableBoard.TableBoardControl;
import an.tableBoard.TableBoardView;
import sup.menuManagement.MMcontroller;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainView extends JFrame {

	AdminMainControl adminControl;
	TableBoardView boareView;
	AdminSettingsControl settingcontrol;
	private JPanel contentPane;
	MMcontroller mmcontrol;
	public JButton btnNewButton_6;
	TableBoardControl tbc;
	//	public static void main(String[] args) {
	//		
	//	}



	public AdminMainView(AdminMainControl adminControl) {

		this.adminControl = adminControl;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_7 = new JButton("���� ����");
		btnNewButton_6 = new JButton("�Ǹ� ����");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				tbc = new TableBoardControl(AdminMainView.this);
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				

				// �ǸŽ����� ������, ų ���ְ� ��ư Ȱ��ȭ�� ���ְ�
				// �ǸŽ����� ������ �ǸŽ��� ��ư�� �������� ��Ȱ��ȭ�� �����ش�.


			}
		});
		panel_3.add(btnNewButton_6);

		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				//btnNewButton_7.setEnabled(false);
			}
		});
		panel_3.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("�������");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//������� ������ �����ϱ�.
				new SalesControl();
			}
		});
		panel_3.add(btnNewButton_8);

		JButton btnNewButton_4 = new JButton("�޴�����");
		panel_3.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmcontrol = new MMcontroller( 7777);
			}
		});

		JButton btnNewButton_5 = new JButton("������ ����");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AdminSettingsControl();

				//������ ���� GUI�� ���� �� �� �ְ� �����.

			}
		});
		panel_3.add(btnNewButton_5);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);

		setVisible(true);
	}

}
