package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooserMain extends JFrame {

	public ChooserMain() {

		setTitle("Chooser");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println(screen); // java.awt.Dimension[width=1920,height=1080]
		int xpos = (int) (screen.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2);
		this.setLocation(xpos, ypos);
		setBounds(xpos - 100, ypos - 200, 200, 400);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFileChooser fc = new JFileChooser();
		fc.setLocation(0, 0);

//		new FileNameExtensionFilter(description, extensions)
		FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일", "jpg", "gif", "png", "bmp", "jpeg");
		// 창에 출력된 문자열 // 파일필터 확장자들
		fc.setFileFilter(filter);

		// fc.showOpenDialog(null);
		// 리턴 선택유무 1 : 선택없음 창닫기, 0 : 파일선택
		int ret = fc.showOpenDialog(null); // 파일의 선택유무

		if (ret == JFileChooser.APPROVE_OPTION) {// 파일선택 : 0

			System.out.println(fc.getSelectedFile().getPath());

		} else {
			System.out.println("파일선택 안함");
		}

//		System.out.println(ret);

//		add(fc);

		// 리턴 값 : Color
		JColorChooser cc = new JColorChooser();

		Color ccRet = cc.showDialog(null, "색깔고르라", Color.yellow);

//		System.out.println(ccRet);

		JLabel lb = new JLabel("선택한 색");
		lb.setOpaque(true);
		lb.setBounds(0, 0, 50, 50);

		if (ccRet != null) // 선택하지 않을 경우 null
			lb.setBackground(ccRet); // 선택한 색으로 배경색 선정

		add(lb);

		setVisible(true);
	}

	public static void main(String[] args) {

		new ChooserMain();

	}

}
