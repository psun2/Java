package swing_p;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JToolBar;

public class ToolBarMain extends JFrame {

	public ToolBarMain() {

		setBounds(100, 100, 700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("ToolBar");

		JToolBar bar = new JToolBar("툴바");
		bar.setBackground(Color.pink);
//		add(bar, "North");
		add(bar, "South");

//		bar.add(new JButton("파일"));

		JButton btn = new JButton("파일");
		btn.setToolTipText("파일저장버튼입니다."); // 마우스 오버시 tooltip
		bar.add(btn);

		bar.add(new JButton(new ImageIcon("img/camera.png")));
		bar.add(new JLabel("카메라모드 : "));

		JComboBox box = new JComboBox();
		box.addItem("일반");
		box.addItem("광학");
		box.addItem("접근");
		box.addItem("전방");
		box.setSelectedItem("접근");
		bar.add(box);

		setVisible(true);

	}

	public static void main(String[] args) {

		new ToolBarMain();

	}

}
