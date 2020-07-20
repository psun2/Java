package swing_p;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class SpinnerMain extends JFrame {

	public SpinnerMain() {

		setTitle("Spinner");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
		this.setLocation(xpos, ypos);
		setBounds(xpos - 100, ypos - 200, 200, 400);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new FlowLayout());
		setLayout(new GridLayout(3, 2));

		JSpinner sp1 = new JSpinner();
		JSpinner sp2 = new JSpinner();
		JSpinner sp3 = new JSpinner();

		Dimension d = new Dimension(100, 50);
		sp1.setPreferredSize(d);
		sp2.setPreferredSize(d);
		sp3.setPreferredSize(d);

		add(new JLabel("list"));
		add(sp1);

		add(new JLabel("숫자"));
		add(sp2);

		add(new JLabel("날짜"));
		add(sp3);

		// 콤보 박스로 대체 가능
		String[] dinner = { "짜장면", "회덮밥", "바스버거", "치킨", "곱창" };
		SpinnerListModel mo1 = new SpinnerListModel(dinner);
		sp1.setModel(mo1);
		sp1.setValue("회덮밥"); // setModel 이후에 설정가능 이전 설정시 Error

//		new SpinnerNumberModel(value, minimum, maximum, stepSize)
		// 초기값, 최소, 최대, 단계
		SpinnerNumberModel mo2 = new SpinnerNumberModel(20, 10, 30, 5);
		sp2.setModel(mo2);
		sp2.setValue(15); // setModel 이후에 설정가능 이전 설정시 Error

		Date vv = new Date(2002 - 1900, 1 - 1, 10);
		Date start = new Date(1990 - 1900, 1 - 1, 10);
		Date end = new Date(2020 - 1900, 12 - 1, 10);

		// new SpinnerDateModel(value, start, end, calendarField)
		// 초기날짜, 최소날짜, 최대날짜, Calendar 단위 필드
		SpinnerDateModel mo3 = new SpinnerDateModel(vv, start, end, Calendar.MONTH);

		sp3.setModel(mo3);
		sp3.setEditor(new JSpinner.DateEditor(sp3, "yyyy-MM-dd"));

		setVisible(true);
	}

	public static void main(String[] args) {

		new SpinnerMain();

	}

}
