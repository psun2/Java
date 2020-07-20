package swing_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Exam2Main extends JFrame {

	ArrayList<JRadioButton> qq1;
	ArrayList<JCheckBox> qq2;
	JLabel res, timerLb;
	boolean chk = false;
	JButton btn;

	class Timer extends Thread {
		@Override
		public void run() {
			for (int i = 20; i > 0; i--) {
				try {
					sleep(1000);
					if (chk)
						break;
					timerLb.setText("남은시간 : " + i + "   ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			calc();
		}
	}

	void calc() {

		chk = true;
		btn.setEnabled(false);

		int cnt = 0;

		if (qq1.get(1).isSelected())
			cnt += 20;

		for (JCheckBox box : qq2) {
			if (box.isSelected())
				cnt += 20;
		}

		res.setText("결과:" + cnt);
	}

	public Exam2Main() {
		int ox = -3200;
		setBounds(ox + 100, 50, 500, 600);
		setTitle("Exam");
		setLayout(new GridLayout(14, 1));
		timerLb = new JLabel("남은시간");
		timerLb.setHorizontalAlignment(JLabel.RIGHT);
		add(timerLb);

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer().start();
	}

	public static void main(String[] args) {
		new ExamMain();
	}

}
