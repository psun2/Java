package exam_falie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import test.Main;

class CanvasAA extends JFrame {

	Toolbar toolbar;
	Board board;
	boolean first;

	public CanvasAA() {
		this.first = true;

		setTitle("그림판");
		setIconImage(new ImageIcon("./exam_img/logo.png").getImage());
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.toolbar = new Toolbar(this);
		add(toolbar, "North");

//		JPanel pp = new JPanel();

//		add(pp, "Center");
//		pp.setBackground(Color.pink);

		this.board = new Board(toolbar);
		add(board, "Center");

		setVisible(true);

	}

	void refresh() {

		setVisible(false);
		setVisible(true);
	}

}

class Toolbar extends JPanel {

	CanvasAA canvas;
	Color currentColor;
	int currentBrush;

	Color[] colors = { Color.black, new Color(255, 0, 0), new Color(255, 112, 18), new Color(255, 255, 0),
			new Color(0, 255, 0), new Color(0, 0, 255), new Color(5, 0, 165), new Color(128, 65, 217) };

	public Toolbar(CanvasAA canvas) {

		this.canvas = canvas;
		this.currentColor = Color.black;
		this.currentBrush = 10;

		JToolBar toolbar = new JToolBar();

		add(toolbar);

//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		toolbar.setLayout(new GridLayout());
		toolbar.setFloatable(false); // 툴바 고정

		// 무지개 선택
		toolbar.add(new JLabel("색상선택 : "));

		String titles[] = { "검정", "빨강", "주황", "노랑", "초록", "파랑", "남색", "보라" };

		JComboBox<String> colorBox = new JComboBox<String>(titles);

		toolbar.add(colorBox);

		colorBox.addActionListener(new ComboBoxEvent());

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

		// 모두 지우기
		JButton removeAll = new JButton("모두 지우기");
		toolbar.add(removeAll);

		removeAll.addActionListener(new RemoveAllEvent());

	}

	class ComboBoxEvent implements ActionListener { // 콤보 박스에 있는 색상표로 index를 반환받아 색상 선택

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox box = (JComboBox) e.getSource();

//			System.out.println(box.getSelectedIndex()); // 몇번 아이템을 눌렀는지 index번호 반환

			Toolbar.this.currentColor = Toolbar.this.colors[box.getSelectedIndex()];

			// System.out.println(Toolbar.this.currentColor);

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
			currentColor = Color.black;
			canvas.board = new Board(Toolbar.this);
			canvas.refresh();
		}

	}

}

class Board extends JPanel implements MouseMotionListener {

	boolean first;
	Toolbar toolbar;
	int x, y, width, height;
	Color color;

	public Board(Toolbar toolbar) {
		System.out.println("생성");

		// 초기값 설정
		this.toolbar = toolbar;
		this.first = true;
		this.x = -100;
		this.y = -100;

		addMouseMotionListener(this);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);
		System.out.println("paint() 실행");

		g.setClip(0, 100, 500, 600);

		if (first) {
			System.out.println("진입");
			System.out.println(first);

			g.fillRect(0, 101, 1000, 1000);

			first = false;
		}

		System.out.println(first);

		this.color = toolbar.currentColor;
		this.width = toolbar.currentBrush;
		this.height = toolbar.currentBrush;

		g.setColor(color);
		g.fillOval(x, y, width, height);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		x = e.getX() - (this.width / 2);
		y = e.getY() - (this.height / 2);

		toolbar.canvas.toolbar.repaint();
		repaint();// paint() 호출

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

public class Main {

	public static void main(String[] args) {
		new CanvasAA();
	}

}
