package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PaintMain extends JFrame {

	public PaintMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Paint(Canvas_그림판)");

		setVisible(true);

	}

	// 페인트메소드로 그린 것들은 객체가 아니므로 자리를 옴길 수 없습니다.

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		System.out.println("paint() 호출");

		g.setColor(Color.pink);
		g.setFont(new Font("엽서체", Font.BOLD, 30));
		g.drawString("그림을 그려요", 10, 100); // 해당좌푯에 그림을 그립니다.

		g.setColor(Color.green);
		g.fillRect(10, 110, 100, 40); // 사각형

		g.setColor(Color.blue);
		g.drawRect(30, 90, 100, 40); // 사각형

		g.setColor(Color.cyan);
		g.fillOval(10, 160, 50, 100); // 타원형
		g.drawRect(10, 160, 50, 100);

		g.setColor(Color.yellow);
		g.fillOval(70, 160, 50, 50); // 원
		g.drawRect(70, 160, 50, 50);

		g.setColor(Color.magenta);
		g.drawLine(10, 50, 100, 270); // 사선

		g.setColor(Color.ORANGE);
		// g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		// 사각형을 먼저 그리고 => arcWidth, arcHeight 의 값을 주어 사각형의 모서리를 깍아 내어
		// 라운드를 줍니다.
		g.fillRoundRect(50, 280, 200, 200, 100, 100);

		g.setColor(Color.gray);
		g.fillRoundRect(50, 280, 200, 200, 200, 200);

		g.setColor(Color.red);
		// g.drawArc(x, y, width, height, startAngle, arcAngle);
		g.drawArc(50, 500, 100, 100, 0, 90);

		g.setColor(Color.LIGHT_GRAY);
		g.fillArc(150, 500, 100, 100, 45, 90);

		g.setColor(Color.blue);
		g.drawPolygon(new int[] { 250, 300, 400, 270 }, new int[] { 430, 370, 520, 480 }, 4);
	}

	public static void main(String[] args) {
		new PaintMain();
	}

}
