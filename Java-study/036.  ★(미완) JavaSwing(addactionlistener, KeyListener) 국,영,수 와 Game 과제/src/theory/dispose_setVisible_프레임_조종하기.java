package theory;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class MainFrame extends JFrame {

	public MainFrame() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 500;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setTitle("메인 프레임");
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton btn = new JButton("클릭시 다른 프레임");

		btn.setVerticalAlignment(JButton.CENTER);

		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SubFrame a = new SubFrame();
				dispose();
//				a.test();

				JOptionPane.showMessageDialog(a, "Frame가 바뀌었습니다.", "짜잔", JOptionPane.INFORMATION_MESSAGE);
//				JOptionPane.showMessageDialog(null, "Default");
//				JOptionPane.showMessageDialog(null, "에러 메시지", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
//				JOptionPane.showMessageDialog(null, "알람 메시지", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
//				JOptionPane.showMessageDialog(null, "경고 메시지", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
//				JOptionPane.showMessageDialog(null, "질문 메시지", "QUESTION_MESSAGE", JOptionPane.QUESTION_MESSAGE);
//				JOptionPane.showMessageDialog(null, "아이콘이 없는 메시지", "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);

				// showInputDialog(Component parentComponent, Object message, String title, int
				// messageType, Icon icon, Oes, Object initialSelectionValue)

			}
		});

		setVisible(true);

	}

}

class SubFrame extends JFrame implements KeyListener {

	public SubFrame() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 500;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setTitle("서브 프레임");
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		add(new JLabel("C 를 누르면 메인 프레임으로 돌아갑니다."));

		addKeyListener(this);

		setVisible(true);
	}

	void test() {

		setVisible(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_C:
			new MainFrame();
			dispose();
			JOptionPane.showMessageDialog(null, "메인프레임 으로 돌아왓습니다.", "환영합니다.", JOptionPane.ERROR_MESSAGE);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

public class dispose_setVisible_프레임_조종하기 {

	public static void main(String[] args) {

		new MainFrame();

	}

}
