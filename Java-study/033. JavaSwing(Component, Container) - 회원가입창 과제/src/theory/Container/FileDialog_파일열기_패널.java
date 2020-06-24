package theory.Container;

import java.awt.FileDialog;

import javax.swing.JFrame;

public class FileDialog_파일열기_패널 {

	public static void main(String[] args) {

		JFrame frame = new JFrame("FileDialog");
		frame.setBounds(100, 100, 400, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FileDialog fd = new FileDialog(frame, "파일열기", FileDialog.LOAD);

		fd.setLocation(300, 300); // 열리는 창의 위치를 조종 할 수 있습니다.
//		fd.setDirectory("D:"); // 열리는 폴더의 위치를 지정 할 수 있습니다.
		fd.setDirectory("D:\\Study"); // 열리는 폴더의 위치를 지정 할 수 있습니다.
		

		fd.setVisible(true);

		frame.setVisible(true);

	}

}
