package game_p;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.xml.crypto.Data;

import jdbc_p.RankDAO;
import jdbc_p.RankDTO;

public class MePuyoPanel extends JPanel {

	String id;

	ImageIcon background;

	MeGameInfo meInfo;
	PuyoGameInfo info;

	Puyo me;
	Puyo you;

	MyLabel meLb;
	MyLabel youLb;

	ArrayList<MyLabel> puyoLbs;
	HashSet<MyLabel> bombArr;
	HashSet<String> bombArrColor;
	HashMap<String, HashSet<MyLabel>> map;

	int LocationX, LocationY, endLine, step, cutLine, score, jum, combo, comboCnt, comboChk; // �ѿ䰡 ���������� ĭ��

	boolean bombChk, itemChk, lobbyChk;

	PuyoTimer timer;

	PuyoFrame frame;

	String itemColor;

	public ExecutorService threadPool;

	public MePuyoPanel(PuyoFrame frame) {
		// TODO Auto-generated constructor stub

		init(frame);

		// TODO Auto-generated constructor stub
		this.background = new ImageIcon("./img/background.png");
		setLayout(null);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

		createPuyo(); // �ѿ���� ��ŸƮ
		timer.start();

	}

	void init(PuyoFrame frame) {

		// �г��� �����Ǿ� add �Ǿ��ٴ°��� ������ ������ �ǹ�

		this.id = frame.meId;
		this.frame = frame;
		this.threadPool = Executors.newCachedThreadPool(); // ������ Ǯ �ʱ�ȭ
		this.meInfo = new MeGameInfo(); // ���� ���� Ŭ���� ����
		this.step = 3; // �ѿ䰡 ���������� ĭ��
		this.puyoLbs = new ArrayList<MyLabel>();
		this.map = new HashMap<String, HashSet<MyLabel>>();
		this.bombArr = new HashSet<MyLabel>();
		this.bombArrColor = new HashSet<String>();
		this.bombChk = false;
		this.itemChk = false;
		this.lobbyChk = false;
		this.score = 0;
		this.jum = 0;
		this.combo = 0;
		this.comboCnt = 0;
		this.itemColor = "nuisance";
		this.LocationX = (Puyo.PUYOSIZE * 6) / 2; // �ѿ���� ���� ��ġ�� ��Ʊ����� x
		this.LocationY = -Puyo.PUYOSIZE; // �ѿ���� ���� ��ġ�� ��Ʊ����� y
		// this.endLine = LocationY - 1;
		this.endLine = 0;
		this.timer = new PuyoTimer(this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	void createPuyo() { // �ѿ��� ������� ������ ���Ͽ� ������ ���

		Runnable thread = new Runnable() { // �ѿ� ���� ������ => �����Ӱ� ���� ���ƾ� �ϹǷ� �����带 ��

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) { // ������ ���������� ���� �ݺ��Ѵ�.

					endChk();

					if (meInfo.endGame) { // end ���ӳ��� chk
						// System.out.println("��������");
						// �̱۸�� : // JOptionPane.showMessageDialog(MePuyoPanel.this, "���� ����!");

						System.out.println("��������");
						setVisible(false); // ȭ�鿡 ȸ���� �׸�������
						setVisible(true);

						updateRank(); // ���� ������Ʈ
//						frame.data.chk = true;

						if (frame.threadPool != null && !frame.threadPool.isShutdown()) {
							frame.threadPool.shutdown();
						}

						if (threadPool != null && !threadPool.isShutdown()) { // ������ ������ ������ Ǯ�� ���� �ִٸ�
							threadPool.shutdown(); // ������ �������Ƿ� ��� �����带 ����.
						}

						if (frame.enemyData.endGame && MePuyoPanel.this.meInfo.endGame) {
							MePuyoPanel.this.lobbyChk = true;
							ModalFrame mf = new ModalFrame(MePuyoPanel.this.frame, "�¸�");
						} else { // ������ ������ �ȳ������� ���� �й�
							ModalFrame mf = new ModalFrame(MePuyoPanel.this.frame, "�й�");
						}

						if (!lobbyChk)
							frame.cn.send(frame.data);

						return; // ���� ����
					}

//					if (MePuyoPanel.this.itemChk)
//						addItem();

					if (!bombChk) {

						// me ����
						me = new Puyo();
						meLb = new MyLabel(new ImageIcon("./img/" + me.color + "-48.png"));
						meLb.setBounds(LocationX, LocationY, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
						meLb.setName(me.color);
						add(meLb);
						meInfo.puyos.add(me); // ����Ŭ������ ���� ����
						puyoLbs.add(meLb); // �󺧸� ���� ����
						addMap(meLb); // �� �гο��� ����� ���� ����

						sleepThread();

						// you ����
						you = new Puyo();
						youLb = new MyLabel(new ImageIcon("./img/" + you.color + "-48.png"));
						youLb.setBounds(LocationX, LocationY - Puyo.PUYOSIZE, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
						youLb.setName(you.color);
						add(youLb);
						meInfo.puyos.add(you);
						puyoLbs.add(youLb);
						addMap(youLb);

						move(); // ����

					} else {

						if (bombChk()) { // �����忩�� ��� �����Ű�� ���� �߻�
							bomb(); // �׻� ���Ѻ��ٰ� ���� ���� 4�� �̻� ������ ���� ������ ����
							if (MePuyoPanel.this.itemChk)
								addItem();
							bombChk = false; // 356 Line ���� ���� ���� �Ұ��� ����, ������ �Ǿ� ��� ������
							// bomb �޼ҵ忡�� ���� ���Ƿ� �� �޼ҵ��� ��������
							// �ٸ� ��Ұ� ���ü� �ֵ��� boolean ��������
							modifyNode();
						} else {
							if (MePuyoPanel.this.itemChk)
								addItem();
							bombChk = false; // 356 Line ���� ���� ���� �Ұ��� ����, ������ �Ǿ� ��� ������
							// bomb �޼ҵ忡�� ���� ���Ƿ� �� �޼ҵ��� ��������
							// �ٸ� ��Ұ� ���ü� �ֵ��� boolean ��������
							modifyNode();
						}

					}

				}

			}

		};

		threadPool.submit(thread); // �����带 ����ϱ� ���� ������ Ǯ�� ���

	}

	void addMap(MyLabel puyo) { // �����ɶ� �򺰷� ������ �ѹ��� ����

		if (this.map.containsKey(puyo.getName())) {

			map.get(puyo.getName()).add(puyo);

		} else {
			HashSet<MyLabel> set = new HashSet<MyLabel>();
			set.add(puyo);
			this.map.put(puyo.getName(), set);
		}

	}

	void move() { // �ڵ����� �������� y�ุ ������Ʈ �ϸ� �˴ϴ�.
		// �ѿ䰡 ���� �Ǹ� x ���� key����� �����ϰ�, y������ �ڵ����� �帥��.
		while (true) {

			if (me.stopChk && you.stopChk) { // �ѿ� �ѽ��� ���� �ڸ��� ��Ҵ�.
				modifyNode();
				comboChk = -1; // �޺� chk �ʱ�ȭ
				this.bombChk = true; // ������ ��� �ߴ��ϰ� ���� ��Ұ� �ִ��� �˻�
				return; // ���� �ѿ� ������ ���� �ݺ��� ����
			}

			sleepThread();
			endMove(me, meLb); // �ѿ䰡 ������ �귯���� ���ϴ�.
			fixBug(me, meLb); // ������ ����� ����� ����
			endMove(you, youLb); // �ѿ䰡 ������ �귯���� ���ϴ�
			fixBug(you, youLb); // ������ ����� ����� ����
			updateInfo();

		}

	} // move �Լ� ��

	void endMove(Puyo puyo, MyLabel lb) { // �ѿ䰡 ���������� �귯 ���� �� �� �ְ� �ϴ� �Լ�

		if (puyo.stopChk)
			return;

		int y = nodeY(lb); // y���� �ѿ䰡 ���õ� y���� ��

		if (y <= getSize().height - Puyo.PUYOSIZE) { // �ѿ��� y���� ���� �ٴڰ� ������ �Ҷ� ������ ���ǿ� ����
			// ���⼭ �ٴڱ��� ������....
			// �� ���� �ٸ� ��Ұ� �ִٸ� stop
			if (search(lb)) { // ���� ����� y ���� ���ڸ��� ������ �ɶ��� �ѿ䰡 �ڸ��� ��Ҵٶ�� �ǹ�. �� �ٴڿ� ���� ����.
				y = cutLine;
				puyo.stopChk = true; // �ѿ䰡 ���� ����� �� �ѿ��� ��ġ�� ���� �Ǿ����� ....
			}

			if (y >= getSize().height - Puyo.PUYOSIZE)
				y = getSize().height - Puyo.PUYOSIZE;

			lb.setLocation(lb.getX(), y);

		} else {
			puyo.stopChk = true; // �Ʒ� �ѿ� �Ǵ� ���� �϶� ��ġ�� ���� �Ǿ�����....
		}

	} // endMove ��

	int nodeY(MyLabel puyo) { // �ѿ��� y��ǥ�� ��ȯ �޴� �Լ�

		int y = puyo.getY();
		int result = y + step;

		return result;

	} // nodeY ��

	boolean search(MyLabel puyo) { // �ѿ䰡 ������ �带�� �� �ؿ� �����ΰ� ���� �Ѵٸ� y���� �ٽ� �����Ͽ� ��ȯ
		// ������ : �� ���� �����϶� ��ģ���� �Ʒ� ģ�� ������ �������� ���� �߻�
		boolean result = false;

		for (MyLabel pu : puyoLbs) {
			if (puyo.getX() == pu.getX()) { // x���� ���ƾ� ���� �����Դϴ�.
				if (puyo.getY() < pu.getY()) { // �����Ǵ� �ѿ��� y �� ���� ���� ���� ���� y���� ū �ֵ鸸 ����. => ������ �ؿ� �ִ� �ֵ�
					if (puyo.getY() + Puyo.PUYOSIZE > pu.getY()) { // ���� y�� �ؿ��ִ� �ѿ��� y���� ���ų� Ŀ������ �Ҷ�
						// System.out.println("puyo.Lb.getY() + Puyo.PUYOSIZE : " + (puyo.getY() +
						// Puyo.PUYOSIZE));
						// System.out.println(" pu.Lb.getY() : " + pu.getY());
						this.cutLine = pu.getY() - Puyo.PUYOSIZE;
						// System.out.println("this.cutLine : " + this.cutLine);
						result = true;
					}
				}
			}

		}

		return result;

	} // inspectY �Լ� ��

	void modifyNode() {

		for (MyLabel puyo : puyoLbs) {

			// y ��ǥ�� �ڲ� �Դ� ���� �߻�!!!!
			// y ��ǥ�� 50���� ������ 0�� �ȵǴ� �� ã��

			if (puyo.getY() % Puyo.PUYOSIZE != 0) {
				// modifyArr.add(puyo);
				double y = puyo.getY();
				double remainder = Puyo.PUYOSIZE - (puyo.getY() % Puyo.PUYOSIZE);
				int result = (int) (y + remainder);
				// ���� Ư���� ��ǥ�� �������� �ʰԵ� �� ���� ������ ũ�⸸ŭ ���� ������ ��ŭ�� �����־� �ٽ� ����

				puyo.setLocation(puyo.getX(), result);

			}

		}

	}

	void fixBug(Puyo puyo, MyLabel lb) { // ������ ����� ����� ���� (�߰��� �ɸ��� ����)

		if (!puyo.stopChk)
			return;

		int chk = 0;
		if (puyo.stopChk && lb.getY() != getSize().height - Puyo.PUYOSIZE) {

			for (MyLabel pu : puyoLbs) {

				if (lb.getY() + Puyo.PUYOSIZE == pu.getY())
					chk++;
			}

		}

		// System.out.println(chk);

		if (chk == 0) {
			puyo.stopChk = false;
			endMove(puyo, lb);
		}

	}

	////////////// TODO ���� ����
	boolean bombChk() {

		boolean result = false;

		for (Entry<String, HashSet<MyLabel>> puyo : map.entrySet()) {

			if (!puyo.getKey().equals(itemColor)) {
				if (puyo.getValue().size() >= MeGameInfo.PANG) {
					this.bombArrColor.add(puyo.getKey());
				}
			}

		}

		if (this.bombArrColor.size() != 0)
			result = true;

		return result;

	}

	void bomb() {

		// for���� ���ư��� map�� remove�� �ǹǷ� Ŭ���� �ϳ� ��� �մϴ�.
		HashMap<String, HashSet<MyLabel>> cloneMap = new HashMap<String, HashSet<MyLabel>>(this.map);

		for (String color : bombArrColor) {

			for (Entry<String, HashSet<MyLabel>> colorMap : cloneMap.entrySet()) {

				if (colorMap.getKey().equals(color)) {
					deepBomb(colorMap.getValue());
				}
				this.bombArr = new HashSet<MyLabel>(); // ���� ����� ���� �ʿ� �����Ƿ� �ʱ�ȭ
				this.bombArrColor = new HashSet<String>();

			}

		}

		// System.out.println("bombArrColor : " + bombArrColor);

	}

	void deepBomb(HashSet<MyLabel> colors) { // clolors �� ������Ʈ�� �ȵ�

		for (MyLabel puyo : colors) {

			modifyNode();

			HashSet<MyLabel> equals = new HashSet<MyLabel>(); // �پ� �ִ� ���� ���� ū ������� ���� �迭

			equals.add(puyo);

			// ���ڰ��� �������� ������ �Ǵ� puyo�� ��ǥ�� ����
			int x = puyo.getX();
			int y = puyo.getY();

			for (MyLabel pu : colors) {

				if (x == pu.getX() && y == pu.getY() + Puyo.PUYOSIZE
						|| x == pu.getX() && y == pu.getY() - Puyo.PUYOSIZE)
					equals.add(pu);
				if (y == pu.getY() && x == pu.getX() + Puyo.PUYOSIZE
						|| y == pu.getY() && x == pu.getX() - Puyo.PUYOSIZE)
					equals.add(pu);

			}

			equals = deepDeepBomb(colors, equals);

			if (equals.size() >= 4) {
				bombArr = equals;
				jum = 10;
				this.score += bombArr.size() * jum;
				remove();
				empty();
				modifyNode();
				return;
			}

		}

	}

	HashSet<MyLabel> deepDeepBomb(HashSet<MyLabel> colors, HashSet<MyLabel> equals) {

		if (equals.size() <= 1)
			return equals;

		HashSet<MyLabel> result = new HashSet<MyLabel>();

		HashSet<MyLabel> removeColor = new HashSet<MyLabel>(colors);
		removeColor.removeAll(equals);

		for (MyLabel puyo : equals) {

			modifyNode();
			result.add(puyo); // ó������ result �� equals �� ���� �� ������ �� ������ �߰� set�̿��� �ߺ� �Ұ�!!

			// ���ڰ��� �������� ������ �Ǵ� puyo�� ��ǥ�� ����
			int x = puyo.getX();
			int y = puyo.getY();

			for (MyLabel pu : removeColor) {

				if (x == pu.getX() && y == pu.getY() + Puyo.PUYOSIZE
						|| x == pu.getX() && y == pu.getY() - Puyo.PUYOSIZE)
					result.add(pu);
				if (y == pu.getY() && x == pu.getX() + Puyo.PUYOSIZE
						|| y == pu.getY() && x == pu.getX() - Puyo.PUYOSIZE)
					result.add(pu);

			}

		}

//      System.out.println("** ���� colors : " + colors);
//      System.out.println("** ���� �ֵ鸸 ��ƿ� �迭 : " + equals);
//      System.out.println("**  ���� colors ���� ���� �ֵ��� ������ �迭 : " + removeColor);
//		System.out.println("** result �迭 : " + result);

		if (result.size() > equals.size())
			result = deepDeepBomb(colors, result);

		return result;

	}

	void remove() {

		for (MyLabel puyo : bombArr) {
			// ������ �ѿ� �гο��� ���� �۾�
			remove(puyo); // �гο��� ����
			setVisible(false); // update
			setVisible(true); // update
		}

		modifyNode();

		item(bombArr);

		comboChk++; // ó�� �������� 0 �̵ǰ�
		// ��������� �̱����� �� ��ĥ�� �������Ƿ� 1 �̵Ǽ� ���� �޺� ����
		if (comboChk > 0)
			this.comboCnt++;
		combo = comboCnt * (jum * 2);

	}

	void item(HashSet<MyLabel> bombArr) { // ������ ���� ������ ���������� ����
		// �������� ������ ���κ��� �ٽ� ������Ʈ �ϴ� �κ��� �߿�
		// �׷� ���������� ���λ����� ? meInfo�� ... ?

		ArrayList<MyLabel> item = new ArrayList<MyLabel>(bombArr);

		// ���� ����߿� ������ ���� �ִ��� Ȯ��
		for (MyLabel myLabel : item) {

			if (myLabel.getName().equals("ninja")) {
				meInfo.itemChk = true; // �ִٸ� true
				// System.out.println("�������� ������ ���� �Ϳ� : " + meInfo.itemChk);
				// �����ӿ��� ������ �ٽ� false�� �ٲ�
				break;
			}

		}

		modifyNode();

	}

	void empty() { // ���� �ѿ䰡 ������ ������� �޲ٴ� �޼ҵ� ����

		for (MyLabel puyo : bombArr) {
			// ���� �ѿ並 �� �� ��̿��� ����
			map.get(puyo.getName()).remove(puyo);
			meInfo.puyos.remove(equalsPuyo(puyo));
			puyoLbs.remove(puyo); // Ǫ����� ��� �迭���� ����
		}

		emptyMove();

	}

	void emptyMove() { // �ڵ����� �������� y�ุ ������Ʈ �ϸ� �˴ϴ�.

		// �ƿ� ó������ ������Ʈ �� �༮�� ��������
		// �ٴڿ� �ִ� ���̵��� �������� �ʿ� �����Ƿ� ����

		modifyNode();

		// HashSet�� ��� TreeSet���� �ȱ�� �۾��� ����
		// �Ʒ��� ���� �������� ������Ʈ ����
		HashSet<MyLabel> up = new HashSet<MyLabel>(); // hashSet => ���尡 ��ġ�� �Ǵ� ���� �߰�

		for (MyLabel puyo : bombArr) { // ���� �ֵ�
			for (MyLabel pu : puyoLbs) { // �����ֵ��� ����
				if (puyo.getX() == pu.getX()) { // ������ �ѿ�� x�� ����
					if (puyo.getY() > pu.getY()) { // ������ �ѿ亸�� ���� �ִٸ�...
						equalsPuyo(pu).stopChk = false;
						up.add(pu);

					}
				}

			}

		}

		TreeSet<MyLabel> updatePuyo = new TreeSet<MyLabel>(up);

		if (updatePuyo.size() == 0) { // ������Ʈ �� ��Ұ� ���ٸ� ����
			return;
		}

		// �ִٸ� �Ʒ��� ���� ����

		emptyEndMove(updatePuyo); // ��ҵ��� ������ �̵��� ������
		modifyNode();

		if (bombChk()) { // ��������� ���� ���� �ֳ� �˻��մϴ�.
			bomb();
		}

	} // move �Լ� ��

	void emptyEndMove(TreeSet<MyLabel> updatePuyo) {

		while (true) {

			for (MyLabel puyo : updatePuyo) {

				int index = 0;
				for (MyLabel pu : updatePuyo) {

					if (equalsPuyo(pu).stopChk)
						index++;

				}

				if (updatePuyo.size() == index) // ������Ʈ ��� ���� retuen
					return;

				if (!equalsPuyo(puyo).stopChk) {

					endMove(equalsPuyo(puyo), puyo); // �ѿ䰡 ������ �귯���� ���ϴ�.
					updateInfo();
					sleepThread();

				}

			}
		}

	}

	Puyo equalsPuyo(MyLabel lb) { // ������ �󺧿� �´� puyo Ŭ������ ��ȯ�ϴ� �޼ҵ�

		int index = puyoLbs.indexOf(lb);

		Puyo result = meInfo.puyos.get(index);

		return result;

	}

	void addItem() {

		modifyNode();

		ArrayList<MyLabel> lbs = positionUpdate(this.puyoLbs);

		int x = 0;
		int y = Puyo.PUYOSIZE * 12;

		for (int i = 0; i < 6; i++) {

			MyLabel lb = new MyLabel(new ImageIcon("./img/" + itemColor + "-48.png"));
			lb.setName(itemColor);
			lb.setBounds(x, y, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
			lbs.add(lb);
			meInfo.puyos.add(new Puyo(lb.getName()));
			x += Puyo.PUYOSIZE;

		}

		reInit(lbs);

	}

	ArrayList<MyLabel> positionUpdate(ArrayList<MyLabel> lbs) { // ��ĭ�� ���� �÷��ݴϴ�.

		ArrayList<MyLabel> result = new ArrayList<MyLabel>(lbs);

		for (MyLabel myLabel : result) {

			int x = myLabel.getX();
			int y = myLabel.getY() - Puyo.PUYOSIZE;

			myLabel.setLocation(x, y);

		}

		return result;

	}

	void reInit(ArrayList<MyLabel> lbs) {

		removeComponent(this.puyoLbs);

		// �������� �߰��� �迭�� �ٲ�ġ��
		this.puyoLbs = lbs;

		changeMap(this.puyoLbs);

		painting(this.puyoLbs);

		updateInfo();

		this.itemChk = false;

	}

	void changeMap(ArrayList<MyLabel> puyoLbs) {

		HashMap<String, HashSet<MyLabel>> newMap = new HashMap<String, HashSet<MyLabel>>();

		for (MyLabel puyo : puyoLbs) {

			if (!puyo.getName().equals(this.itemColor)) {

				if (!newMap.containsKey(puyo.getName())) {

					HashSet<MyLabel> newSet = new HashSet<MyLabel>();
					newSet.add(puyo);

					newMap.put(puyo.getName(), newSet);

				} else {

					newMap.get(puyo.getName()).add(puyo);

				}

			}

		}

		this.map = newMap;

	}

	void removeComponent(ArrayList<MyLabel> lbs) {

		for (MyLabel puyoLb : lbs) {
			remove(puyoLb);
		}

	}

	void painting(ArrayList<MyLabel> lbs) {

		// �׷��ֱ� �۾� -- allStop �Ǿ��� ������ �׷� �־����....

		for (MyLabel myLabel : lbs) {
			add(myLabel);
		}

		setVisible(false);
		setVisible(true);

	}

	void updateInfo() { // �ð����� �г��� ������
		// �����ٷ�� Ŭ������ ������ ������Ʈ �Ѵ�.
		// System.out.println("updateInfo");

		for (int i = 0; i < puyoLbs.size(); i++) {
			// System.out.println(puyoLbs.size());
			meInfo.puyos.get(i).x = puyoLbs.get(i).getX();
			meInfo.puyos.get(i).y = puyoLbs.get(i).getY();
		}

		meInfo.score = score;
		meInfo.combo = comboCnt;
		meInfo.second = timer.second;
		meInfo.total = score + combo;

		if (frame.data != null)
			frame.data.data = meInfo;

		// System.out.println("updateInfo");

	}

	void endChk() {

		for (MyLabel puyo : puyoLbs) {
			if (puyo.getX() == LocationX && puyo.getY() == endLine)
				meInfo.endGame = true;

		}
	}

	void updateRank() { // ��ŷ ������Ʈ

		RankDTO dto = new RankDAO().detail(id);

		Integer currentScore = dto.getScore();

		if (currentScore < meInfo.total)
			currentScore = meInfo.total;

		dto.setScore(currentScore);

		new RankDAO().modifyScore(dto);

	}

	void sleepThread() {

		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) { // ������ �������� ������ ����
		super.paint(g);
		if (!this.meInfo.endGame)
			return;
		BufferedImage img = (BufferedImage) createImage(getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
		g2.drawImage(img, 0, 0, null);
	}

}