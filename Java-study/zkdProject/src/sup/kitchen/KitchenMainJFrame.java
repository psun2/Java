package sup.kitchen;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import sup.menu.OrderMenu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class KitchenMainJFrame extends JFrame {

	KitchenMainController kitchenMainController;
	JPanel contentPane;
	KitchenMainController control;
	JPanel waiting_p;
	public KitchenMainJFrame(KitchenMainController control) {
		this.control = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 10, 1300,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		waiting_p = new JPanel();
		waiting_p.setLayout(new FlowLayout());
		
		contentPane.add(waiting_p);
		setVisible(true);
		
	}
}
class orderPane extends JPanel{
	Order order;
	KitchenMainController control;
	String tableNum ,time;
	JPanel list_p;
	JLabel tableNumL;
	String state;
	JButton nextBtn;
	JLabel menu_name;
	JLabel menu_cnt;
	JLabel menu_state;
	public orderPane(Order order, KitchenMainController control) {	
		this.control = control;
		this.order = order;
		this.tableNum = order.getTable_num();
		this.state = order.getMenu_state();
		this.time = order.getTime();
		
		if(order.menu_state.equals("�������")) {
			setBackground(Color.BLUE);
		} else if (order.menu_state.equals("������")) {
			setBackground(Color.red);	
		}else {
			setBackground(Color.green);
		}
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250,80));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tableNumL = new JLabel(tableNum+"  "+time);
		tableNumL.setHorizontalAlignment(SwingConstants.CENTER);
		
		list_p = new JPanel();
		list_p.setLayout(new GridLayout(1,4));
		
		addorder();
		add(tableNumL,"North");
		add(list_p,"Center");
		setVisible(true);
	}
	void addorder() {
		
		nextBtn = new JButton("����");
		menu_name = new JLabel(order.getMenu_Name());
		menu_cnt = new JLabel(order.getMenu_cnt()+"");
		menu_state = new JLabel(order.getMenu_state());
		
		list_p.add(menu_name);
		list_p.add(menu_cnt);
		list_p.add(menu_state);
		list_p.add(nextBtn);
		
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.changeState(order,state,orderPane.this);
			}
		});
	}
	public void re() {
		validate();
	}
	
}

//class OrderLable extends JPanel{
//	ArrayList<Order> orders;
//	KitchenMainController control;
//	String tableNum ,time;
//	JPanel list_p;
//	JLabel tableNumL;
//	public OrderLable(String tableNum,String time, KitchenMainController control) {
//		setLayout(new BorderLayout());
//		setPreferredSize(new Dimension(100,100));
//		setBackground(Color.pink);
//		this.control = control;
//		this.tableNum = tableNum;
//		this.time = time;
//		tableNumL = new JLabel(tableNum);
//		orders = new ArrayList<Order>();
//		list_p = new JPanel();
//		list_p.setLayout(new GridLayout(10,1));
//		add(tableNumL,"North");
//		add(list_p,"Center");
//		setVisible(true);
//	}
//	void addOrder(Order order) {
//		orders.add(order);
//	}
//	void addPanel() {
//		System.out.println(orders.size());
//		for (Order orderMenu : orders) {
//			JPanel list_in = new JPanel();
//			JButton nextBtn = new JButton("����");
//			JLabel menu_name = new JLabel(orderMenu.getMenu_Name());
//			JLabel menu_cnt = new JLabel(orderMenu.getMenu_cnt()+"");
//			
//			list_in.setLayout(new GridLayout(1,3));
//			
//			menu_name.setHorizontalAlignment(SwingConstants.CENTER);
//			menu_cnt.setHorizontalAlignment(SwingConstants.CENTER);
//			
//			list_in.add(menu_name);
//			list_in.add(menu_cnt);
//			list_in.add(nextBtn);
//			list_p.add(list_in);
//		}
//	}
//}
