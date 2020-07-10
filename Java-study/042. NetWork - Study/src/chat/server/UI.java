package chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	public UI() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setLocationRelativeTo(null);
		setTitle("관리자");
		setResizable(false);
		setLayout(new BorderLayout());

		JTextArea textView = new JTextArea();
		textView.setEditable(false);
		add(textView, "Center");

		JTextField input = new JTextField();
		input.addActionListener(new inputAction());
		add(input, "South");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		
		// 위에서 시작시 lock이 걸려 코드가 밑으로 흐르지 않습니다.
		new Server(); // 서버 시작
	}

	class inputAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JTextField input = (JTextField) e.getSource();

			System.out.println("inputAction : " + input.getText());

			input.setText("");

		}

	}

	// test
	public static void main(String[] args) {
		new UI();
	}

}