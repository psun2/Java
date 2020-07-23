package mulGameTestDDongDataTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class 대기방 extends JFrame {

	final int width = 806;
	final int height = 679;
	boolean readyChk = false;

	JPanel meP, youP, state;
	MePuyoPanel me;

	public 대기방() {
		// TODO Auto-generated constructor stub

		System.out.println("Frame 시작 == 게임 시작");
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		meP = new JPanel();
		meP.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		meP.setBackground(Color.black);
		add(meP);

		state = new JPanel();
		state.setLayout(null);
		state.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 4, Puyo.PUYOSIZE * 13);
		state.setBackground(Color.red);
		add(state);

		System.out.println(state.getSize()); // 200 , 650

		JToggleButton ready = new JToggleButton("준비");
		JButton exit = new JButton("나가기");

		// 버튼 간격 50;
		// 버튼 총 길이 130;
		// 남은 길이 520;
		// 520 - 130 / 2
		int btnSizeW = 100;
		int btnSizeH = 40;
		int x = (state.getSize().width - btnSizeW) / 2;
		// System.out.println(x);
		int gap = 50;
		int y = (state.getSize().height - (btnSizeH * 2)) / 2;
		// System.out.println(y);

		// ready.setBackground(null);
		ready.setBorderPainted(false);
		// ready.setBorder(null);

		exit.setBorderPainted(false);

		ready.setBounds(x, y, btnSizeW, btnSizeH);
		exit.setBounds(x, y + gap, btnSizeW, btnSizeH);

		ready.addActionListener(new ReadyBtn());
		exit.addActionListener(new ExitBtn());

		state.add(ready);
		state.add(exit);

		youP = new JPanel();
		youP.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		youP.setBackground(Color.green);
		add(youP);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	class ReadyBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JToggleButton temp = (JToggleButton) e.getSource();

			if (readyChk) {
				temp.setText("준비");
				readyChk = false;
			} else {
				temp.setText("준비 완료");
				readyChk = true;
				// 내가 준비일때 상대방의 통신상태를 받아
				// 게임 이시작 됨....
				// me 패널과 you 패널을 리무브

				temp.setEnabled(false);
				대기방.this.remove(meP);
				대기방.this.remove(youP);

				me = new MePuyoPanel();
				me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
				
				
				대기방.this.add(me);

				
				
				//System.out.println(Arrays.toString(대기방.this.getKeyListeners()));
				YouPuyoPanel you = new YouPuyoPanel();
				you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
				대기방.this.add(you);

				대기방.this.setVisible(false);
				대기방.this.setVisible(true);

				대기방.this.setFocusable(true);
				대기방.this.addKeyListener(new ActionKey(me));
				// 게임이 끝나며ㅑㄴ 원상태로 돌리기
				
			}

		}

	}

	class ExitBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			대기방.this.dispose();

		}

	}

	public static void main(String[] args) {
		new 대기방();
	}

}
