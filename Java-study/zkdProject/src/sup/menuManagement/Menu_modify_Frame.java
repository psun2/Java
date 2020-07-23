package sup.menuManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sup.menu.Menu;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Menu_modify_Frame extends JFrame {
	File imageF;
	JPanel contentPane;
	JLabel image_label;
	JTextField textField;
	JTextField textField_1;
	JComboBox comboBox;
	MMcontroller mmCon;
//	public static void main(String[] args) {
//		new Menu_modify_Frame(new MMcontroller("localhost", 7777));
//	}
	public Menu_modify_Frame(MMcontroller mmCon, Menu menu, MenuManagementMain mmGui) {
		this.mmCon = mmCon;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		image_label = new JLabel("New label");
		image_label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(image_label);
		
		JPanel panel_update = new JPanel();
		contentPane.add(panel_update);
		panel_update.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel manuNameLabel = new JLabel("\uBA54\uB274 \uC774\uB984");
		manuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_update.add(manuNameLabel);
		
		textField = new JTextField(menu.getMenu_name());
		panel_update.add(textField);
		textField.setColumns(10);
		
		JLabel menuPriceLabel_1 = new JLabel("\uAC00\uACA9");
		menuPriceLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_update.add(menuPriceLabel_1);
		
		textField_1 = new JTextField(menu.getMenu_price()+"");
		panel_update.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel menuCatagLabel = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		menuCatagLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_update.add(menuCatagLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC548\uC8FC", "\uC220"}));
		comboBox.setSelectedItem(menu.getMenu_catag());
		panel_update.add(comboBox);
		
		JLabel imageLabel_3 = new JLabel("\uC774\uBBF8\uC9C0 \uB85C\uB4DC");
		imageLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_update.add(imageLabel_3);
		try {
			System.out.println(menu.getImageF());
			BufferedImage bf = ImageIO.read(menu.getImageF());
			image_label.setIcon(new ImageIcon(bf));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
//		JButton imageloadBtn = new JButton("\uD30C\uC77C \uB85C\uB4DC");
//		imageloadBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					imageF = mmCon.fileOpenDlg();
//					System.out.println(imageF);
//					BufferedImage bf = ImageIO.read(imageF);
//					image_label.setIcon(new ImageIcon(bf));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		panel_update.add(imageloadBtn);
		
		JLabel lblNewLabel = new JLabel("");
		panel_update.add(lblNewLabel);
		
		JButton applyButton = new JButton("\uC801\uC6A9");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(imageF);
				mmCon.update_menu(new Menu(menu.getMenu_code(), textField.getText(), 
								(String) comboBox.getModel().getSelectedItem(), Integer.parseInt(textField_1.getText())));
				mmGui.menuTable = mmCon.refreshMeneegementList(mmGui.model, mmGui.menuTable);
			}
		});
		panel_update.add(applyButton);
		setVisible(true);
	}

}
