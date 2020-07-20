package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ContainerMain {

	public static void main(String[] args) {

		JFrame f1 = new JFrame("JPanel");

		f1.setBounds(100, 100, 300, 200);
		f1.setLayout(null);

		JPanel p1 = new JPanel(); // 컨테이너 입니다.
		p1.setBounds(10, 10, 100, 100);
		p1.setBackground(Color.yellow);

		for (int i = 0; i < 5; i++) {
			p1.add(new JButton("버튼" + i));
		}

		f1.add(p1);

		JPanel p2 = new JPanel();
		p2.setBounds(120, 10, 100, 100);
		p2.setBackground(Color.pink);
		f1.add(p2);

		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//////////////////////////////////////////////////////////////////////

		JFrame f2 = new JFrame("JScrollPane");
		f2.setBounds(400, 100, 300, 200);
		f2.setLayout(null);

		// 가로 방향
		JPanel p21 = new JPanel();
		JScrollPane jp1 = new JScrollPane(p21);
		jp1.setBounds(10, 10, 100, 100);
		p21.setBackground(Color.yellow);
		f2.add(jp1);

		/// 세로 방향
		JPanel p22 = new JPanel();

		// JPanel 이 inner 일때 크기 설정
		p22.setPreferredSize(new Dimension(100, 210));

		JScrollPane jp2 = new JScrollPane(p22);
		jp2.setBounds(120, 10, 130, 100);
		p22.setBackground(Color.pink);
		p22.setLayout(null);
		f2.add(jp2);

		for (int i = 0; i < 5; i++) {
			p21.add(new JButton("버튼" + i));

			JButton btn = new JButton("버튼" + i);
			btn.setBounds(15, i * 30, 70, 30);
			p22.add(btn);
		}

		f2.setVisible(true);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame f3 = new JFrame("JDialog");
		f3.setBounds(100, 300, 300, 200);
		f3.setLayout(null);

		// Dialog 는 기본 적으로 Frame 에 종속되지만... 별개로 작동...
		// true : doModal
		// false : Modaless
		// default => false;
		JDialog dd = new JDialog(f3, "메세지창", false);
		// modal == true 만 박아줌
		dd.setBounds(400, 300, 300, 200);
		dd.setLayout(new FlowLayout());
		dd.add(new JLabel("경고 메세지를 발령"));
		dd.add(new JButton("버튼이다"));

		dd.setVisible(true);
		f3.setVisible(true);
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFrame f4 = new JFrame("JDialog");
		f4.setBounds(100, 500, 300, 200);

		FileDialog fd = new FileDialog(f4, "파일열기", FileDialog.LOAD);
		fd.setLocation(300, 300);
//		fd.setDirectory("D:\\JINWOO");
		fd.setDirectory("D:");

		fd.setVisible(true); // 파일 창 나옴
		f4.setVisible(true);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(fd.getDirectory()); // D:\ // 선택한 파일의 위치 와
		System.out.println(fd.getFile()); // msdia80.dll // 선택한 파일명
	}

}
