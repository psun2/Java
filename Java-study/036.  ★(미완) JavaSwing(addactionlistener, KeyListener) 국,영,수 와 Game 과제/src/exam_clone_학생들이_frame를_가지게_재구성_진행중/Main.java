package exam_clone_학생들이_frame를_가지게_재구성_진행중;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

enum Title {

	KOR("국어"), ENG("영어"), MATH("수학");

	String title;

	private Title(String title) {
		this.title = title;
	}

}

enum Result {

	NAME("이름"), KOR("국어"), ENG("영어"), MATH("수학"), TOTAL("총점"), AVERAGE("평균"), RANK("등수");

	String title;

	private Result(String title) {
		this.title = title;

	}

}

class Paint extends JFrame {

	ArrayList<JMenuItem> menuItems;
	LinkedHashMap<String, JPanel> showJPanel;
	School school;
	ArrayList<JButton> submitBtns;
	ArrayList<JLabel> timerLbs;
	JButton stratBtn;
	JTabbedPane tab;
	LinkedHashMap<String, ArrayList<Component>> multipleResult;
	LinkedHashMap<String, ArrayList<Component>> result;

	public Paint(String title, School school) {

		super(title);
		this.showJPanel = new LinkedHashMap<String, JPanel>();
		this.school = school;
		this.submitBtns = new ArrayList<JButton>();
		this.result = new LinkedHashMap<String, ArrayList<Component>>();
		this.multipleResult = new LinkedHashMap<String, ArrayList<Component>>();

		int width, height, x, y;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		width = 500;

		height = 700;
		x = (screen.width / 2) - (width / 2);
		y = (screen.height / 2) - (height / 2);
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();
	}

	void init() {

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		JMenu menu = new JMenu("메뉴(M)");
		menu.setMnemonic('M');
		menuBar.add(menu);

		this.menuItems = new ArrayList<JMenuItem>();

		String items = "시험보기(P),종료(X)";
		for (String itemTitle : items.split(",")) {
			JMenuItem item = new JMenuItem(itemTitle);
			menu.add(item);
			menuItems.add(item);
		}

		JPanel startP = startScreen();
		initPanel("start", startP);

		JPanel previewP = previewScreen();
		initPanel("preview", previewP);

		JPanel testP = testScreen();
		initPanel("test", testP);

		for (JMenuItem jMenuItem : menuItems) {

			jMenuItem.setMnemonic(shortcutKey(jMenuItem.getText())); // 단축키 설정

			jMenuItem.addActionListener(new ActionListener() { // 메뉴아이템의 이벤트 설정

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					char eventKey = (char) jMenuItem.getMnemonic();

					for (Entry<String, JPanel> panel : showJPanel.entrySet()) {

						panel.getValue().setVisible(false);

						if (eventKey == 'X')
							dispose();
						else if (eventKey != getCharKey(panel.getKey())) {
							panel.getValue().setVisible(false);
						} else {
							panel.getValue().setVisible(true);
						}
					}

				}
			});
		}

		for (JButton btn : this.submitBtns) {

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					btn.setEnabled(false);

				}
			});
		}

		CardLayout card = new CardLayout();
		setLayout(card);
		setVisible(true);

	}

	void initPanel(String key, JPanel panel) {

		if (!key.equals("start"))
			panel.setVisible(false);

		add(panel);
		showJPanel.put(key, panel);

	}

	char shortcutKey(String title) {

		char key = 0;

		char[] chars = title.toCharArray();

		key = chars[chars.length - 2];

		return key;

	}

	char getCharKey(String title) {

		char key = ' ';

		for (String str : showJPanel.keySet()) {
			if (title.equals(str))
				key = str.toUpperCase().toCharArray()[0];

		}

		return key;

	}

	JPanel startScreen() {

		JPanel p = new JPanel();

		JLabel msg = new JLabel("환영합니다.");
		msg.setHorizontalAlignment(JLabel.CENTER);

		p.add(msg);
		p.setLayout(new GridLayout(p.getComponentCount(), 1));

		return p;

	}

	JPanel previewScreen() {

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		JLabel msg = new JLabel("버튼을 누를시 시험이 시작됩니다.");
		msg.setHorizontalAlignment(JLabel.CENTER);
		p.add(msg, "Center");
		p.add(msg);

		stratBtn = new JButton("시험시작");
		stratBtn.setPreferredSize(new Dimension(0, 150));
		p.add(stratBtn, "South");

		return p;

	}

	JPanel testScreen() {

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));

		tab = new JTabbedPane();
		p.add(tab);

		timerLbs = new ArrayList<JLabel>();

		for (Entry<String, School.Subject> subject : this.school.subjects.entrySet()) {
			tab.addTab(subject.getKey(), examScreen(subject.getValue(), subject.getKey()));
		}

		return p;

	}

	JPanel examScreen(School.Subject subject, String title) {

		ArrayList<Component> answer = new ArrayList<Component>();
		ArrayList<Component> multipleAnswer = new ArrayList<Component>();

		JPanel p = new JPanel();

		p.setName(title);

		JLabel timerLb = new JLabel("시험 시작전");
		p.add(timerLb);
		this.timerLbs.add(timerLb);
		timerLb.setHorizontalAlignment(JLabel.RIGHT);
		timerLb.setVerticalAlignment(JLabel.NORTH);

		for (School.Subject.Question question : subject.exams) {
			if (question.view == null) {
				p.add(new JLabel(question.question));
				JTextField answerTf = new JTextField();
				p.add(answerTf);
				answer.add(answerTf);
			} else {
				p.add(new JLabel(question.question));
				ButtonGroup bg = new ButtonGroup();
				for (String view : question.view.split(",")) {
					JRadioButton rb = new JRadioButton(view);
					bg.add(rb);
					p.add(rb);
					multipleAnswer.add(rb);
				}
			}
		}

		this.result.put(title, answer);
		this.multipleResult.put(title, multipleAnswer);

		JButton submitBtn = new JButton("제 출");
		p.add(submitBtn);
		this.submitBtns.add(submitBtn);

		p.setLayout(new GridLayout(p.getComponentCount(), 1));

		return p;

	}

	JPanel gradeScreen() {

		Object[] index = new Object[Result.values().length];

		for (int i = 0; i < Result.values().length; i++) {
			index[i] = Result.values()[i].title;
		}

		Object[][] data = addData(index.length);

		JPanel p = new JPanel();

		JTable table = new JTable(data, index);
		JScrollPane sp = new JScrollPane(table);

		p.add(sp);
		p.setLayout(new GridLayout(p.getComponentCount(), 1));

		return p;

	}

	Object[][] addData(int length) {

		Object[][] data = new Object[school.students.size()][length];

		TreeSet<School.Student> students = new TreeSet<School.Student>(school.students);

		Object[] arr = students.toArray();

		for (int i = 0; i < arr.length; i++) {

			Object[] temp = new Object[length];

			for (int j = 0; j < temp.length; j++) {

				temp[j] = ((School.Student) arr[i]).arr.get(j);

			}

			data[i] = temp;
		}

		System.out.println(Arrays.deepToString(data));

		return data;

	}

	void result() {

		add(this.gradeScreen());

		for (JPanel panel : this.showJPanel.values()) {
			panel.setVisible(false);
		}

	}

}

class School {

	Paint frame;
	LinkedHashMap<String, School.Subject> subjects;
	ArrayList<Student> students;
	int index, subjectIndex, count;

	public School() {
		this.subjects = new LinkedHashMap<String, School.Subject>();
		this.students = new ArrayList<Student>();
		this.count = 0;
	}

	synchronized void rank(School.Student me) {

		count++;

		for (School.Student you : students) {
			if (me.avg < you.avg)
				me.rank++;

		}

		me.arrInit();

		if (count == students.size())
			frame.result();

	}

	class Subject {

		String title;
		ArrayList<School.Subject.Question> exams;
		Timer timer;

		public Subject(String title) {
			super();
			this.title = title;
			this.exams = new ArrayList<School.Subject.Question>();
			this.timer = new Timer(5, title);
			School.this.subjectIndex = 0;
			subjects.put(title, this);

		}

		void addExam(String question, String view, String anwser) {

			if (view == null)
				exams.add(new Question(question, anwser));
			else
				exams.add(new Question(question, view, anwser));

		}

		@Override
		protected Object clone() throws CloneNotSupportedException {

			School.Subject clone = School.this.new Subject(this.title);

			clone.exams = this.exams;

			return clone;
		}

		class Question {

			String question, view, anwser;

			public Question(String question, String anwser) {
				super();
				this.question = question;
				this.anwser = anwser;
			}

			public Question(String question, String view, String anwser) {
				super();
				this.question = question;
				this.view = view;
				this.anwser = anwser;
			}

		}

		class Timer extends Thread {

			int second;
			boolean chk;

			public Timer(int second, String name) {
				super(name);
				this.second = second;
				this.chk = false;
			}

			@Override
			public void run() {

				int index = -1;

				String currentName = null;

				while (this.second >= 0) {

					currentName = School.this.frame.tab.getSelectedComponent().getName();
					// 현재 탭의 이름을 받아옴

					changeTab(currentName);

					if (chk) {

						index = chkIndex();

						if (!submitChk(index))
							break;

						School.this.frame.timerLbs.get(index).setText("남은시간 : " + second);

						try {
							sleep(1000);
							second--;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

				School.this.frame.submitBtns.get(index).setEnabled(false); // 버튼 비활성화

				School.this.students.get(School.this.index).jumChk(currentName); // 점수

				School.this.subjectIndex++;

				if (School.this.subjectIndex == School.this.subjects.size()) {
					School.this.students.get(School.this.index).calc();
					School.this.index++;
				}

			}

			int chkIndex() {

				int i;

				School.Subject subject = null;

				ArrayList<School.Subject> temp = new ArrayList<School.Subject>();

				for (School.Subject sub : School.this.subjects.values()) {

					if (sub.timer.chk)
						subject = sub;

					temp.add(sub);

				}

				i = temp.indexOf(subject);

				return i;

			}

			void changeTab(String currentName) {

				for (Entry<String, Subject> subject : School.this.subjects.entrySet()) {
					if (subject.getValue().title.equals(currentName))
						subject.getValue().timer.chk = true;
					else
						subject.getValue().timer.chk = false;
				}

			}

			boolean submitChk(int index) {

				return School.this.frame.submitBtns.get(index).isEnabled(); // 버튼의 현재 상태를 보내줍니다.

			}

		}

	}

	class Student extends Thread implements Comparable<Student> {

		String name;
		ArrayList<Integer> jum;
		int tot, avg, rank;
		boolean chk;
		ArrayList<Object> arr;

		public Student(String name) {
			this.name = name;
			this.jum = new ArrayList<Integer>();
			School.this.students.add(this);
			chk = true;
			rank = 1;
		}

		@Override
		public void run() {

			School.this.index = 0;

			while (School.this.index < School.this.students.size()) {

				if (myIndex() == School.this.index && this.chk) {

					test();

					this.chk = false;

				}

				try {
					sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			School.this.rank(this);

		}

		void test() {

			School.this.frame = new Paint(name + " 학사정보", School.this);

			frame.stratBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					frame.showJPanel.get("preview").setVisible(false);
					frame.showJPanel.get("test").setVisible(true);

					timerStart(); // 시험이 시작되면 타이머가 흐릅니다...

				}
			});

		}

		void timerStart() {

			LinkedHashMap<String, School.Subject> temp = cloneMap();
			temp.get("국어").timer.chk = true;
			for (School.Subject subject : temp.values()) {
				subject.timer.start();
			}
		}

		LinkedHashMap<String, School.Subject> cloneMap() {

			LinkedHashMap<String, School.Subject> temp = new LinkedHashMap<String, School.Subject>();

			for (Entry<String, Subject> tt : School.this.subjects.entrySet()) {
				try {
					temp.put(tt.getKey(), (Subject) tt.getValue().clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return temp;

		}

		void jumChk(String title) {

			int cnt = 0;

			ArrayList<String> exams = new ArrayList<String>();
			ArrayList<String> multipleExams = new ArrayList<String>();

			for (School.Subject.Question chk : School.this.subjects.get(title).exams) {

				// exams 문제와 답을 가지고 있음....

				int index = School.this.subjects.get(title).exams.indexOf(chk);

				if (chk.view == null) { // 보기가 없을때

					exams.add(chk.anwser);

				} else {
					multipleExams.add(chk.anwser);
				}

			}

			for (int i = 0; i < exams.size(); i++) { // 주관식

				ArrayList input = frame.result.get(title);

				if (exams.get(i).equals(((JTextField) input.get(i)).getText()))
					cnt++;

			}

			for (int i = 0; i < multipleExams.size(); i++) { // 객관식

				int answer = Integer.parseInt(multipleExams.get(i)) - 1;

				ArrayList input = frame.multipleResult.get(title);

				if (((JRadioButton) input.get(answer)).isSelected())
					cnt++;
			}

			int index = 0;

			for (Title tt : Title.values()) {
				if (title.equals(tt)) {
					index = tt.ordinal();
					break;
				}
			}

			cnt = (cnt * 100) / School.this.subjects.get(title).exams.size();

			this.jum.add(index, cnt);

		}

		int myIndex() {

			return School.this.students.indexOf(this);

		}

		void calc() {

			for (Integer num : this.jum) {
				tot += num;
			}

			this.avg = tot / jum.size();

		}

		void arrInit() {

			this.arr = new ArrayList<Object>();
			arr.add(name);

			for (Integer i : jum) {
				arr.add(i);
			}

			arr.add(tot);

			arr.add(avg);

			arr.add(rank);

		}

		@Override
		public int compareTo(Student you) {

			int result = rank - you.rank;

			if (result == 0)
				name.compareTo(you.name);

			return result;
		}

	}
}

public class Main {

	public static void main(String[] args) {

		School school = new School();

		School.Subject korea = school.new Subject("국어");
		School.Subject english = school.new Subject("영어");
		School.Subject math = school.new Subject("수학");

		korea.addExam("1. 가 다음은 ?", null, "나");
		korea.addExam("2. 나 다음은 ?", "다,라,마,바", "1");
		korea.addExam("3. 다 다음은 ?", null, "라");

		english.addExam("1. banana ?", null, "바나나");
		english.addExam("2. orange ?", "귤,오렌지,배,사과", "2");
		english.addExam("3. i ?", null, "아이");

		math.addExam("1. 1 + 1 ?", null, "2");
		math.addExam("2. 2 + 2 ?", "1,2,3,4", "4");
		math.addExam("3. 3 + 3 ?", null, "6");

		School.Student[] students = { school.new Student("한가인"), school.new Student("두가인"), school.new Student("삼가인"),
				school.new Student("사가인"), school.new Student("오가인") };

		for (School.Student student : students) {
			student.start();
		}

	}

}
