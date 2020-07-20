package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//이벤트 리스너 구현하는 클래스 정의
class BtnAction implements ActionListener {

	JLabel lb;

	public BtnAction(JLabel lb) {
		super();
		this.lb = lb;
	}

	@Override /// 이벤트 실행 감시하는 추상메소드
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("눌렀네 ");
		lb.setText("누른 후");
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
		setIconImage(new ImageIcon("./img/logo.png").getImage());
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

		// 이벤트 소스 -btn
		// 이벤트 리스너 -btnAction
		btn1.addActionListener(new BtnAction(lb1));

//		btn2.addActionListener(new InnAction());
		btn2.addActionListener(new changeColor());

		setVisible(true);
	}

	class InnAction implements ActionListener {

		@Override /// 이벤트 실행 감시하는 추상메소드
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("눌렀네 ");
			i++;
			lb2.setText("누른 후2\t" + i);
		}

	}

	class changeColor implements ActionListener {

		@Override /// 이벤트 실행 감시하는 추상메소드
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Color[] arr = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(0, 0, 99),
					new Color(155, 77, 203), Color.cyan };
			ccLb.setBackground(arr[i % arr.length]);
			i++;

		}

	}

	public static void main(String[] args) {
		new EventMain();
	}

}
