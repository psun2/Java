package exam;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class MulQQ { // 한문제 한문제 정보를 다루는 클래스
	String question, answer; // 질답
	int jum, chk; // 점수 와 답이
	// chk = 문제를 냈을때 답이 맞으면 숫자가 올라감;
	MulTimer timer; // 타이머

	public MulQQ(String question, String answer, int jum, MulTimer timer) {
		super();
		this.question = question;
		this.answer = answer;
		this.jum = jum;
		this.timer = timer;
	}

	void go() {

		while (true) { // 답이 맞을때까지 무한 반복
			String res = JOptionPane.showInputDialog(question);
			if (timer.chk) // 타이머가 다 되어도 브레이크
				break;
			if (res.equals(answer)) { // 답이 맞으면 브레이크 chk 를 1로 바꾸고 브레이크
				chk = 1;
				break;
			} else if (res.equals("p")) { // p 면 브레이크
				break;
			}
		} // 오답시 무한 루프
	}

	int ppp() {
		int res = jum * chk; // 점수는 배점 * 맞았으면 1
		System.out.println(question + ": " + res + "(" + answer + ")"); // 문제 점수 (답변)
		return res;
	}
}

class MulTimer extends Thread {

	int cnt = 0; // 한문제당 10초로 가정 // cnt 즉 문제의 갯수를 받아옴
	boolean chk = false; // 타이머를 정지시키기 위한 도구

	@Override
	public void run() {

		for (int i = cnt * 10; i > 0; i--) {

			if (chk) // 시간안에 문제를 다 풀면 chk 가 true로 되면서 break;
				break;
			System.out.println(i);

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		chk = true;
		System.out.println("타이머 종료");

	}
}

class MultiQuestion extends Thread {

	MulTimer timer;

	ArrayList<MulQQ> qq;

	public MultiQuestion(MulTimer timer) {
		this.timer = timer;

		qq = new ArrayList<MulQQ>();
	}

	void add(String question, String answer, int jum) {
		qq.add(new MulQQ(question, answer, jum, timer)); // 한문제의 정보를 다루고 있는 클래스를
		// 문제집의 데이타에 저장
	}

	@Override
	public void run() {

		timer.cnt = qq.size(); // 타이머는 문제당 10 초 즉 문제가 있는 ArrayList 의 사이즈 만큼 가져가게 됨
		timer.start();
		for (MulQQ mq : qq) {

			mq.go();

			if (timer.chk) // 타이머가 종료되면 문제도 멈춤
				break;

		}

		timer.chk = true; // 문제가 다 끝나면 타이머를 종료 시키는 버튼
		System.out.println("문제 종료");
		ppp();

	}

	void ppp() { // 문제가 끝나면 정산 부분
		int tot = 0;

		for (MulQQ mulQQ : qq) {
			tot += mulQQ.ppp(); // 문제를 돌면서 문제 안에서 반환하는 점수들을 tot 에 더한다
		} // 문제 마다 배점이 다를 수 가 있다 !!!!!

		System.out.println("합계:" + tot);
	}
}

public class ThreadExamMain {

	public static void main(String[] args) {

		MulTimer timer = new MulTimer();
		MultiQuestion qq = new MultiQuestion(timer);

		qq.add("성혁이 생일은?", "4월9일", 10);
		qq.add("지우의 파트너 포켓몬은?", "피카츄", 20);
		qq.add("성혁이네 푸들 이름은?", "까망", 10);
		qq.add("까망이가 제일 좋아하는 음식은?", "까망베르치즈", 20);
		qq.add("까망이가 제일 무서워하는 것은?", "청소기", 15);
		qq.add("성혁이네 제니는 무슨 종일까?", "잭러셀테리어", 15);
		qq.add("제니가 먹을 걸 달라고 하는 행동은?", "박수", 10);

		qq.start();

	}

}