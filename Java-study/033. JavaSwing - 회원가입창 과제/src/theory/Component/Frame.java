package theory.Component;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Frame {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Frame title");

		// setLocation
//		frame.setLocation(300, 300);

		// Point
//		frame.setLocation(Point p);
		Point p = new Point(300, 300);
		frame.setLocation(p);

//////////////////////////////////////////////////////////////////

		// setSize
//		frame.setSize(400, 400);

//		Dimension
//		frame.setSize(Dimension d);
		Dimension d = new Dimension(400, 400);
		frame.setSize(d);

//////////////////////////////////////////////////////////////////

		// setBounds
//		frame.setBounds(300, 300, 400, 400); // frame 의 크기 설정

		frame.setVisible(true); // run 시 눈에 보여주는 역할 을 합니다.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X버튼을 눌럿을시 종료 됩니다.
		// setDefaultCloseOperation 이 없어도 종료는 되지만 eclips 의 콘솔창에서는 계속 가동 되어 있는 것으로 보아 빨간
		// 불이 남아 있습니다.

	}

}
