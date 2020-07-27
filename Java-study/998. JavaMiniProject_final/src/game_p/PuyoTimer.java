package game_p;

public class PuyoTimer extends Thread { // Ÿ�̸� ������ // ������ ���� ����

	MePuyoPanel panel; // �����ӿ� �ִ� ������ ������Ʈ �ϱ� ���� �޾ƿ�
	int second; // ��
	boolean first;

	public PuyoTimer(MePuyoPanel panel) {
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.second = 0;
		this.first = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				if (panel.meInfo.endGame) // ������ ���������� ��� ����
					return;

				if (!first) {

					if (second % 30 == 0) { // 30�ʸ��� �ӵ��� 2�� ����

						panel.step += 2;
					}
				}
				panel.info.score.setText("���� : " + (panel.score + panel.combo) + "��");
				panel.info.combo.setText("���� : " + panel.comboCnt + "����");
				panel.info.second.setText("��� �ð� : " + second + "s");

				// updateInfo();
				sleep(1000);
				second++;

				first = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
