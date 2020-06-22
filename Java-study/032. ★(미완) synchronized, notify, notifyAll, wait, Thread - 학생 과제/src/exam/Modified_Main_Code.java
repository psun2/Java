package exam;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

자고 일어나서 다시 생각 문제가 끝나도 끝 타이머가 끝나도 끝 입력된 result 가 다른과목이면 유예

class ExamSubject extends Thread { // outer

	String title;
	Exam exam;
	ArrayList<Exam> exams;
	ExamTimer timer;
	boolean now; // 맨처음이 국어이면 now 는 true 로 바꿔주고 국어 진행후 국어가 끝나면 now = flase;

	public ExamSubject(String title) {
		super(title);
		this.title = title;
		this.exams = new ArrayList<Exam>();
		this.timer = new ExamTimer(title);
		this.now = false;
	}

	void examAdd(String question, String answer, int jum) {
		exams.add(new Exam(question, answer, jum));
	}

	@Override
	public void run() {

		while (true) {

			if (this.now == true) { // 현재 과목이 true 일때만 진행

				timer.start();

				for (Exam exam : exams) {
					exam.goTest();
				}

			}

			break;

		}

		// 여기서 다음 쓰레드의 now 를 true로 바꾸어줌

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	class Exam { // inner

		String question, answer; // 질 답
		int jum; // 배점
		int result;
		boolean examChk;

		public Exam(String question, String answer, int jum) {
			super();
			this.question = question;
			this.answer = answer;
			this.jum = jum;
			this.examChk = false;
			this.result = 0;
		}

		void goTest() {

			while (true) {

				if (ExamSubject.this.timer.timerChk)
					break;

				String result = JOptionPane.showInputDialog(question);

				if (ExamSubject.this.now)
					return;

				if (result.equals(answer)) {
					this.result = jum;
					return;
				}

				if (result.equals("p"))
					return;

			}

			this.examChk = true; // 문제가 끝나면 타이머도 끝나야 하기 때문...

		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	class ExamTimer extends Thread { // inner

		boolean timerChk;

		public ExamTimer(String title) {
			super(title);
			this.timerChk = false;
		}

		@Override
		public void run() {

			int second = 20; // 시간제한

			while (second > 0) {

				if (ExamSubject.this.now) {

					if (ExamSubject.this.exam.examChk)
						break;

					System.out.println(getName() + " " + second);

					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			this.timerChk = true; // timer 가 끝나면 문제도 끝나야 하기 때문...

		}

	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class Modified_Main_Code {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
