package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Paint extends JFrame {

	Board board;
	ColorTool color;

	public Paint() {
		// TODO Auto-generated constructor stub

		int width = 800;
		int height = 600;

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("그림판");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.board = new Board();
		add(board, "Center");

		this.color = new ColorTool();
		add(color, "South");

		setVisible(true);
	}

	class Board extends JPanel implements MouseMotionListener {

		Image img;
		Graphics gimg;
		int x, y;

		public Board() {
			// TODO Auto-generated constructor stub
			setBackground(Color.pink);
			addMouseMotionListener(this);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			if (img == null) {
//				this.img = createImage(width, height)
				this.img = createImage(this.getWidth() - 100, this.getHeight() - 100);
				this.gimg = img.getGraphics();

				gimg.setColor(Color.white);
				gimg.setFont(new Font("굴림", Font.PLAIN, 20));
				gimg.fillRect(0, 0, img.getWidth(this), img.getHeight(this));
			}

			x = (this.getWidth() - img.getWidth(this)) / 2;
			y = (this.getHeight() - img.getHeight(this)) / 2;
			g.drawImage(img, x, y, this); // observer => image 판을 붙일 대상
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			gimg.setColor(Paint.this.color.colorLb.getBackground());
			gimg.drawString("♡", e.getX() - x, e.getY() - y);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class ColorTool extends JPanel {

		JLabel colorLb;

		public ColorTool() {
			// TODO Auto-generated constructor stub

			ArrayList<JSlider> sliders = new ArrayList<JSlider>();

			sliders.add(new JSlider(0, 255, 0));
			sliders.add(new JSlider(0, 255, 0));
			sliders.add(new JSlider(0, 255, 0));
			sliders.add(new JSlider(0, 255, 255));

			for (JSlider jSlider : sliders) {
				jSlider.setPreferredSize(new Dimension(100, 100));
				add(jSlider);

				jSlider.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						colorLb.setBackground(new Color(sliders.get(0).getValue(), sliders.get(1).getValue(),
								sliders.get(2).getValue(), sliders.get(3).getValue()));
						///////////////////////////
						repaint(); // 여기 문제가 심각//
						// 이걸 지우고 확인해보세요///////
						///////////////////////////
					}
				});
			}

			this.colorLb = new JLabel();
			colorLb.setBackground(Color.black);
			colorLb.setOpaque(true);
			colorLb.setPreferredSize(new Dimension(100, 100));

			add(colorLb);
		}

	}

}

public class ImagePaintExamMain__drawing_board {

	public static void main(String[] args) {
		new Paint();
	}

}
