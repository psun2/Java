package theory;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Copy_Exam extends JFrame {

	Container cntentPane;
	JLabel imageLabel = new JLabel();

	public Copy_Exam() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 200;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height);
		setBounds(x, y, width, height);
		setTitle("Menu와 JFileChooser 활용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cntentPane = getContentPane();
		cntentPane.add(imageLabel);
		createMenu();

		setVisible(true);
	}

	void createMenu() {

		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");

		// open 메뉴아이템에 Action 리스너를 등록한다.
		openItem.addActionListener(new OpenActionListener());
		fileMenu.add(openItem);
		mb.add(fileMenu);
		this.setJMenuBar(mb);
	}

	class OpenActionListener implements ActionListener {

		JFileChooser chooser;

		public OpenActionListener() {
			chooser = new JFileChooser();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images" // 파일 유형에 표시될 문자열
					, "jpg", "gif"); // 파일 필터로 사용되는 확장자
			chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정

			// 파일 다이얼로그 출력
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소버튼을 누른 경우
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 사용자가 파일을 선택하고 "열기" 버튼을 누른경우
			String filePath = chooser.getSelectedFile().getPath(); // 파일의 경로를 알아 옵니다.
			imageLabel.setIcon(new ImageIcon(filePath)); // 파일을 로딩하여 이미지 레이블에 출력합니다.
			pack(); // 이미지의 크기에 맞추어 프레임의 크기 조절
		}

	}

	public static void main(String[] args) {
		new Copy_Exam();
	}
}
