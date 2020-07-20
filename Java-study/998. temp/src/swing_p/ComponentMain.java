package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class ComponentMain {

	public static void main(String[] args) {

		JFrame f = new JFrame("컴포넌트다");

		// 사이즈 조절
		// f.setSize(width, height); 가로, 세로
//		f.setSize(800, 700);
		// Dimension : 치수
		Dimension d = new Dimension(800, 700); // width, height
//		f.setSize(d);
		// 16 ~ 17 Line 은 f.setSize(800, 700); 와 같은 결과 발생

		// 위치변경
		// f.setLocation(x, y); // 모니터를 기준
//		f.setLocation(100, 300);
		// x 축으로 100 만큼, y 축으로 300만큼 에서 시작
		Point p = new Point(100, 300); // x, y
//		f.setLocation(p);
		// 25 ~ 26 Line 은 f.setLocation(100, 300); 와 같은 결과 발생

		// 사이즈 조절과 위치변경을 한번에 진행 하는 setBounds
//		f.setBounds(r);
//		f.setBounds(x, y, width, height);
//		f.setBounds(100, 300, 800, 700);
		Rectangle r = new Rectangle(p, d);
		f.setBounds(r);
		// 35 ~ 36 Line 은 f.setBounds(100, 300, 800, 700); 와 같은결과

		// 버튼 생성
		JButton b1 = new JButton("버튼1");
//		b1.setBounds(100, 100, 100, 100); // 왜 안먹힘 ?
		f.setLayout(null); // 프레임 안에서 일어나기 때문에
		// 프레임의 정렬 기준을 날림

		b1.setBounds(20, 50, 70, 30); // 이제는 됨 ?
		f.add(b1);

		JButton b2 = new JButton("버튼2");
		b2.setBounds(100, 50, 70, 30); // 이제는 됨 ?
		b2.setEnabled(false); // 활성화를 막음....
		f.add(b2);

		JButton b3 = new JButton("버튼3");
		b3.setBounds(180, 50, 70, 30); // 이제는 됨 ?
		b3.setBackground(Color.pink); // 색 설정 background
		b3.setForeground(Color.green); // 색 설정 background
		f.add(b3);

		ImageIcon icon4 = new ImageIcon("img/call.png");
		JButton b4 = new JButton(icon4);
		b4.setBounds(20, 100, 100, 100);
		f.add(b4);

		JButton b5 = new JButton("버튼5", new ImageIcon("img/no.png"));
//		b5.setBounds(140, 100, 500, 500);
		b5.setBounds(140, 100, 100, 100);
		// Vertical : 수직 // 현재 이미지의 크기보다 버튼의 크기가 커서 안보임
//		b5.setVerticalTextPosition(SwingConstants.BOTTOM);
		// Horizontal : 수평 // 현재 이미지의 크기보다 버튼의 크기가 커서 안보임
//		b5.setHorizontalTextPosition(SwingConstants.CENTER);
		b5.setVerticalTextPosition(0); // 홀수 // top // bottom
		b5.setHorizontalTextPosition(0); // 짝수 // left // center // right
		f.add(b5);

		ButtonGroup bg1 = new ButtonGroup(); // 한팀으로 설정
		JToggleButton tb1 = new JToggleButton("토글1");
		tb1.setBounds(20, 210, 70, 30);
		f.add(tb1);
		JToggleButton tb2 = new JToggleButton("토글2", true); // true : default 눌려 있음
		tb2.setBounds(90, 210, 70, 30);
		f.add(tb2);
		JToggleButton tb3 = new JToggleButton("토글3");
		tb3.setBounds(160, 210, 70, 30);
		f.add(tb3);
		bg1.add(tb1);
		bg1.add(tb2);
		bg1.add(tb3);

		////////////////// set//////////////////////////
		Vector<String> email = new Vector<String>();
		email.add("네이버");
		email.add("다음");
		email.add("구글");
		email.add("야후");

		JComboBox<String> box = new JComboBox<String>(email);
		box.setBounds(20, 250, 120, 30);
		box.setSelectedItem("구글"); // 기본값
		f.add(box);
		////////////////// set//////////////////////////

		ButtonGroup bg2 = new ButtonGroup(); // 한팀으로 설정
		JRadioButton rb1 = new JRadioButton("남자");
		rb1.setBounds(20, 290, 100, 30);
		f.add(rb1);

		JRadioButton rb2 = new JRadioButton("여자", true); // 기본값 설정
		rb2.setBounds(120, 290, 100, 30);
		f.add(rb2);

		JRadioButton rb3 = new JRadioButton("it인");
		rb3.setBounds(220, 290, 100, 30);
		f.add(rb3);

		bg2.add(rb1);
		bg2.add(rb2);
		bg2.add(rb3);

		// 체크박스는 다중선택을 위해 사용 하므로, 구룹을 거의 사용하지 않습니다.
		JCheckBox cb1 = new JCheckBox("독서");
		cb1.setBounds(20, 330, 70, 30);
		f.add(cb1);

		JCheckBox cb2 = new JCheckBox("게임");
		cb2.setBounds(100, 330, 70, 30);
		f.add(cb2);

		JCheckBox cb3 = new JCheckBox("잠자기");
		cb3.setBounds(180, 330, 70, 30);
		f.add(cb3);

		String[] pp = { "의사", "판사", "안사", "못사" };
		JList<String> list = new JList<String>(pp);
		list.setBounds(20, 370, 100, 100);
		list.setSelectedValue("안사", true); // default 설정
		f.add(list);

//////////////////////////////////////////////////////////////////////
		JLabel lb1 = new JLabel("id");
		lb1.setBounds(350, 20, 70, 30);
		f.add(lb1);

		JTextField tf = new JTextField("aaa");
		tf.setBounds(420, 20, 100, 30);
		f.add(tf);
//////////////////////////////////////////////////////////////////////

		JLabel lb2 = new JLabel("비밀번호");
		lb2.setBounds(350, 60, 70, 30);
		f.add(lb2);

		JPasswordField pf = new JPasswordField();
		pf.setBounds(420, 60, 100, 30);
		f.add(pf);
//////////////////////////////////////////////////////////////////////

		Object[] index = { "이름", "국어", "영어", "수학" };
		Object[][] data = { { "박성언", 77, 78, 79 }, { "박시현", 67, 68, 69 }, { "안정민", 97, 89, 99 }, { "이주혁", 57, 58, 59 },
				{ "이호인", 87, 88, 89 } };

//		JScrollPane // 혼자서는 의미가 없음. table과 같이 사용.
		JTable table = new JTable(data, index);
		JScrollPane sp1 = new JScrollPane(table);
		sp1.setBounds(350, 100, 200, 103);
		f.add(sp1);

//////////////////////////////////////////////////////////////////////

		// TextArea
		JTextArea area = new JTextArea();
		JScrollPane sp2 = new JScrollPane(area);
		sp2.setBounds(560, 100, 200, 103);
		f.add(sp2);

		// 진행바
		JProgressBar bar = new JProgressBar();
		bar.setBounds(350, 250, 300, 30);
		bar.setValue(50);
		f.add(bar);

//		JSlider slider = new JSlider();
//		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // default
		JSlider slider = new JSlider(JSlider.WIDTH, 0, 100, 50);
		slider.setBounds(350, 290, 300, 100);
		// 큰 눈금
		slider.setMajorTickSpacing(50); // 50단위
		// 작은 눈금
		slider.setMinorTickSpacing(10); // 10단위
		// 숫자 표시 - 눈금이 잇어야 표시 가능
		slider.setPaintLabels(true);
		// 선 표시
		slider.setPaintTicks(true);
		// 바 표시
		slider.setPaintTrack(true);

		f.add(slider);
		System.out.println(slider.getPaintLabels());

		// setVisible 게 없으면 보이지가 않음.
		// X 를 눌러 창을 닫아도 꺼지지 않음....
		f.setVisible(true); // 보여 지는 역할을 합니다.
		// ✔ 항상 최 하위에 존재해야 메인이 돌면서 계속 바꾸어줌

		// 창닫기 버튼을 눌렀을때... 자바의 콘솔이 꺼지는 역할을 합니다.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation 이 없으면 콘솔은 무한루프 처럼 꺼지지 않습니다.
		// ✔ 항상 최 하위에 존재해야 메인이 돌면서 계속 바꾸어줌

	}
}