package singleChat_GUI_faild2;

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

public class ChatClient extends JFrame {

	JTextField url, port;
	JTextArea textView;
	JButton connectionBtn;
	Client client;

	public ChatClient() {
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

			String url = ChatClient.this.url.getText();
			String port = ChatClient.this.port.getText();

			ChatClient.this.client = new Client(url, port);
			System.out.println("서버에 접속 합니다.");
			// 소케에 url 과 port 번호 넘겨주기

			// 서버에 접속되면 비활성화
			ChatClient.this.url.setEditable(false);
			ChatClient.this.port.setEditable(false);
			ChatClient.this.connectionBtn.setEnabled(false);

			// 서버에 접속되면 받을수 있게 recevie 를 활성화 합니다.
			new Receive(ChatClient.this.client.socket, textView).start();
		}

	}

	class inputAction implements ActionListener {
		
		public inputAction() {
			// TODO Auto-generated constructor stub
		System.out.println("이벤트 생성");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			System.out.println("엔터");

			JTextField input = (JTextField) e.getSource();

			System.out.println("inputAction : " + input.getText());

			new Send2(ChatClient.this.client.socket, input.getText());

			input.setText("");

			System.out.println(ChatClient.this.client.socket); // 여기선 소켓 살아 있음
		}

	}

	// test
	public static void main(String[] args) {
		new ChatClient();
	}

}
