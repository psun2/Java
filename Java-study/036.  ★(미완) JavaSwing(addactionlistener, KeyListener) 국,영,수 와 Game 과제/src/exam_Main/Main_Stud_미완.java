package exam_Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class Timer extends Thread {
	boolean timer_chk = false;
	boolean kor_chk = false;
	boolean eng_chk = false;
	boolean math_chk = false;
	boolean testoff = false;

	int kcnt = 0;
	int ecnt = 0;
	int mcnt = 0;

	int i = 5;
	int j = 60;
	int k = 60;
	JLabel jp = new JLabel();
	JButton korbtn = new JButton("국어");
	JButton engbtn = new JButton("영어");
	JButton mathbtn = new JButton("수학");
	JButton btn = new JButton("제출하기");

	public Timer() {

	}

	@Override
	public void run() {
		while (true) {
			try {
				if (testoff == true)
					break;

				if (kor_chk == true) { // 국어시험을 푸는도중
					i--;
					jp.setText("국어 시간가는중" + i);
				}
				if (i == 0) {
					kor_chk = false;

				}

				if (eng_chk == true) {
					j--;
					jp.setText("영어 시간가는중" + j);
				}

				if (j == 0) {
					eng_chk = false;

				}

				if (math_chk == true) {
					k--;
					jp.setText("수학 시간가는중" + k);
				}

				if (k == 0) {
					math_chk = false;
				}

				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (i == 0) {
				btn.setEnabled(false);
				korbtn.setEnabled(false);
				jp.setText("제한 시간 종료");
			}
			if (kor_chk == false) {
				btn.setEnabled(true);
			}

			if (j == 0) {
				btn.setEnabled(false);
				engbtn.setEnabled(false);
				jp.setText("제한 시간 종료");
			}

			if (eng_chk == false) {
				btn.setEnabled(true);
			}

			if (k == 0) {

				btn.setEnabled(false);
				mathbtn.setEnabled(false);
				jp.setText("제한 시간 종료");
			}

			if (math_chk == false) {
				btn.setEnabled(true);
			}

		}

	}

}

public class Main_Stud_미완 extends JFrame {

	ArrayList<JRadioButton> qq, qq2, qq3;
	ArrayList<JRadioButton> engqq, engqq2, engqq3;
	ArrayList<JRadioButton> mathqq, mathqq2, mathqq3;
	JPanel jp = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	String str1, str2, str3 = "";

	public static final int cnt = 0;

	JLabel sub = new JLabel();
	JLabel e1 = new JLabel();
	JLabel e2 = new JLabel();
	JLabel e3 = new JLabel();

	JRadioButton ex1 = new JRadioButton();
	JRadioButton ex2 = new JRadioButton();
	JRadioButton ex3 = new JRadioButton();
	boolean koroff = false;
	boolean engoff = false;
	boolean methoff = false;
	JLabel lb = new JLabel();
	JLabel lb2 = new JLabel();
	Timer t;

	public Main_Stud_미완(Timer t) {
		this.t = t;
		setBounds(0, 0, 400, 650);
		setTitle("Exam");
		setLayout(new GridLayout(14, 2));

		jp.setLayout(new FlowLayout());
		jp2.setLayout(new FlowLayout());
		jp3.setLayout(new FlowLayout());

		t.jp.setHorizontalAlignment(JLabel.RIGHT);
		add(t.jp);

		JPanel btnp = new JPanel();
		btnp.setLayout(new GridLayout(1, 2));

		btnp.add(t.korbtn);
		btnp.add(t.engbtn);
		btnp.add(t.mathbtn);

		add(btnp);
		add(t.btn);

		if (t.timer_chk == true) {
			t.korbtn.setEnabled(false);
		}

		t.korbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (t.eng_chk == true || t.math_chk == true) {
					jp.removeAll();
					jp2.removeAll();
					jp3.removeAll();
				}

				if (t.kor_chk == false) {

					sub.setText("국어");
					add(sub);

					e1.setText("1.정확한 맞춤법은?");
					add(e1);

					qq = new ArrayList<JRadioButton>();
					ButtonGroup bg = new ButtonGroup();

					for (String str : "안되요,안돼요,안됭당께,안됨묘".split(",")) {
						ex1 = new JRadioButton(str);
						jp.add(ex1);
						bg.add(ex1);
						qq.add(ex1);

					}

					add(jp);

					e2.setText("2.다음중 비유법 아닌것은?");
					add(e2);

					qq2 = new ArrayList<JRadioButton>();
					ButtonGroup bg2 = new ButtonGroup();
					for (String str2 : "직유법,은유법,의인법,활유법".split(",")) {
						ex2 = new JRadioButton(str2);
						jp2.add(ex2);
						bg2.add(ex2);
						qq2.add(ex2);

					}

					add(jp2);

					e3.setText("3.대한민국 시인이 아닌사람은?");
					add(e3);
					qq3 = new ArrayList<JRadioButton>();
					ButtonGroup bg3 = new ButtonGroup();
					for (String str3 : "박목월,김수영,정지용,데카르트".split(",")) {
						ex3 = new JRadioButton(str3);
						jp3.add(ex3);
						bg3.add(ex3);
						qq3.add(ex3);

					}
				}
				add(jp3);
				lb.setText("결과:");
				add(lb);

				t.kor_chk = true; // 국어 시험중일때
				t.eng_chk = false;

			}

		});

		t.engbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setLayout(new GridLayout(14, 2));
				if (t.kor_chk == true || t.math_chk == true) {

					jp.removeAll();
					jp2.removeAll();
					jp3.removeAll();
				}
				if (t.eng_chk == false) {

					sub.setText("영어");
					add(sub);

					t.btn.setEnabled(true);

					e1.setText("1.의미가 다른것은?");
					add(e1);

					engqq = new ArrayList<JRadioButton>();
					ButtonGroup bg = new ButtonGroup();

					for (String str4 : "Good,veryGood,Niceshot,Goway".split(",")) {
						ex1 = new JRadioButton(str4);
						jp.add(ex1);
						bg.add(ex1);
						engqq.add(ex1);

					}

					add(jp);

					e2.setText("2.번역한 결과가 다른것은?");
					add(e2);

					engqq2 = new ArrayList<JRadioButton>();
					ButtonGroup bg2 = new ButtonGroup();
					int cnt = 0;
					for (String str5 : "love 사랑,life 삶,tea 차,pang 빵".split(",")) {
						JRadioButton bb5 = new JRadioButton(str5);
						jp2.add(bb5);
						bg2.add(bb5);
						engqq2.add(bb5);

					}

					add(jp2);

					e3.setText("3.영어 빈도중 사용 빈도가  가장 낮은것은 ? ");
					add(e3);
					engqq3 = new ArrayList<JRadioButton>();
					ButtonGroup bg3 = new ButtonGroup();
					for (String str6 : "always, usually, somtimes, never".split(",")) {
						JRadioButton bb6 = new JRadioButton(str6);
						jp3.add(bb6);
						bg3.add(bb6);
						engqq3.add(bb6);

					}

					add(jp3);
				}

				lb.setText("결과:");
				add(lb);

				t.eng_chk = true; // 영어 시험중일떄
				t.kor_chk = false;// 다시 시험치로갈떄
				t.math_chk = false;

			}

		});

		t.mathbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (t.eng_chk == true || t.kor_chk == true) {
					jp.removeAll();
					jp2.removeAll();
					jp3.removeAll();
				}

				if (t.math_chk == false) {
					sub.setText("수학");
					add(sub);

					e1.setText("1.우선순위가 가장 높은것은?");
					add(e1);

					mathqq = new ArrayList<JRadioButton>();
					ButtonGroup bg = new ButtonGroup();
					for (String str4 : "+(플러스),-(마이너스),*(곱하기),/".split(",")) {
						JRadioButton bb4 = new JRadioButton(str4);
						jp.add(bb4);
						bg.add(bb4);
						mathqq.add(bb4);
					}
					add(jp);

					e2.setText("2.덧샘 결과가 다른것은?");
					add(e2);
					mathqq2 = new ArrayList<JRadioButton>();
					ButtonGroup bg2 = new ButtonGroup();
					for (String str5 : "1+1=0,15+15=30,40+40=80,20+25=45".split(",")) {
						JRadioButton bb5 = new JRadioButton(str5);
						jp2.add(bb5);
						bg2.add(bb5);
						mathqq2.add(bb5);

					}

					add(jp2);

					e3.setText("3.곱셈의결과로 맞지않는것은? ");
					add(e3);
					mathqq3 = new ArrayList<JRadioButton>();
					ButtonGroup bg3 = new ButtonGroup();
					for (String str6 : "365*0=1 , 1*1=1, 2*9=18, 3*6=18".split(",")) {
						JRadioButton bb6 = new JRadioButton(str6);
						jp3.add(bb6);
						bg3.add(bb6);
						mathqq3.add(bb6);

					}

				}
				add(jp3);
				lb.setText("결과:");
				add(lb);

				t.math_chk = true; // 영어 시험중일떄
				t.kor_chk = false;// 다시 시험치로갈떄
				t.eng_chk = false;// 다시 시험치로갈떄

			}
		});

		t.btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (t.kor_chk == true) {
					if (t.timer_chk == false) {
						if (qq.get(2).isSelected()) {
							t.kcnt += 30;
						}
						if (qq2.get(3).isSelected()) {
							t.kcnt += 30;
						}

						if (qq3.get(3).isSelected()) {
							t.kcnt += 40;
						}
						lb.setText("국어시험 결과:" + t.kcnt);
						koroff = true;

					}
					if (koroff = true) { // 국어시험 결과가 나왔을때
						t.korbtn.setEnabled(false);
					}

				}

				if (t.eng_chk == true) {
					if (t.timer_chk == false) {
						if (engqq.get(2).isSelected()) {
							t.ecnt += 30;
						}
						if (engqq2.get(3).isSelected()) {
							t.ecnt += 30;
						}

						if (engqq3.get(3).isSelected()) {
							t.ecnt += 40;
						}
						lb.setText("영어 결과:" + t.ecnt);
						engoff = true;
					}
					if (engoff = true) { // 영어시험 결과가 나왔을때
						t.engbtn.setEnabled(false);
					}

				}

				if (t.math_chk == true) {
					if (t.timer_chk == false) {
						if (mathqq.get(3).isSelected()) {
							t.mcnt += 30;
						}
						if (mathqq2.get(0).isSelected()) {
							t.mcnt += 30;
						}

						if (mathqq3.get(0).isSelected()) {
							t.mcnt += 40;
						}
						lb.setText("수학 결과:" + t.ecnt);

						methoff = true;
					}

					if (methoff) // 수학시험 결과 나왔을때
						t.mathbtn.setEnabled(false);

				}

				if (koroff == true && methoff == true && engoff == true) {

					t.testoff = true;
					add(new JLabel("총점" + (t.kcnt + t.ecnt + t.mcnt)));
					add(new JLabel("평균" + (t.kcnt + t.ecnt + t.mcnt) / 3));

				}

			}

		});

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		Timer t = new Timer();
		Main_Stud_미완 j = new Main_Stud_미완(t);
		t.start();

	}

}