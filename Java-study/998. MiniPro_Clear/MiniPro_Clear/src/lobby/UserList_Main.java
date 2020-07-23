package lobby;

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

		JLabel lobbyList = new JLabel("로비");
		lobbyList.setBounds(0, 0, 261, 50);
		lobbyList.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
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
	
					int cnt = 0; // 테이블에 들어갈 내용 배열 줄수 체크를 위해 생성
					index = new Object [] {"ID"}; // 로비 유저 리스트 컬럼
					lobbyL = new Object[getLobby.size()][1]; // 로비인 유저 아이디 담을 배열

					for (String list : getLobby) { // 로비인 유저 접속순으로 출력

						lobbyL[cnt][0] = list;

						cnt++;
					}
					
					lobbyT = new JTable(lobbyL,index);
					lobbyT.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
					JScrollPane sp2 = new JScrollPane(lobbyT);
					sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					sp2.setBounds(0, 50, 261, 550);
					add(sp2);

					sleep(1500); // 1.5초에 한번씩 업데이트
//					System.out.println("쓰레드 계속 도나??");

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
