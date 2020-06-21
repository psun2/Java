package FailureTask;

import java.util.ArrayList;
import java.util.TreeSet;

enum Subject {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String subject;

	private Subject(String subject) {
		this.subject = subject;
	}

}

class Question { // 시험문제

	String subject, question, answer;

	int jum;

	public Question(String subject, String question, String answer, int jum) {
		super();
		this.subject = subject;
		this.question = question;
		this.answer = answer;
		this.jum = jum;
	}

}

class School { // 학교는 학생들의 정보와 시험지를 가지고 있습니다.

	ArrayList<Question> kor;
	ArrayList<Question> eng;
	ArrayList<Question> math;
	TreeSet<Student> studentsData;
	ArrayList[] arr;

	public School() {
		this.kor = new ArrayList<Question>();
		this.eng = new ArrayList<Question>();
		this.math = new ArrayList<Question>();
		this.studentsData = new TreeSet<Student>();
		arr = new ArrayList[] { kor, eng, math };
	}

	void examAdd(String subject, String question, String answer, int jum) {

		for (int i = 0; i < Subject.values().length; i++) {
			if (subject.equals(Subject.values()[i].subject)) {
				arr[i].add(new Question(subject, question, answer, jum));
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

		}

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

		school.examAdd("영어", "english", "영어", 10);
		school.examAdd("영어", "quiz", "퀴즈", 10);
		school.examAdd("영어", "puzzle", "퍼즐", 10);
		school.examAdd("영어", "apple", "사과", 10);
		school.examAdd("영어", "iphone", "아이폰", 10);
		school.examAdd("영어", "samsung", "삼성", 10);
		school.examAdd("영어", "grade", "등급", 10);
		school.examAdd("영어", "banana", "바나나", 10);
		school.examAdd("영어", "coffee", "커피", 10);
		school.examAdd("영어", "student", "학생", 10);

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

	}

}
