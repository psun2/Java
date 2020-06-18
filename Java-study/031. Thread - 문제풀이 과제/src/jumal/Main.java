package jumal;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

class Timer extends Thread {

	int timeRequired; // 경과 시간

	Test test;

	public Timer(Test test) {
		super();
		this.test = test;
	}

	@Override
	public void run() {

		timeRequired = 0;

		while (test.chk) {

			String show = test.subject + "시험 시작";

			if (timeRequired != 0)
				show = timeRequired + "  초 경과";

			System.out.println(show);

			try {
				Thread.sleep(1000);
				timeRequired++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println(test.subject + "끝");
	}

}

class Test extends Thread { // 문제의 조건에 맞추기 위하여 국, 영, 수 클래스를 모두 생성

	String subject; // 시험 과목 이름
	ArrayList<String> question; // 질문
	ArrayList<String> answer; // 답
	ArrayList<Integer> keys; // enum
	boolean chk = true; // timer 종료
	Timer timer; // 시간 측정 Timer 클래스
//	int timeRequired, manjum; // 소요시간과 만점
	int cnt; // 맞은 갯수 저장

	public Test(String subject) {
		super();
		this.subject = subject;
		this.question = new ArrayList<String>();
		this.answer = new ArrayList<String>();
	}

	void add(String question, String answer) { // 질문과 답을 받아 각각의 ArrayList 에 저장
		this.question.add(question);
		this.answer.add(answer);
	}

	@Override
	public void run() {
		test();
	}

	int test() { // 시험 시작

		timer = new Timer(this); // 타이머 생성
		timer.start(); // 타이머 스타트

		cnt = 0;
		for (String q : question) {

			int index = question.indexOf(q);

			while (true) {

				String result = JOptionPane.showInputDialog(q);

				if (result.equals(answer.get(index))) { // 정답시 정답의 갯수를 늘려주고, 정지
					System.out.println("입력 : " + result + "\t정답");
					cnt++;
					break;
				} else if (result.equals("p")) { // 패스시 다음 문제로 넘어가기 위하여 정지
					System.out.println("입력 : " + result + "\t패스");
					break;
				} else {
					System.out.println("입력 : " + result + "\t틀림");
					continue;
				}
			}

		}

		chk = false; // 타이머를 종료하기 위해 바꾸어줌
//		this.timeRequired = timer.timeRequired; // 소요시간 교체
//		System.out.println("timer에서 반환된 시험 시간 : " + timeRequired);
//		mapPut(cnt); // 결과 저장

		return cnt;
	}
//
//	void mapPut(int cnt) {
//
//		this.keys = new ArrayList<Integer>();
//		keys.add(Result.Jumsu.ordinal());
//		keys.add(Result.Pass.ordinal());
//		keys.add(Result.TimeRequired.ordinal());
//
//		this.manjum = 10;
//		String[] result = { cnt * 10 + "점", manjum - cnt + "개", timeRequired + "초" };
//
//		for (int i = 0; i < keys.size(); i++) {
//			this.result.put(keys.get(i), result[i]);
//		}
//
//		result();
//
//	}

//	void result() {
//
//		String print = "--" + subject + "시험결과--\n";
//
//		for (Entry res : this.result.entrySet()) {
//			print += Result.values()[(int) res.getKey()].kind + " :\t";
//			print += res.getValue() + "\n";
//		}
//
//		System.out.println(print);
//
//	}

}

class Eng extends Test {

	public Eng(String subject) {
		super(subject);
	}

}

class Math extends Test {

	public Math(String subject) {
		super(subject);
		// TODO Auto-generated constructor stub
	}

}

class StudentsData {

	ArrayList<Student> students;

	public StudentsData() {
		students = new ArrayList<Student>();
	}

	class Student { // 헉생정보

		String name, kind, gender;
		int ban, tot, avg, rank;
		TreeMap<String, Integer> result;

		public Student(int ban, String name) {
			super();
			this.ban = ban;
			this.name = name;
			this.result = new TreeMap<String, Integer>();
		}

//		void test(Test subject) { // 한과목씩 시험
//
//			result.put(subject.subject, subject.test());
//
//			System.out.println(result);
//		}
		void test(Test kor, Test mat) { // 한과목씩 시험

//			for (Test sub : subject) {
//
//				result.put(sub.subject, sub.test());
//			}

			int a = kor.test();
			int b = mat.test();

			System.out.println(result);
		}

	}
}

public class Main {

	public static void main(String[] args) {

//		★ 6월 19일
//		※※※※※!!!!!주말과제!!!!!※※※※※
//		- 국어문제 영어문제화면 왔다갔다하면서 풀 수 있게
//		- 국어풀다 영어문제가면 국어문제는 시간스탑 영어는 시간 시작 이런식으로 왔다갔다 풀 수 있게 만들기
//		- 문제를 못맞추면 다음으로 넘어갈수 없게
//		(문제 틀렸는지 맞았는지 체크)
//		- 도저히 못풀면 패스 - 패스는 P입력하면 넘어가게
//		- 맞춘문제 갯수, 틀린문제 갯수 출력
//		- 문제 푼 명수대로 과목별 점수 총점, 평균 출력하고 등수 오름차순으로 정렬

		Test kor = new Test("국어"); // 국어 과목 시험문제 작성

		kor.add("가 다음은 ?", "나");
		kor.add("나 다음은 ?", "다");
		kor.add("다 다음은 ?", "라");
		kor.add("마 다음은 ?", "바");
		kor.add("바 다음은 ?", "사");
		kor.add("사 다음은 ?", "아");
		kor.add("아 다음은 ?", "자");
		kor.add("자 다음은 ?", "차");
		kor.add("차 다음은 ?", "카");
		kor.add("카 다음은 ?", "타");

		Test mat = new Test("수학"); // 국어 과목 시험문제 작성

		mat.add("가 다음은 ?", "나");
		mat.add("나 다음은 ?", "다");
		mat.add("다 다음은 ?", "라");
		mat.add("마 다음은 ?", "바");
		mat.add("바 다음은 ?", "사");
		mat.add("사 다음은 ?", "아");
		mat.add("아 다음은 ?", "자");
		mat.add("자 다음은 ?", "차");
		mat.add("차 다음은 ?", "카");
		mat.add("카 다음은 ?", "타");

		kor.start();
		mat.start();

		StudentsData data = new StudentsData();

		StudentsData.Student hong = data.new Student(1, "홍길동");

//		hong.test(kor);
//		hong.test(mat);
//		hong.test(kor, mat);

	}

}
