//문제점 : 국, 영, 수를 동시에 돌려야 하는데 지금 내 로직은 세개 같아보이는 하나 이다.
//synchronized 를 사용하려면 먼저 세개의 각기 다른 클래스에서 공통적으로 가지고 공유하는 클래스가 있어야 합니다.
//각 시험마다 시간을 재어야 하므로, 타이머는 공유할 수 없습니다.
//ex) 국어.시험을 본다.
//	영어.시험을 본다.
//	수학.시험을 본다.
//시험을 보는 공통적인 요소

package FailureTask2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import org.omg.CORBA.Current;

enum Subject {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String subject;

	private Subject(String subject) {
		this.subject = subject;
	}

}

class Timer extends Thread {

	@Override
	public void run() {

	}

	synchronized void test() {

		try {
			sleep(1000);
			System.out.println("진입이 잘되요");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class QuestionData extends Thread { // 시험문제

	String subject;
	ArrayList<Question> kor; // 국어
	ArrayList<Question> eng; // 영어
	ArrayList<Question> math; // 수학
	ArrayList[] arr;
	Test test;

	public QuestionData(String subject, Test test) {
		super();
		this.subject = subject;
		this.kor = new ArrayList<Question>();
		this.eng = new ArrayList<Question>();
		this.math = new ArrayList<Question>();
		arr = new ArrayList[] { kor, eng, math };
		this.test = test;
	}

	@Override
	public synchronized void run() {

		test.test(this.kor, this.eng, this.math);

	}

	class Question {

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

class Test {

	ArrayList subject;
	ArrayList<String> title = new ArrayList();

	public Test() {
		subject = new ArrayList();
		title.add("kor");
		title.add("eng");
		title.add("math");
	}

	synchronized void test(ArrayList kor, ArrayList eng, ArrayList math) {

		subject.add(kor);
		subject.add(eng);
		subject.add(math);

//			notify();

		int index = title.indexOf(Thread.currentThread().getName());

		Iterator<ArrayList> lit = subject.iterator();

		while (lit.hasNext()) {

			ArrayList buf = lit.next();

			String res = null;
			for (int i = 0; i < buf.size(); i++) {

				QuestionData.Question buf2 = (QuestionData.Question) buf.get(i);

				res = JOptionPane.showInputDialog(buf2.question);
				System.out.println("답안 출력 결과 : " + res);
			}

		}

		try {

//				wait();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class School { // 학교는 학생들의 정보와 시험지를 가지고 있습니다.

	TreeSet<Student> studentsData;
	QuestionData kor;
	QuestionData eng;
	QuestionData math;
	QuestionData[] subject;
	Test test;

	public School() {
		test = new Test();

		init();

		this.studentsData = new TreeSet<Student>();
		subject = new QuestionData[] { kor, eng, math };
	}

	void init() {

		this.kor = new QuestionData("국어", test);
		this.eng = new QuestionData("영어", test);
		this.math = new QuestionData("수학", test);

		kor.setName("kor");
		eng.setName("eng");
		math.setName("math");

	}

	void examAdd(String subject, String question, String answer, int jum) {

		for (int i = 0; i < Subject.values().length; i++) {
			if (subject.equals(Subject.values()[i].subject)) {
				this.subject[i].arr[i].add(this.subject[i].new Question(question, answer, jum)); // 문제 추가
				break;
			}
		}

	}

	class Student implements Comparable<Student> { // 학교 안에는 학생들이 있습니다.

		String name;
		int tot, avg, rank;

		public Student(String name) {
			super();
			this.name = name;
		}

//		쓰레드로 시험을 실행

		void test() { // 시험을 보고, tot 와 avg 를 설정
			// 여기서 점수를 업데이트; // 문제를 다풀면 쓰레드 멈춤

//			eng.start();
//			math.start();

			// 여기서 스타트가 되지요
			try {
				for (QuestionData student : subject) {
					student.start();
				}
//				School.this.kor.start(); // ✔✔TODO 여기 두번 실행되는 Error 을 어떻게 잡지 ?
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

//		@Override
//		public void run() {
//
//			for (ArrayList ele : arr) {
//
//				for (int i = 0; i < ele.size(); i++) {
//
//					String question = ((Question) ele.get(i)).question;
//
//					String res = JOptionPane.showInputDialog(question);
//					// 입력을 국영수 로 받음
//					// 이때 중요한 점이 해당 문제와 시간이 모두 save
//
//					for (int j = 0; j < Subject.values().length; j++) {
//						if (res.equals(Subject.values()[i])) {
//							synchronized (res) {
//
//							}
//						}
//
//					}
//
//					System.out.println(res);
//
//				}
//				System.out.println();
//			}
//		}

		void rank() { // 등수를 구하여 등수 셋팅
			School.this.studentsData.add(this);
		}

		@Override
		public int compareTo(Student you) { // 정렬

			int result = avg - you.avg;

			if (result == 0)
				name.compareTo(you.name);

			return result;
		}

	}

}

public class Main {

	public static void main(String[] args) {

		School school = new School();

		School.Student[] students = { school.new Student("한가인"), school.new Student("드가인"), school.new Student("삼가인"),
				school.new Student("사가인"), school.new Student("오가인"), school.new Student("육가인"),
				school.new Student("칠가인"), school.new Student("팔가인"), school.new Student("구가인"),
				school.new Student("십가인") };

		school.examAdd("국어", "가 다음은?", "나", 10);
		school.examAdd("국어", "나 다음은 ?", "다", 10);
		school.examAdd("국어", "다 다음은 ?", "라", 10);
		school.examAdd("국어", "라 다음은 ?", "마", 10);
		school.examAdd("국어", "마 다음은 ?", "바", 10);
		school.examAdd("국어", "바 다음은 ?", "사", 10);
		school.examAdd("국어", "사 다음은 ?", "아", 10);
		school.examAdd("국어", "아 다음은 ?", "자", 10);
		school.examAdd("국어", "자 다음은 ?", "차", 10);
		school.examAdd("국어", "차 다음은 ?", "카", 10);

		school.examAdd("영어", "english ?", "영어", 10);
		school.examAdd("영어", "quiz ?", "퀴즈", 10);
		school.examAdd("영어", "puzzle ?", "퍼즐", 10);
		school.examAdd("영어", "apple ?", "사과", 10);
		school.examAdd("영어", "iphone ?", "아이폰", 10);
		school.examAdd("영어", "samsung ?", "삼성", 10);
		school.examAdd("영어", "grade ?", "등급", 10);
		school.examAdd("영어", "banana ?", "바나나", 10);
		school.examAdd("영어", "coffee ?", "커피", 10);
		school.examAdd("영어", "student ?", "학생", 10);

		school.examAdd("수학", "1 + 1 ?", "2", 10);
		school.examAdd("수학", "2 + 2 ?", "4", 10);
		school.examAdd("수학", "3 + 3 ?", "6", 10);
		school.examAdd("수학", "4 + 4 ?", "8", 10);
		school.examAdd("수학", "5 + 5 ?", "10", 10);
		school.examAdd("수학", "2 * 2 ?", "4", 10);
		school.examAdd("수학", "3 * 3 ?", "9", 10);
		school.examAdd("수학", "4 * 4 ?", "16", 10);
		school.examAdd("수학", "5 * 5 ?", "25", 10);
		school.examAdd("수학", "6 * 6 ?", "36", 10);

		for (School.Student student : students) {
			student.test();
		}

//		students[0].test();

//		System.out.println(new School().new Student("십가인"));

	}

}
