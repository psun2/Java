package swing_p;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ExamMain3 extends JFrame {

	int x, y, width, height;
	ArrayList<JRadioButton> qq1;
	JLabel res;
	ArrayList<JCheckBox> qq2;
	JButton btn;
	Timer timer;
	JLabel time;
	int cnt = 0;

	public ExamMain3() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.width = 500;
		this.height = 600;
		this.x = (screen.width / 2) - (width / 2);
		this.y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Exam");
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		setLayout(null);
		setFont(new Font("굻림체", Font.ITALIC, 12));

		this.timer = new Timer();

		JPanel outer = new JPanel();
		outer.setBounds(0, 0, 400, 400);
		outer.setLayout(new GridLayout(1, 2));

		add(outer);

		JPanel inner1 = new JPanel();
		inner1.setLayout(new GridLayout(13, 1));
		JPanel inner2 = new JPanel();
		inner2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		outer.add(inner1);
		outer.add(inner2);

		inner1.add(new JLabel("1. 선생님은?"));

		qq1 = new ArrayList<JRadioButton>();

		ButtonGroup bg = new ButtonGroup();
		for (String str : "감찍해요,귀여워요,예뻐요,멋져요".split(",")) {
			JRadioButton bb = new JRadioButton(str);
			inner1.add(bb);
			qq1.add(bb);
			bg.add(bb);
		}

		inner1.add(new JLabel("2. 학생은?"));

		qq2 = new ArrayList<JCheckBox>();

		for (String str : "열심히,부지런히,끊임없이,계속".split(",")) {
			JCheckBox bb = new JCheckBox(str);
			inner1.add(bb);
			qq2.add(bb);
		}

		btn = new JButton("제출");
		btn.setBounds(0, 400, 500, 100);

		add(btn);

		add(res = new JLabel("결과"));
		res.setBounds(0, 500, 500, 100);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (qq1.get(1).isSelected()) // 선택 되었느냐....
					cnt += 20;

				for (JCheckBox box : qq2) {
					if (box.isSelected())
						cnt += 20;
				}

				res.setText("결과 : " + cnt);

				ExamMain3.this.timer.chkTimer = false;
				ExamMain3.this.btn.setEnabled(false);
			}
		});

		timer.start();
		inner2.add(time = new JLabel("Timer : start"));
		setVisible(true);
	}

	class Timer extends Thread {

		int max = 20;
		int second = 0;
		boolean chkTimer = true;

		@Override
		public void run() {

			for (int i = 0; i < max; i++) {
				if (chkTimer) {
					try {
						sleep(1000);
						second++;
						ExamMain3.this.time.setText("Timer : " + second);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			res.setText("결과 : " + cnt);
			chkTimer = false;
			if (!chkTimer) {
//				ExamMain.this.time.setText("시간종료");
				ExamMain3.this.btn.setEnabled(false);
			}

		}

	}

	public static void main(String[] args) {

		new ExamMain3();

	}

}
