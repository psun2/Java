// 200729 �����Ϸ�
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

   Lobby_Main lobby;


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
      Object [][] myRankL = new Object[1][1];

      for (User chk : userInfo) { // ������ �ޱ�� ���� ������� ��� �� �迭�� ����ش�

         chk.rankCalc(userInfo); // ��ŷ ��� �� ������� ��� �ش�

         userL[cnt][0] = chk.rank;
         userL[cnt][1] = chk.id;
         userL[cnt][2] = chk.score;

         cnt++;
      }


      JLabel totRank = new JLabel("��ü����");
      totRank.setBounds(29, 20, 110, 30);
      totRank.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
      add(totRank);

      JTable rankT = new JTable(userL, index);
      rankT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
      rankT.setEnabled(false);
      JScrollPane sp2 = new JScrollPane(rankT);
      sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      sp2.setBounds(29, 60, 450, 450);
      add(sp2);

   }

   public static void main(String[] args) {

      new RankMain_GUI();
   }
}