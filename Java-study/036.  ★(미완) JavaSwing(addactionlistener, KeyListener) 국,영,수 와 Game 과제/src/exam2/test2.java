package exam2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class TestFrame extends JFrame {

	boolean chk = false;

	public TestFrame() {
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn = new JButton("종료 test");

		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		setVisible(true);
	}

	void exit() {

		dispose();

	}

}

// frame 가 shcool 을 가 져 간다면 ???

class Testaa {

	boolean chk = false;

	void start() {

		if (chk) {
			new TestFrame();
		} else
			System.out.println("test");

	}

}

public class test2 {

	public static void main(String[] args) {

		Testaa[] arr = { new Testaa(), new Testaa(), new Testaa() };
//		arr[0].chk = true;
//		arr[0].start();

		int start = 0;

		while (true) {

			arr[start].chk = true;
			arr[start].start();
			
			if(arr[start].chk)

		}



	}

}
