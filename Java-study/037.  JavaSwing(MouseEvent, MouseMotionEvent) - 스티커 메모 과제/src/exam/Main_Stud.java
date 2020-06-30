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
import javax.swing.JTextField;

/*
 * 추가버튼을 누르면 포스트잇이 생김
 * 
 * 그 안에 무언가를 적어넣을 수 있음
 * 
 * 옮기기 가능하게 -> 드래그를 활용하자
 * 
 * 삭제 가능하게 -> 더블클릭으로 삭제를 가능하게 해보자 
 * 
 * 정렬 버튼 추가하기...
 * 
 * 
 * 
 */
public class Main_Stud extends JFrame implements MouseMotionListener, MouseListener {
	String text = "";
	ArrayList<PostIt> post;
	JTextField jf;

	int cnt = 0; // 더블클릭하면 삭제되게할 count
	boolean chk = false;
	boolean chk2 = false;

	class PostIt extends JLabel {

		int size;
		int x, y;
		String text = "";

		public PostIt(String text) {
			x = 50;
			y = 100;

			size = 100;
			this.text = text;
			setText(text);
			setBounds(x, y, size, size);
			setBackground(new Color((int) (Math.random() * 200 + 55), (int) (Math.random() * 200 + 55),
					(int) (Math.random() * 200 + 55)));
			setOpaque(true);

			Main_Stud.this.add(this);
//			System.out.println(Main_Stud.this.getComponentCount()); // 추가가 되도 계속 1
		}

	}

	public Main_Stud() {
		post = new ArrayList<Main_Stud.PostIt>();

		setBounds(100, 50, 800, 800);
		setTitle("냉장고 게시판");
		setLayout(null);

		JLabel anno = new JLabel("포스트잇 안에 작성할 메모를 입력하세요 : ");
		anno.setBounds(30, 20, 250, 30);
		add(anno);

		jf = new JTextField();
		jf.setBounds(280, 20, 200, 30);
		add(jf);

		text = jf.getText();

		JButton maker = new JButton("포스트잇 만들기");
		maker.setBounds(50, 60, 150, 30);
		add(maker);
		maker.addActionListener(new InnAction());

		JButton sorter = new JButton("포스트잇 정렬하기");
		sorter.setBounds(220, 60, 150, 30);
		add(sorter);
		sorter.addActionListener(new InnAction2());

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
		addMouseListener(this);

		new Timer().start();
	}

	class InnAction implements ActionListener { // 생성버튼 관련 액션리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			chk = true;
//            System.out.println(jf.getText()); 
		}

	}

	class InnAction2 implements ActionListener {// 정렬버튼 관련 액션리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			chk2 = true;
		}

	}

	class Timer extends Thread {
		int sero;
		int garo;

		@Override
		public void run() {
			while (true) {
				try {
					sleep(1);
					if (chk) { // 생성 버튼을 누를때마다 포스트잇을 만들고 다시 chk를 false로 만듬
						post.add(new PostIt(jf.getText()));
						jf.setText(""); // textfield초기화
//                        System.out.println(post);
						chk = false;
					}

					if (chk2) {// 정렬버튼을 누르면 한줄에 4개씩 정렬
						garo = 0;
						sero = 0;
						for (int i = 0; i < post.size(); i++) {
							if (i % 4 == 0) {
								garo = 0;
								sero += 200;
							}
							post.get(i).setLocation(50 + 150 * garo, 50 + sero);
							garo++;
						}
						chk2 = false;
					}

				} catch (InterruptedException e) {

				}
			}
		}
	}

	public static void main(String[] args) {
		new Main_Stud();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < post.size(); i++) {
			if (e.getX() < post.get(i).getX() + 100 && e.getX() > post.get(i).getX()
					&& e.getY() < post.get(i).getY() + 100 && e.getY() > post.get(i).getY()) {
				cnt++;
				if (cnt == 2) { // 움직이거나 드래그하지않고 더블클릭하면 포스트가 삭제됨
					post.get(i).setVisible(false);
					post.remove(i);
				}
				break;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		for (int i = 0; i < post.size(); i++) {
			cnt = 0; // 드래그 할때마다 count 초기화
			if (e.getX() < post.get(i).getX() + 100 && e.getX() > post.get(i).getX()
					&& e.getY() < post.get(i).getY() + 100 && e.getY() > post.get(i).getY()) {
				post.get(i).setLocation(e.getX() - 50, e.getY() - 50);
				break;
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cnt = 0; // 움직일때 카운트를 초기화함
	}
}