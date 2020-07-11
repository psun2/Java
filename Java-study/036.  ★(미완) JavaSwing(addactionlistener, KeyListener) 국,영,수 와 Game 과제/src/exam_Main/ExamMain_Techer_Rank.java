package exam_Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Quiz2 {
	JLabel qq;
	ArrayList<JRadioButton> bogi;
	String type, strDab;
	int intAns, intDab;
	JTextField strAns;
	Subject2 subject;

	void end() {
		if (type.equals("주관식"))
			strAns.setEditable(false);
		else {
			for (JRadioButton jr : bogi) {
				jr.setEnabled(false);
			}
		}
	}

	public Quiz2(Subject2 subject, String qq, int intDab, String... bogi) {
		super();
		this.subject = subject;
		this.qq = new JLabel(qq);
		this.intDab = intDab;
		this.bogi = new ArrayList<JRadioButton>();

		ButtonGroup bg = new ButtonGroup();
		for (String string : bogi) {
			JRadioButton btn = new JRadioButton(string);
			this.bogi.add(btn);
			bg.add(btn);
		}

		type = "객관식";
	}

	public Quiz2(Subject2 subject, String qq, String strDab) {
		super();
		this.subject = subject;
		this.qq = new JLabel(qq);
		this.strDab = strDab;
		strAns = new JTextField();
		type = "주관식";
	}

	int result() {
		int res = 0;
		if (type.equals("주관식") && strAns.getText().equals(strDab))
			res = 1;
		else if (type.equals("객관식") && bogi.get(intDab).isSelected()) {
			res = 1;
		}
		return res;
	}

	void ppp() {
		subject.add(qq);

		if (type.equals("주관식"))
			subject.add(strAns);
		else {
			for (JRadioButton jr : bogi) {
				subject.add(jr);
			}

		}

	}

}

class Subject2 extends JPanel {

	Stud2 st;
	ArrayList<Quiz2> quizs;

	JLabel res, timerLb;
	boolean chk = false, go = false;
	JButton btn;
	int jumsu;

	class Timer extends Thread {
		@Override
		public void run() {
			int i = 10;
			while (i > 0) {
				try {
					sleep(1000);
					if (chk)
						break;

					if (go) {
						i--;
					}
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

		for (Quiz2 quiz : quizs) {
			quiz.end();
			cnt += quiz.result();
		}

		jumsu = 100 * cnt / quizs.size();

		st.finalGo();
	}

	public Subject2(Stud2 st, String name) {
		setName(name);
		this.st = st;
		quizs = new ArrayList<Quiz2>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	}

	void starttt() {
		timerLb = new JLabel("남은시간");
		timerLb.setHorizontalAlignment(JLabel.RIGHT);
		add(timerLb);

		for (Quiz2 quiz : quizs) {
			quiz.ppp();
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

		new Timer().start();
	}

}

class Stud2 extends JFrame {

	ExamMain_Techer_Rank exam;
	ArrayList<Subject2> subjects;
	int num, rank, tot, avg;

	public Stud2(int num, ExamMain_Techer_Rank exam, String name) {

		this.exam = exam;
		this.num = num;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 500, 600);
		setName(name);
		setTitle(name);

		subjects = new ArrayList<Subject2>();
		JTabbedPane tab = new JTabbedPane();

		subjects.add(new Subject2(this, "국어"));
		subjects.add(new Subject2(this, "영어"));
		subjects.add(new Subject2(this, "수학"));

		subjects.get(0).quizs.add(new Quiz2(subjects.get(0), "1.가 다음은?", "나"));
		subjects.get(0).quizs.add(new Quiz2(subjects.get(0), "2.나 다음은?", "다"));
		subjects.get(0).quizs
				.add(new Quiz2(subjects.get(0), "1.달면 삼키고 쓰면 뱉는다는 뜻을 가진 사자성어는 ?", 0, "감탄고토", "거두절미", "갑남을녀", "각골난망"));
		subjects.get(0).quizs.add(
				new Quiz2(subjects.get(0), "4.물건을 보면 갖고 싶은 욕심이 생김을 이르는 사자성어는 ?", 2, "경거망동", "건곤일척", "견물생심", "견강부회"));

		subjects.get(1).quizs.add(new Quiz2(subjects.get(1), "1.a 다음은?", "b"));
		subjects.get(1).quizs.add(new Quiz2(subjects.get(1), "2.배는 영어로 ?", 0, "pear", "peak", "pean", "peal"));
		subjects.get(1).quizs.add(new Quiz2(subjects.get(1), "3.b 다음은?", "c"));
		subjects.get(1).quizs
				.add(new Quiz2(subjects.get(1), "4.멜론은 영어로 ?", 3, "watermelon", "melong,", "mello", "melon"));
		subjects.get(1).quizs.add(new Quiz2(subjects.get(1), "5.c 다음은?", "d"));

		subjects.get(2).quizs.add(new Quiz2(subjects.get(2), "1. 3+3= ?", 1, "4", "6", "8", "10"));
		subjects.get(2).quizs.add(new Quiz2(subjects.get(2), "2. 1+1=?", "2"));
		subjects.get(2).quizs.add(new Quiz2(subjects.get(2), "3. 2+1=?", "3"));
		subjects.get(2).quizs.add(new Quiz2(subjects.get(2), "4. 5+5= ?", 0, "10", "20", "30", "40"));

		for (Subject2 sub : subjects) {
			tab.addTab(sub.getName(), sub);
			sub.starttt();
		}

		add(tab);

		tab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				for (Subject2 subject : subjects) {
					subject.go = false;
				}

				System.out.println(tab.getSelectedIndex());
				subjects.get(tab.getSelectedIndex()).go = true;
			}
		});

	}

	void rankCalc() {
		rank = 1;

		for (Stud2 you : exam.studs) {
			if (avg < you.avg) {
				rank++;
			}
		}
	}

	void starttt() {
		setVisible(true);
		subjects.get(0).go = true;
	}

	void finalGo() {

		String str = "시험완료:";

		tot = 0;

		for (Subject2 sub : subjects) {
			if (!sub.chk)
				return;
			str += sub.getName() + "(" + sub.jumsu + ")";
			tot += sub.jumsu;
		}
		avg = tot / subjects.size();

		System.out.println(str);
		// setVisible(false);
		dispose(); // window 종료

		if (exam.studs.size() > num + 1)
			exam.studs.get(num + 1).starttt();
		else {
			exam.end();
		}
	}

	Vector result() {
		Vector res = new Vector();

		res.add(getName());

		for (Subject2 sub : subjects) {
			res.add(sub.jumsu);

		}
		res.add(tot);
		res.add(avg);
		res.add("가가가가가가양미우수수".charAt(avg / 10));
		res.add(rank);
		return res;
	}
}

public class ExamMain_Techer_Rank extends JFrame {

	ArrayList<Stud2> studs;

	public ExamMain_Techer_Rank() {
		studs = new ArrayList<Stud2>();
		studs.add(new Stud2(0, this, "지민이너"));
		studs.add(new Stud2(1, this, "홍성혁이"));
		studs.add(new Stud2(2, this, "핸준아"));

		studs.get(0).starttt();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void end() {

		setBounds(100, 50, 500, 600);
		setName("결과");

		Vector<Vector> rowData = new Vector<Vector>();

		for (Stud2 st : studs) {
			st.rankCalc();
			rowData.add(st.result());
		}

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("이름");
		columnNames.add("국어");
		columnNames.add("영어");
		columnNames.add("수학");
		columnNames.add("총점");
		columnNames.add("평균");
		columnNames.add("등급");
		columnNames.add("등수");

		JTable table = new JTable(rowData, columnNames);

		JScrollPane js = new JScrollPane(table);

		add(new JLabel("결과"), "North");
		add(js, "Center");

		setVisible(true);
	}

	public static void main(String[] args) {
		new ExamMain_Techer_Rank();
	}

}