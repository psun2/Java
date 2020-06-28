package exam3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class paint extends JFrame {

	JTextField nameTf;
	LinkedHashMap<String, JPanel> SubPs;
	Shcool shcool;
	JPanel subject;
	CardLayout card;

	public paint(String title, Shcool shcool) {

		super(title + " 시험");

		this.shcool = shcool;

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

		JPanel loginP = new JPanel();

		loginP.setLayout(new FlowLayout(FlowLayout.CENTER, 0, getSize().height / 2));

		JPanel nameP = new JPanel();
		nameP.add(new Label("Name :"));
		nameTf = new JTextField(10);
		nameP.add(nameTf);
		loginP.add(nameP);

		JPanel btnP = new JPanel();
		JButton startBtn = new JButton("시험시작");
		btnP.add(startBtn);
		loginP.add(btnP);

		add(loginP);

		/////////////////////////////////////////////////////////////////////////////////////

		JPanel subjectP = new JPanel();
		subjectP.setLayout(new BorderLayout());

		JPanel subBtnP = new JPanel();
		subjectP.add(subBtnP, "North");

//		ArrayList<JButton> subBtns = new ArrayList<JButton>();

		for (String title : "국어,영어,수학".split(",")) {
//			subBtns.add();
			subBtnP.add(new JButton(title));
		}

		JPanel subQeusP = new JPanel();
		///////////// 추가 해야됨////////////
		for (Component subjectBtn : subBtnP.getComponents()) {
			((JButton) subjectBtn).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String cmd = e.getActionCommand();
					System.out.println(cmd);
					System.out.println(e);

					for (Entry<String, JPanel> ele : SubPs.entrySet()) {
						if (cmd.equals(ele.getKey())) {
//							System.out.println(ele.getValue());
//							System.out.println(ele.getValue().getComponents());
//							System.out.println(Arrays.toString(ele.getValue().getComponents()));
//							add(ele.getValue());
//							System.out.println(ele.getValue().getComponentCount());
							card.show(getContentPane(), ele.getKey());
							
						}

					}
					// 국영수 버튼을 눌렀을때.... 국어 문제 영어문제 수학문제 ....
//					paintSubject();
				}
			});
		}
		subjectP.add(subQeusP, "Center");

		JPanel subminP = new JPanel();
		JButton subBtn = new JButton("제출");
		subminP.add(subBtn);
		subjectP.add(subminP, "South");

		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SubPs = new LinkedHashMap<String, JPanel>();
				paintSub();
				loginP.hide();
				add(subjectP);
			}
		});

		JPanel resultP = new JPanel();

		subBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subjectP.hide();
				add(resultP);
			}
		});

		setVisible(true);

	}

	void paintSub() {

		subject = new JPanel();
		card = new CardLayout();
		subject.setLayout(card);

		for (Entry<String, Shcool.Subject> map : this.shcool.subjects.entrySet()) {

			Shcool.Subject sub = map.getValue();

			for (Shcool.Subject.Question qq : sub.questions) {
				JLabel qqLb = new JLabel(qq.question);
				subject.add(qqLb);

				if (qq.view != null) {
					ButtonGroup bg = new ButtonGroup();

					for (String vi : qq.view.split(",")) {

						JRadioButton rb = new JRadioButton(vi);
						bg.add(rb);
						subject.add(rb);
					}

				} else
					subject.add(new JTextField());

			}

			SubPs.put(map.getKey(), subject);
			add(subject, "국어");
		}
//		add(subject, map.getKey());
//		ghhgjghjghjhgj
		System.out.println(subject.getParent());

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

	void test() {

	}

	class Subject {

		String title;
//		Timer timer;
		boolean chk;
		ArrayList<Shcool.Subject.Question> questions;

		public Subject(String title) {
			this.title = title;
			Shcool.this.subjects.put(title, this);
			this.chk = false;
			this.questions = new ArrayList<Shcool.Subject.Question>();
//			this.timer = new Timer();
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

//		class Timer extends Thread {
//
//			public Timer() {
//				
//			}
//
//			@Override
//			public void run() {
//
//				while (true) {
//
//					if (chk) {
//
//						for (int i = 20; i <= 0; i--) {
//							Shcool.this.frame.timeLb.setText("d");
//							try {
//								sleep(1000);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//
//					}
//
//				}
//
//			}
//
//		}

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

				if (this.chk) {
					Shcool.this.frame = new paint(getName(), Shcool.this);
					this.chk = false;
				}

				// 시험이 다끝날때, 버튼으로 창을 종료 시킨뒤, action 이 실행됬을때
//				if (!Shcool.this.students.get(index).chk && frame.chk) {
//					// 다음학생의 chk 을 true 로 ....
//					index++;
//					Shcool.this.students.get(index).chk = true;
//				}

			}

		}

	}
}

public class Main {

	public static void main(String[] args) {

//		new paint();

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

		Shcool.Student[] students = { shcool.new Student("한가인"), shcool.new Student("두가인"), shcool.new Student("삼가인"),
				shcool.new Student("사가인"), shcool.new Student("오가인") };

		students[0].chk = true;
		students[0].start();
	}

}
