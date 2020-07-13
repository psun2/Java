package exam_다시_될_때_까지_멀티_유아이_UDT_다시;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	JTextArea chatView;
	boolean chk;
	String msg;

	public UI() {
		// TODO Auto-generated constructor stub

		this.chk = false;

		setBounds(710, 240, 500, 600);
		setLayout(new BorderLayout());

		chatView = new JTextArea();
		chatView.setEditable(false); // 편집 불가능
		JScrollPane chatCroll = new JScrollPane(chatView);
		add(chatCroll, "Center");

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		add(p, "South");
		JTextField chat = new JTextField();
		JButton btn = new JButton("전송");
		p.add(chat);
		p.add(btn);

		chat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chatView.getColumns() == 0)
					msg = chat.getText();
				System.out.println("진입");
				msg = "\n" + chat.getText();
				chat.setText("");
				System.out.println(chk);
				chk = true;
				System.out.println(chk);
				// System.out.println(msg);
			}
		});

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(chatView.getColumns());
				if (chatView.getColumns() == 0)
					msg = chat.getText();
				System.out.println("진입");
				msg = "\n" + chat.getText();
				chat.setText("");
				System.out.println(chk);
				chk = true;
				System.out.println(chk);
				// System.out.println(msg);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
