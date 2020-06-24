package exam;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Main_JTextPane {

	public static void main(String[] args) {

		JFrame signUp = new JFrame("회원가입");

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

		JTextPane idInput = new JTextPane();
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

		JTextPane nameInput = new JTextPane();
		nameInput.setBounds(30, 380, 300, 30);
		signUp.add(nameInput);

		//

		JLabel birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(30, 430, 100, 15);
		signUp.add(birthLabel);

		JTextPane yearInput = new JTextPane();
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

		JTextPane dayInput = new JTextPane();
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

		JTextPane emailIdInput = new JTextPane();
		emailIdInput.setBounds(30, 605, 90, 30);
		signUp.add(emailIdInput);

		JLabel at = new JLabel("@");
		at.setBounds(130, 605, 20, 30);
		signUp.add(at);

		JTextPane emailIdInput2 = new JTextPane();
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

		JTextPane midNum = new JTextPane();
		midNum.setBounds(180, 680, 70, 30);
		signUp.add(midNum);

		hyphen = new JLabel("-");
		hyphen.setBounds(250, 680, 5, 30);
		signUp.add(hyphen);

		JTextPane enddNum = new JTextPane();
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
		signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X를 눌렀을때 종료하는 역할을 함.

	}

}
