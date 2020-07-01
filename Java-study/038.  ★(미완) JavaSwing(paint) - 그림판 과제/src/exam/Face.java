package exam;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Face extends JFrame {

	public Face() {
		// TODO Auto-generated constructor stub
		setTitle("ㅁㅅ");
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		g.setColor(new Color(255, 229, 197));
		g.fillOval(200, 200, 100, 100);

		g.setColor(Color.black);

		g.fillArc(200, 200, 100, 100, 0, 180);

		g.drawArc(220, 250, 20, 20, 0, 180);
		g.drawArc(270, 250, 20, 20, 0, 180);

		g.fillOval(223, 255, 15, 15);
		g.fillOval(273, 255, 15, 15);

		g.fillOval(245, 270, 5, 5);
		g.fillOval(255, 270, 5, 5);

		g.setColor(Color.red);
		g.fillArc(240, 270, 20, 20, 180, 190);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Face();
	}

}
