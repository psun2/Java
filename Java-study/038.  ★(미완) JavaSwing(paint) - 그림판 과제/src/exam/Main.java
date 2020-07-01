package exam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Board extends JFrame implements MouseMotionListener {

	boolean first;
	Toolbar toolbar;
	int x, y, width, height;
	Color color;
	int brush;

	public Board() {

		this.first = true;
		this.x = -100;
		this.y = -100;
		this.brush = 2;

		setTitle("그림판");
		setIconImage(new ImageIcon("./exam_img/logo.png").getImage());
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.toolbar = new Toolbar(this);
		add(toolbar, "North");

		setVisible(true);

		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);

		toolbar.repaint();

		if (first) {
			g.setClip(0, 60, 500, 600); // 다시 그릴 범위 설정
			g.setColor(Color.white);
			g.fillRect(0, 0, 500, 600);
			first = false;
		}

		this.color = toolbar.currentColor;
		this.width = toolbar.currentBrush;
		this.height = toolbar.currentBrush;

		g.setColor(color);

		switch (this.brush) {
		case 2:
			g.fillOval(x, y, width, height);
			break;
		case 1:
			g.fillRect(x, y, width, height);
			break;
		case 0:
//			g.fillPolygon(new int[] { 100, 5, 130, 15, 75 }, new int[] { 95, 155, 160, 90, 210 }, 5);
			g.fillPolygon(new int[] { x + 85, x - 10, x + 115, x, x + 60 }, new int[] { y, y + 65, y + 70, y, y + 120 },
					5);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		x = e.getX() - (this.width / 2);
		y = e.getY() - (this.height / 2);

		repaint();// paint() 호출
		toolbar.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + ", " + e.getY());
	}

}

class Toolbar extends JPanel {

	Board board;
	Color currentColor;
	int currentBrush;

	Color[] colors = { Color.black, new Color(255, 0, 0), new Color(255, 112, 18), new Color(255, 255, 0),
			new Color(0, 255, 0), new Color(0, 0, 255), new Color(5, 0, 165), new Color(128, 65, 217) };

	String titles[] = { "검정", "빨강", "주황", "노랑", "초록", "파랑", "남색", "보라 ", "색상선택" };

	String[] brush = { "★", "■", "●", "브러쉬 선택" };

	public Toolbar(Board board) {

		this.board = board;
		this.currentColor = Color.black;
		this.currentBrush = 10;

		JToolBar toolbar = new JToolBar();

		add(toolbar);

		toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));
//		toolbar.setLayout(new GridLayout());
		toolbar.setFloatable(false); // 툴바 고정

		// 무지개 선택
//		toolbar.add(new JLabel("색상선택 : "));

//		JComboBox<String> colorBox = new JComboBox<String>(titles); // 사용 불가 
//
//		toolbar.add(colorBox);
//
//		colorBox.addActionListener(new ComboBoxEvent());

		JSpinner colorSp = new JSpinner();
		toolbar.add(colorSp);

		SpinnerListModel colorMo = new SpinnerListModel(titles);
		colorSp.setModel(colorMo);

		colorSp.addChangeListener(new ColorChoice());

		colorSp.setValue("색상선택");

		// 다른색상
		JButton otherBtn = new JButton("다른색상");
		toolbar.add(otherBtn);

		otherBtn.addActionListener(new OtherBtnEvent());

		// 브러쉬 굵기 선택
		JLabel brush = new JLabel("굵기 선택 : ");

		toolbar.add(brush);

		JSpinner thickness = new JSpinner();

		SpinnerNumberModel model = new SpinnerNumberModel(10, 5, 50, 5);
		thickness.setModel(model);

		toolbar.add(thickness);

		thickness.addChangeListener(new SpinerEvent());

		// 브러쉬 선택
		JSpinner brushSele = new JSpinner();
		toolbar.add(brushSele);
		SpinnerListModel brushMo = new SpinnerListModel(this.brush);
		brushSele.setModel(brushMo);
		brushSele.setValue("브러쉬 선택");
		brushSele.addChangeListener(new BrushEvent());

		// 지우개
		JButton eraser = new JButton("지우개");
		toolbar.add(eraser);

		eraser.addActionListener(new Eraser());

		// 모두 지우기
		JButton removeAll = new JButton("모두 지우기");
		toolbar.add(removeAll);

		removeAll.addActionListener(new RemoveAllEvent());

	}

//	class ComboBoxEvent implements ActionListener { // 콤보 박스에 있는 색상표로 index를 반환받아 색상 선택
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			JComboBox box = (JComboBox) e.getSource();
//
////		System.out.println(box.getSelectedIndex()); // 몇번 아이템을 눌렀는지 index번호 반환
//
//			Toolbar.this.currentColor = Toolbar.this.colors[box.getSelectedIndex()];
//
//			// System.out.println(Toolbar.this.currentColor);
//
//		}
//
//	}

	class ColorChoice implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub

			ArrayList<String> temp = new ArrayList<String>();

			for (String string : Toolbar.this.titles) {
				temp.add(string);
			}

			JSpinner selected = (JSpinner) e.getSource();

//			System.out.println(selected.getValue());

			int index = temp.indexOf(selected.getValue());

			if (index == Toolbar.this.colors.length)
				return;

			Toolbar.this.currentColor = colors[index];

		}

	}

	class OtherBtnEvent implements ActionListener { // otherBtn 의 이벤트 로 색상판을 띄워 색상 선택

		@Override
		public void actionPerformed(ActionEvent e) {

			JColorChooser color = new JColorChooser();

			Toolbar.this.currentColor = color.showDialog(null, "색상선택", new Color(255, 0, 0));

			// System.out.println(Toolbar.this.currentColor);

		}

	}

	class SpinerEvent implements ChangeListener { // 스피너의 입력 값으로 value 를 변경하는 이벤트

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub

			JSpinner selected = (JSpinner) e.getSource();

			// System.out.println(selected.getValue());

			Toolbar.this.currentBrush = (int) selected.getValue();

			// System.out.println(Toolbar.this.currentBrush);

		}

	}

	class RemoveAllEvent implements ActionListener { // 모두 지우기

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Toolbar.this.board.x = -100;
			Toolbar.this.board.y = -100;
			Toolbar.this.board.first = true;
			Toolbar.this.board.repaint();
		}

	}

	class Eraser implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Toolbar.this.currentColor = Color.white;
		}

	}

	class BrushEvent implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {

			ArrayList<String> temp = new ArrayList<String>();

			for (String string : Toolbar.this.brush) {
				temp.add(string);
			}

			JSpinner selected = (JSpinner) e.getSource();

			// System.out.println(((String) selected.getValue()).length());

			if (((String) selected.getValue()).length() != 1)
				return;

			int brushNum = temp.indexOf(selected.getValue());

			Toolbar.this.board.brush = brushNum;

		}

	}

}

public class Main {

	public static void main(String[] args) {
		new Board();
	}

}
