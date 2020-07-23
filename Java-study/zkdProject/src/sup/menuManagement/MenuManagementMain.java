package sup.menuManagement;

import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sup.menu.Menu;
import sup.menu.MenuMainView;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class MenuManagementMain extends JFrame {
	
	DefaultTableModel model;
	JPanel contentPane;
	JTable menuTable;
	MMcontroller mmCon;
	int selectedIndex;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MenuManagementMain(MMcontroller mmCon) {
		this.mmCon = mmCon;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		menuTable = new JTable();
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(menuTable);
		
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("\uBA54\uB274 \uCD94\uAC00");
		
		btnNewButton.addActionListener(new btnAction());
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(btnNewButton);
		

		//메뉴 삭제 버튼
		JButton deleteMenuBtn = new JButton("\uBA54\uB274 \uC0AD\uC81C");
		deleteMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String) menuTable.getValueAt(selectedIndex, 0);
				mmCon.del_menu(value);
				menuTable = mmCon.refreshMeneegementList(model, menuTable);
			}
		});
		panel.add(deleteMenuBtn);
		
		JButton modifyMenuBtn = new JButton("\uBA54\uB274 \uC218\uC815");
		
		//메뉴 수정 버튼
		modifyMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu((String)menuTable.getValueAt(selectedIndex, 0), (String)menuTable.getValueAt(selectedIndex, 1)
						,(String) menuTable.getValueAt(selectedIndex, 3)
						,Integer.parseInt((String) menuTable.getValueAt(selectedIndex, 2)));
				mmCon.open_menuModifyFream(MenuManagementMain.this, menu);
//				mmCon.update_menu(new Menu((String)menuTable.getValueAt(selectedIndex, 0), (String)menuTable.getValueAt(selectedIndex, 1)
//														,(String) menuTable.getValueAt(selectedIndex, 3)
//														,Integer.parseInt((String) menuTable.getValueAt(selectedIndex, 2))));
//				menuTable = mmCon.refreshMeneegementList(model, menuTable);
			}
		});
		panel.add(modifyMenuBtn);
		
		menuTable = mmCon.insertMenu(model,menuTable);
		
		
		setVisible(true);
		menuTable.addMouseListener(new mouseAction());
	}
	
	
	
	class btnAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mmCon.open_menuInsertFrame(MenuManagementMain.this);
			
		}
		
	}
	class mouseAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {

			selectedIndex = menuTable.getSelectedRow();
			System.out.println(selectedIndex);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}