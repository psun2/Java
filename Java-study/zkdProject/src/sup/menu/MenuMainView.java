package sup.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;

import hong.InitData;
import sup.menuManagement.MenuManagementDBControl;
import java.awt.Font;
import java.awt.CardLayout;



public class MenuMainView extends JFrame {
	JPanel panel_8;
	JPanel contentPane;
	MenuMainController viewMenu;
	Basket basket;
	OrderMenu orderMenu;
	JPanel anjuPane;
	JPanel drinkPane;
	JLabel sumPrice;
	String tableNum;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MenuMainView(String tableNum, MenuMainController viewMenu) {
		JPanel panel = new JPanel();
		JPanel panel_2 = new JPanel();
		this.tableNum = tableNum;
		this.viewMenu = viewMenu;
		viewMenu.menus = new MenuManagementDBControl().list();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
		panel_8 = new JPanel();
		JPanel panel_left = new JPanel();
		JPanel panel_1 = new JPanel();
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(panel_left);
		ButtonGroup menuButtonGroup = new ButtonGroup();
		panel_left.setLayout(new BorderLayout(0, 0));
		panel_left.add(panel_1, BorderLayout.NORTH);
		JButton foodBtn = new JButton("\uC548\uC8FC");
		JButton drinkBtn = new JButton("\uC220");
		foodBtn.setEnabled(false);
		foodBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				drinkBtn.setEnabled(true);
				foodBtn.setEnabled(false);
				anjuPane.setVisible(true);
				drinkPane.setVisible(false);
				
			}
		});
		panel_1.setLayout(new BorderLayout(0, 0));
		panel.add(foodBtn);
		menuButtonGroup.add(foodBtn);
		anjuPane = new JPanel();
		panel_2.add(anjuPane);
		anjuPane.setBackground(Color.PINK);
		anjuPane.setLayout(new GridLayout(5, 5, 0, 0));
		drinkPane = new JPanel();
		panel_2.add(drinkPane);
		
		drinkPane.setBackground(Color.ORANGE);
		drinkPane.setLayout(new GridLayout(5, 5, 0, 0));
		
		panel.add(drinkBtn);
		
		drinkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				foodBtn.setEnabled(true);
				drinkBtn.setEnabled(false);
		
				drinkPane.setVisible(true);
				anjuPane.setVisible(false);
			}
		});
		menuButtonGroup.add(drinkBtn);
		
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel_left.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JPanel panel_right = new JPanel();
		contentPane.add(panel_right);
		panel_right.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_right.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uCD1D\uD569");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(50, 30));
		panel_7.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("\uC6D0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setPreferredSize(new Dimension(30, 30));
		panel_7.add(lblNewLabel_2, BorderLayout.EAST);
		
		sumPrice = new JLabel("0");
		sumPrice.setBorder(new EmptyBorder(0, 0, 0, 3));
		sumPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_7.add(sumPrice, BorderLayout.CENTER);
		
		JButton orderBtn = new JButton("\uC8FC\uBB38 \uC644\uB8CC");
		panel_7.add(orderBtn, BorderLayout.SOUTH);
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMenu.sendOrderList(tableNum);
				
				viewMenu.sendMessage();
			}
		});
		orderBtn.setPreferredSize(new Dimension(150, 23));
		
		panel_5.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(15, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		lblNewLabel_3.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_right.add(lblNewLabel_3, BorderLayout.NORTH);
		
		
		setVisible(true);
	}
	
	
}

class MenuBtn extends JButton implements ActionListener{
	Menu menu;
	JPanel panel8;
	JLabel sumPrice;
	MenuMainController viewMenu;
	OrderMenu orderMenu;
	Basket basket;
	public MenuBtn(Menu menu, JPanel panel8,JLabel sumPrice, MenuMainController viewMenu, OrderMenu orderMenu) {
		this.menu = menu;
		this.panel8 = panel8;
		this.sumPrice = sumPrice;
		this.viewMenu= viewMenu;
		this.orderMenu = orderMenu;
		setVisible(true);
		addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(viewMenu.checkOrder(menu,sumPrice)) {
			viewMenu.addMenuPrice(sumPrice, menu);
			orderMenu = new OrderMenu(1);
			orderMenu.setMenuName(menu.menu_name);
			orderMenu.setPrice(menu.menu_price);
			orderMenu.setType(menu.getMenu_catag());
			basket = new Basket(menu,panel8,sumPrice,orderMenu,viewMenu);
			viewMenu.baskets.add(basket);
			viewMenu.orderMenus.add(orderMenu);
		}else {
			viewMenu.addOrderCnt(menu, sumPrice, basket);
		}
		panel8.setVisible(false);
		panel8.setVisible(true);
	}
}
