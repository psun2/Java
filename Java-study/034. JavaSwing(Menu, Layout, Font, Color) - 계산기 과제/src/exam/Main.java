package exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class Calculator extends JFrame {

	public Calculator(String title) {
		super(title);
		init();
	}

	void init() {

		setBounds(50, 50, 386, 547);
//		setLayout(new GridLayout(5,1));
		setLayout(null);

		// menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("≡");
		menu.setFont(new Font("Agency", Font.BOLD, 20));
		menuBar.add(menu);

		String itemTitle = "표준,공학용,</> 프로그래머,날짜 계산";

		for (String title : itemTitle.split(",")) {
			menu.add(new JMenuItem(title));
		}

		JLabel menuLabel = new JLabel("프로그래머");
		menuBar.add(menuLabel);

		// result
		JPanel action = new JPanel();
		action.setBackground(new Color(241, 243, 242));
		action.setLayout(new BorderLayout());
		JLabel actionLabel = new JLabel("0");
		actionLabel.setFont(new Font("Agency", Font.BOLD, 20));
		action.add(actionLabel, "East");
		action.setBounds(0, 0, 370, 60);
		add(action);

		// option
		JPanel option = new JPanel();
		option.setBounds(0, 60, 370, 90);
		option.setBackground(new Color(241, 243, 242));
		option.setLayout(null);

		JLabel hex = new JLabel("HEX");
		hex.setBounds(10, 10, 30, 10);
		option.add(hex);
		Label zero = new Label("0");
		zero.setBounds(45, 10, 30, 10);
		option.add(zero);

		JLabel DEC = new JLabel("DEX");
		DEC.setBounds(10, 30, 30, 10);
		option.add(DEC);
		zero = new Label("0");
		zero.setBounds(45, 30, 30, 10);
		option.add(zero);

		JLabel OCT = new JLabel("OCT");
		OCT.setBounds(10, 50, 30, 10);
		option.add(OCT);
		zero = new Label("0");
		zero.setBounds(45, 50, 30, 10);
		option.add(zero);

		JLabel BIN = new JLabel("BIN");
		BIN.setBounds(10, 70, 30, 10);
		option.add(BIN);
		zero = new Label("0");
		zero.setBounds(45, 70, 30, 10);
		option.add(zero);

		add(option);

		// btns
		JPanel btns = new JPanel();
		btns.setBounds(0, 150, 370, 30);
		btns.setLayout(new GridLayout(1, 5, 10, 0));
		btns.setBackground(new Color(241, 243, 242));

		ImageIcon icon1 = new ImageIcon("./exam-icons/icon1.png");
		JButton button1 = new JButton(icon1);
		button1.setBackground(new Color(241, 243, 242));
		button1.setBorder(null);
		btns.add(button1);

		ImageIcon icon2 = new ImageIcon("./exam-icons/icon2.png");
		JButton button2 = new JButton(icon2);
		button2.setBackground(new Color(241, 243, 242));
		button2.setBorder(null);
		btns.add(button2);

		JButton button3 = new JButton("DWORD");
		button3.setBackground(new Color(241, 243, 242));
		button3.setBorder(null);
		btns.add(button3);

		JButton button4 = new JButton("MS");
		button4.setBackground(new Color(241, 243, 242));
		button4.setBorder(null);
		btns.add(button4);

		JButton button5 = new JButton("M▼");
		button5.setBackground(new Color(241, 243, 242));
		button5.setEnabled(false);
		button5.setBorder(null);
		btns.add(button5);

		add(btns);

		// optionMenu
		JPanel optionMenu = new JPanel();
		optionMenu.setBounds(0, 180, 370, 30);
		optionMenu.setBackground(new Color(241, 243, 242));
		optionMenu.setLayout(new FlowLayout(FlowLayout.LEFT));

		ImageIcon icon3 = new ImageIcon("./exam-icons/icon3.png");
		JLabel bit = new JLabel(icon3);
		bit.setBackground(new Color(241, 243, 242));
		optionMenu.add(bit);

		String bitBoxTitle = "비트,AND,OR,NOT,NAND,NOR,XOR";

		JComboBox<String> bitBox = new JComboBox<String>(bitBoxTitle.split(","));
		bitBox.setBackground(new Color(241, 243, 242));
		bitBox.setBorder(null);
		bitBox.setSelectedIndex(0);
		optionMenu.add(bitBox);

		ImageIcon icon4 = new ImageIcon("./exam-icons/icon4.png");
		JLabel bitShift = new JLabel(icon4);
		bitShift.setBackground(new Color(241, 243, 242));
		optionMenu.add(bitShift);

		String bitShiftBoxTitle = "비트 시프트,산술 시프트,논리적 시프트,원형 시프트 회전,자리 올림 순환 시프트를 통해 회전";

		JComboBox<String> bitShiftBox = new JComboBox<String>(bitShiftBoxTitle.split(","));
		bitShiftBox.setBackground(new Color(241, 243, 242));
		bitShiftBox.setBorder(null);
		bitShiftBox.setSelectedIndex(0);
		optionMenu.add(bitShiftBox);

		add(optionMenu);

		// nums
		JPanel nums = new JPanel();
		nums.setBounds(0, 210, 370, 267);
		nums.setBackground(new Color(241, 243, 242));
		nums.setLayout(new GridLayout(6, 5));

		String numsTitle = "A,<<,>>,c,X,B,(,),%,÷,C,7,8,9,X,D,4,5,6,-,E,1,2,3,+,F,±,0,.,=";

		for (String title : numsTitle.split(",")) {
			nums.add(new JButton(title));
		}

		int[] enabled = { 0, 5, 10, 15, 12, 13, 15, 20, 25, 28 };

		for (int i = 0; i <= 29; i++) {

			boolean chk = true;

			for (int j : enabled) {
				if (i == j) {
					nums.getComponent(i).setEnabled(false);
					chk = false;
					break;
				}

			}

			if (chk)
				nums.getComponent(i).setBackground(new Color(255, 193, 227));
		}

		add(nums);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		chkSize();

	}

	void chkSize() {

		while (true) {

			try {
				Thread.sleep(1000);
				System.out.println(getSize());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class Main {

	public static void main(String[] args) {

		new Calculator("계산기");

	}

}
