package exam;

import java.util.ArrayList;

class ExamSchool {

	String shcool;

	public ExamSchool(String shcool) {
		super();
		this.shcool = shcool;
	}

	class Tracher {

		String subject;

		public Tracher(String subject) {
			super();
			this.subject = subject;
		}

		class Subject extends Thread {

//			Exam exam;
			Timer timer;
			ArrayList<Exam> exams;

			public Subject() {
				super(Tracher.this.subject);
//				this.exam = new Exam();
				this.timer = new Timer();
				this.exams = new ArrayList<Exam>();
			}

			class Timer extends Thread {

				public Timer() {
					super(Tracher.this.subject);
				}

			}

			void addExam(String question, String answer, int jum) {
				exams.add(new Exam(String question, String answer, int jum));
			}

		}

		class Exam {

			String question, answer;
			int jum, result;

		}

	}

	class Student {

	}
}

public class Modified_Main_Code {

	public static void main(String[] args) {

		ExamSchool shcool = new ExamSchool("고등학교");

	}

}