package theory;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ExamMain extends JFrame {

	ArrayList<JRadioButton> qq1;
	ArrayList<JCheckBox> qq2;
	JLabel res, timerLb;
	boolean chk = false;
	JButton btn;

	public ExamMain() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Exam");
		setLayout(new GridLayout(14, 1));
		setFont(new Font("굻림체", Font.ITALIC, 12));

		timerLb = new JLabel("남은시간");
		add(timerLb);
		timerLb.setHorizontalAlignment(JLabel.RIGHT);

		add(new JLabel("1. 선생님은?"));

		qq1 = new ArrayList<JRadioButton>();
		ButtonGroup bg = new ButtonGroup();
		for (String str : "깜찍해요,귀여워요,예뻐요,멋져요".split(",")) {
			JRadioButton bb = new JRadioButton(str);
			add(bb);
			qq1.add(bb);
			bg.add(bb);
		}

		add(new JLabel("2. 학생은?"));

		qq2 = new ArrayList<JCheckBox>();

		for (String str : "열심히,부지런히,끊임없이,계속".split(",")) {
			JCheckBox bb = new JCheckBox(str);
			add(bb);
			qq2.add(bb);

		}

		btn = new JButton("제출");
		add(btn);
		add(res = new JLabel("결과"));

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calc();
			}
		});

		setVisible(true);

		new Timer().start();
	}

	void calc() {

		chk = true;

		btn.setEnabled(false);

		int cnt = 0;

		for (JCheckBox box : qq2) {
			if (box.isSelected())
				cnt += 20;
		}

		if (qq1.get(1).isSelected())
			cnt += 20;

		res.setText("결과:" + cnt);

	}

	class Timer extends Thread {

		int second = 20;

		@Override
		public void run() {

			while (second >= 0) {

				if (chk)
					break;

				timerLb.setText("남은시간 : " + second);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				second++;

			}
			calc();
		}

	}

	public static void main(String[] args) {
		new ExamMain();
	}

}
