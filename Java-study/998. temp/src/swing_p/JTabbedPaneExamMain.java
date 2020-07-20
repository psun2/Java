package swing_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class JTabbedPaneExam extends JFrame {

	public JTabbedPaneExam() {

		init();
	}

	void init() {

		setTitle("합체");
		setBounds(100, 100, 700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tab = new JTabbedPane();

		tab.addTab("회원가입", signUP());
		tab.addTab("계산기", calculator());

		////////////////////
		add(tab);

		setVisible(true);

	}

	JPanel signUP() {

		JPanel signUp = new JPanel();

		signUp.setBounds(100, 10, 380, 1000); // 초기 size 설정 x, y, width, height
		signUp.setLayout(null); // Layout 을 내가 설정한 대로 배치 할수 있게 해줌...

		ImageIcon logoImg = new ImageIcon("./exam_img/naver.png");
		JButton logo = new JButton(logoImg);
		logo.setBounds(30, 30, 245, 50);
		signUp.add(logo);

		//

		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(30, 130, 100, 15);
		signUp.add(idLabel);

		JTextField idInput = new JTextField();
		idInput.setBounds(30, 155, 300, 30);
		signUp.add(idInput);

		//

		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(30, 205, 100, 15);
		signUp.add(pwLabel);

		JPasswordField pwInput = new JPasswordField();
		pwInput.setBounds(30, 230, 300, 30);
		signUp.add(pwInput);

		//

		JLabel pwChkLabel = new JLabel("비밀번호 재확인");
		pwChkLabel.setBounds(30, 280, 100, 15);
		signUp.add(pwChkLabel);

		JPasswordField pwChkInput = new JPasswordField();
		pwChkInput.setBounds(30, 305, 300, 30);
		signUp.add(pwChkInput);

		//

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(30, 355, 100, 15);
		signUp.add(nameLabel);

		JTextField nameInput = new JTextField();
		nameInput.setBounds(30, 380, 300, 30);
		signUp.add(nameInput);

		//

		JLabel birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(30, 430, 100, 15);
		signUp.add(birthLabel);

		JTextField yearInput = new JTextField();
		yearInput.setBounds(30, 455, 90, 30);
		signUp.add(yearInput);

		Vector<String> month = new Vector<String>();
		for (int i = 1; i <= 12; i++) {
			month.add("" + i);
		}
		month.add("월");

		JComboBox<String> monthBox = new JComboBox<String>(month);
		monthBox.setBounds(135, 455, 90, 30);
		monthBox.setSelectedItem("월");
		signUp.add(monthBox);

		JTextField dayInput = new JTextField();
		dayInput.setBounds(240, 455, 90, 30);
		signUp.add(dayInput);

		//

		JLabel genderLabel = new JLabel("성별");
		genderLabel.setBounds(30, 505, 100, 15);
		signUp.add(genderLabel);

		JRadioButton man = new JRadioButton("man");
		man.setBounds(30, 530, 90, 30);
		signUp.add(man);

		JRadioButton woman = new JRadioButton("woman");
		woman.setBounds(135, 530, 90, 30);
		signUp.add(woman);

		JRadioButton bisexual = new JRadioButton("bisexual");
		bisexual.setBounds(240, 530, 90, 30);
		signUp.add(bisexual);

		ButtonGroup genderBtn = new ButtonGroup();
		genderBtn.add(man);
		genderBtn.add(woman);
		genderBtn.add(bisexual);

		//

		JLabel emailChkLabel = new JLabel("본인 확인 이메일");
		emailChkLabel.setBounds(30, 580, 100, 15);
		signUp.add(emailChkLabel);

		JTextField emailIdInput = new JTextField();
		emailIdInput.setBounds(30, 605, 90, 30);
		signUp.add(emailIdInput);

		JLabel at = new JLabel("@");
		at.setBounds(130, 605, 20, 30);
		signUp.add(at);

		JTextField emailIdInput2 = new JTextField();
		emailIdInput2.setBounds(160, 605, 90, 30);
		signUp.add(emailIdInput2);

		String[] emailArr = { "naver.com", "직접입력" };

		Vector<String> emails = new Vector<String>();
		for (String str : emailArr) {
			emails.add(str);
		}

		JComboBox<String> email = new JComboBox<String>(emails);
		email.setBounds(250, 605, 90, 30);
		email.setSelectedItem("직접입력");
		signUp.add(email);

		//

		JLabel phone = new JLabel("휴대전화");
		phone.setBounds(30, 655, 100, 15);
		signUp.add(phone);

		String[] agencyArr = { "SKT", "KT", "LU+" };

		Vector<String> agencys = new Vector<String>();
		for (String str : agencyArr) {
			agencys.add(str);
		}

		JComboBox<String> agency = new JComboBox<String>(agencys);
		agency.setBounds(30, 680, 70, 30);
		agency.setSelectedItem("SKT");
		signUp.add(agency);

		Vector<String> nums = new Vector<String>();
		for (int i = 0; i <= 9; i++) {
			String temp = "01";
			nums.add(temp + i);
		}

		JComboBox<String> num = new JComboBox<String>(nums);
		num.setBounds(105, 680, 70, 30);
		num.setSelectedItem("010");
		signUp.add(num);

		JLabel hyphen = new JLabel("-");
		hyphen.setBounds(175, 680, 5, 30);
		signUp.add(hyphen);

		JTextField midNum = new JTextField();
		midNum.setBounds(180, 680, 70, 30);
		signUp.add(midNum);

		hyphen = new JLabel("-");
		hyphen.setBounds(250, 680, 5, 30);
		signUp.add(hyphen);

		JTextField enddNum = new JTextField();
		enddNum.setBounds(255, 680, 70, 30);
		signUp.add(enddNum);

		//

		JLabel introduce = new JLabel("자기소개");
		introduce.setBounds(30, 730, 100, 15);
		signUp.add(introduce);

		JTextArea introduceArea = new JTextArea();

		JScrollPane introduceScroll = new JScrollPane(introduceArea);
		introduceScroll.setBounds(30, 755, 300, 100);
		signUp.add(introduceScroll);
		//

		ImageIcon join = new ImageIcon("./exam_img/가입하기Btn.png");

		JButton joinBtn = new JButton(join);
		joinBtn.setBounds(30, 875, 300, 50);
		signUp.add(joinBtn);

		//

		signUp.setVisible(true); // Swing 창을 눈에 보이는 역할을함

		return signUp;

	}

	JPanel calculator() {

		JPanel calculator = new JPanel();

		calculator.setBounds(50, 50, 386, 547);
//		setLayout(new GridLayout(5,1));
		calculator.setLayout(null);

		// menu
		JMenuBar menuBar = new JMenuBar();
//		calculator.setJMenuBar(menuBar);

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
		calculator.add(action);

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

		calculator.add(option);

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

		calculator.add(btns);

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

		calculator.add(optionMenu);

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

		calculator.add(nums);

		calculator.setVisible(true);

//		chkSize();

		return calculator;

	}

}

public class JTabbedPaneExamMain {
	public static void main(String[] args) {

		new JTabbedPaneExam();

	}
}
