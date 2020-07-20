package thread_p;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class MulTimer2 extends Thread {

	boolean chk = false;

	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println(i + " 초");
			try {
				sleep(1000); // 1초 마다 시간이 감
			} catch (Exception e) {
				e.getMessage();
			}
		}

		System.out.println("시간종료");
		chk = true;
	}

}

class MultiQuestion2 extends Thread {

	ArrayList<String> question;
	MulTimer timer;

	public MultiQuestion2() {
		question = new ArrayList<String>();
		timer = new MulTimer();
		init();
	}

	void init() {
		question.add("1 + 1 ?");
		question.add("선생님은 ? ");
		question.add("배고프면 ? ");
		question.add("yellow ? ");
		question.add("java ? ");
	}

	@Override
	public void run() {
		timer.start();
		for (String string : question) {
			String res = JOptionPane.showInputDialog(string);
			System.out.println(res);
			if (timer.chk) {
				timer.stop();
				stop();
			}
		}
		System.out.println("문제종료");
		timer.stop();
	}

}

public class MultiThreadMain2 {

	public static void main(String[] args) {

//		String str = JOptionPane.showInputDialog("쇼 끝은 없는");
//		System.out.println(str);
		// Scanner GUI

//		MulTimer timer = new MulTimer();

//		timer.start();

		MultiQuestion2 qq = new MultiQuestion2();

		qq.start();

	}

}
