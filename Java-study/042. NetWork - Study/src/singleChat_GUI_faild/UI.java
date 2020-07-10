package singleChat_GUI_faild;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	String message;
	JTextArea ta;

	public UI(Socket socket) {
		// TODO Auto-generated constructor stub

		setSize(500, 500);
		setLayout(new BorderLayout());
		ta = new JTextArea();
		ta.setEditable(false);
		add(ta, "Center");

		JTextField jf = new JTextField();
//		jf.setEditable(false);

		add(jf, "South");

		try {
			jf.addActionListener(new ActionListener() {

				OutputStream os = socket.getOutputStream();

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JTextField j = (JTextField) e.getSource();
					message = j.getText();
					byte[] buffer = message.getBytes();
					try {
						os.write(buffer);
						os.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					System.out.println(message);
					j.setText("");
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		// new UI();
	}

}
