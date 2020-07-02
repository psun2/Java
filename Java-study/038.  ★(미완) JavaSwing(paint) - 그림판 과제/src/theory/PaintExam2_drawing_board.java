package theory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Paint extends JFrame {

	Board board;
	Colrs color;

	public Paint() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PaintExam2_drawing_board");
		setLayout(new BorderLayout());

		this.board = new Board();
		add(board, "Center");

		this.color = new Colrs();
		add(color, "South");

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g); // super 로 board 위에 frame 이 칠해져
		// board 의 색이 나타나지 않는 현상을 super을 막음으로서 해결 완료

		board.repaint();
		color.repaint();
	}

	class Board extends JPanel implements MouseMotionListener {

		int x, y, width, height;
		boolean chk;

		public Board() {
			// TODO Auto-generated constructor stub

			// setBackground(Color.white); // super.paint(g); 를 닫는 순간 먹히지 않습니다.

			this.chk = true;
			this.x = -10; // 점의 시작위치
			this.y = -10; // 점의 시작위치
			this.width = 10; // 굵기를 표현
			this.height = 10; // 굵기를 표현

			addMouseMotionListener(this);

		}

		@Override
		public void paint(Graphics g) { // 문제점 !! 버튼이 계속 뜹니다 ㅠㅠ 버그라고 합니다. ㅜㅜ
			// TODO Auto-generated method stub
			// super.paint(g); // => 계속계속 painting이 되면서 배경을 갈아 엎어 주석 처리또는 삭제해야
			// 화면에 그려집니다.

			System.out.println(Paint.this.getHeight());
			System.out.println(this.getHeight());
			System.out.println(Paint.this.color.getHeight());
			System.out.println(this.getX());
			if (chk) {
				System.out.println("진입");
				g.setColor(Color.white);
				g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
				this.chk = false;
			}
			g.setColor(Paint.this.color.current);
			g.fillOval(x - (width / 2), y - (height / 2), width, height);

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			// x = e.getX();
			// y = e.getY();
		}

	}

	class Colrs extends JPanel {

		Color current;

		public Colrs() {

			this.current = Color.black;

			Color[] colors = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(0, 0, 99),
					new Color(155, 77, 203), Color.cyan };

			ArrayList<JButton> btns = new ArrayList<JButton>();

			for (Color color : colors) {
				JButton btn = new JButton();
				btn.setBackground(color);
				btn.setPreferredSize(new Dimension(50, 50));
				add(btn);
				btns.add(btn);
			}

			for (JButton btn : btns) {
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton target = (JButton) e.getSource();
						current = target.getBackground();
					}
				});
			}

		}

	}

}

public class PaintExam2_drawing_board {

	public static void main(String[] args) {

		new Paint();

	}

}