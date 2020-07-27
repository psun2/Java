package game_p;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuyoGameInfo extends JPanel {

	JLabel score; // ���� ��
	JLabel combo; // �޺� ��
	JLabel second; // �ð� ��

	public PuyoGameInfo() {
		// TODO Auto-generated constructor stub

		setLayout(null);
		setBackground(Color.black);

		score = new JLabel("����");
		combo = new JLabel("�޺�");
		second = new JLabel("��� �ð�");

		score.setForeground(Color.white);
		combo.setForeground(Color.white);
		second.setForeground(Color.white);

		score.setBounds(0, 0, 100, Puyo.PUYOSIZE);
		combo.setBounds(100, 0, 200, Puyo.PUYOSIZE);
		second.setBounds(200, 0, 300, Puyo.PUYOSIZE);

		add(score);
		add(combo);
		add(second);

		setVisible(false);
		setVisible(true);

	}
}