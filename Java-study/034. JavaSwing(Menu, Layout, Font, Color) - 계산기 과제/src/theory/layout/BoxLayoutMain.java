package theory.layout;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Box extends JFrame {

	public Box(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Constructs a BoxLayout that produces debugging messages.
		 *
		 * @param target the container that needs to be laid out
		 * @param axis   the axis to lay out components along. Can be one of:
		 *               <code>BoxLayout.X_AXIS</code>, <code>BoxLayout.Y_AXIS</code>,
		 *               <code>BoxLayout.LINE_AXIS</code> or
		 *               <code>BoxLayout.PAGE_AXIS</code>
		 *
		 * @param dbg    the stream to which debugging messages should be sent, null if
		 *               none
		 */
//	    BoxLayout(Container target, int axis, PrintStream dbg) {
//	        this(target, axis);
//	        this.dbg = dbg;
//	    }
//	    
//		new BoxLayout(target, axis)

		// x축으로 정렬
//		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
//		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		// y축으로 정렬
//		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		for (int i = 0; i < 5; i++) {
			add(new JButton("btn" + i));
		}

		// 혼합 사용 가능
		JPanel pan = new JPanel();
		pan.setSize(300, 100);
		pan.setBackground(Color.PINK);
		pan.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 5; i++) {
			pan.add(new JButton("btn" + i));
		}
		add(pan);

		setVisible(true);

	}
}

public class BoxLayoutMain {

	public static void main(String[] args) {

		new Box("BoxLayout");

	}

}
