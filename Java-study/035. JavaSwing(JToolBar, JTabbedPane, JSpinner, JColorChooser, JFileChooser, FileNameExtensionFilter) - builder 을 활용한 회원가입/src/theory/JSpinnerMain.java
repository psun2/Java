package theory;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

class Spinner extends JFrame {

	public Spinner(String title) {
		setTitle(title);
		init();
	}

	void init() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println(screen); // java.awt.Dimension[width=1920,height=1080]
		int xpos = (int) (screen.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2);
		int width = 400;
		int height = 400;
		setBounds(xpos - (width / 2), ypos - (height / 2), width, height);
		setIconImage(new ImageIcon("위메프img/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 2));

		JSpinner sp1 = new JSpinner();
		JSpinner sp2 = new JSpinner();
		JSpinner sp3 = new JSpinner();
		JSpinner sp4 = new JSpinner();

		// setLayout(new GridLayout(3, 2)); 이 Grid 상태라 사이즈 설정이 무의미 합니다.
//		Dimension d = new Dimension(100, 10);
//		sp1.setPreferredSize(d);
//		sp2.setPreferredSize(d);
//		sp3.setPreferredSize(d);

		add(new JLabel("list"));
		add(sp1);

		add(new JLabel("숫자"));
		add(sp2);

		add(new JLabel("날짜"));
		add(sp3);

		add(new JLabel("기본 Spinner"));
		add(sp4);

		// ListModel 콤보 박스로 대체 가능
		String[] dinner = { "짜장면", "회덮밥", "바스버거", "치킨", "곱창" };
		SpinnerListModel mo1 = new SpinnerListModel(dinner); // 모델을 셋팅 할 수 있는 객체 생성
		sp1.setModel(mo1);
		sp1.setValue("회덮밥"); // setModel 이후에 설정가능 이전 설정시 Error // default 값 설정

		// SpinnerNumberModel
		// int value, int minimum, int maximum, int stepSize
		// 초기값, 최소값, 최대값, 단계
		SpinnerNumberModel mo2 = new SpinnerNumberModel(10, 0, 20, 5);
		sp2.setModel(mo2);
		sp2.setValue(3); // default 값 설정

		// Date 객체 생성
		Date today = new Date();
		Date start = new Date(1990 - 1900, 1 - 1, 1);
		Date end = new Date(2050 - 1900, 12 - 1, 1);

		// SpinnerDateModel
		// Date value, Comparable start, Comparable end, int calendarField
		SpinnerDateModel mo3 = new SpinnerDateModel(today, start, end, Calendar.MONTH);
		sp3.setModel(mo3);
		sp3.setEditor(new JSpinner.DateEditor(sp3, "yyyy-MM-dd\tHH:mm:ss")); // SimpleDateFormat

		setVisible(true);

	}
}

public class JSpinnerMain {

	public static void main(String[] args) {

		new Spinner("JSpinner");

	}
}
