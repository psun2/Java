package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class ImagePaintMain extends JFrame implements MouseMotionListener {

	Image img;
	Graphics gimg; // setVisible 이전 생성시 오류 가 발생합니다.

	public ImagePaintMain() {

		int width = 800;
		int height = 600;

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("그림판");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// this.img = createImage(600, 400); // image의 크기를 설정합니다.
		// this.gimg = img.getGraphics();
		// Graphics 생성 오류 => 보여야 무엇을 가지고 올텐데... 보이지가 않아 가져올수 없어 nullpoint
		// Exception in thread "main" java.lang.NullPointerException
		// at theory.ImagePaintMain.<init>(ImagePaintMain.java:31)
		// at theory.ImagePaintMain.main(ImagePaintMain.java:38)

		setVisible(true);

		this.img = createImage(600, 400); // image의 크기를 설정합니다.
		this.gimg = img.getGraphics();

		gimg.setColor(Color.white);
		gimg.fillRect(0, 0, 600, 400);
		
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g); // image 는 일반 paint 와 는 달리 super를 안죽여도 됩니다.

		g.drawImage(img, 100, 100, this); // observer(관찰자) : image를 붙일 판의 이름
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImagePaintMain();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		gimg.setColor(Color.pink);
		gimg.setFont(new Font("굴림", Font.PLAIN, 20));
		gimg.drawString("☆", e.getX() - 100, e.getY() - 100);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
