package theory.Component;

import javax.swing.JFrame;
import javax.swing.JList;

public class List {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JList");
		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		String[] lists = { "의사", "판사", "안사", "못사" };
		JList<String> list = new JList<String>(lists);
		list.setBounds(20, 20, 100, 100);
		list.setSelectedValue("안사", true); // default 설정
		frame.add(list);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
