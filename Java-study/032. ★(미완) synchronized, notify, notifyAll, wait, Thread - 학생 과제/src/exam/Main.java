package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import javax.swing.JOptionPane;

class Timer extends Thread { // 시험과 가치 흘러가야 하므로 쓰레드 여야함

	int time;
	School.Student student;
	boolean chk;

	public Timer(String title, School.Student student) {
		super(title);
		this.student = student;
		this.chk = false;

	}

	void chk() {
		chk = true;
		student.school.currentThread = null;
	}

	@Override
	public void run() {

		time = 0;

		Timer: while (true) {

			while (getName().equals(student.school.currentThread)) {

				try {
					sleep(1000);
					time++;
					System.out.println(this.student.getName() + " 학생 " + getName() + "시험\t" + time + "초 경과");
//					System.out.println(Thread.currentThread().getName());
//					System.out.println(student.school.currentThread);
//					System.out.println(Thread.this.getState());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (chk) {
//				System.out.println(getName() + "브레이크 걸림");
				break Timer;

			}
		}

		System.out.println(this.student.getName() + " 학생 " + getName() + " 타이머종료");

	}

}

enum Title {

	KOREAN("국어"), ENGLISH("영어"), MATH("수학");

	String title;

	private Title(String title) {
		this.title = title;
	}

}

class Subject extends Thread implements Cloneable { // 국, 영, 수 셋트로 학생별 1개씩

	String subject;
	Timer timer;
	ArrayList<Question> questions;
	School.Student student;

	public Subject(String subject) {
		super(subject);
		this.subject = subject;
		this.questions = new ArrayList<Question>();
	}

	void examAdd(String question, String answer, int jum) {

		this.questions.add(new Question(question, answer, jum));

	}

	void test(School.Student student) {
		this.student = student;
		this.timer = new Timer(getName(), student);
		start(); // 각 과목 문제풀이 시작
	}

	@Override
	public void run() { // 1명씩 시험 보는거 완성
		student.school.test(this);// 각 과목을 한번에 시험 칠수 없으므로 동기화 진행
//		System.out.println("쓰레드 끝나고 쓰레드 이름 바뀜 여기는 run");
		student.school.currentThread = Thread.currentThread().getName(); // 한 쓰레드가 끝날때마다 현재의 쓰레드 이름으로 업데이트
		// 여길 살려줘야 마지막 과목 시험시 타이머가 중지 됩니다..... ?
		this.student.cnt++;
//		System.out.println("cnt (출력시점) : " + this.student.cnt);
//		System.out.println(this.student.subjec.size());
		if (this.student.cnt == this.student.subjec.size()) { // 과목이 다 끝나면 다음 학생이 시험을 봄 // while 을 멈춰주기위한 cnt++
			this.student.school.chk = true;
			this.student.school.cnt++; // 시험본 학생의 인원을 chk
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // Wow

		Subject clone = new Subject(this.subject);
		clone.timer = this.timer;
		clone.questions = this.questions;

		return clone;
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

	String name;
	ArrayList<Student> students;
	TreeSet<Student> sortStudents;
	ArrayList<Teacher> teacher;
	boolean chk;
	int cnt;
	String currentThread;
	int temp;

	public School(String name) {
		this.students = new ArrayList<Student>();
//		this.sortStudents = new TreeSet<Student>();
		this.teacher = new ArrayList<Teacher>();
		chk = true;
		cnt = 0;
	}

	synchronized void exam(Student student) { // 한명씩 시험을 start (문제도 동기화 필요)

		try { // 한명씩 들어 오는거 확인 ok
			if (student.school.chk && student.chk) { // 똑같은 학생이 한번더 와서 두가지를 chk 함
				student.school.chk = false;
				student.chk = false;
				System.out.println(student.getName() + " 시험 시작");
				for (Subject sub : student.subjec) { // 국, 영, 수 쓰레드 시작
					sub.test(student);
				}
				wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		notifyAll();
	}

	synchronized void test(Subject subject) {

		System.out.println(subject.subject + " 시험시작");

		currentThread = Thread.currentThread().getName(); // 초기값 설정

		subject.timer.start(); // 시험 시작과 함께 시간도 스타트

		for (Question obj : subject.questions) {

			int index = subject.questions.indexOf(obj); // 문제의 인덱스 번호를 알고 있다면... 답의 인덱스 와 비교

			currentThread = Thread.currentThread().getName();

			while (true) {

//				currentThread = Thread.currentThread().getName(); // 문제가 도는 중간중간 맞나 확인

				String result = JOptionPane.showInputDialog(obj.question);

				if (result.equals(subject.questions.get(index).answer)) { // 정답시 정답의 갯수를 늘려주고, 정지
					System.out.println("입력 : " + result + "\t정답");

					int arrIndex = 0;
					for (Title e : Title.values()) {
						if (e.title.equals(subject.getName())) {
							arrIndex = e.ordinal();
							break;
						}
					}

					subject.student.jum[arrIndex] += subject.questions.get(index).jum;

					System.out.println(Arrays.toString(subject.student.jum));

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

		try {
			notifyAll(); // 남은 찌꺼기 쓰레드를 깨워 주지 않았으므로 한과목이라도 먼저 끝나면 다시 모든 쓰레드를 깨움
			currentThread = null;
			subject.timer.chk();
			System.out.println(subject.getName() + " 시험종료");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		currentThread = null; // 시간 초기화
//		System.out.println("쓰레드 끝나고 쓰레드 이름 바뀜 여기는 test");

	}

	synchronized void waitThread() {

		notifyAll(); // 검사가 진행되면 모든 쓰레드를 깨움

		try {
			wait(); // 조건에 맞으면 정지
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void rank() {

		for (Student me : this.students) {
			for (Student you : this.students) {
				if (me.avg < you.avg)
					me.rank++;
			}
		}

		addTreeSet();

	}

	void addTreeSet() {

		this.sortStudents = new TreeSet(students);

	}

	void show() {

		for (Student student : sortStudents) {
			System.out.println(student);
		}

	}

	class Teacher {

		Subject subjec;

		public Teacher(String subject) {
			this.subjec = new Subject(subject);
			School.this.teacher.add(this);
		}

	}

	class Student extends Thread implements Comparable<Student> {

		String name;
		int sum, avg, rank = 1;
		int[] jum;
		ArrayList<Subject> subjec;
		School school;
		int cnt;
		boolean chk;

		public Student(String name, School school) {
			super(name);
			this.name = name;
			this.school = school;
			School.this.students.add(this);
			cnt = 0;
			chk = true;
		}

		void test() { // 테스트가 시작되면 문제지를 받음.

			subjec = new ArrayList<Subject>();

			for (Teacher teacher : School.this.teacher) {
				try {
					subjec.add((Subject) teacher.subjec.clone()); // 학생마다 쓰레드가 돌아야 하므로, clone
				} catch (CloneNotSupportedException e) { // clone 하지 않으면 주소가 공유되어, 끝난 쓰레드를 다시 실행하는 Error 발생
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			this.jum = new int[this.subjec.size()];

			start(); // 시험지가 배부 되면 쓰레드 시작

		}

		@Override
		public void run() { // 동기화를 사용하기 위해 Thread 가 아닌 다른 클래스 사용

			while (true) { // 마지막 까지 살아 남는 부분

				if (School.this.cnt == School.this.students.size()) {
					calc();
					break;
				}

				School.this.exam(this);

			}

		}

		void calc() {

			for (int su : jum) {
				sum += su;
			}

			avg = sum / jum.length;

		}

		@Override
		public int compareTo(Student you) { // 정렬

			int result = rank - you.rank;

			if (result == 0)
				name.compareTo(you.name);

			return result;
		}

		@Override
		public String toString() {

			String result = name + "\t";

			for (int su : jum) {
				result += su + "\t";
			}

			result += sum + "\t" + avg + "\t" + rank;

			return result;
		}

	}
}

public class Main {

	public static void main(String[] args) {

		// 쓰레드를 돌릴려면 주소를 공유하면 안됨....

		// 학생수 쓰레드, 학생마다 과목 쓰레드가 존재, 과목마다 타이머 쓰레드가 존재

		// 학생 쓰레드는 과목 쓰레드를 가지고 있고, 과목 쓰레드 안에는 타이머 쓰레드가 존재

		School school = new School("고등학교"); // 학교

		School.Teacher korean = school.new Teacher("국어"); // 선생님
		School.Teacher english = school.new Teacher("영어");
		School.Teacher math = school.new Teacher("수학");

//		School.Student[] students = { school.new Student("한가인", school), school.new Student("두가인", school),
//				school.new Student("삼가인", school), school.new Student("사가인", school), school.new Student("오가인", school),
//				school.new Student("육가인", school), school.new Student("팔가인", school), school.new Student("구가인", school),
//				school.new Student("십가인", school) }; // 학생
		School.Student[] students = { school.new Student("한가인", school), school.new Student("두가인", school),
				school.new Student("삼가인", school), school.new Student("사가인", school) }; // 학생

		korean.subjec.examAdd("가 다음은?", "나", 10); // 선생님이 문제를 출제해요
		korean.subjec.examAdd("나 다음은 ?", "다", 10);
		korean.subjec.examAdd("다 다음은 ?", "라", 10);
		korean.subjec.examAdd("라 다음은 ?", "마", 10);
		korean.subjec.examAdd("마 다음은 ?", "바", 10);
//		korean.subjec.examAdd("바 다음은 ?", "사", 10);
//		korean.subjec.examAdd("사 다음은 ?", "아", 10);
//		korean.subjec.examAdd("아 다음은 ?", "자", 10);
//		korean.subjec.examAdd("자 다음은 ?", "차", 10);
//		korean.subjec.examAdd("차 다음은 ?", "카", 10);

		english.subjec.examAdd("english", "영어", 10);
		english.subjec.examAdd("quiz", "퀴즈", 10);
		english.subjec.examAdd("puzzle", "퍼즐", 10);
		english.subjec.examAdd("apple", "사과", 10);
		english.subjec.examAdd("iphone", "아이폰", 10);
//		english.subjec.examAdd("samsung", "삼성", 10);
//		english.subjec.examAdd("grade", "등급", 10);
//		english.subjec.examAdd("banana", "바나나", 10);
//		english.subjec.examAdd("coffee", "커피", 10);
//		english.subjec.examAdd("student", "학생", 10);

		math.subjec.examAdd("1 + 1 ?", "2", 10);
		math.subjec.examAdd("2 + 2 ?", "4", 10);
		math.subjec.examAdd("3 + 3 ?", "6", 10);
		math.subjec.examAdd("4 + 4 ?", "8", 10);
		math.subjec.examAdd("5 + 5 ?", "10", 10);
//		math.subjec.examAdd("2 * 2 ?", "4", 10);
//		math.subjec.examAdd("3 * 3 ?", "9", 10);
//		math.subjec.examAdd("4 * 4 ?", "16", 10);
//		math.subjec.examAdd("5 * 5 ?", "25", 10);
//		math.subjec.examAdd("6 * 6 ?", "36", 10);

//		System.out.println(school.teacher.get(0).subjec.questions.get(0).question); // 출력 ok

//		System.out.println(students[0].subjec); // 여긴 main 쓰레드가 더 빨리 돌아서 null

		for (School.Student student : students) {
			student.test();
		}

//		students[0].test();
//		System.out.println(students[0].subjec); // [Thread[국어,5,main], Thread[영어,5,main], Thread[수학,5,main]]

		try {
			Thread.sleep(150000); // 메인 쓰레드가 더 빨리 돌아요, 강제로 멈춰요
			school.rank();
			Thread.sleep(1000);
			school.show(); // 출력
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		try {
//			
//			for (School.Student student : students) {
//				student.test();
//			}
//			
//		} finally {
//			school.rank(); // 등수 계산
//			school.show(); // 출력
//		}

	}

}

//한가인 시험 시작
//영어 시험시작
//한가인 학생 영어시험	1초 경과
//한가인 학생 영어시험	2초 경과
//한가인 학생 영어시험	3초 경과
//입력 : 영어	정답
//[0, 10, 0]
//한가인 학생 영어시험	4초 경과
//한가인 학생 영어시험	5초 경과
//입력 : 퀴즈	정답
//[0, 20, 0]
//한가인 학생 영어시험	6초 경과
//입력 : 퍼즐	정답
//[0, 30, 0]
//한가인 학생 영어시험	7초 경과
//입력 : 사과	정답
//[0, 40, 0]
//한가인 학생 영어시험	8초 경과
//입력 : 아이폰	정답
//[0, 50, 0]
//영어 시험종료
//한가인 학생 영어시험	9초 경과
//한가인 학생 영어 타이머종료
//국어 시험시작
//한가인 학생 국어시험	1초 경과
//입력 : 나	정답
//[10, 50, 0]
//입력 : 다	정답
//[20, 50, 0]
//한가인 학생 국어시험	2초 경과
//입력 : 라	정답
//[30, 50, 0]
//입력 : 마	정답
//[40, 50, 0]
//한가인 학생 국어시험	3초 경과
//입력 : 바	정답
//[50, 50, 0]
//국어 시험종료
//한가인 학생 국어시험	4초 경과
//한가인 학생 국어 타이머종료
//수학 시험시작
//한가인 학생 수학시험	1초 경과
//입력 : 2	정답
//[50, 50, 10]
//입력 : 4	정답
//[50, 50, 20]
//한가인 학생 수학시험	2초 경과
//입력 : 6	정답
//[50, 50, 30]
//한가인 학생 수학시험	3초 경과
//입력 : 8	정답
//[50, 50, 40]
//입력 : 10	정답
//[50, 50, 50]
//수학 시험종료
//한가인 학생 수학시험	4초 경과
//한가인 학생 수학 타이머종료
//두가인 시험 시작
//영어 시험시작
//두가인 학생 영어시험	1초 경과
//두가인 학생 영어시험	2초 경과
//두가인 학생 영어시험	3초 경과
//두가인 학생 영어시험	4초 경과
//두가인 학생 영어시험	5초 경과
//두가인 학생 영어시험	6초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//수학 시험시작
//두가인 학생 영어시험	7초 경과
//두가인 학생 수학시험	1초 경과
//입력 : 2	정답
//[0, 0, 10]
//두가인 학생 수학시험	2초 경과
//입력 : 4	정답
//[0, 0, 20]
//입력 : 6	정답
//[0, 0, 30]
//두가인 학생 수학시험	3초 경과
//입력 : 8	정답
//[0, 0, 40]
//두가인 학생 수학시험	4초 경과
//입력 : 10	정답
//[0, 0, 50]
//수학 시험종료
//두가인 학생 수학시험	5초 경과
//두가인 학생 수학 타이머종료
//국어 시험시작
//두가인 학생 국어시험	1초 경과
//두가인 학생 국어시험	2초 경과
//입력 : 나	정답
//[10, 0, 50]
//두가인 학생 국어시험	3초 경과
//입력 : 다	정답
//[20, 0, 50]
//입력 : 라	정답
//[30, 0, 50]
//두가인 학생 국어시험	4초 경과
//입력 : 마	정답
//[40, 0, 50]
//입력 : 바	정답
//[50, 0, 50]
//국어 시험종료
//두가인 학생 국어시험	5초 경과
//두가인 학생 국어 타이머종료
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//두가인 학생 영어시험	8초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//두가인 학생 영어시험	9초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//영어 시험종료
//두가인 학생 영어시험	10초 경과
//두가인 학생 영어 타이머종료
//삼가인 시험 시작
//영어 시험시작
//삼가인 학생 영어시험	1초 경과
//삼가인 학생 영어시험	2초 경과
//삼가인 학생 영어시험	3초 경과
//입력 : 영어	정답
//[0, 10, 0]
//삼가인 학생 영어시험	4초 경과
//입력 : 퀴즈	정답
//[0, 20, 0]
//삼가인 학생 영어시험	5초 경과
//입력 : 퍼즐	정답
//[0, 30, 0]
//삼가인 학생 영어시험	6초 경과
//삼가인 학생 영어시험	7초 경과
//입력 : 사과	정답
//[0, 40, 0]
//삼가인 학생 영어시험	8초 경과
//입력 : 아이폰	정답
//[0, 50, 0]
//영어 시험종료
//삼가인 학생 영어시험	9초 경과
//삼가인 학생 영어 타이머종료
//수학 시험시작
//삼가인 학생 수학시험	1초 경과
//삼가인 학생 수학시험	2초 경과
//입력 : 2	정답
//[0, 50, 10]
//삼가인 학생 수학시험	3초 경과
//삼가인 학생 수학시험	4초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//국어 시험시작
//삼가인 학생 수학시험	5초 경과
//삼가인 학생 국어시험	1초 경과
//삼가인 학생 국어시험	2초 경과
//입력 : 나	정답
//[10, 50, 10]
//삼가인 학생 국어시험	3초 경과
//입력 : 다	정답
//[20, 50, 10]
//삼가인 학생 국어시험	4초 경과
//입력 : 라	정답
//[30, 50, 10]
//삼가인 학생 국어시험	5초 경과
//입력 : 마	정답
//[40, 50, 10]
//입력 : 바	정답
//[50, 50, 10]
//국어 시험종료
//삼가인 학생 국어시험	6초 경과
//삼가인 학생 국어 타이머종료
//삼가인 학생 수학시험	6초 경과
//삼가인 학생 수학시험	7초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//삼가인 학생 수학시험	8초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//수학 시험종료
//삼가인 학생 수학시험	9초 경과
//삼가인 학생 수학 타이머종료
//사가인 시험 시작
//수학 시험시작
//사가인 학생 수학시험	1초 경과
//사가인 학생 수학시험	2초 경과
//입력 : 2	정답
//[0, 0, 10]
//사가인 학생 수학시험	3초 경과
//입력 : 4	정답
//[0, 0, 20]
//입력 : 6	정답
//[0, 0, 30]
//사가인 학생 수학시험	4초 경과
//입력 : 8	정답
//[0, 0, 40]
//사가인 학생 수학시험	5초 경과
//입력 : 10	정답
//[0, 0, 50]
//수학 시험종료
//사가인 학생 수학시험	6초 경과
//사가인 학생 수학 타이머종료
//국어 시험시작
//사가인 학생 국어시험	1초 경과
//사가인 학생 국어시험	2초 경과
//입력 : 나	정답
//[10, 0, 50]
//사가인 학생 국어시험	3초 경과
//입력 : 다	정답
//[20, 0, 50]
//사가인 학생 국어시험	4초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//영어 시험시작
//사가인 학생 국어시험	5초 경과
//사가인 학생 영어시험	1초 경과
//입력 : 영어	정답
//[20, 10, 50]
//사가인 학생 영어시험	2초 경과
//입력 : 퀴즈	정답
//[20, 20, 50]
//사가인 학생 영어시험	3초 경과
//입력 : 퍼즐	정답
//[20, 30, 50]
//사가인 학생 영어시험	4초 경과
//사가인 학생 영어시험	5초 경과
//입력 : 사과	정답
//[20, 40, 50]
//사가인 학생 영어시험	6초 경과
//입력 : 아이폰	정답
//[20, 50, 50]
//영어 시험종료
//사가인 학생 영어시험	7초 경과
//사가인 학생 영어 타이머종료
//사가인 학생 국어시험	6초 경과
//사가인 학생 국어시험	7초 경과
//사가인 학생 국어시험	8초 경과
//사가인 학생 국어시험	9초 경과
//사가인 학생 국어시험	10초 경과
//사가인 학생 국어시험	11초 경과
//사가인 학생 국어시험	12초 경과
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//입력 : p	패스시 오답처리되며, 다른 과목으로 넘어감
//국어 시험종료
//사가인 학생 국어시험	13초 경과
//사가인 학생 국어 타이머종료
//한가인	50	50	50	150	50	1
//사가인	20	50	50	120	40	2
//삼가인	50	50	10	110	36	3
//두가인	50	0	50	100	33	4
