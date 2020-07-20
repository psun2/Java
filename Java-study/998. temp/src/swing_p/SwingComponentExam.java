package swing_p;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SwingComponentExam {

	public static void main(String[] args) {

		JFrame membership = new JFrame("회원가입");

		membership.setBounds(400, 100, 800, 800); // 프레임 크기 설정
		membership.setLayout(null); // 레이아웃 설정

		JLabel id = new JLabel("아이디");
		id.setBounds(100, 30, 100, 50);
		membership.add(id);

		JLabel idstar = new JLabel("*");
		idstar.setBounds(140, 52, 10, 10);
		idstar.setForeground(Color.red);
		membership.add(idstar);

		JTextField idinput = new JPasswordField();
		idinput.setBounds(250, 42, 300, 30);
		membership.add(idinput);

//////////////////////////////////////////////////////////////////////////////////

		JLabel password = new JLabel("비밀번호");
		password.setBounds(100, 80, 100, 50);
		membership.add(password);

		JLabel pwstar = new JLabel("*");
		pwstar.setBounds(155, 102, 10, 10);
		pwstar.setForeground(Color.red);
		membership.add(pwstar);

		JTextField pwinput = new JPasswordField();
		pwinput.setBounds(250, 92, 300, 30);
		membership.add(pwinput);

//////////////////////////////////////////////////////////////////////////////////

		JLabel pwChk = new JLabel("비밀번호 확인");
		pwChk.setBounds(100, 130, 100, 50);
		membership.add(pwChk);

		JLabel pwChkstar = new JLabel("*");
		pwChkstar.setBounds(185, 152, 10, 10);
		pwChkstar.setForeground(Color.red);
		membership.add(pwChkstar);

		JTextField pwChkinput = new JPasswordField();
		pwChkinput.setBounds(250, 142, 300, 30);
		membership.add(pwChkinput);

//////////////////////////////////////////////////////////////////////////////////
		JLabel name = new JLabel("이름");
		name.setBounds(100, 180, 100, 50);
		membership.add(name);

		JLabel namestar = new JLabel("*");
		namestar.setBounds(130, 202, 10, 10);
		namestar.setForeground(Color.red);
		membership.add(namestar);

		JTextField nameinput = new JPasswordField();
		nameinput.setBounds(250, 192, 300, 30);
		membership.add(nameinput);

//////////////////////////////////////////////////////////////////////////////////

		JLabel email = new JLabel("email");
		email.setBounds(100, 230, 100, 50);
		membership.add(email);

		JLabel emailstar = new JLabel("*");
		emailstar.setBounds(140, 252, 10, 10);
		emailstar.setForeground(Color.red);
		membership.add(emailstar);

		JTextField emailinput = new JPasswordField();
		emailinput.setBounds(250, 242, 100, 30);
		membership.add(emailinput);

		String str = "입력하신 이메일은 아이디, 비밀번호 분실 시 찾기 위한 용도로 사용되오니 신중하게 입력해 주세요.";
		JLabel message = new JLabel(str);
		message.setForeground(Color.blue);
		message.setBounds(250, 282, 600, 100);
		membership.add(message);

		JLabel gol = new JLabel("@");
		gol.setBounds(360, 230, 20, 50);
		membership.add(gol);

		JTextField emailinput2 = new JPasswordField();
		emailinput2.setBounds(390, 242, 100, 30);
		membership.add(emailinput2);

		Vector<String> emailList = new Vector<String>();
		emailList.add("네이버");
		emailList.add("다음");
		emailList.add("구글");
		emailList.add("야후");
		emailList.add("직접입력");

		JComboBox<String> box = new JComboBox<String>(emailList);
		box.setBounds(500, 242, 100, 30);
		box.setSelectedItem("직접입력"); // 기본값
		membership.add(box);

//////////////////////////////////////////////////////////////////////////////////

		JLabel bith = new JLabel("생년월일");
		bith.setBounds(100, 280, 100, 50);
		membership.add(bith);

		JLabel bithstar = new JLabel("*");
		bithstar.setBounds(155, 302, 10, 10);
		bithstar.setForeground(Color.red);
		membership.add(bithstar);

		Vector<String> year = new Vector<String>();
		year.add("1990");
		year.add("1991");
		year.add("1992");
		year.add("1993");
		year.add("년도");

		JComboBox<String> yearBox = new JComboBox<String>(year);
		yearBox.setBounds(250, 292, 100, 30);
		yearBox.setSelectedItem("년도"); // 기본값
		membership.add(yearBox);

		JLabel y = new JLabel("년");
		y.setBounds(360, 280, 20, 50);
		membership.add(y);

		Vector<String> month = new Vector<String>();
		month.add("1990");
		month.add("1991");
		month.add("1992");
		month.add("1993");
		month.add("월");

		JComboBox<String> monthBox = new JComboBox<String>(month);
		monthBox.setBounds(380, 292, 100, 30);
		monthBox.setSelectedItem("월"); // 기본값
		membership.add(monthBox);

		JLabel m = new JLabel("월");
		m.setBounds(490, 280, 20, 50);
		membership.add(m);

		Vector<String> day = new Vector<String>();
		day.add("1990");
		day.add("1991");
		day.add("1992");
		day.add("1993");
		day.add("일");

		JComboBox<String> dayBox = new JComboBox<String>(day);
		dayBox.setBounds(510, 292, 100, 30);
		dayBox.setSelectedItem("일"); // 기본값
		membership.add(dayBox);

		JLabel d = new JLabel("일");
		d.setBounds(620, 280, 20, 50);
		membership.add(d);

//////////////////////////////////////////////////////////////////////////////////

		membership.setVisible(true); // 프레임이 눈에 보이도록 설정
		membership.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 close 설정
	}

}
