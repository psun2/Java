package newasd;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MeGUI extends JPanel {

	Socket socket;
	InfoGUI info;
	Game game;
	MyLabel one, two;

	int oneX, twoX, startY;

	Element oneEle, twoEle;

	boolean oneChk, twoChk;

	ArrayList<MyLabel> lbs;
//	ArrayList
//	HashMap

	public MeGUI(Socket socket, Game game) {
		// TODO Auto-generated constructor stub

		this.socket = socket;
		this.game = game;
		this.oneX = game.size * 2 / 2;
		this.twoX = (game.size * 2 / 2) + game.size;
		this.startY = -game.size;
		this.oneChk = true;
		this.twoChk = true;
		this.lbs = new ArrayList<MyLabel>();
		setLayout(null);
		setBackground(Color.CYAN);

		info = new InfoGUI();
		info.setBounds(0, 0, 50 * 6, 50);
		add(info);

		create();

	}

	void create() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					if (oneChk && twoChk) {
						oneEle = new Element(oneX, startY);
						one = new MyLabel(new ImageIcon("./img/" + oneEle.color + "-48.png"));
						one.setName(oneEle.color);
						one.setBounds(oneEle.x, oneEle.y, game.size, game.size);
						add(one);
						lbs.add(one);
						oneChk = false;

						twoEle = new Element(twoX, startY);
						two = new MyLabel(new ImageIcon("./img/" + twoEle.color + "-48.png"));
						two.setName(twoEle.color);
						two.setBounds(twoEle.x, twoEle.y, game.size, game.size);
						add(two);
						lbs.add(two);
						twoChk = false;
					}

					try {
						move(one);
						move(two);
						Thread.sleep(1000);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};

		GUI.threadPool.submit(thread);

	}

	void move(MyLabel lb) {
		System.out.println("asdasd");

		int x = lb.getX() / game.size;
		int y = lb.getY() / game.size;

		System.out.println(Arrays.deepToString(game.node));
		System.out.println(game.kan - 1);
		System.out.println(game.jul - 1);

		for (int i = 0; i < game.node.length; i++) {

			for (int j = 0; j < game.node[i].length; j++) {

				if (i == game.node.length - 1) {
					game.node[i][j] = 1;
					y = i;
					x = j;
				}

				y = i + game.size;
				x = j;

			}
		}

		lb.setLocation(x, y);

	}

}