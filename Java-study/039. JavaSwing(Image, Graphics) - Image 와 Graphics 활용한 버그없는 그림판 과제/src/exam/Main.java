package exam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Paint extends JFrame {

	BrushTool brushTool;
	Board board;
	ColorTool colorTool;
	boolean brushChk;
	int lineX, lineY;

	public Paint() {
		// TODO Auto-generated constructor stub

		setTitle("그림판");
		setIconImage(new ImageIcon("./exam_img/logo.png").getImage());
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.brushChk = true;
		this.brushTool = new BrushTool();
		this.board = new Board();
		this.colorTool = new ColorTool();
		add(brushTool, "North");
		add(board, "Center");
		add(colorTool, "South");

		setVisible(true);

	}

	class Board extends JPanel implements MouseMotionListener {

		Image img;
		Graphics gimg;
		int imgX, imgY;

		public Board() {
			// TODO Auto-generated constructor stub
			this.img = null;
			this.gimg = null;
			addMouseMotionListener(this);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			if (img == null) {
				img = createImage(400, 400);
				gimg = img.getGraphics();

				gimg.setColor(Color.white);
				gimg.fillRect(0, 0, 400, 400);
				System.out.println(this.getY());
			}

//			System.out.println("Frame : " + Paint.this.getWidth());
//			System.out.println("Board : " + this.getWidth());
//			System.out.println("Image : " + this.img.getWidth(this));
//			System.out.println(Paint.this.getHeight() - this.img.getHeight(this));
//			System.out.println(this.getSize());

			this.imgX = (Paint.this.getWidth() - this.img.getWidth(this)) / 2;
			this.imgY = (this.getHeight() - this.img.getHeight(this)) / 2;

			g.drawImage(img, imgX, imgY, this);

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getX() + ", " + e.getY());
			if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				gimg.setColor(Paint.this.colorTool.currentColor.getBackground());
				gimg.setFont(new Font("굴림", Font.PLAIN, Paint.this.brushTool.brushSize));
				if (brushChk) {
					gimg.drawLine(lineX, lineY, e.getX() - imgX, e.getY() - imgY);
					Paint.this.lineX = e.getX() - imgX;
					Paint.this.lineY = e.getY() - imgY;
				} else {
					gimg.drawString(Paint.this.brushTool.brush.getText(), e.getX() - imgX, e.getY() - imgY);
				}
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			Paint.this.lineX = e.getX() - imgX; // 이곳이 필요한 이유는 다른곳에서는 필요가 없지만 시작위치 때문에 필요 합니다.
			Paint.this.lineY = e.getY() - imgY; // 드래그가 끝겨서 업데이트가 되지 않아 LineX, Y 가 업데이트 되지 않습니다.
		}

	}

	class ColorTool extends JPanel {

		ArrayList<JSlider> sliders;
		JLabel currentColor;

		public ColorTool() {
			// TODO Auto-generated constructor stub
//			setBackground(Color.pink);
			this.sliders = new ArrayList<JSlider>();

			// min, max, value
			sliders.add(new JSlider(0, 255, 0)); // r
			sliders.add(new JSlider(0, 255, 0)); // g
			sliders.add(new JSlider(0, 255, 0)); // b
			sliders.add(new JSlider(0, 255, 255)); // a

			for (JSlider jSlider : sliders) { // 슬라이더 이벤트
				// 큰 눈금
				jSlider.setMajorTickSpacing(50); // 50단위
				// 작은 눈금
				jSlider.setMinorTickSpacing(10); // 10단위
				// 선 표시
				jSlider.setPaintTicks(true);

				jSlider.setPreferredSize(new Dimension(100, 30));

				add(jSlider);

				jSlider.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						ColorTool.this.currentColor.setBackground(new Color(sliders.get(0).getValue(),
								sliders.get(1).getValue(), sliders.get(2).getValue(), sliders.get(3).getValue()));

						ColorTool.this.repaint(); // 슬라이더를 움직일때마다 .... Label 이 먹힘 ...
						// 이벤트가 발생될때마다 repaint 시켜주면 ok
					}
				});
			}

			currentColor = new JLabel();
			currentColor.setBackground(Color.black);
			currentColor.setOpaque(true);
			currentColor.setPreferredSize(new Dimension(50, 50));
			add(currentColor);
		}

	}

	class BrushTool extends JPanel {

		JLabel brush;
		int brushSize;
		int index;

		public BrushTool() {
			// TODO Auto-generated constructor stub
//			setBackground(Color.pink);
			JSlider slider = new JSlider(0, 40, 10);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(5);
			slider.setPaintTicks(true);

			add(slider);

			this.brushSize = slider.getValue();

			slider.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					BrushTool.this.brushSize = slider.getValue();
				}
			});

			this.brush = new JLabel();
			add(brush);
			String[] brushShape = { "Line", "●", "■", "▲", "♥", "★" };
			brush.setText(brushShape[0]);

			JButton btn = new JButton("모양변경");
			add(btn);

			index = 1;
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (index % brushShape.length == 0) {
						Paint.this.brushChk = true;
						brush.setText(brushShape[index % brushShape.length]);
					} else {
						Paint.this.brushChk = false;
						brush.setText(brushShape[index % brushShape.length]);
						index++;
					}
				}
			});

			JButton eraser = new JButton("지우개");
			add(eraser);
			eraser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Paint.this.colorTool.currentColor.setBackground(Color.white);
				}
			});
		}
	}

}

public class Main {

	public static void main(String[] args) {

		new Paint();

	}

}
