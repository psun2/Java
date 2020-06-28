package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 이벤트를 구현하는 가장 원초적인 방법 - 다른 클래스를 만들어 구현
class BtnAction implements ActionListener {

	JLabel lb;

	public BtnAction(JLabel lb) {
		this.lb = lb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("눌렸습니다.");

		if (lb.getText().equals("누른후"))
			lb.setText("초기화");
		else
			lb.setText("누른후");

	}

}

public class EventMain extends JFrame {

	int x, y, width, height;

	JLabel lb2, ccLb;

	int i;

	public EventMain() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.width = 500;
		this.height = 600;
		this.x = (screen.width / 2) - (width / 2);
		this.y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Event");
		setLayout(new FlowLayout());

		JButton btn1 = new JButton("날 눌러봐1");
		JButton btn2 = new JButton("날 눌러봐2");

		JLabel lb1 = new JLabel("누르기 전");
		lb2 = new JLabel("누르기 전2");

		ccLb = new JLabel();

		Dimension d = new Dimension(100, 100);
		ccLb.setPreferredSize(d);
		ccLb.setOpaque(true);
		ccLb.setBackground(Color.yellow);

		add(btn1);
		add(lb1);

		add(btn2);
		add(lb2);
		add(ccLb);

		btn1.addActionListener(new BtnAction(lb1));
		btn2.addActionListener(new InnAction());
		// 이벤트를 구현하는 방법3 - 직접 생성하여 구현
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color[] colors = { new Color(255, 0, 0), new Color(255, 50, 0), new Color(255, 255, 0),
						new Color(0, 255, 0), new Color(0, 0, 255), new Color(0, 5, 255), new Color(100, 0, 255) };
				ccLb.setBackground(colors[i % colors.length]);
			}
		});

		setVisible(true);
	}

	// 이벤트를 구현하는 방법2 - inner 클래스를 활용하여, 맴버 변수의 접근을 자유 롭게 합니다.
	class InnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			i++;
			System.out.println("눌렀네 ");
			lb2.setText("누른 후2\t" + i);
		}

	}

	public static void main(String[] args) {
		new EventMain();
	}

}
