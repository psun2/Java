package exam_Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Quiz {

	JLabel qq; // 질문
	ArrayList<JRadioButton> bogi; // 객관식의 보기
	String type, strDab; // 타입과 // 주관식 정답
	int intDab; // 객관식의 정답
	JTextField strAns; // 주관식 input
	Subject subject; // 과목..... 그 과목에 맞는 퀴즈 이기 때문에...

	public Quiz(Subject subject, String qq, String strDab) {
		super();
		this.subject = subject;
		this.qq = new JLabel(qq);
		this.strDab = strDab;
		strAns = new JTextField();
		type = "주관식";
	}

	public Quiz(Subject subject, String qq, int intDab, String... bogi) {
		super();
		this.subject = subject;
		this.qq = new JLabel(qq);
		this.intDab = intDab;
		this.bogi = new ArrayList<JRadioButton>();
		initBogi(bogi);
		type = "객관식";
	}

	void initBogi(String[] bogi) { // 보기를 RadioButton 에 셋팅 합니다.

		ButtonGroup bg = new ButtonGroup();
		for (String string : bogi) {
			JRadioButton btn = new JRadioButton(string);
			this.bogi.add(btn);
			bg.add(btn);
		}

	}

	void paint() { // 그리는 역할 (subject panel 에 질문과 답을 그립니다.) => 그래서 subject를 알아야 하는구나...

		subject.add(qq);

		if (type.equals("주관식"))
			subject.add(strAns);
		else {
			for (JRadioButton jr : bogi) {
				subject.add(jr);
			}

		}

	}

	int result() { // 맞춘갯수 반환

		int res = 0;
		if (type.equals("주관식") && strAns.equals(strDab))
			res = 1;
		else if (type.equals("객관식") && bogi.get(intDab).isSelected()) {
			res = 1;
		}
		return res;
	}

	void end() { // 끝낫을시 답을 수정할 수 없게 비활성화
		// 각각의 필드를 따로 따로 떼어 놨기 때문에 ... quiz에서 비활성화를 시켜줍니다.
		if (type.equals("주관식"))
			strAns.setEditable(false);
		else {
			for (JRadioButton jr : bogi) {
				jr.setEnabled(false);
			}
		}
	}

}

class Subject extends JPanel { // 메인 프레임에 넣을 Panel을 상속 받아 사용

	Stud st; // 과목은 학생 정보를 가지고 있어야 함...
	ArrayList<Quiz> quizs; // 해당 과목에 맞는 퀴즈들을 가지고 있습니다....

	JLabel timerLb;
	boolean chk = false, go = false;
	JButton btn;
	int jumsu;

	public Subject(Stud st, String name) { // 생성 될때 학생의 정보를 가져옵니다...
		setName(name);
		this.st = st;
		quizs = new ArrayList<Quiz>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 패널에 추가된 요소들이 ... Y축으로 정렬될수 있게 레이아웃을 잡아줍니다...

	}

	void start() { // 시험시작

		timerLb = new JLabel("남은시간");
		timerLb.setHorizontalAlignment(JLabel.RIGHT);
		add(timerLb); // 상단엔 타이머

		for (Quiz quiz : quizs) { // 과목에 맞는 문제들을 돌면서 패널에 그려 줍니다.
			quiz.paint(); // 각 문제를 그리는 함수 실행
		}

		btn = new JButton("제출"); // 마지막에 제출 버튼 추가
		add(btn);

		btn.addActionListener(new ActionListener() { // 제출 버튼의 이벤트

			@Override
			public void actionPerformed(ActionEvent e) {
				submitAndCalc();
			}
		});

		new Timer().start();
	}

	void submitAndCalc() { // 제출 버튼을 누르면 정답이 맞는지 확인하고 몇개가 맞았는지를 계산합니다.

		chk = true; // 제출버튼을 눌럿다면 타이머 정지
		btn.setEnabled(false); // 해당과목의 버튼을 비활성화

		int cnt = 0;

		for (Quiz quiz : quizs) { // 정답의 갯수를 셉니다.
			quiz.end(); // 각 퀴즈들의 입력을 막습니다.
			cnt += quiz.result();
		}

		jumsu = 100 * cnt / quizs.size();

		st.finalGo(); // 제출버튼을 눌러 세과목이 끝낫나 확인 하고 세과목이 끝나면 다음 학생을 시작 합니다.
	}

	class Timer extends Thread { // 타이머 Thread
		@Override
		public void run() {
			int i = 5;
			while (i > 0) {
				try {
					sleep(1000);
					if (chk)
						break;

					if (go) // 여기에 해당될때만 시간이 간다....
						i--;

					timerLb.setText("남은시간 : " + i + "   ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			submitAndCalc(); // 타이머가 정지 되어도 시험은 끝 점수 계산
		}
	}

}

class Stud extends JFrame implements Comparable<Stud> { // 사실상 student 는 각종 문제를 추가 시킵니다... 이게 맞나 싶습니다..?

	ExamMain exam;
	ArrayList<Subject> subjects;
	int num, avg, rank;
	JTabbedPane tab = new JTabbedPane();

	public Stud(int num, ExamMain exam, String name) {
		this.exam = exam;
		this.num = num;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 500, 600);
		setTitle(name);
		setName(name);

		subjects = new ArrayList<Subject>();

		subjects.add(new Subject(this, "국어"));
		subjects.add(new Subject(this, "영어"));
		subjects.add(new Subject(this, "수학"));

		subjects.get(0).quizs.add(new Quiz(subjects.get(0), "1.가 다음은?", "나"));
		subjects.get(0).quizs.add(new Quiz(subjects.get(0), "2.나 다음은?", "다"));
		subjects.get(0).quizs
				.add(new Quiz(subjects.get(0), "1.달면 삼키고 쓰면 뱉는다는 뜻을 가진 사자성어는 ?", 0, "감탄고토", "거두절미", "갑남을녀", "각골난망"));
		subjects.get(0).quizs.add(
				new Quiz(subjects.get(0), "4.물건을 보면 갖고 싶은 욕심이 생김을 이르는 사자성어는 ?", 2, "경거망동", "건곤일척", "견물생심", "견강부회"));

		subjects.get(1).quizs.add(new Quiz(subjects.get(1), "1.a 다음은?", "b"));
		subjects.get(1).quizs.add(new Quiz(subjects.get(1), "2.배는 영어로 ?", 0, "pear", "peak", "pean", "peal"));
		subjects.get(1).quizs.add(new Quiz(subjects.get(1), "3.b 다음은?", "c"));
		subjects.get(1).quizs
				.add(new Quiz(subjects.get(1), "4.멜론은 영어로 ?", 3, "watermelon", "melong,", "mello", "melon"));
		subjects.get(1).quizs.add(new Quiz(subjects.get(1), "5.c 다음은?", "d"));

		subjects.get(2).quizs.add(new Quiz(subjects.get(2), "1. 3+3= ?", 1, "4", "6", "8", "10"));
		subjects.get(2).quizs.add(new Quiz(subjects.get(2), "2. 1+1=?", "2"));
		subjects.get(2).quizs.add(new Quiz(subjects.get(2), "3. 2+1=?", "3"));
		subjects.get(2).quizs.add(new Quiz(subjects.get(2), "4. 5+5= ?", 0, "10", "20", "30", "40"));

		// 각 과목별 문제를 추가 후

		// 각 과목별 start
		for (Subject sub : subjects) {
			tab.addTab(sub.getName(), sub);
			sub.start();
		}

		add(tab);

		tab.addChangeListener(new ChangeListener() { // tab의 변화를 알 수 있는 이벤트

			@Override
			public void stateChanged(ChangeEvent e) {

				for (Subject subject : subjects) {
					subject.go = false; // 탭이 바뀌면 false를 해 해당 탭의 타이머를 정지 합니다.
				}

				System.out.println(tab.getSelectedIndex()); // 탭을 클릭할때마다 해당 탭의 idnex번호를 가져옵니다.
				subjects.get(tab.getSelectedIndex()).go = true; // 클릭된 탭의 조건을 true 바꾸어 시간이 가도록 합니다.

			}
		});

	}

	void start() {
		setVisible(true); // 스타트가 되면 해당 패널이 보입니다.
		subjects.get(0).go = true; // 0번째 과목이 실행되고 시간이 시작 됩니다.
	}

	Vector result() { // 한 학생의 점수 계산

		Vector res = new Vector();

		res.add(getName());
		int tot = 0;
		for (Subject sub : subjects) {
			res.add(sub.jumsu);
			tot += sub.jumsu;
		}
		res.add(tot);
		res.add(avg = tot / subjects.size());
		System.out.println(avg);
		res.add(grade(tot));
		rank();
		res.add(rank);
		return res;
	}

	String grade(int tot) {

		int index = (int) Math.floor((tot / subjects.size()) / 10);

		String grade = "가,가,가,가,가,양,미,우,수,수";

		grade = grade.split(",")[index];

		return grade;
	}

	void rank() {

		for (Stud me : exam.studs) {

			me.rank = 1;

			for (Stud you : exam.studs) {

				if (me.avg < you.avg)
					me.rank++;

			}

		}

	}

	void finalGo() {

		String str = "시험완료:";

		for (Subject sub : subjects) {
			// 제출버튼을 눌렀을때 한 과목이라도 false 즉 아직 진행되지 않은 상태라면 return 이 되어 코드가 죽어 다음 과목으로 넘어갈때까지
			// 정지가 됩니다.
			if (!sub.chk)
				return;
			str += sub.getName() + "(" + sub.jumsu + ")";
		}

		System.out.println(str);
		result();
		// setVisible(false); // 화면만 일시적으로 숨김

		// 함수가 return 되지 않고 즉, 3과목을 다 끝냇을때.... 해당 frane를 종료합니다.
		dispose(); // window 종료

		if (exam.studs.size() > num + 1) // 시험이 종료되면 다음 사람을 스타트 해줌....
			exam.studs.get(num + 1).start();
		else { // 모든 사람의 시험이 끝났다면 결과를 ....
			rank(); // 등수를 계산하고....
			exam.end(); // 모든학생의 시험이 끝나면 결과를 출력시키는 다른 프레임을 띄워 줍니다.
		}
	}

	@Override
	public int compareTo(Stud you) { // 정렬

		int result = rank - you.rank;

		if (result == 0)
			getName().compareTo(you.getName());

		return result;
	}
}

public class ExamMain extends JFrame { // 결과 만을 가짐

	ArrayList<Stud> studs;

	public ExamMain() { // 내 코드로 보자면 Exam 은 School 이 되겠습니다.

		studs = new ArrayList<Stud>();
		studs.add(new Stud(0, this, "지민이너"));
		studs.add(new Stud(1, this, "홍성혁이"));
		studs.add(new Stud(2, this, "핸준아"));

		studs.get(0).start(); // ExamMain 이 생성되면 여기서 0번 학생이 스타트가 됩니다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void end() {

		setBounds(100, 50, 500, 600);
		setName("결과");

		Vector<Vector> rowData = new Vector<Vector>();

		TreeSet<Stud> cloneStuds = new TreeSet<Stud>(studs);
		for (Stud st : cloneStuds) {
			rowData.add(st.result());
		}

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("이름");
		columnNames.add("국어");
		columnNames.add("영어");
		columnNames.add("수학");
		columnNames.add("총점");
		columnNames.add("평균");
		columnNames.add("등급");
		columnNames.add("등수");

		JTable table = new JTable(rowData, columnNames);

		JScrollPane js = new JScrollPane(table);

		add(new JLabel("결과"), "North");
		add(js, "Center");

		setVisible(true);
	}

	public static void main(String[] args) {

		new ExamMain();

	}

}
