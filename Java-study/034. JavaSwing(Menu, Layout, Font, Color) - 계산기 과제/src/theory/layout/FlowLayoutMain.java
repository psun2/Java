package theory.layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class Flow extends JFrame {

	public Flow(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Creates a new flow layout manager with the indicated alignment and the
		 * indicated horizontal and vertical gaps.
		 * <p>
		 * The value of the alignment argument must be one of
		 * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
		 * <code>FlowLayout.CENTER</code>, <code>FlowLayout.LEADING</code>, or
		 * <code>FlowLayout.TRAILING</code>.
		 * 
		 * @param align the alignment value
		 * @param hgap  the horizontal gap between components and between the components
		 *              and the borders of the <code>Container</code>
		 * @param vgap  the vertical gap between components and between the components
		 *              and the borders of the <code>Container</code>
		 */
//	    public FlowLayout(int align, int hgap, int vgap) {
//	        this.hgap = hgap;
//	        this.vgap = vgap;
//	        setAlignment(align);
//	    }

//		new FlowLayout(align, hgap, vgap)
//   	Prams : 정렬, 가로간격, 세로간격

		setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 10));

		for (int i = 0; i < 10; i++) {
			add(new JButton("btn" + i));
		}

		setVisible(true);

	}
}

public class FlowLayoutMain {

	public static void main(String[] args) {

		new Flow("FlowLayout");

	}

}
