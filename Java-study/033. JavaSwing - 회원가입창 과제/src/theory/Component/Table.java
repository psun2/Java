package theory.Component;

import javax.swing.JFrame;
import javax.swing.JTable;

public class Table {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTable");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		Object[] index = { "이름", "국어", "영어", "수학" };
		Object[][] data = { { "박성언", 77, 78, 79 }, { "박시현", 67, 68, 69 }, { "안정민", 97, 89, 99 }, { "이주혁", 57, 58, 59 },
				{ "이호인", 87, 88, 89 } };

		JTable table = new JTable(data, index);
		table.setBounds(30, 30, 200, 200);
		frame.add(table);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
