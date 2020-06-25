package theory;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileChooser extends JFrame {

	int x, y, width, height;

	public FileChooser(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = 400;
		this.height = 400;
		this.x = (screen.width / 2) - (width / 2);
		this.y = (screen.height / 2) - (height / 2);
		setBounds(this.x, this.y, this.width, this.height);

		setIconImage(new ImageIcon("./위메프img/이메일.png").getImage());

		init();
	}

	void init() {

		JFileChooser fc = new JFileChooser();
		fc.setLocation(this.x, this.y);

//		showOpenDialog 는 showDialog 를 return 합니다.
		// fc.showOpenDialog(null); // 아직은 무슨차인지 모르겠습니다.
		// fc.showOpenDialog(this);
		// fc.showDialog(null, "파일 열기창의 이름을 정합니다.");

		// 반환된 showDialog 는 // 리턴 선택유무 1 : 선택없음 창닫기, 0 : 파일선택 // 숫자를 반환 합니다.
		// CANCEL_OPTION = 1;
		// APPROVE_OPTION = 0;

		// FileNameExtensionFilter 를 사용 하여, 확장자를 지정할 수 있습니다.
		// new FileNameExtensionFilter(description, extensions)
		FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일", "jpg", "gif", "png", "bmp", "jpeg");
		// 창에 출력된 문자열 // 파일필터 확장자들
		fc.setFileFilter(filter);

		// APPROVE_OPTION = 0; 파일이 선택 됨.
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// System.out.println(fc.getSelectedFile()); // File 반환
			System.out.println(fc.getSelectedFile().getPath()); // String 반환
//			String[] temp = fc.getSelectedFile().getPath().split("."); // 실패
//			String fileExtensions = temp[temp.length - 1];
//			String fileName = temp[temp.length - 2];
//			System.out.println(fileName + ", " + fileExtensions);
		} else
			System.out.println("선택된 파일이 없습니다.");

		setVisible(true);
	}

}

public class Chooser_JFileChooser {

	public static void main(String[] args) {
		new FileChooser("JFileChooser");
	}

}
