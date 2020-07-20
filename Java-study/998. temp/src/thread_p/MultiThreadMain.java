package thread_p;

import java.util.ArrayList;

import javax.swing.JOptionPane;

class MulTimer extends Thread {

	boolean chk = false;
//	MultiQuestion qq;

//	public MulTimer() {
//		this.qq = new MultiQuestion(this);
//		qq.start();
//	}

	@Override
	public void run() {
		for (int i = 20; i > 0; i--) {
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

class MultiQuestion extends Thread {

	boolean chk = false;

	MulTimer timer;

	ArrayList<String> qq;

	public MultiQuestion(MulTimer timer) {
		this.timer = timer;

		qq = new ArrayList<String>();

		qq.add("1+1 ?");
		qq.add("선생님은 ?");
		qq.add("배고프면 ?");
		qq.add("yellow ?");
		qq.add("java ?");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (String str : qq) {
			String res = JOptionPane.showInputDialog(str);
			if (timer.chk)
				break;
			System.out.println(res);
		}

		System.out.println("문제 종료");
		chk = true;
	}
}

public class MultiThreadMain {

	public static void main(String[] args) {

		// String str = JOptionPane.showInputDialog("쇼 끝은없는");
		// System.out.println(str);

		MulTimer timer = new MulTimer();
		MultiQuestion qq = new MultiQuestion(timer);
		timer.start();
		qq.start();

	}

}
