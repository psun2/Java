package theory.Container;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dialog {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JDialog");
		frame.setBounds(100, 100, 800, 800);
		frame.setLayout(null);

		// Dialog 는 기본 적으로 Frame 에 종속되지만... 별개로 작동...
		// true : doModal
		// false : Modaless
		// default => false;
		JDialog jd1 = new JDialog(frame, "메세지창", false);
		// modal == true 만 박아주면 됨
		jd1.setBounds(400, 300, 300, 200);
		jd1.setLayout(new FlowLayout());
		jd1.add(new JLabel("경고 메세지를 발령"));
		jd1.add(new JButton("버튼이다"));

		jd1.setVisible(true);

		JDialog jd2 = new JDialog(frame, "Modal", true);
		// modal == true 만 박아주면 됨
		jd2.setBounds(400, 300, 300, 200);
		jd2.setLayout(new FlowLayout());
		jd2.add(new JLabel("경고메세지 종료 전까지 진행이 안됨."));
		jd2.add(new JButton("버튼이다"));
		
		jd2.setVisible(true);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
