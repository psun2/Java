package an.sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import an.settlement.SettlementDAO;
import an.settlement.SettlementDTO;

import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;





public class SalesView extends JFrame {

	
	
	ArrayList<SettlementDTO> orderlist;
	
	
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTable table;
	SalesControl salescontrol;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//an_salesView frame = new an_salesView();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesView(SalesControl salescontrol) {
		this.salescontrol = salescontrol;
		
		
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 800, 600);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 2400, 1600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		panel.setBackground(Color.GREEN);
		panel.setBounds(247, 0, 537, 112);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("월 별");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("시간대 별");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("판매품목 별");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnNewButton_2);
		panel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 37, 235, 75);
		contentPane.add(panel_2);
		
		
		
		Date vv = new Date(2002-1900, 1-1,10);
		
		Date start = new Date(1900-1900, 1-1, 10);
		
		Date end = new Date(2020-1900, 12-1, 10);
		
		JSpinner spinner = new JSpinner();
		SpinnerDateModel spi = new SpinnerDateModel(vv,start,end,Calendar.MONTH);
		spinner.setModel(spi);
		spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
		panel_2.add(spinner);
		
		
		
		JSpinner spinner_1 = new JSpinner();
		
		SpinnerDateModel spi2 = new SpinnerDateModel(vv,start,end,Calendar.MONTH);
		spinner_1.setModel(spi2);
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "yyyy-MM-dd"));
		panel_2.add(spinner_1);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 133, 784, 428);
		contentPane.add(scrollPane);
		
		table = new JTable();
		Vector<String> index = new Vector<String>();
		index.add("결제코드");
		index.add("테이블NO");
		index.add("수량");
		index.add("결제메뉴");
		index.add("가격");
		index.add("결제시간");
		
		
		
		DefaultTableModel df = new DefaultTableModel(index,0);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar_1);
		
		setVisible(true);
	}
}
