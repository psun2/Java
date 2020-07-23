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

		JButton btnNewButton_7 = new JButton("마감 정산");
		btnNewButton_6 = new JButton("판매 시작");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				tbc = new TableBoardControl(AdminMainView.this);
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				

				// 판매시작이 꺼지면, 킬 수있게 버튼 활성화를 해주고
				// 판매시작이 켜지면 판매시작 버튼을 못누르게 비활성화를 시켜준다.


			}
		});
		panel_3.add(btnNewButton_6);

		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				//btnNewButton_7.setEnabled(false);
			}
		});
		panel_3.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("매출관리");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//매출관리 생성자 생성하기.
				new SalesControl();
			}
		});
		panel_3.add(btnNewButton_8);

		JButton btnNewButton_4 = new JButton("메뉴관리");
		panel_3.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmcontrol = new MMcontroller( 7777);
			}
		});

		JButton btnNewButton_5 = new JButton("관리자 설정");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AdminSettingsControl();

				//관리자 설정 GUI와 연결 할 수 있게 만들기.

			}
		});
		panel_3.add(btnNewButton_5);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);

		setVisible(true);
	}

}
