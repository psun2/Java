package exam;

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

class Fram extends JFrame {

	Shcool.Subject subject;
	JPanel testMain__testExam;

	public Fram(String title, Shcool.Subject subject) {

		super(title);

		this.subject = subject;

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

				}
			});
		}

		testMain__testExam = new JPanel(); // 문제와 timer를 보여줌
		testMain__testExam.setLayout(new GridLayout(12, 1));
		main__testMain.add(testMain__testExam, "Center");

		// 여기 손 봐야함
		for (Shcool.Subject.Question exam : subject.questions) {

			JLabel questionLb = new JLabel(exam.question);
			testMain__testExam.add(questionLb);

			if (exam.view != null) {

				ButtonGroup bg = new ButtonGroup();

				ArrayList<JRadioButton> viewBtns = new ArrayList<JRadioButton>();

				for (String view : exam.view.split(",")) {

					JRadioButton viewRb = new JRadioButton(view);
					testMain__testExam.add(viewRb);
					viewBtns.add(viewRb);
					bg.add(viewRb);
				}

				int index = Integer.parseInt(exam.answer) - 1;

				if (viewBtns.get(index).isSelected())
					exam.jum = 1;

				System.out.println(getState());

			}

			JTextField answerTf = new JTextField();
			testMain__testExam.add(answerTf);

			if (answerTf.getText().equals(exam.answer))
				exam.jum = 1;

		}

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

	public Shcool() {
		this.subjects = new LinkedHashMap<String, Shcool.Subject>();
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

		void add(String question, String answer) {
			questions.add(new Question(question, answer));
		}

		void add(String question, String view, String answer) {
			questions.add(new Question(question, view, answer));
		}

		void goTest() {

//			Fram frame1 = new Fram("시험", this);
//			Fram frame2 = new Fram("시험", this);
//			Fram frame3 = new Fram("시험", this);

			while (true) {
				if (!chk) {
					Fram frame = new Fram("시험", this);

				}
				this.chk = true;
				System.out.println(chk);
				System.out.println("프레임끝 리턴");
			}
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

		}

	}

	class Student {

		void test() {

		}

	}
}

public class Main {

	public static void main(String[] args) {

		// .hide();
		// .show();

		Shcool shcool = new Shcool();

		Shcool.Subject korea = shcool.new Subject("국어");
		Shcool.Subject eng = shcool.new Subject("영어");
//		shcool.new Subject("수학");

//		korea.add("질문", "답변");
		korea.add("질문", "싫어요,조아요,글쎄요", "3");

		System.out.println(korea.questions);
		korea.goTest();
//		eng.goTest();

		System.out.println("고테스트 끝남ㄴ");

		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println("t1");
				System.out.println(korea.chk);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		korea.questions.get(1).goTest();

	}

}
