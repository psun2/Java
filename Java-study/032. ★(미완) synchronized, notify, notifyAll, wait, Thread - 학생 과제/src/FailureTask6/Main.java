package FailureTask6;

import java.io.ObjectInputStream.GetField;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import javax.sound.midi.Instrument;
import javax.swing.JOptionPane;

class Timer extends Thread { // 시험과 가치 흘러가야 하므로 쓰레드 여야함

	int time, stop;
	Subject subject;
	School school;

	public Timer(String title, School school) {
		super(title);
		this.school = school;

	}

	void add(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void run() {

		time = 0;
		timer: while (true) {

			while (getName().equals(school.currentThread)) {

				try {
					sleep(1000);
					time++;
					System.out.println(getName() + "시험\t" + time + "초 경과");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (school.currentThread == null)
				break timer;
		}

		System.out.println(getName() + " 타이머종료");

	}

}

enum Title {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String subject;

	private Title(String subject) {
		this.subject = subject;
	}

}

class Subject extends Thread { // 과목이 있고

	String subject;
	School school;
	Timer timer; // 각 과목의 시간은 다르게 돈다. 각 과목마다 timer 생성
	ArrayList<Question> questions;
	School.Student student;

	public Subject(String subject, Timer timer, School school) {
		super(subject);
		this.subject = subject;
		this.timer = timer;
		this.school = school;
		this.questions = new ArrayList<Question>();
		timer.add(this);
	}

	void examAdd(String question, String answer, int jum) {

		this.questions.add(new Question(question, answer, jum));

	}

	void temporary(School.Student student) {
		this.student = student;
	}

	@Override
	public void run() {

		school.test(this);

		calc();
	}

	void calc() {

		int arr[] = school.jumsu;
		int sum = 0;
		int avg = 0;

		for (int i : arr) {
			sum += i;
		}

		avg = sum / arr.length;

		student.jum = arr;
		student.tot = sum;
		student.avg = avg;

		school.studentsData.add(student);

		school.rank();

		print();
	}

	void print() {

		for (School.Student stud : school.studentsData) {
			System.out.println(stud);
		}

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

class School {

	TreeSet<Student> studentsData; // 학생
	ArrayList<Teacher> teachers; // 선생님
	ArrayList<Subject> test; // 시험에 관한 문제 정보
	int[] jumsu = new int[3];
	String stop = null;
	String currentThread = null;

	public School() {
		super();
		this.studentsData = new TreeSet<Student>();
		this.teachers = new ArrayList<Teacher>();
		this.test = new ArrayList<Subject>();

	}

	synchronized void test(Subject subject) {

		System.out.println(subject.subject + " 시험시작");

		subject.timer.start(); // 시험 시작과 함께 시간도 스타트

//		jumsu = new int[test.size()]; // 배열 초기화

		currentThread = Thread.currentThread().getName(); // 시작시때문에 여기서 한번 더 받아주어야함

		for (Question obj : subject.questions) {

			int index = subject.questions.indexOf(obj);

			while (true) {

				currentThread = Thread.currentThread().getName();

				String result = JOptionPane.showInputDialog(obj.question);

				if (result.equals(subject.questions.get(index).answer)) { // 정답시 정답의 갯수를 늘려주고, 정지
					System.out.println("입력 : " + result + "\t정답");

					int arrIndex = 0;
					for (Title e : Title.values()) {
						if (e.subject.equals(currentThread)) {
							arrIndex = e.ordinal();
							break;
						}
					}
					this.jumsu[arrIndex] += subject.questions.get(index).jum;

					break;
				} else if (result.equals("p")) { // 패스시 다른 과목으로 넘어감
					System.out.println("입력 : " + result + "\t패스시 다른 과목으로 넘어감");
					waitThread(subject);
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

	synchronized void waitThread(Subject subject) {

		notifyAll(); // 검사가 진행되면 모든 쓰레드를 깨움

		try {
			wait(); // 조건에 맞으면 정지

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void rank() {

		for (Student me : studentsData) {

			for (Student you : studentsData) {
				if (me.avg < you.avg)
					me.rank++;
			}

		}
	}

	class Teacher { // 문제는 과목 담당 선생님이 만든다.

		String title;
		Subject subject;

		public Teacher(String title, School school) {
			super();
			this.title = title;
			this.subject = new Subject(title, new Timer(title, school), school);
			School.this.test.add(this.subject); // 학교에 문제정보를 담은 클래스를 추가
			School.this.teachers.add(this); // 학교에 선생님 추가
		}

		void examAdd(String question, String answer, int jum) {
			this.subject.examAdd(question, answer, jum);
		}

	}

	class Student implements Comparable<Student> {

		String name;
		int tot, avg, rank = 1;
		int[] jum;

		public Student(String name) {
			super();
			this.name = name;
		}

		synchronized void test() {

			for (int i = 0; i < School.this.test.size(); i++) {
				School.this.test.get(i).start();
				School.this.test.get(i).temporary(this);
			}
		}

		@Override
		public int compareTo(Student you) { // 정렬

			int res = rank - you.rank;

			if (res == 0)
				name.compareTo(you.name);

			return res;

		}

		@Override
		public String toString() {

			String result = name + "\t";

			for (int i : this.jum) {
				result += i + "\t";
			}

			result += tot + "\t" + avg + "\t" + rank;

			return result;
		}

	}

}

//class DaemonTest extends Thread {
//
//	public DaemonTest(String name) {
//		// TODO Auto-generated constructor stub
//		super(name);
//	}
//
//	@Override
//	public void run() {
//		while (true) {
//			try {
//				sleep(3000);
//				System.out.println(getName());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//}

public class Main {

	public static void main(String[] args) throws Exception {

//		DaemonTest daemon = new DaemonTest("데몬");
//
//		daemon.setDaemon(true);
//
//		daemon.start();

		School school = new School(); // 학교 설립

		School.Teacher korean = school.new Teacher("국어", school); // 국어 선생님
		School.Teacher english = school.new Teacher("영어", school); // 영어 선생님
		School.Teacher math = school.new Teacher("수학", school); // 수학 선생님

		School.Student[] students = { school.new Student("한가인"), school.new Student("드가인"), school.new Student("삼가인"),
				school.new Student("사가인"), school.new Student("오가인"), school.new Student("육가인"),
				school.new Student("칠가인"), school.new Student("팔가인"), school.new Student("구가인"),
				school.new Student("십가인") }; // 학생

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

		students[0].test();
		Thread.sleep(60000);
		students[1].test();
		Thread.sleep(60000);
		students[2].test();
		Thread.sleep(60000);
		students[3].test();
		Thread.sleep(60000);
		students[4].test();
		Thread.sleep(60000);
		students[5].test();
		Thread.sleep(60000);
		students[6].test();
		Thread.sleep(60000);
		students[7].test();
		Thread.sleep(60000);
		students[8].test();
		Thread.sleep(60000);
		students[9].test();
		Thread.sleep(60000);

	}

}
