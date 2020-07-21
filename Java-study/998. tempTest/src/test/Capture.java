package test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Capture extends JFrame {

//	GraphicsDevice screen = null;
//	DisplayMode display = null;

	public Capture() {
		// TODO Auto-generated constructor stub

		new File("캡쳐테스트").mkdir();
//		screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		display = screen.getDisplayMode();

		setSize(500, 500); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		JButton btn = new JButton("눌르면 화면 캡쳐");
		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// 전체 화면 Capture
					BufferedImage screencapture = new Robot()
//							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
							.createScreenCapture(new Rectangle(new Dimension(getSize().width, getSize().height)));
//							.createScreenCapture((new Rectangle(0, 0, display.getWidth(), display.getHeight())));

					// JPEG 저장.
					File file = new File("./캡쳐테스트/캡쳐되나요.png");
					ImageIO.write(screencapture, "png", file);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (AWTException e2) {
					e2.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌
	}

	public static void main(String[] args) {
		new Capture();

	}
}
