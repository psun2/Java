package lobby_p;

import java.awt.Font;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jdbc_p.RankDAO;
import jdbc_p.RankDTO;

import javax.swing.ScrollPaneConstants;

class User implements Comparable<User>{

	String id;
	Integer score, rank;


	public User(String id, Integer score) {

		super();
		this.id = id;
		this.score = score;
		this.rank = rank;
	}


	void rankCalc(TreeSet<User> you){ // ���� �ޱ�� ��

		rank = 1;

		for (User uR : you) {
			if(score<uR.score)
				rank++;
		}
		
		RankDTO dto = new RankDTO();
		dto.setId(id);
		dto.setRank(rank);
		
		new RankDAO().modifyRank(dto); // �����ޱ�� ������ DB�� ����
	}


	@Override
	public String toString() {

		return rank + "," + id + "," + score;
	}


	@Override
	public int compareTo(User you) { // ���� ���� ���� ������ ���

		int res = you.score - score;

		if(res==0)
			res = id.compareTo(you.id);

		return res;
	}
}



public class RankMain_GUI extends JPanel {


	public RankMain_GUI() {

		setBounds(0,0, 636,902);
		setLayout(null);

		// == DB���� ������ ������ ������ �ޱ�� ========
		String id;
		Integer score;

		TreeSet<User> userInfo = new TreeSet<User>(); // �������� ������ ���� treeset, ������ �������ؼ� ��ŷ 

		RankDTO dto = new RankDTO();
		RankDAO dao = new RankDAO();

		for (RankDTO info : new RankDAO().list()) { // DB�� �ִ� ������ �����񱳸� ���� TreeSet�� ����

			id = info.getId();
			score = info.getScore();

			userInfo.add(new User(id, score));
		}

		
		int cnt = 0; // ���̺� �� ���� �迭 �ټ� üũ�� ���� ����
		
		Object [] index = {"RANK", "ID", "SCORE"}; // ��ŷ���̺� �÷�
		Object [][] userL = new Object[userInfo.size()][3]; // ������ ��ŷ, ������ ��ŷ������ ���� �迭

		for (User chk : userInfo) { // ������ �ޱ�� ���� ������� ��� �� �迭�� ����ش�

			chk.rankCalc(userInfo); // ��ŷ ��� �� ������� ��� �ش�

			userL[cnt][0] = chk.rank;
			userL[cnt][1] = chk.id;
			userL[cnt][2] = chk.score;
			
			cnt++;
		}

		
		// ��� ���߿� ������ ���������̰� �Ҷ� ����; ������ ���� �̿�
//		Object [][] myRankL = {("RANK", "ID", "SCORE")};
//		
//		JLabel myRank = new JLabel("������");
//		myRank.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
//		myRank.setBounds(30, 30, 110, 50);
//		getContentPane().add(myRank);
//		
//		JTable myRankT = new JTable(myRankL, index);
//		myRankT.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
//		JScrollPane sp1 = new JScrollPane((Component) null);
//		sp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		sp1.setBounds(30, 90, 450, 50);
//		getContentPane().add(sp1);
		
		JLabel totRank = new JLabel("��ü����");
		totRank.setBounds(30, 100, 110, 50);
		totRank.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		add(totRank);
		
		JTable rankT = new JTable(userL, index);
		rankT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		JScrollPane sp2 = new JScrollPane(rankT);
		sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp2.setBounds(30, 160, 450, 450);
		add(sp2);
		
	}

	public static void main(String[] args) {

		new RankMain_GUI();
	}
}
