package thread_p;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class MulQQ {
	String question, answer;
	int jum, chk;
	MulTimer3 timer;

	public MulQQ(String question, String answer, int jum, MulTimer3 timer) {
		super();
		this.question = question;
		this.answer = answer;
		this.jum = jum;
		this.timer = timer;
	}

	void go() {
		while (true) {
			String res = JOptionPane.showInputDialog(question);
			if (timer.chk)
				break;
			if (res.equals(answer)) {
				chk = 1;
				break;
			} else if (res.equals("p")) {
				break;
			}
		}
	}

	int ppp() {
		int res = jum * chk;
		System.out.println(question + ": " + res + "(" + answer + ")");
		return res;
	}
}

class MulTimer3 extends Thread {

	int cnt = 0;
	boolean chk = false;

	@Override
	public void run() {
		for (int i = cnt * 10; i > 0; i--) {

			if (chk)
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

class MultiQuestion3 extends Thread {

	MulTimer3 timer;

	ArrayList<MulQQ> qq;

	public MultiQuestion3(MulTimer3 timer) {
		this.timer = timer;

		qq = new ArrayList<MulQQ>();
	}

	void add(String question, String answer, int jum) {
		qq.add(new MulQQ(question, answer, jum, timer));
	}

	@Override
	public void run() {

		timer.cnt = qq.size();
		timer.start();
		for (MulQQ mq : qq) {

			mq.go();

			if (timer.chk)
				break;

		}
		timer.chk = true;
		System.out.println("문제 종료");
		ppp();
	}

	void ppp() {
		int tot = 0;
		for (MulQQ mulQQ : qq) {
			tot += mulQQ.ppp();
		}
		System.out.println("합계:" + tot);
	}
}

public class ThreadExamMain {

	public static void main(String[] args) {
		// String str = JOptionPane.showInputDialog("쇼 끝은없는");
		// System.out.println(str);

		MulTimer3 timer = new MulTimer3();
		MultiQuestion3 qq = new MultiQuestion3(timer);

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
