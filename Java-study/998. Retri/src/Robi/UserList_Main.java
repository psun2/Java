package Robi;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;

public class UserList_Main extends JPanel{

	String id;

	
	public UserList_Main() {

		setBounds(0,0, 636,902);
		setLayout(null);

		JLabel lobbyList = new JLabel("유저목록");
		lobbyList.setBounds(0, 0, 261, 50);
		//lobbyList.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		add(lobbyList);

		JTable lobbyT = new JTable();

		new Real(lobbyT).start();

	}

	class Real extends Thread{

		ArrayList<String> getLobby;
		
		JTable lobbyT;
		
		Object [] index;
		Object [][] lobbyL;
		
		public Real(JTable jt) {
			
			this.lobbyT = jt;
		}

		
		@Override
		public void run() {

			while(true) {

				try {
					
					getLobby = new ArrayList<String>();

					for (LobbyDTO info : new LobbyDAO().list()) {
						id = info.getId();

						getLobby.add(id);
					}
	
//					System.out.println(getLobby.toString());
	
					int cnt = 0; // ���̺� �� ���� �迭 �ټ� üũ�� ���� ����
					index = new Object [] {"ID"}; // �κ� ���� ����Ʈ �÷�
					lobbyL = new Object[getLobby.size()][1]; // �κ��� ���� ���̵� ���� �迭

					for (String list : getLobby) { // �κ��� ���� ���Ӽ����� ���

						lobbyL[cnt][0] = list;

						cnt++;
					}
					
					lobbyT = new JTable(lobbyL,index);
					//lobbyT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
					JScrollPane sp2 = new JScrollPane(lobbyT);
					sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					sp2.setBounds(0, 50, 261, 550);
					add(sp2);

					sleep(1500); // 1.5�ʿ� �ѹ��� ������Ʈ
//					System.out.println("������ ��� ����??");

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}


	public static void main(String[] args) {
		
		new UserList_Main();
	}
}
