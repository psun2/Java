package exam_Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

//부딪히면 아웃 
//적이 다섯개

public class Main_Stud extends JFrame implements KeyListener {

	JLabel me, you;
	JLabel[] yous = new JLabel[5];
	JFrame jf = new JFrame();
	int[] xtemp = { 1, 2, 3, 4, 5 };
	int[] ytemp = { 1, 2, 3, 4, 5 };
	boolean chk = false;
	int i = 0;

	public Main_Stud() {
		setBounds(100, 50, 500, 600);
		setTitle("피하기");
		setLayout(null);

		me = new JLabel();
		me.setBounds(250, 300, 50, 50);
		me.setBackground(new Color(255, 10, 110));
		me.setOpaque(true);
		add(me);

		jf.setBounds(250, 300, 400, 200);
		jf.setLayout(new GridLayout(2, 1));

		for (int i = 0; i < yous.length; i++) {
			int x = (int) (Math.random() * 450 + 1);
			int y = (int) (Math.random() * 550 + 1);
			yous[i] = new JLabel();
			yous[i].setBounds(x, y, 50, 50);
			yous[i].setBackground(new Color(0, 0, 0));
			yous[i].setOpaque(true);
			add(yous[i]);
		}

		setVisible(true);
		setResizable(false); // 화면 크기 변경을 못하게함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this); // this이므로 자기 자신을 받는다.
		new Timer().start();

	}

	class Timer extends Thread {
		@Override
		public void run() {
			while (true) {
				i++;
				try {
					sleep(33); // 1000/30 움직임을 부르럽게 하는 정도
					youMove();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (chk)
					break;
			}
			double time = ((double) ((int) ((i * 0.033) * 100)) / 100);

			jf.setVisible(true); // 미리 만들어 놓고 게임 오버 되었을시 보이게 해줌
			JLabel jl = new JLabel("게임오버");
			JLabel jl2 = new JLabel("버틴시간 : " + time + "초");
			jl.setHorizontalAlignment(JLabel.CENTER);
			jl2.setHorizontalAlignment(JLabel.CENTER);
			jf.add(jl);
			jf.add(jl2);

		}
	}

	void youMove() {
		for (int i = 0; i < yous.length; i++) {

			int x = yous[i].getX() + xtemp[i];
			int y = yous[i].getY() + ytemp[i];

			if (x < 0) {
				if (x < 0)
					xtemp[i] = (int) (Math.random() * 10 + 5);
				xtemp[i] = xtemp[i];
			}

			if (x > 440) {
				xtemp[i] = (int) (Math.random() * 10 + 5);
				xtemp[i] = -xtemp[i];
			}
			if (y < 0) {
				ytemp[i] = (int) (Math.random() * 10 + 5);
				ytemp[i] = ytemp[i];
			}
			if (y > 515) {
				ytemp[i] = (int) (Math.random() * 10 + 5);
				ytemp[i] = -ytemp[i];
			}

//        if(x<0 || x>440) {
//            xtemp[i] =(int)(Math.random()*10+10);
//            xtemp[i] *= -1;
//        }
//        
//        if(y<0 || y>515) {
//            ytemp[i] =(int)(Math.random()*10+10);
//            ytemp[i] *= -1;
//        }

			yous[i].setLocation(x, y);
//        if( ( x+30> me.getX() &&  x-30<me.getX() )  && ( y-30<me.getY() && y+30>me.getY() ) ) {
////            System.out.println(i+"겹침 :me.x : "+me.getX()+"me.y : "+me.getY()+"몹 좌표 x : "+x+"y : "+y);
////            System.out.println("겹침");
//            chk=true;
//        }
			if (((x < me.getX() && me.getX() < x + 50) || (x < (me.getX() + 50) && (me.getX() + 50) < x + 50))
					&& ((y < me.getY() && me.getY() < y + 50) || (y < (me.getY() + 50) && (me.getY() + 50) < y + 50))) {
				chk = true;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 입력하는 key를 받음, 방향키를 눌러도 찍힘
		int key = e.getKeyCode();
		// 어떤 키를 누르던지 int로 들어간다...
		// VK는 가상의 키 라는 뜻이다.
		/*
		 * up down right left
		 * 
		 */

		int x = me.getX();
		int y = me.getY();

		switch (key) {
		case KeyEvent.VK_UP: // 방향키 up
			System.out.println("위");
			y -= 10;
			break;
		case KeyEvent.VK_DOWN: // 방향키 up
			System.out.println("아래");
			y += 10;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			x -= 10;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			x += 10;
			break;

		}
		if (x < 0)
			x = 0;

		if (x > 440) // 좌표의 기준이 왼쪽 상단이기 때문에!!
			x = 440;

		if (y < 0)
			y = 0;

		if (y > 515)
			y = 515;

		me.setLocation(x, y);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Main_Stud();
	}
}