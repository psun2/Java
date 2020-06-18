package theory;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class Timer extends Thread {

	boolean chk = false;

	@Override
	public void run() {

		for (int i = 20; i > 0; i--) {

			if (chk)
				break;

			System.out.println(i + " 초");

			try {
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		chk = true;

	}

}

class Question extends Thread {

	ArrayList<String> question;

	Timer timer;

	public Question(Timer timer) {

		this.timer = timer;

		question = new ArrayList<String>();

		question.add("1+1 ?");
		question.add("선생님은 ?");
		question.add("배고프면 ?");
		question.add("yellow ?");
		question.add("java ?");

	}

	@Override
	public void run() {

		timer.start();

		for (String q : question) {

			String result = JOptionPane.showInputDialog(q);

			if (timer.chk)
				break;

			System.out.println(result);

		}

		timer.chk = true;
		System.out.println("문제 종료");

	}

}

public class MultiThreadMain {

	public static void main(String[] args) {

		Timer timer = new Timer();

		Question question = new Question(timer);
//		timer.start();
		question.start();

	}

}
