package exam;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Todo extends JFrame implements MouseListener, MouseMotionListener {

	ArrayList<Memo> memos;
	JPanel mainbox;
	JPanel currentP = null;
	Color currentC = null;
	int currentX, currentY;

	public Todo() {

		this.memos = new ArrayList<Memo>();

		setTitle("TODO");
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainbox = new JPanel();
		add(mainbox);
		mainbox.setLayout(null);

		setJMenuBar(new Menu(this));

		setResizable(false);
		refresh();

		addMouseListener(this);
		addMouseMotionListener(this);

	}

	void refresh() {

		setVisible(false);

		setVisible(true);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if (memos.size() != 0) {
			for (JPanel m : memos) {
				if (currentP != null) {
					currentP.setBackground(Color.white);
					currentP.setLocation(e.getX() - currentP.getWidth() / 2, e.getY() - currentP.getHeight() / 2);

				}
				if (e.getX() >= m.getX() && e.getX() <= m.getX() + m.getWidth() && e.getY() >= m.getY()
						&& e.getY() <= m.getY() + m.getHeight()) {
					if (currentP == null) {
						currentP = m;
						currentC = m.getBackground();
						currentX = m.getX(); // 프레임 밖으로 나가지 못하도록 ... 나가면 원래 자리로....
						currentY = m.getY();
					}

				}

			}
		} else {
			System.out.println("mouseDragged : " + e.getX() + ", " + e.getY() + " 움직일 요소가 없습니다.");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (currentP != null) {
			if (currentP.getX() < 0 || currentP.getX() + currentP.getWidth() > mainbox.getWidth() || currentP.getY() < 0
					|| currentP.getY() + currentP.getHeight() > mainbox.getHeight())
				currentP.setLocation(currentX, currentY);
			currentP.setBackground(currentC);
			currentP = null;
		} else {
			System.out.println("mouseReleased : " + e.getX() + ", " + e.getY() + " 움직일 요소가 없습니다.");

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

class Menu extends JMenuBar {

	Todo todo;

	public Menu(Todo todo) {

		this.todo = todo;

		JMenu menu = new JMenu("메뉴");
		add(menu);

		JMenuItem add = new JMenuItem("추가");
		JMenuItem sort = new JMenuItem("정렬");
		JMenuItem removeAll = new JMenuItem("모두삭제");

		menu.add(add);
		menu.add(sort);
		menu.add(removeAll);

		add.addActionListener(new ActionListener() { // 추가

			@Override
			public void actionPerformed(ActionEvent e) {

				String input = JOptionPane.showInputDialog("할일을 추가해 주세요");

				Memo memo = new Memo(input, todo); // 메모생성
				todo.mainbox.add(memo); // 프레임에 추가
				todo.memos.add(memo); // arraylist 에 추가
				todo.refresh(); // 이거때문에 생고생 // 생성이 되면 새로 고침을 해서 요소를 보이게함

			}
		});

		sort.addActionListener(new ActionListener() { // 정렬

			@Override
			public void actionPerformed(ActionEvent e) {

				Memo first = null;

				if (todo.memos.size() != 0)
					first = todo.memos.get(0);
				else
					return; // 정렬 내용이 없음....

				int xDistance = first.xDistance;
				int yDistance = first.yDistance;
				int x = 0;
				int y = 0;

				for (JPanel p : todo.memos) {

					int width = first.width;
					int height = first.height;

					if (!p.equals(first)) {
						x = first.getX() + first.getWidth() + xDistance;
						y = first.getY();
					}

					if (x > todo.mainbox.getWidth() - first.getWidth()) {
						x = 0;
						y = first.getY() + first.getHeight() + yDistance;

					}

					p.setBounds(x, y, width, height);

					first = (Memo) p;
				}

				todo.refresh();

			}
		});

		removeAll.addActionListener(new ActionListener() { // 전체삭제

			@Override
			public void actionPerformed(ActionEvent e) {

				for (JPanel p : todo.memos) {
					p.setVisible(false);
					todo.mainbox.remove(p);
				}

				ArrayList<JPanel> remove = new ArrayList<JPanel>(todo.memos);

				todo.memos.removeAll(remove);

//				System.out.println(todo.memos.size());

				todo.refresh();
			}
		});

	}

}

class Memo extends JPanel {

	String content;
	int x, y;
	int width, height;
	Todo todo;
	int xDistance;
	int yDistance;

	public Memo(String content, Todo todo) {

		this.content = content;
		this.todo = todo;

		x = 0;
		y = 0;
		width = 100;
		height = 150;

		// width = 484 // distance 28
		// height = 538 // distance 44

		this.xDistance = 28;
		this.yDistance = 44;

		if (todo.memos.size() != 0)
			point();

		setBounds(x, y, width, height);
//		setSize(100, 150); // 여기선 사이즈 가 조절이 안되다가
//		setPreferredSize(new Dimension(100, 150)); // 이것도 안먹고

		Color[] colors = { Color.yellow, Color.green, Color.cyan, };
		int colorIndex = (int) (Math.random() * 3);
		setBackground(colors[colorIndex]);

		JButton removeBtn = new JButton("지우기");
//		removeBtn.setSize(100, 150); // 버튼의 사이즈를 늘렸는데 버튼은 안늘어 나고 패널이 늘어난다...?
		add(removeBtn);

		removeBtn.addActionListener(new ActionListener() { // 삭제

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = todo.memos.indexOf(Memo.this);
				Memo.this.setVisible(false);
				todo.mainbox.remove(todo.memos.get(index));

				todo.memos.remove(index);
				todo.refresh();
//				System.out.println(todo.memos.size());

			}
		});

		init();

	}

	void point() {

		JPanel last = todo.memos.get(todo.memos.size() - 1);

		x = last.getX() + width + xDistance;

		y = last.getY();

		if (x > todo.mainbox.getWidth() - width) {
			x = 0;
			y = last.getY() + height + yDistance;
		}

	}

	void init() {

		int stringNum = 7;

		int count = content.length() / stringNum;
		int remainder = content.length() % stringNum;

		int cnt = 1;
		for (int i = 0; i < content.length(); i += stringNum) {
			if (cnt <= count) {
				JLabel lb = new JLabel(content.substring(i, i + stringNum));
				add(lb);
				cnt++;
			} else {
				break;
			}
		}

		JLabel lb = new JLabel(content.substring(stringNum * count, (stringNum * count) + remainder));

		lb.setSize(width, height); // 이걸 안하면 왜 사이즈 유지가 안되지 ?
		add(lb);

	}

}

public class Main {

	public static void main(String[] args) {

		new Todo();

	}

}
