package FailureTask4;

import java.util.ArrayList;
import java.util.TreeSet;

import FailureTask.Question;
import FailureTask.School.Student;

class Timer extends Thread { // 시험과 가치 흘러가야 하므로 쓰레드 여야함

	boolean chk;
	int time;

	public Timer(String subject) {
		super(subject);
		chk = true;
	}

	@Override
	public void run() {

		time = 0;
		while (chk) {

			try {
				sleep(1000);
				time++;
				System.out.println(getName() + "시험\t" + time + "초 경과");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

enum Subject {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String subject;

	private Subject(String subject) {
		this.subject = subject;
	}

}

class QuestionData extends Thread { // 과목

	String subject;
	Timer timer; // 각 과목의 시간은 다르게 돈다. 각 과목마다 timer 생성
	ArrayList<Question> kor;
	ArrayList<Question> eng;
	ArrayList<Question> math;
	ArrayList[] subjects;

	public QuestionData(String subject) {
		super(subject);
		this.subject = subject;
		timer = new Timer(getName());
		subjects = new ArrayList[] { kor, eng, math };
	}

	class Question { // 질문과 답변 점수

		String question, answer;

		int jum;

		public Question(String question, String answer, int jum) {
			super();
			this.question = question;
			this.answer = answer;
			this.jum = jum;
		}

	}
}

class Korean extends Thread {

	String subject, question, answer;
	Timer timer; // 각 과목의 시간은 다르게 돈다. 각 과목마다 timer 생성
	int jum;
	ArrayList<Korean> questions;

	public Korean(String subject, String question, String answer, Timer timer, int jum) {
		super();
		this.subject = subject;
		this.question = question;
		this.answer = answer;
		this.timer = timer;
		this.jum = jum;
		questions.add(this);
	}

}

class English extends Korean {

	public English(String subject, String question, String answer, Timer timer, int jum) {
		super(subject, question, answer, timer, jum);
		// TODO Auto-generated constructor stub
	}

}

class Math extends Korean {

	public Math(String subject, String question, String answer, Timer timer, int jum) {
		super(subject, question, answer, timer, jum);
		// TODO Auto-generated constructor stub
	}

}

class School {

	// 학생 데이타를 가짐
	// 선생 데이타를 가짐
	TreeSet<Student> studentsData; // 학생
	TreeSet<Teacher> teachers; // 선생님

	class Teacher { // 문제는 과목 담당 선생님이 만든다.

		// 문제 클래스 생성 why? 과목마다 선생님이 다름
		// 생성 될때 학교에 귀속
		String subject;
		QuestionData question;

		public Teacher(String subject) {
			super();
			this.subject = subject;
			question = new QuestionData(subject);
		}

		void examAdd(String subject, String question, String answer, int jum) {

			for (int i = 0; i < Subject.values().length; i++) {
				if (subject.equals(Subject.values()[i].subject)) {
					this.question.subjects[i].add(this.question.new Question(question, answer, jum));
					break;
				}
			}

		}

	}

	class Student implements Comparable {

		// 생성 될때 학교에 귀속

	}

}

public class Main {

	public static void main(String[] args) {

	}

}
