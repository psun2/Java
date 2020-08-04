package login_p;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.RankDAO;
import java.awt.Font;
import javax.swing.JComboBox;

public class Join extends JFrame {

	public ArrayList<String> data = new ArrayList<String>(); // id, pw, name, email, birth 값을 저장 해서 main으로 넘겨줌

	GameUserDTO dto = new GameUserDTO();
	GameUserDAO dao = new GameUserDAO();

	String[] regex = { "^[a-zA-Z0-9_]{5,12}$", // 아이디 영어랑 숫자만 5글자에서 12글자 사이
			"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // 영문숫자특수문자 포함 8자 이상
			"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // 영문숫자특수문자 포함 8자 이상
			"^[가-힣]{2,4}$", // 이름한글 2~4자
			"^[0-9a-zA-Z_]{2,}@[a-zA-Z]{2,}.(([a-zA-Z]{2,})|([a-zA-Z]{2,}.[a-zA-Z]{2,}))" // 이메일 유효성 검사
	};

	public String id; // id 담아둘 공간
	public String pw; // pw 담아둘 공간
	public String pw2; // pw확인란
	public String name; // 이름
	public String email; // 메일
	public String birth; // 생일

	JTextField JoinIdText;
	JPasswordField passwordField;
	JPasswordField passwordField_1;
	JTextField JoinNameText;
	JTextField JoinEmailText;

	JButton btnCancelMake; // 종료 버튼
	JButton btnMakeMember; // 회원가입시 완료 버튼
	JButton btnIdChk; // 아이디 중복 검사
	int monthL = 1;
	int dayL = 1;
	int yearL = 2020;
	int days = 1;
	int lastday;
	int i=0;

	boolean truechk = false;
	boolean monchk = false;

	public Join() {

		getContentPane().setForeground(Color.RED); // 회원 가입 gui창

		setTitle("회원가입");
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		getContentPane().setLayout(null);
		setSize(600, 700);
		setLocationRelativeTo(null); // gui 화면 정 중앙으로 맞춰줌
		getContentPane().setLayout(null);
		setResizable(false); // gui 사이즈 수정 불가

		JLabel lbJoinId = new JLabel("아이디");
		lbJoinId.setBounds(98, 95, 74, 27);
		getContentPane().add(lbJoinId);

		JLabel lbJoinPw = new JLabel("비밀번호");
		lbJoinPw.setBounds(98, 145, 74, 27);
		getContentPane().add(lbJoinPw);

		JLabel lbJoinPwC = new JLabel("비밀번호 확인");
		lbJoinPwC.setBounds(98, 195, 122, 27);
		getContentPane().add(lbJoinPwC);

		JLabel lbName = new JLabel("이름");
		lbName.setBounds(98, 245, 74, 27);
		getContentPane().add(lbName);

		JLabel lbJoinBirth = new JLabel("생년월일");
		lbJoinBirth.setBounds(98, 295, 74, 27);
		getContentPane().add(lbJoinBirth);

		JLabel lbJoinEmail = new JLabel("이메일");
		lbJoinEmail.setBounds(98, 345, 74, 27);
		getContentPane().add(lbJoinEmail);

		JoinIdText = new JTextField();

		JoinIdText.setBounds(223, 95, 187, 27);
		JoinIdText.setColumns(10);
		getContentPane().add(JoinIdText);

		passwordField = new JPasswordField();
		passwordField.setBounds(223, 145, 187, 27);
		getContentPane().add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(223, 195, 187, 27);
		getContentPane().add(passwordField_1);

		JoinNameText = new JTextField();
		JoinNameText.setColumns(10);
		JoinNameText.setBounds(223, 245, 187, 27);
		getContentPane().add(JoinNameText);

		JoinEmailText = new JTextField();
		JoinEmailText.setColumns(10);
		JoinEmailText.setBounds(223, 345, 187, 27);
		getContentPane().add(JoinEmailText);

		btnCancelMake = new JButton("종료");
		btnCancelMake.setBackground(Color.LIGHT_GRAY);
		btnCancelMake.setBounds(313, 435, 129, 29);
		getContentPane().add(btnCancelMake);
		btnCancelMake.addActionListener(butAct);

		btnMakeMember = new JButton("회원가입");
		btnMakeMember.setBackground(Color.LIGHT_GRAY);
		btnMakeMember.setBounds(142, 435, 129, 29);
		getContentPane().add(btnMakeMember);
		btnMakeMember.addActionListener(butAct);
		btnMakeMember.setEnabled(false);

		btnIdChk = new JButton("중복확인");
		btnIdChk.setBackground(Color.LIGHT_GRAY);
		btnIdChk.setBounds(424, 95, 108, 27);
		getContentPane().add(btnIdChk);

		JLabel lblNewLabel = new JLabel("영문 또는 숫자로 5글자 이상");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(223, 120, 187, 21);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("영문,숫자,특수문자 조합 8글자 이상");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(223, 170, 187, 21);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("한글 2~4글자");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(223, 270, 187, 21);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("ex) 아이디@도메인 주소");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBounds(223, 370, 187, 21);
		getContentPane().add(lblNewLabel_5);

		Calendar calders = Calendar.getInstance();

		JComboBox year = new JComboBox(); // 년
		year.setBounds(223, 297, 74, 23);
		for (int i = 1920; i <= 2020; i++) {
			year.addItem(i);
		}

		JComboBox month = new JComboBox(); // 월
		month.setBounds(322, 297, 52, 23);
		for (int i = 1; i <= 12; i++) {
			month.addItem(i);
		}

		JComboBox<Integer> day = new JComboBox(); // 일
		day.setBounds(396, 297, 46, 23);

		for (int j = 1; j <= 31; j++) {
			day.addItem(j);
		}
		
		
		year.setSelectedItem(2020);
		getContentPane().add(year);
		getContentPane().add(month);
		getContentPane().add(day);

		
		year.addActionListener(new  ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yearL = (int) year.getItemAt(year.getSelectedIndex());
			}
		});
		
		
		month.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			

				if(day.getItemCount()>1) {
					day.removeAllItems();
				}
				
				monthL = (int) month.getItemAt(month.getSelectedIndex());
				calders.set(yearL, monthL - 1, days);
				lastday = calders.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				for (int j = 1; j <= lastday; j++) {
					day.addItem(j);
				}

			}
		});
		
	
		day.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(day.getSelectedItem()!=null) {
					dayL = (int) day.getItemAt(day.getSelectedIndex());
					System.out.println(dayL);
				}
			}
		});
		


		JLabel lblNewLabel_2 = new JLabel("년");
		lblNewLabel_2.setBounds(300, 295, 21, 27);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("월");
		lblNewLabel_2_1.setBounds(378, 295, 21, 27);
		getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("일");
		lblNewLabel_2_1_1.setBounds(446, 295, 21, 27);
		getContentPane().add(lblNewLabel_2_1_1);

		btnIdChk.addActionListener(butAct);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	ActionListener butAct = new ActionListener() { // 액션리스트

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCancelMake) { // 종료 버튼 누를 시 로그인 창으로 화면 전환

				Login lt = new Login();
				dispose();

			}

			if (e.getSource() == btnIdChk) { // 중복 검사
				String jt = JoinIdText.getText().trim();
				if ("".equals(jt) || !Pattern.matches(regex[0], jt)) {
					JOptionPane.showMessageDialog(null, "아이디를 확인 해주세요");
				} else if (dao.idChk(jt) == 0) {
					JOptionPane.showMessageDialog(null, JoinIdText.getText() + " 중복입니다.");
				} else if (dao.idChk(jt) == 1 && Pattern.matches(regex[0], jt)) {
					JOptionPane.showMessageDialog(null, JoinIdText.getText() + " 사용가능합니다.");
					JoinIdText.setEditable(false);
					btnMakeMember.setEnabled(true);
				}

			}

			

			if (e.getSource() == btnMakeMember) { // 회원 가입
				id = JoinIdText.getText().trim();
				pw = passwordField.getText().trim();
				pw2 = passwordField_1.getText().trim();
				name = JoinNameText.getText().trim();
				birth = yearL + "년" + monthL + "월" + dayL + "일";
				email = JoinEmailText.getText().trim();
				
				
				System.out.println(birth);

				String[] joinChk = { id, pw, pw2, name, email }; // 유효성 검사를 위해 배열형
				String[] pppChk = { "id를 확인 해주세요", "비밀번호를 확인해 주세요", "비밀번호를 확인해 주세요", "이름을 확인해 주세요", "email을 확인해 주세요" }; // 에러내용

				while (true) { // while 문을 돌려서 유효성 검사 진행
					int i = 0; // id와 유효성 검사 배열, 에러 알림메세지 배열 인덱스값 표현 용 변수
					if (true) {
						if (Pattern.matches(regex[i], joinChk[i])) { // 패턴이 일치하면 data에 저장
							dto.setId(id);
						} else { // 일치 하지 않을 시 id확인 메세지 창
							JOptionPane.showMessageDialog(null, pppChk[i]);
							break;
						}
						i++;

						if (Pattern.matches(regex[i], joinChk[i])) { // 패턴이 일치하면 data에 저장
						} else { // 일치 하지 않을 시 id확인 메세지 창
							JOptionPane.showMessageDialog(null, pppChk[i]);
							break;
						}

						i++; // id 통과시 i를 증가시켜 다음 배열 순서 진행
						if (Pattern.matches(regex[i], joinChk[i])) {
							if (pw2.equals(pw))
								dto.setPw(pw);
						} else {
							JOptionPane.showMessageDialog(null, pppChk[i]);
							break;
						}
						i++;
						if (Pattern.matches(regex[i], joinChk[i])) {
							dto.setName(name);
						} else {
							JOptionPane.showMessageDialog(null, pppChk[i]);
							break;
						}

						if (yearL==0 || monthL==0 || dayL==0) {	
							JOptionPane.showMessageDialog(null, "생년월일을입력해주세요");
							break;
						} else {
							dto.setBirth(birth);
							
						}
						i++;

						if (Pattern.matches(regex[i], joinChk[i])) {
							dto.setEmail(email);
						} else {
							JOptionPane.showMessageDialog(null, pppChk[i]);
							break;
						}

					}

					new GameUserDAO().insert(dto);
					JOptionPane.showMessageDialog(null, name + "님 회원가입 완료");

					new RankDAO().insertId(id);

					Login lt = new Login();
					dispose();
					break;

				}

			}

		}
	};
	
	public static void main(String[] args) {
		new Join();
	}
}
