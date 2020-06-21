package FailureTask8;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;

class Timer extends Thread {

	School school;
	boolean chk;
	int time;

	public Timer(String subject, School school) {
		super(subject);
		this.school = school;
		chk = true;
	}

	@Override
	public void run() {

		System.out.println(chk);
		school.goTime(this);

	}
}

class Subject extends Thread { // 과목이 있고

	String subject;
	School school;

	ArrayList<Question> questions;

	public Subject(String subject, School school) {
		super(subject);
		this.subject = subject;
		this.school = school;
		this.questions = new ArrayList<Question>();
	}

	void examAdd(String question, String answer, int jum) {

		this.questions.add(new Question(question, answer, jum));

	}

	@Override
	public void run() {

		school.goExan(this);
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

	// 학생 데이타를 가짐
	// 선생 데이타를 가짐
	TreeSet<Student> studentsData; // 학생
	ArrayList<Teacher> teachers; // 선생님
	ArrayList<Subject> test; // 시험에 관한 문제 정보
	ArrayList<Timer> timer; // 타이머 // 각 시험마다 타이머가 따로 돌아야 합니다.

	public School() {
		super();
		this.studentsData = new TreeSet<Student>();
		this.teachers = new ArrayList<Teacher>();
		this.test = new ArrayList<Subject>();
		this.timer = new ArrayList<Timer>();
	}

	synchronized void goExan(Subject subject) {

		for (Question question : subject.questions) {
			String res = JOptionPane.showInputDialog(question.question);

			System.out.println(res);
		}

	}

	synchronized void goTime(Timer timer) { // 모든 쓰레드가 정지가 됩니다. synchronized
		
		System.out.println("진입");

		timer.time = 0;

		while (timer.chk) {

			try {
				Thread.sleep(1000);
				timer.time++;
				System.out.println(timer.getName() + "시험\t" + timer.time + "초 경과");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(timer.getName() + "시험\t" + timer.time + "초 경과후 종료");

	}

	class Teacher { // 문제는 과목 담당 선생님이 만든다.

		// 문제 클래스 생성 why? 과목마다 선생님이 다름
		// 생성 될때 학교에 귀속
		String title;
		Subject subject;

		public Teacher(String subject, School school) {
			super();
			this.title = subject;
			this.subject = new Subject(subject, school); // 학교 정보와 과목
			School.this.timer.add(new Timer(subject, school)); // 학교정보와 타이머 과목별 선생님은 시험에 대한 timer을 만듬.
			School.this.test.add(this.subject); // 학교에 문제정보를 담은 클래스를 추가
			School.this.teachers.add(this); // 학교에 선생님 추가
		}

		void examAdd(String question, String answer, int jum) {
			this.subject.examAdd(question, answer, jum);
		}

	}

	class Student implements Comparable<Student> {

		// 생성 될때 학교에 귀속

		String name;

		public Student(String name) {
			super();
			this.name = name;
		}

		void test() {

			for (int i = 0; i < School.this.test.size(); i++) {
				School.this.test.get(i).start();
				School.this.timer.get(i).start();
			}

		}

		@Override
		public int compareTo(Student arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}

public class Main {

	public static void main(String[] args) {

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

		System.out.println(school.test.get(0).questions.get(0).question);
		System.out.println(school.test.get(1).questions.get(0).question);
		System.out.println(school.test.get(2).questions.get(0).question);

		System.out.println(school.test); // 국영수 쓰레드

		students[0].test();

	}

}
