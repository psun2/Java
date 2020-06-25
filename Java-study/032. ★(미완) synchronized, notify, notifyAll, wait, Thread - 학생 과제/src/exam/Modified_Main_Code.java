package exam;

import java.util.ArrayList;

class ExamSchool {

	String shcool;

	public ExamSchool(String shcool) {
		super();
		this.shcool = shcool;
	}

	class Tracher { // Subject 의 outer

		String subject;
		Subject subjectObj;
		ArrayList<Subject> subjects;

		public Tracher(String subject) {
			super();
			this.subject = subject;
			this.subjectObj = new Subject();
			this.subjects = new ArrayList<Subject>();
		}

		class Subject extends Thread { // Tracher 의 inner

			Timer timer;
			ArrayList<Exam> exams;

			public Subject() {
				super(Tracher.this.subject);
				this.timer = new Timer();
				this.exams = new ArrayList<Exam>();
				Tracher.this.subjects.add(this);
			}

			class Timer extends Thread { // Subject 의 inner

				public Timer() {
					super(Tracher.this.subject);
				}

			}

			void addExam(String question, String answer, int jum) {
				exams.add(new Exam(question, answer, jum));
			}

			class Exam { // Subject 의 inner

				String question, answer;
				int jum, result;

				public Exam(String question, String answer, int jum) {
					super();
					this.question = question;
					this.answer = answer;
					this.jum = jum;
				}

			}

		}

	}

	class Student {

	}
}

public class Modified_Main_Code {

	public static void main(String[] args) {

		ExamSchool shcool = new ExamSchool("고등학교");

		ExamSchool.Tracher korean = shcool.new Tracher("국어");
		ExamSchool.Tracher english = shcool.new Tracher("영어");
		ExamSchool.Tracher math = shcool.new Tracher("수학");
		
		korean.subjectObj.addExam(question, answer, jum);

		map.get("국어").add("달면 삼키고 쓰면 뱉는다는 뜻을 가진 사자성어는 ?", "감탄고토");
		map.get("국어").add("앞뒤의 잔말을 빼고 요점만 말하는 뜻을 가진 사자성어는 ?", "거두절미");
		map.get("국어").add("평범한 남녀를이르는 사자성어는 ?", "갑남을녀");
		map.get("국어").add("잘못을 고치고 옳은 길에 들어선다는 뜻을 가진 사자성어는 ?", "개과천선");
		map.get("국어").add("입은 은혜에 대한 고마운 마음이 뼈에 사무쳐 잊혀지지 않는다는 뜻을 가진 사자성어는 ?", "각골난망");

		map.get("영어").add("사과는 영어로?", "apple");
		map.get("영어").add("바나나는 영어로?", "banana");
		map.get("영어").add("배는 영어로?", "pear");
		map.get("영어").add("복숭아는 영어로?", "peach");
		map.get("영어").add("자두는 영어로?", "plum");

		map.get("수학").add("1+1= ?", "2");
		map.get("수학").add("2+2= ?", "4");
		map.get("수학").add("3+3= ?", "6");
		map.get("수학").add("4+4= ?", "8");
		map.get("수학").add("5+5= ?", "10");

	}

}