package an.tableBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class yeonji_CallView extends JFrame {

	private JPanel contentPane;
	JButton btnNewButton;
	static yeonji_CallControl callcontrol;
//	public static void main() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new yeonji_CallView(callcontrol);
//			
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public yeonji_CallView(yeonji_CallControl callcontrol){
		
		
		
		this.callcontrol = callcontrol;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		btnNewButton = new JButton("1번 테이블 호출");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				callcontrol.changePannel1();
				
				
				//new yeonji_CallControl();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("2번 테이블 호출");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("3번 테이블 호출");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("4번 테이블 호출");
		panel.add(btnNewButton_2);
		
		setVisible(true);
	}

}
