package exam;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JOptionPane;

class Timer extends Thread {

	int timeRequired; // 경과 시간
	boolean chk; // timer 종료

	public Timer() {
		chk = true;
	}

	@Override
	public void run() {

		timeRequired = 0;

		while (chk) {

			if (timeRequired != 0)
				System.out.println(timeRequired + " 초 경과");

			if (!chk)
				break;

			try {
				Thread.sleep(1000);
				timeRequired++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

enum Result {

	Jumsu("점수"), Pass("틀린갯수"), TimeRequired("소요시간");

	String kind;

	Result(String kind) {
		this.kind = kind;
	}

}

class Test {

	String subject; // 시험 과목 이름
	ArrayList<String> question; // 질문
	ArrayList<String> answer; // 답
	ArrayList<Integer> keys; // enum
	Timer timer; // 시간 측정 Timer 클래스
	int timeRequired, manjum; // 소요시간과 만점
	TreeMap<Integer, String> result; // 결과 => 점수, 틀린갯수, 소요시간

	public Test(String subject) {
		super();
		this.subject = subject;
		this.question = new ArrayList<String>();
		this.answer = new ArrayList<String>();
		this.result = new TreeMap<Integer, String>();
	}

	void add(String question, String answer) { // 질문과 답을 받아 각각의 ArrayList 에 저장
		this.question.add(question);
		this.answer.add(answer);
	}

	void test() { // 시험 시작

		System.out.println(subject + "시험 시작");
		timer = new Timer(); // 타이머 생성
		timer.start(); // 타이머 스타트

		int cnt = 0;
		for (String q : question) {

			int index = question.indexOf(q);

			while (true) {

				String result = JOptionPane.showInputDialog(q);

				System.out.println(result);

				try {
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
				} catch (Exception e) {

				}

			}

		}

		timer.chk = false; // 타이머를 종료하기 위해 바꾸어줌
		this.timeRequired = timer.timeRequired; // 소요시간 교체
//		System.out.println("timer에서 반환된 시험 시간 : " + timeRequired);
		System.out.println(subject + "시험 끝");
		mapPut(cnt); // 결과 저장
	}

	void mapPut(int cnt) {

		this.keys = new ArrayList<Integer>();
		keys.add(Result.Jumsu.ordinal());
		keys.add(Result.Pass.ordinal());
		keys.add(Result.TimeRequired.ordinal());

		this.manjum = 10;
		String[] result = { cnt * 10 + "점", manjum - cnt + "개", timeRequired + "초" };

		for (int i = 0; i < keys.size(); i++) {
			this.result.put(keys.get(i), result[i]);
		}

		result();

	}

	void result() {

		String print = "--" + subject + "시험결과--\n";

		for (Entry res : this.result.entrySet()) {
			print += Result.values()[(int) res.getKey()].kind + " :\t";
			print += res.getValue() + "\n";
		}

		System.out.println(print);

	}

}

public class Main {

	public static void main(String[] args) {

//		* 과제 2> 문제풀기화면 만들기   
//		- 문제 틀렸는지 맞았는지 체크
//		- 문제를 못맞추면 다음으로 넘어갈수 없게
//		- 도저히 못풀면 패스 - 패스는 P입력하면 넘어가게
//		- 맞춘문제 갯수, 틀린문제 갯수 출력

		Test test = new Test("국어");

		test.add("가 다음은 ?", "나");
		test.add("나 다음은 ?", "다");
		test.add("다 다음은 ?", "라");
		test.add("마 다음은 ?", "바");
		test.add("바 다음은 ?", "사");
		test.add("사 다음은 ?", "아");
		test.add("아 다음은 ?", "자");
		test.add("자 다음은 ?", "차");
		test.add("차 다음은 ?", "카");
		test.add("카 다음은 ?", "타");

		// 총 10문제 1개당 10점
		test.test();

//		국어시험 시작
//		1  초 경과
//		2  초 경과
//		3  초 경과
//		4  초 경과
//		5  초 경과
//		6  초 경과
//		입력 : rk	틀림
//		입력 : r	틀림
//		7  초 경과
//		입력 : 가	틀림
//		8  초 경과
//		입력 : 나	정답
//		9  초 경과
//		입력 : 다	정답
//		10  초 경과
//		입력 : 라마	틀림
//		11  초 경과
//		입력 : 라	정답
//		12  초 경과
//		입력 : 나	틀림
//		13  초 경과
//		입력 : ak	틀림
//		14  초 경과
//		입력 : ㅔ	틀림
//		15  초 경과
//		입력 : p	패스
//		16  초 경과
//		입력 : 사	정답
//		입력 : 아	정답
//		17  초 경과
//		18  초 경과
//		입력 : 자	정답
//		입력 : 차	정답
//		19  초 경과
//		입력 : p	패스
//		입력 : p	패스
//		--국어시험결과--
//		점수 :	70점
//		틀린갯수 :	3개
//		소요시간 :	19초

//===========================================================================================================================

//		 ✔ program
//		사전적 의미: 진행 계획이나 순서. 또는, 그 목록. 순화어는 `계획(표)', `차례(표)', 
//		어떤 문제를 해결하기 위해 컴퓨터에게 주어지는 처리 방법과 순서를 기술한 일련의 명령문의 집합체. 
//		: 프로그램은 명령어의 집합
//
//		✔ process
//		사전적 의미 : 방법, 진행, 돌기
//		프로세스(process)는 컴퓨터에서 연속적으로 실행되고 있는 컴퓨터 프로그램을 말한다. 종종 스케줄링의 대상이 되는 작업(task)이라는 용어와 거의 같은 의미로 쓰인다. 여러 개의 프로세서를 사용하는 것을 멀티프로세싱이라고 하며 같은 시간에 여러 개의 프로그램을 띄우는 시분할 방식을 멀티태스킹이라고 한다. 프로세스 관리는 운영 체제의 중요한 부분이 되었다.
//		: 현재 실행중인 program(명령어의 집합) 들.....
//
//		✔ procedure
//		사전적 의미 : 순서, 진행상의 순서
//		일련의 쿼리를 마치 하나의 함수처럼 실행하기 위한 쿼리의 집합이다.
//		쿼리(Query) => 쿼리란 데이터베이스에 정보를 요청하는 것이다.
//
//		✔ progress 
//		사전적 의미 : 진행, 진보, 경과
//		: 진행정도를 나타내는.....
//
//		✔ proceed
//		사전적 의미 : 진행하다, 나아가다
//		Spring 의 AOP 에서 사용 되는... 메소드...코드의 진행을 제어(?)
//		import org.aspectj.lang.ProceedingJoinPoint;

	}

}
