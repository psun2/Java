package exam2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

class Frame extends JFrame {

	Shcool shcool;
	JPanel testMain__testExam;
	JLabel timeLb = new JLabel("asdasdasdasd");
	boolean chk;

	public Frame(String title, Shcool shcool) {

		super(title);

		this.shcool = shcool;
		this.chk = false;

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

		JPanel main = new JPanel();
		main.setLayout(new FlowLayout(FlowLayout.CENTER, 0, (getSize().height / 2)));
		add(main);

		JButton start = new JButton("시험시작");
		main.add(start);

		JPanel main__testMain = new JPanel(); // 모든 Panel 들을 담고 있음
		main__testMain.setLayout(new BorderLayout());

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.hide();
				add(main__testMain);
			}
		});

		JPanel testMain__testBtns = new JPanel(); // 각 과목으로 넘어갈수 있게 버튼을 보여줌

		main__testMain.add(testMain__testBtns, "North");

		for (String btnTitle : "국어,영어,수학".split(",")) { // 패널에 버튼 추가

			testMain__testBtns.add(new JButton(btnTitle)); // 패널에 추가

		}

		for (Component subjectBtn : testMain__testBtns.getComponents()) { // 국,영,수 버튼에 이벤트 추가
			((JButton) subjectBtn).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(e);

					String key = e.getActionCommand();
					shcool.subjects.get(key).chk = true;
//					while (true) {

					if (shcool.subjects.get(key).chk) {
						System.out.println(key);
						shcool.subjects.get(key).chk = false;
					}

//					}

//					Frame.this.chk = true;
				}
			});
		}

		testMain__testExam = new JPanel(); // 문제와 timer를 보여줌
		testMain__testExam.setLayout(new GridLayout(12, 1));
		main__testMain.add(testMain__testExam, "Center");

		// 여기 손 봐야함
//		for (Shcool.Subject.Question exam : shcool.subject.questions) {
//
//			JLabel questionLb = new JLabel(exam.question);
//			testMain__testExam.add(questionLb);
//
//			if (exam.view != null) {
//
//				ButtonGroup bg = new ButtonGroup();
//
//				ArrayList<JRadioButton> viewBtns = new ArrayList<JRadioButton>();
//
//				for (String view : exam.view.split(",")) {
//
//					JRadioButton viewRb = new JRadioButton(view);
//					testMain__testExam.add(viewRb);
//					viewBtns.add(viewRb);
//					bg.add(viewRb);
//				}
//
//				int index = Integer.parseInt(exam.answer) - 1;
//
//				if (viewBtns.get(index).isSelected())
//					exam.jum = 1;
//
//				System.out.println(getState());
//
//			}
//
//			JTextField answerTf = new JTextField();
//			testMain__testExam.add(answerTf);
//
//			if (answerTf.getText().equals(exam.answer))
//				exam.jum = 1;
//
//		}

		JPanel testMain__testResult = new JPanel(); // 제출버튼과 결과를 보여줌
		testMain__testResult.setLayout(new GridLayout(2, 1));
		main__testMain.add(testMain__testResult, "South");

		JButton testResult__submit = new JButton("제출");
		testMain__testResult.add(testResult__submit);

		JLabel testResult__result = new JLabel("결과");
		testMain__testResult.add(testResult__result);

		setVisible(true);

	}

}

class Shcool {

	LinkedHashMap<String, Subject> subjects;
	ArrayList<Student> students;
	Frame frame;

	public Shcool() {
		this.subjects = new LinkedHashMap<String, Shcool.Subject>();
		this.students = new ArrayList<Shcool.Student>();
	}

	class Subject {

		String title;
		Timer timer;
		boolean chk;
		ArrayList<Shcool.Subject.Question> questions;

		public Subject(String title) {
			this.title = title;
			Shcool.this.subjects.put(title, this);
			this.chk = false;
			this.questions = new ArrayList<Shcool.Subject.Question>();
			this.timer = new Timer();
		}

		void add(String question, String view, String answer) {

			if (view == null)
				questions.add(new Question(question, answer));
			else
				questions.add(new Question(question, view, answer));
		}

		class Question {

			String question, view, answer;
			int jum;

			public Question(String question, String answer) {
				this.question = question;
				this.answer = answer;
				Shcool.Subject.this.questions.add(this);
			}

			public Question(String question, String view, String answer) {
				this.question = question;
				this.view = view;
				this.answer = answer;
				Shcool.Subject.this.questions.add(this);
			}

		}

		class Timer extends Thread {

			boolean chk;

			public Timer() {
				this.chk = false;
			}

			@Override
			public void run() {

				while (true) {

					if (chk) {

						for (int i = 20; i <= 0; i--) {
							Shcool.this.frame.timeLb.setText("d");
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

				}

			}

		}

	}

	class Student extends Thread {

		String name;
		boolean chk;

		public Student(String name) {
			super(name);
			this.name = name;
			this.chk = false;
			Shcool.this.students.add(this);
		}

		@Override
		public void run() {

			int index = 0;
			while (true) {

//					여기서 조건에 맞는 학생만 run ...

				if (chk) {
					Shcool.this.frame = new Frame(getName(), Shcool.this);
					this.chk = false;
				}

				// 시험이 다끝날때, 버튼으로 창을 종료 시킨뒤, action 이 실행됬을때
				if (!Shcool.this.students.get(index).chk && frame.chk) {
					// 다음학생의 chk 을 true 로 ....
					index++;
					Shcool.this.students.get(index).chk = true;
				}

			}

		}

	}
}

public class Main {

	public static void main(String[] args) {

		// .hide();
		// .show();

		Shcool shcool = new Shcool();

		Shcool.Subject korea = shcool.new Subject("국어");
		Shcool.Subject english = shcool.new Subject("영어");
		Shcool.Subject math = shcool.new Subject("수학");

		korea.add("가 다음은 ?", null, "나");
		korea.add("나 다음은 ?", "다,라,마,바", "1");
		korea.add("다 다음은 ?", null, "라");

		english.add("banana ?", null, "바나나");
		english.add("orange ?", "귤,오렌지,배,사과", "2");
		english.add("다 다음은 ?", null, "라");

		math.add("1 + 1 ?", null, "2");
		math.add("2 + 2 ?", "1,2,3,4", "4");
		math.add("3 + 3 ?", null, "6");

		// 문제 끝

		// 학생 생성
		Shcool.Student[] students = { shcool.new Student("한가인"), shcool.new Student("두가인"), shcool.new Student("삼가인"),
				shcool.new Student("사가인"), shcool.new Student("오가인") };

		students[0].chk = true;
		for (Shcool.Student student : students) {
			student.start();
		}
		// JFrame 가 다 가져가야함.....

	}

}
