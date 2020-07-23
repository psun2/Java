package an.tableBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentView extends JFrame implements Runnable{

	
	ArrayList<PaymentDTO> payment;
	
	PaymentControl payControl;
	
	private Thread thread;
	private JLabel label;
	///
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	JTextField textField_11;
	private JTable table_1;

	public static void main() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					PaymentView frame = new PaymentView(payControl);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentView(PaymentControl payControl) {
		this.payControl = payControl;

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		getContentPane().setLayout(null);
	
		//setLayout(new FlowLayout());
		
		label = new JLabel();
		label.setBounds(93, 5, 200, 20);
		label.setForeground(Color.black);
		label.setFont(new Font("Serif",Font.PLAIN, 15));
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}

		getContentPane().add(label);

		JPanel panel_1 = new JPanel();
		
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(0, 410, 394, 32);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JButton btnNewButton_1 = new JButton("지정 취소");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("전체 취소");
		panel_1.add(btnNewButton_2);

		
		JButton btnNewButton_3 = new JButton("△");
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("▽");
		panel_1.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 452, 394, 32);
		panel_2.setLayout(null);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("PAYMENT INFORMATION");
		panel_2.add(lblNewLabel_2);
		
		JTextPane textPane_2 = new JTextPane();
		panel_2.add(textPane_2);
		

		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 368, 394, 32);
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("  총 매출액");
		panel_6.add(lblNewLabel_1);
		
		JTextPane textPane_1 = new JTextPane();
		panel_6.add(textPane_1);
		
		JLabel lblNewLabel = new JLabel("  수량");
		panel_6.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		panel_6.add(textPane);
		
		JPanel panel_7 = new JPanel();
		//
		panel_7.setBounds(0, 494, 394, 67);
		contentPane.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 4));
		
		JLabel lblNewLabel_5 = new JLabel("받을 금액");
		panel_7.add(lblNewLabel_5);
		
		textField = new JTextField();
		panel_7.add(textField);
		textField.setColumns(10);

		
		JLabel lblNewLabel_3 = new JLabel("받은 금액");
		panel_7.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		panel_7.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("방문 시간");
		panel_7.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		panel_7.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("거스름 돈");
		panel_7.add(lblNewLabel_7);
	

		
		textField_7 = new JTextField();
		panel_7.add(textField_7);
		textField_7.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(689, 309, 95, 252);
		contentPane.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_18 = new JButton("결제 하기");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//DTO/DAO 실행하는 MAIN문을 여기다가 실행을 시켜서, 클릭 시 데이터를 해당 데이터를 삽입 할 수 있게 한다.
				
				// 결제 버튼을 누를 수 있게 액션 한다.
				
				dispose();
				
			}
		});
		panel_8.add(btnNewButton_18);
		
		JPanel panel_3 = new JPanel();
		
		panel_3.setBounds(406, 35, 378, 264);
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("결제 금액");
		panel_3.add(lblNewLabel_9);
		
		textField_11 = new JTextField();
		panel_3.add(textField_11);
		textField_11.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		//panel_4.setLayout(null);
		panel_4.setBounds(406, 309, 271, 252);
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(4, 4, 0, 0));
		
		JButton btnNewButton = new JButton("7");
		panel_4.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("8");
		panel_4.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("9");
		panel_4.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("B/S");
		panel_4.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("4");
		panel_4.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("5");
		panel_4.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("6");
		panel_4.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("CLR");
		panel_4.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("1");
		panel_4.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("2");
		panel_4.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("3");
		panel_4.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("%");
		panel_4.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("0");
		panel_4.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("00");
		panel_4.add(btnNewButton_17);
		
		JButton btnNewButton_21 = new JButton(".");
		panel_4.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("확인");
		panel_4.add(btnNewButton_22);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 35, 394, 323);
		contentPane.add(scrollPane);

		
		
		
		table = new JTable();
		
		Vector<String> index = new Vector<String>();

		index.add("테이블번호");
		index.add("메뉴");
		index.add("수량");
		index.add("가격");
		
		DefaultTableModel df = new DefaultTableModel(index,0);
		payment = new PaymentDAO().payment();
				
		
		
		for (PaymentDTO order : payment) {
			
			Vector<Object> vct = new Vector<Object>();
			
			vct.add(order.getBills_tablenum());
			vct.add(order.getBills_orderedmenu());
			vct.add(order.getBills_count());
			vct.add(order.getBills_price());
			
			
			
			
			
			df.addRow(vct);
		}
		
		JTable table = new JTable(df);
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel_11 = new JLabel("영업 일자 :");
		lblNewLabel_11.setBounds(0, 5, 81, 20);
		contentPane.add(lblNewLabel_11);
		setVisible(true);
	}


	public void run() {
		while(true){
			Calendar cal = Calendar.getInstance();
			
			String now = 
					cal.get(Calendar.YEAR)+"년"+
					(cal.get(Calendar.MONTH)+1)+"월"+
					cal.get(Calendar.DATE)+"일"+
					cal.get(Calendar.HOUR)+"시"+
					cal.get(Calendar.MINUTE)+"분"+
					cal.get(Calendar.SECOND)+"초";
			label.setText(now);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
}
