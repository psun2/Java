package exam;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JButton;

public class MyForm extends JFrame {

	/**
	 * 
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				MyForm frame = new MyForm();

				frame.setVisible(true);

			}

		});

	}

	/**
	 * 
	 * Create the frame.
	 * 
	 */

	public MyForm() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 362, 249);

		setTitle("ThaiCreate.Com Java GUI Tutorial");

		getContentPane().setLayout(null);

// Popup Menu

		final JPopupMenu popMenu = new JPopupMenu();

		JMenuItem menuItem1 = new JMenuItem("Menu 1");

		JMenuItem menuItem2 = new JMenuItem("Menu 2");

		JMenuItem menuItem3 = new JMenuItem("Menu 3");

		popMenu.add(menuItem1);

		popMenu.add(menuItem2);

		popMenu.add(menuItem3);

// Button Open
		JButton btnOpenMenu = new JButton("Open Menu");

		btnOpenMenu.setBounds(122, 71, 109, 23);

		btnOpenMenu.setAlignmentX(RIGHT_ALIGNMENT);

		btnOpenMenu.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				popMenu.show(e.getComponent(), e.getX(), e.getY());

			}

		});

		getContentPane().add(btnOpenMenu);

	}

}