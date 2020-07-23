package an.tableBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import an.adminMain.AdminMainView;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TableBoardView extends JFrame implements WindowListener {

	private JPanel contentPane;
	JPanel order_p;
	AdminMainView mainview;
	static TableBoardControl boardControl;
	JLabel lblNewLabel,lblNewLabel_1,
	lblNewLabel_4,lblNewLabel_5,lblNewLabel_6,lblNewLabel_7,
	lblNewLabel_8,lblNewLabel_9,lblNewLabel_10,lblNewLabel_11,
	lblNewLabel_12,lblNewLabel_13,lblNewLabel_14,lblNewLabel_15,
	lblNewLabel_16,lblNewLabel_17;
	JButton table_1,table_2,table_3,table_4,table_5,table_6,table_7,table_8;
	/**
	 * Launch the application.
	 */
	
	JPanel panel_16;
	PaymentControl payControl;
	
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
//			setVisible(true);
			public void run() {
				try {
//					an_tableBoardView frame = new an_tableBoardView(boardControl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableBoardView(TableBoardControl boardControl,AdminMainView mainview ) {
			this.boardControl = boardControl;
		
		 this.mainview = mainview;
		order_p = new JPanel();	
		addWindowListener(this);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_16 = new JPanel();
		panel_16.setBackground(Color.DARK_GRAY);
		panel_2.add(panel_16);
		
		JButton btnNewButton_1 = new JButton("N0_1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payControl = new PaymentControl();
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("남:  ");
		
		JLabel lblNewLabel_3 = new JLabel("여:  ");
		
		table_1 = new JButton("호출 종료");
		table_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_26 = new JButton("성비 등록");
		btnNewButton_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//성비 등록

				new TableBoardSexRateControl(TableBoardView.this,"table_1");

				
			}
		});
		
		lblNewLabel = new JLabel("");
		
		lblNewLabel_1 = new JLabel("");
		GroupLayout gl_panel_16 = new GroupLayout(panel_16);
		gl_panel_16.setHorizontalGroup(
			gl_panel_16.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_16.createSequentialGroup()
					.addGroup(gl_panel_16.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1))
					.addContainerGap())
				.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
				.addComponent(btnNewButton_26, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_panel_16.setVerticalGroup(
			gl_panel_16.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16.createSequentialGroup()
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26))
		);
		panel_16.setLayout(gl_panel_16);
		
		JPanel panel_16_1 = new JPanel();
		panel_16_1.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_16_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_1 = new JLabel("\uC5EC:  ");
		
		table_2 = new JButton("호출 종료");
		table_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_2.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("N0_2");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_26_1 = new JButton("성비 등록");
		btnNewButton_26_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_6");
			}
		});
		
		lblNewLabel_12 = new JLabel("");
		
		lblNewLabel_13 = new JLabel("");
		GroupLayout gl_panel_16_1 = new GroupLayout(panel_16_1);
		gl_panel_16_1.setHorizontalGroup(
			gl_panel_16_1.createParallelGroup(Alignment.LEADING)
				.addComponent(table_2, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(btnNewButton_26_1, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addGroup(gl_panel_16_1.createSequentialGroup()
					.addGroup(gl_panel_16_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_12)
						.addComponent(lblNewLabel_13))
					.addGap(71))
				.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_panel_16_1.setVerticalGroup(
			gl_panel_16_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_1.createSequentialGroup()
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_12))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_13))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_1))
		);
		panel_16_1.setLayout(gl_panel_16_1);
		
		JPanel panel_16_2 = new JPanel();
		panel_16_2.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_16_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_2 = new JLabel("\uC5EC:  ");
		
		table_5 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_5.setBackground(Color.gray);
			}
		});
		
		JButton table5 = new JButton("N0_5");
		
		JButton btnNewButton_26_2 = new JButton("성비 등록");
		btnNewButton_26_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_7");
			}
		});
		
		lblNewLabel_14 = new JLabel("");
		
		lblNewLabel_15 = new JLabel("");
		GroupLayout gl_panel_16_2 = new GroupLayout(panel_16_2);
		gl_panel_16_2.setHorizontalGroup(
			gl_panel_16_2.createParallelGroup(Alignment.LEADING)
				.addComponent(table5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(btnNewButton_26_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addGroup(gl_panel_16_2.createSequentialGroup()
					.addGroup(gl_panel_16_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_15)
						.addComponent(lblNewLabel_14))
					.addGap(71))
				.addComponent(table_5, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_panel_16_2.setVerticalGroup(
			gl_panel_16_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_2.createSequentialGroup()
					.addComponent(table5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_14))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_15))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_2))
		);
		panel_16_2.setLayout(gl_panel_16_2);
		
		JPanel panel_16_3 = new JPanel();
		panel_16_3.setBackground(Color.DARK_GRAY);
		panel_2.add(panel_16_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_3 = new JLabel("\uC5EC:  ");
		
		table_6 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_6.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_3 = new JButton("N0_6");
		
		JButton btnNewButton_26_3 = new JButton("성비 등록");
		btnNewButton_26_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_8");
			}
		});
		
		lblNewLabel_16 = new JLabel("");
		
		lblNewLabel_17 = new JLabel("");
		GroupLayout gl_panel_16_3 = new GroupLayout(panel_16_3);
		gl_panel_16_3.setHorizontalGroup(
			gl_panel_16_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_3.createSequentialGroup()
					.addGroup(gl_panel_16_3.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_17)
						.addComponent(lblNewLabel_16))
					.addContainerGap())
				.addComponent(table_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(btnNewButton_1_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(btnNewButton_26_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_panel_16_3.setVerticalGroup(
			gl_panel_16_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_3.createSequentialGroup()
					.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_16))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_17))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_3))
		);
		panel_16_3.setLayout(gl_panel_16_3);
		
		order_p = new JPanel();
		contentPane.add(order_p, BorderLayout.EAST);
		order_p.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.SOUTH);
		
		JPanel panel_2_1 = new JPanel();
		contentPane.add(panel_2_1, BorderLayout.CENTER);
		panel_2_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_16_4 = new JPanel();
		panel_16_4.setBackground(Color.DARK_GRAY);
		panel_2_1.add(panel_16_4);
		
		JLabel lblNewLabel_2_4 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_4 = new JLabel("\uC5EC:  ");
		
		table_3 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_3.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_4 = new JButton("N0_3");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_26_4 = new JButton("성비 등록");
		btnNewButton_26_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_4");
			}
		});
		
		lblNewLabel_8 = new JLabel("              ");
		
		lblNewLabel_9 = new JLabel("              ");
		GroupLayout gl_panel_16_4 = new GroupLayout(panel_16_4);
		gl_panel_16_4.setHorizontalGroup(
			gl_panel_16_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_16_4.createSequentialGroup()
					.addGroup(gl_panel_16_4.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_4, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9)
						.addComponent(lblNewLabel_8))
					.addContainerGap())
				.addComponent(btnNewButton_26_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(table_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(btnNewButton_1_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		gl_panel_16_4.setVerticalGroup(
			gl_panel_16_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_4.createSequentialGroup()
					.addComponent(btnNewButton_1_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_4))
		);
		panel_16_4.setLayout(gl_panel_16_4);
		
		JPanel panel_16_1_1 = new JPanel();
		panel_16_1_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.add(panel_16_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_1_1 = new JLabel("\uC5EC:  ");
		
		table_4 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_4.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_1_1 = new JButton("N0_4");
		
		JButton btnNewButton_26_1_1 = new JButton("성비 등록");
		btnNewButton_26_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_2");
			}
		});
		
		lblNewLabel_4 = new JLabel("              ");
		
		lblNewLabel_5 = new JLabel("              ");
		GroupLayout gl_panel_16_1_1 = new GroupLayout(panel_16_1_1);
		gl_panel_16_1_1.setHorizontalGroup(
			gl_panel_16_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_1_1.createSequentialGroup()
					.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_16_1_1.createSequentialGroup()
							.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5)))
						.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(table_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_26_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
					.addGap(14))
				.addGroup(Alignment.TRAILING, gl_panel_16_1_1.createSequentialGroup()
					.addComponent(btnNewButton_1_1_1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_16_1_1.setVerticalGroup(
			gl_panel_16_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_1_1.createSequentialGroup()
					.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_1_1))
		);
		panel_16_1_1.setLayout(gl_panel_16_1_1);
		
		JPanel panel_16_2_1 = new JPanel();
		panel_16_2_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.add(panel_16_2_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_2_1 = new JLabel("\uC5EC:  ");
		
		table_7 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_7.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_2_1 = new JButton("N0_7");
		
		JButton btnNewButton_26_2_1 = new JButton("성비 등록");
		btnNewButton_26_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_5");
			}
		});
		
		lblNewLabel_10 = new JLabel("              ");
		
		lblNewLabel_11 = new JLabel("              ");
		GroupLayout gl_panel_16_2_1 = new GroupLayout(panel_16_2_1);
		gl_panel_16_2_1.setHorizontalGroup(
			gl_panel_16_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_2_1.createSequentialGroup()
					.addGroup(gl_panel_16_2_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_11)
						.addComponent(lblNewLabel_10))
					.addContainerGap())
				.addComponent(btnNewButton_1_2_1, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
				.addComponent(table_7, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(btnNewButton_26_2_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		gl_panel_16_2_1.setVerticalGroup(
			gl_panel_16_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_2_1.createSequentialGroup()
					.addComponent(btnNewButton_1_2_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_10))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_2_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_11))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_2_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		);
		panel_16_2_1.setLayout(gl_panel_16_2_1);
		
		JPanel panel_16_3_1 = new JPanel();
		panel_16_3_1.setBackground(Color.DARK_GRAY);
		panel_2_1.add(panel_16_3_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("\uB0A8:  ");
		
		JLabel lblNewLabel_3_3_1 = new JLabel("\uC5EC:  ");
		
		table_8 = new JButton("\uD638\uCD9C \uC885\uB8CC");
		table_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_8.setBackground(Color.gray);
			}
		});
		
		JButton btnNewButton_1_3_1 = new JButton("N0_8");
		
		JButton btnNewButton_26_3_1 = new JButton("성비 등록");
		btnNewButton_26_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableBoardSexRateControl(TableBoardView.this,"table_3");
			}
		});
		
		lblNewLabel_6 = new JLabel("              ");
		
		lblNewLabel_7 = new JLabel("              ");
		GroupLayout gl_panel_16_3_1 = new GroupLayout(panel_16_3_1);
		gl_panel_16_3_1.setHorizontalGroup(
			gl_panel_16_3_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_3_1.createSequentialGroup()
					.addGroup(gl_panel_16_3_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_3_3_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_3_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_3_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7)
						.addComponent(lblNewLabel_6))
					.addContainerGap())
				.addComponent(btnNewButton_26_3_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(btnNewButton_1_3_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(table_8, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		gl_panel_16_3_1.setVerticalGroup(
			gl_panel_16_3_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_16_3_1.createSequentialGroup()
					.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel_16_3_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_3_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_16_3_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_3_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(btnNewButton_26_3_1))
		);
		panel_16_3_1.setLayout(gl_panel_16_3_1);
		setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		mainview.btnNewButton_6.setEnabled(true);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		mainview.btnNewButton_6.setEnabled(true);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
