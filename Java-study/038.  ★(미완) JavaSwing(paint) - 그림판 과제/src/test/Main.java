package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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

class Board extends JFrame implements MouseMotionListener {

	boolean first;
	int x, y, width, height;
	Color color;
	Toolbar toolbar;

	public Board() {
		setTitle("그림판");
		setIconImage(new ImageIcon("./exam_img/logo.png").getImage());
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		this.first = true;
		this.first = true;
		this.x = -100;
		this.y = -100;

		this.toolbar = new Toolbar(this);
		add(toolbar, "North");

		setVisible(true);
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);

		while (first) {
			if (first) {
				g.setColor(Color.white);
				g.fillRect(-100, 200, 1000, 1000);
				setVisible(false);
				setVisible(true);
				first = false;
			}
		}

		this.color = toolbar.currentColor;
		this.width = toolbar.currentBrush;
		this.height = toolbar.currentBrush;

		g.setColor(color);
		g.fillOval(x, y, width, height);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

class Toolbar extends JToolBar {

	Board board;
	Color currentColor;
	int currentBrush;

	Color[] colors = { Color.black, new Color(255, 0, 0), new Color(255, 112, 18), new Color(255, 255, 0),
			new Color(0, 255, 0), new Color(0, 0, 255), new Color(5, 0, 165), new Color(128, 65, 217) };

	public Toolbar(Board board) {

		this.board = board;
		this.currentColor = Color.black;
		this.currentBrush = 10;

//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new GridLayout());
		setFloatable(false); // 툴바 고정

		// 무지개 선택
		add(new JLabel("색상선택 : "));

		String titles[] = { "검정", "빨강", "주황", "노랑", "초록", "파랑", "남색", "보라" };

		JComboBox<String> colorBox = new JComboBox<String>(titles);

		add(colorBox);

		colorBox.addActionListener(new ComboBoxEvent());

		// 다른색상
		JButton otherBtn = new JButton("다른색상");
		add(otherBtn);

		otherBtn.addActionListener(new OtherBtnEvent());

		// 브러쉬 굵기 선택
		JLabel brush = new JLabel("굵기 선택 : ");

		add(brush);

		JSpinner thickness = new JSpinner();

		SpinnerNumberModel model = new SpinnerNumberModel(10, 5, 50, 5);
		thickness.setModel(model);

		add(thickness);

		thickness.addChangeListener(new SpinerEvent());

		// 모두 지우기
		JButton removeAll = new JButton("모두 지우기");
		add(removeAll);

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

public class Main extends JPanel {

	public static void main(String[] args) {
		new Board();
	}

}
