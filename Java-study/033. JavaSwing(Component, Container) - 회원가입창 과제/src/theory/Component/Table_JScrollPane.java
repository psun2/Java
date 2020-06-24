package theory.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table_JScrollPane {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTable_JScrollPane");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		Object[] index = { "이름", "국어", "영어", "수학" };
		Object[][] data = { { "박성언", 77, 78, 79 }, { "박시현", 67, 68, 69 }, { "안정민", 97, 89, 99 }, { "이주혁", 57, 58, 59 },
				{ "이호인", 87, 88, 89 } };

//		JScrollPane // 혼자서는 의미가 없음. table과 같이 사용.
		JTable table = new JTable(data, index);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(30, 30, 200, 100);
		frame.add(scroll);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
