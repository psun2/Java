package exam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("대표특가! 위메프");

					ImageIcon img = new ImageIcon("./위메프img/logo.png");

					frame.setIconImage(img.getImage());

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 791);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel(" 이메일");
		lblNewLabel.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\이메일.png"));
		lblNewLabel.setBounds(12, 78, 115, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(" 비밀번호 영문/숫자/특수문자 조합 6 ~ 15자");
		lblNewLabel_1.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\비밀번호.png"));
		lblNewLabel_1.setBounds(12, 123, 326, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 확인");
		lblNewLabel_1_1.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\비밀번호확인.png"));
		lblNewLabel_1_1.setBounds(12, 168, 149, 35);
		contentPane.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(37, 78, 116, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("@");
		lblNewLabel_2.setBounds(166, 78, 12, 35);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(190, 78, 116, 35);
		contentPane.add(textField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "직접입력", "naver.com", "google.com", "daum.net" }));
		comboBox.setBounds(318, 78, 93, 35);
		contentPane.add(comboBox);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(37, 123, 374, 35);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		passwordField_1.setBounds(37, 168, 374, 35);
		contentPane.add(passwordField_1);

		JLabel lblNewLabel_1_1_1 = new JLabel(" 이름");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\이름.png"));
		lblNewLabel_1_1_1.setBounds(12, 213, 61, 35);
		contentPane.add(lblNewLabel_1_1_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(37, 213, 374, 35);
		contentPane.add(textField_4);

		JLabel lblNewLabel_1_1_2 = new JLabel(" 휴대폰 번호 -없이 입력");
		lblNewLabel_1_1_2.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\휴대폰.png"));
		lblNewLabel_1_1_2.setBounds(12, 258, 208, 35);
		contentPane.add(lblNewLabel_1_1_2);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(37, 258, 374, 35);
		contentPane.add(textField_5);

		JLabel lblNewLabel_1_1_3 = new JLabel(" 생년월일 8자리 숫자로 입력 (ex.19920101)");
		lblNewLabel_1_1_3.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\생년월일.png"));
		lblNewLabel_1_1_3.setBounds(10, 305, 279, 35);
		contentPane.add(lblNewLabel_1_1_3);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(37, 305, 374, 35);
		contentPane.add(textField_6);

		JLabel lblNewLabel_1_1_3_1 = new JLabel(" 성별 (선택)");
		lblNewLabel_1_1_3_1.setIcon(new ImageIcon(
				"D:\\Study\\Java\\Java-study\\035. JavaSwing(JToolBar, JTabbedPane, JSpinner, Chooser) - builder 을 활용한 회원가입\\위메프img\\성별.png"));
		lblNewLabel_1_1_3_1.setBounds(12, 350, 115, 35);
		contentPane.add(lblNewLabel_1_1_3_1);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("남자");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(122, 356, 61, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("여자");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(190, 356, 61, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);

		JLabel lblNewLabel_3 = new JLabel("º 캠퍼스 인증 이메일은 24시간 유효합니다.");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(12, 385, 399, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("º 발송된 이메일을 인증하지 않으면 캠퍼스의 혜택을 받으실 수 없습니다.");
		lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_1.setBounds(12, 406, 432, 15);
		contentPane.add(lblNewLabel_3_1);

		JCheckBox chckbxNewCheckBox = new JCheckBox("전체동의");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(12, 427, 85, 23);
		contentPane.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("이용약관");
		chckbxNewCheckBox_1.setBackground(Color.WHITE);
		chckbxNewCheckBox_1.setBounds(12, 463, 85, 23);
		contentPane.add(chckbxNewCheckBox_1);

		JLabel lblNewLabel_4 = new JLabel("(필수)");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(103, 467, 34, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("본 약관에는 마케팅 정보 수신에 대한 동의에 관한 내용이 포함");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_5.setBounds(37, 483, 416, 15);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("되어 있으며, 회원은 언제든지 회원정보 수정에서 수신 거부로 변경할");
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setBounds(37, 502, 395, 15);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("수 있습니다.");
		lblNewLabel_6_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_6_1.setBounds(37, 521, 395, 15);
		contentPane.add(lblNewLabel_6_1);

		JButton btnNewButton = new JButton("전체보기");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(318, 431, 94, 42);
		contentPane.add(btnNewButton);

		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("개인정보 수집 및 이용");
		chckbxNewCheckBox_1_1.setBackground(Color.WHITE);
		chckbxNewCheckBox_1_1.setBounds(12, 542, 154, 23);
		contentPane.add(chckbxNewCheckBox_1_1);

		JLabel lblNewLabel_4_1 = new JLabel("(필수)");
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setBounds(166, 546, 34, 15);
		contentPane.add(lblNewLabel_4_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "전체보기" }));
		comboBox_1.setBounds(318, 546, 93, 21);
		contentPane.add(comboBox_1);

		JCheckBox chckbxNewCheckBox_1_1_1 = new JCheckBox("개인정보 수집 및 이용");
		chckbxNewCheckBox_1_1_1.setBackground(Color.WHITE);
		chckbxNewCheckBox_1_1_1.setBounds(12, 583, 154, 23);
		contentPane.add(chckbxNewCheckBox_1_1_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("(선택)");
		lblNewLabel_4_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1_1.setBounds(166, 587, 34, 15);
		contentPane.add(lblNewLabel_4_1_1);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] { "전체보기" }));
		comboBox_1_1.setBackground(Color.WHITE);
		comboBox_1_1.setBounds(318, 584, 93, 21);
		contentPane.add(comboBox_1_1);

		JLabel lblNewLabel_7 = new JLabel("선택항목은 동의하지 않아도 회원가입이 가능합니다.");
		lblNewLabel_7.setBounds(23, 627, 382, 15);
		contentPane.add(lblNewLabel_7);

		JButton btnNewButton_1 = new JButton("동의하고 회원가입");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(140, 682, 149, 42);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_8 = new JLabel("회원가입");
		lblNewLabel_8.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_8.setBounds(16, 25, 204, 43);
		contentPane.add(lblNewLabel_8);
	}
}
