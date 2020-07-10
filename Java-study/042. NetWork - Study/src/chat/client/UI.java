package chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	JTextField url, port;
	JTextArea textView;
	JButton connectionBtn;
	Client client;

	public UI() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setLocationRelativeTo(null);
		setTitle("고객용");
		setResizable(false);
		setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		this.url = new JTextField();
		try {
			this.url.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = new JTextField();
		this.port.setText("7777");
		this.connectionBtn = new JButton("접속");
		connectionBtn.addActionListener(new connectionBtnAction());

		northPanel.add(new JLabel("URL: "));
		northPanel.add(url);
		northPanel.add(new JLabel("PORT: "));
		northPanel.add(port);
		northPanel.add(connectionBtn);

		add(northPanel, "North");

		this.textView = new JTextArea();
		textView.setEditable(false);
		add(textView, "Center");

		JTextField input = new JTextField();

		input.addActionListener(new inputAction());
		add(input, "South");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	class connectionBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String url = UI.this.url.getText();
			String port = UI.this.port.getText();

			// 소케에 url 과 port 번호 넘겨주기
			UI.this.client = new Client(url, port);
			System.out.println("서버에 접속 합니다.");

			// 서버에 접속되면 비활성화
			UI.this.url.setEditable(false);
			UI.this.port.setEditable(false);
			UI.this.connectionBtn.setEnabled(false);

		}

	}

	class inputAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JTextField input = (JTextField) e.getSource();

			System.out.println("inputAction : " + input.getText());

			System.out.println(UI.this.client.socket);
			System.out.println(input.getText());

			// 채팅 할 때만 업데이트 하고 싶습니다 굳이 쓰레드로 돌릴 필요가 있을까요?
			new Send(UI.this.client.socket, input.getText());
			new Recevie(UI.this.client.socket, textView).start();;

			input.setText("");

		}

	}

	// test
	public static void main(String[] args) {
		new UI();
	}

}
