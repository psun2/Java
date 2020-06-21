package FailureTask7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import javax.swing.JOptionPane;

class Timer extends Thread { // 시험과 가치 흘러가야 하므로 쓰레드 여야함

	int time, stop;
	Subject subject;
	StudentData.Student student;

	public Timer(String title) {
		super(title);
	}

	void add(StudentData.Student student) {
		this.student = student;
	}

	@Override
	public void run() {

		time = 0;
		while (true) {

			while (getName().equals(student.currentThread)) {

				try {
					sleep(1000);
					time++;
					System.out.println(getName() + "시험\t" + time + "초 경과");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (student.currentThread == null)
				break;
		}

		System.out.println(getName() + " 타이머종료");

	}

}

enum Title {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String title;

	private Title(String title) {
		this.title = title;
	}

}

class Subject extends Thread {

	String subject;
	Timer timer;
	ArrayList<Question> questions;
	StudentData.Student student;

	public Subject(String subject) {
		super(subject);
		this.subject = subject;
		this.questions = new ArrayList<Question>();
		this.timer = new Timer(subject);
	}

	void examAdd(String question, String answer, int jum) {

		this.questions.add(new Question(question, answer, jum));

	}

	void test(StudentData.Student student) {

		this.student = student;

	}

	@Override
	public void run() {

		student.result(this);
	}

}

class Question { // 과목에 따른 문제가 있다.

	String question, answer;
	int jum;

	public Question(String question, String answer, int jum) {
		super();
		this.question = question;
		this.answer = answer;
		this.jum = jum;
	}

}

class StudentData {

	TreeSet<Student> data;

	public StudentData() {
		data = new TreeSet<Student>();
	}

	void print() {

		for (Student student : data) {
			System.out.println(student);
		}

	}

	class Student implements Comparable<Student> {

		String name;
		int tot, avg, rank;
		int[] jum;
		ArrayList<Subject> subject;
		String currentThread;

		public Student(String name, Subject korean, Subject english, Subject math) {
			super();
			this.name = name;
			this.subject = new ArrayList<Subject>();
			this.jum = new int[Title.values().length];
			subject.add(korean);
			subject.add(english);
			subject.add(math);
		}

		void test() { // Thread start

			for (Subject sub : subject) {
				sub.test(this);
				sub.start();
			}

//			subject.get(0).start();
//			subject.get(1).start();
//			subject.test(this);
			subject.get(2).test(this);
			subject.get(2).start();

		}

		void init() {
			for (Subject sub : subject) {
				sub.timer.add(this);
			}
		}

		synchronized void result(Subject subject) {

			init();

			System.out.println(subject.subject + " 시험시작");

			subject.timer.start(); // 시험 시작과 함께 시간도 스타트

			currentThread = Thread.currentThread().getName(); // 시작시때문에 여기서 한번 더 받아주어야함

//			System.out.println(currentThread);

//			jumsu = new int[test.size()]; // 배열 초기화

			for (Question obj : subject.questions) {

				int index = subject.questions.indexOf(obj); // 문제의 인덱스 번호를 알고 있다면... 답의 인덱스 와 비교

				while (true) {

					currentThread = Thread.currentThread().getName();

					String result = JOptionPane.showInputDialog(obj.question);

					if (result.equals(subject.questions.get(index).answer)) { // 정답시 정답의 갯수를 늘려주고, 정지
						System.out.println("입력 : " + result + "\t정답");

						int arrIndex = 0;
						for (Title e : Title.values()) {
							if (e.title.equals(currentThread)) {
								arrIndex = e.ordinal();
								break;
							}
						}

						this.jum[arrIndex] += subject.questions.get(index).jum;

						break;
					} else if (result.equals("p")) { // 패스시 다른 과목으로 넘어감
						System.out.println("입력 : " + result + "\t패스시 오답처리되며, 다른 과목으로 넘어감");
						waitThread();
						break;
					} else {
						System.out.println("입력 : " + result + "\t오답");
					}
				}

			}
			System.out.println(subject.subject + " 시험종료");
			notifyAll(); // 남은 찌꺼기 쓰레드를 깨워 주지 않았으므로 한과목이라도 먼저 끝나면 다시 모든 쓰레드를 깨움
			currentThread = null; // 시간 초기화

		}

		void waitThread() {

			notifyAll(); // 함수가 시작시 모든 쓰레드 깨움

			try {
				wait(); // 조건에 맞으면 정지

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		void calc() {

			for (int jum : jum) {
				this.tot += jum;
			}

			this.avg = tot / jum.length;

			rank();
		}

		void rank() {

			rank = 1;

			for (Student you : StudentData.this.data) {
				if (this.avg < you.avg)
					this.rank++;
			}

		}

		@Override
		public String toString() {

			String result = name + "\t";

			for (int jum : jum) {
				result += jum + "\t";
			}

			result += tot + "\t" + avg + "\t" + rank;

			return result;
		}

		@Override
		public int compareTo(Student you) {
			int result = avg - you.avg;

			if (result == 0)
				name.compareTo(you.name);

			return result;
		}

	}
}

public class Main {

	public static void main(String[] args) throws Exception {

		StudentData data = new StudentData(); // 학교 설립

		Subject korean = new Subject("국어");
		Subject english = new Subject("영어");
		Subject math = new Subject("수학");

		korean.examAdd("가 다음은?", "나", 10);
		korean.examAdd("나 다음은 ?", "다", 10);
		korean.examAdd("다 다음은 ?", "라", 10);
		korean.examAdd("라 다음은 ?", "마", 10);
		korean.examAdd("마 다음은 ?", "바", 10);
		korean.examAdd("바 다음은 ?", "사", 10);
		korean.examAdd("사 다음은 ?", "아", 10);
		korean.examAdd("아 다음은 ?", "자", 10);
		korean.examAdd("자 다음은 ?", "차", 10);
		korean.examAdd("차 다음은 ?", "카", 10);

		english.examAdd("english", "영어", 10);
		english.examAdd("quiz", "퀴즈", 10);
		english.examAdd("puzzle", "퍼즐", 10);
		english.examAdd("apple", "사과", 10);
		english.examAdd("iphone", "아이폰", 10);
		english.examAdd("samsung", "삼성", 10);
		english.examAdd("grade", "등급", 10);
		english.examAdd("banana", "바나나", 10);
		english.examAdd("coffee", "커피", 10);
		english.examAdd("student", "학생", 10);

		math.examAdd("1 + 1 ?", "2", 10);
		math.examAdd("2 + 2 ?", "4", 10);
		math.examAdd("3 + 3 ?", "6", 10);
		math.examAdd("4 + 4 ?", "8", 10);
		math.examAdd("5 + 5 ?", "10", 10);
		math.examAdd("2 * 2 ?", "4", 10);
		math.examAdd("3 * 3 ?", "9", 10);
		math.examAdd("4 * 4 ?", "16", 10);
		math.examAdd("5 * 5 ?", "25", 10);
		math.examAdd("6 * 6 ?", "36", 10);

		StudentData.Student[] students = { data.new Student("한가인", korean, english, math),
				data.new Student("드가인", korean, english, math), data.new Student("삼가인", korean, english, math),
				data.new Student("사가인", korean, english, math), data.new Student("오가인", korean, english, math),
				data.new Student("육가인", korean, english, math), data.new Student("칠가인", korean, english, math),
				data.new Student("팔가인", korean, english, math), data.new Student("구가인", korean, english, math),
				data.new Student("십가인", korean, english, math) }; // 학생

		students[0].test();
		Thread.sleep(60000);
		students[1].test();

		for (StudentData.Student student : students) {
			student.test(); // 시험 시작
		}

		// 메인 쓰레드의 움직임을 멈추어 주어야함....?;;;;

	}

}
