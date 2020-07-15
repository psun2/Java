package puyopuyo_failed3;

import javax.swing.JLabel;

public class Puyo extends JLabel {

	PuyoFrame frame; // 프레임 정보를 가져옴 why? 뿌요정보때문에...
	String img;
	boolean chk = true; // 이부분이 false가 되면 이 뿌요는 더이상 무브하지 않는다.

	public Puyo(String img) {
		// TODO Auto-generated constructor stub

		setText(img);
		setName(img);
		setOpaque(true);
	}

	@Override
	public String toString() {
		return "Puyo [img=" + img + ", chk=" + chk + "]";
	}

}
