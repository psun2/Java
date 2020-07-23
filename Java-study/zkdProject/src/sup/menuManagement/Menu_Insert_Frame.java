package sup.menuManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sup.menu.MenuMainView;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Menu_Insert_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField menu_nameF;
	private JTextField menu_priceF;
	private JTextField menu_catagF;
	

	public Menu_Insert_Frame(MenuManagementMain menuGui, MMcontroller control) {
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel menu_nameL = new JLabel("\uBA54\uB274 \uC774\uB984");
		panel.add(menu_nameL);
		
		menu_nameF = new JTextField();
		panel.add(menu_nameF);
		menu_nameF.setColumns(10);
		
		JLabel menu_priceL = new JLabel("\uBA54\uB274 \uAC00\uACA9");
		panel.add(menu_priceL);
		
		menu_priceF = new JTextField();
		panel.add(menu_priceF);
		menu_priceF.setColumns(10);
		
		JLabel menu_catagL = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		panel.add(menu_catagL);
		
		menu_catagF = new JTextField();
		panel.add(menu_catagF);
		menu_catagF.setColumns(10);
		
		JButton menu_create_btn = new JButton("\uD655\uC778");
		menu_create_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				control.create_menu(menu_nameF.getText(), menu_catagF.getText(), 
											   Integer.parseInt(menu_priceF.getText()));
				menuGui.menuTable = control.refreshMeneegementList(menuGui.model, menuGui.menuTable);
				dispose();
			}
		});
		contentPane.add(menu_create_btn, BorderLayout.SOUTH);
	}
}
