package lobby_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import game_p.PuyoFrame;
import game_p.WaitRoom;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;
import lobby_p.RankMain_GUI;



public class Lobby_Main extends JFrame implements DDongInter, WindowListener{


   public ClientNetWork cn;
   DDongData dData;

   JTextArea textArea; // ��ȭ ������� �ԷµǴ� â
   JTextField wrArea; // �޼��� �Է�â   
   
   JScrollPane js;
   JPanel roomList; // �渮��Ʈ
   String ttt;
   
   JPanel userListA;
   String id;
   JTable lobbyT;
   JScrollPane sp2;
   //   UserList_Main userList;

   private JPanel contentPane;


   void UserList_Main (){

      if(sp2!=null) {
         
         userListA.removeAll();
         lobbyT.removeAll();
         sp2.removeAll();
      }
      
      userListA = new JPanel();
      userListA.setBounds(662, 20, 210, 400);
      userListA.setBackground(Color.magenta);
      userListA.setLayout(null);
      contentPane.add(userListA);
      
      JLabel lobbyLabel = new JLabel("�κ�");
      lobbyLabel.setBounds(0, 0, 210, 50);
      lobbyLabel.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
      lobbyLabel.setHorizontalAlignment(JLabel.CENTER);
      userListA.add(lobbyLabel);


      ArrayList<LobbyDTO> arr = new LobbyDAO().list();


      Object [] index ={"ID"};
      Object [][] lobbyL = new Object[arr.size()][1]; 


      for (int i =0; i<arr.size();i++) {

         lobbyL[i][0] = arr.get(i).getId();
      }

      lobbyT = new JTable(lobbyL,index);
      lobbyT.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
      sp2 = new JScrollPane(lobbyT);
      sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      sp2.setBounds(0, 50, 210, 350);
      userListA.add(sp2);

   }


   public Lobby_Main(ClientNetWork cn) {

      this.cn = cn;
      cn.ddInter = this;

      setSize(900, 650);
      setLocationRelativeTo(null);
      setTitle("��������"); // Ÿ��Ʋ
      setIconImage(new ImageIcon("./img/logo.png").getImage());
      contentPane = new JPanel();
      contentPane.setLayout(null);
      setContentPane(contentPane);


      // == ȭ�� ���κ� =====================
      // -- �� ����Ʈ -------
      roomBtn();
      //       -- �� ����Ʈ �� -------

      // -- �������� ����Ʈ -------
      UserList_Main();
      // -- �������� ����Ʈ �� -------


      // == ȭ�� ���κ� �� =====================



      // == ȭ�� �Ʒ��κ� =====================

      // -- ä��â -------
      JPanel chatArea = new ChatUser();
      chatArea.setBounds(25, 430, 618, 175);
      chatArea.setBackground(Color.MAGENTA);
      contentPane.add(chatArea);
      // -- ä��â �� -------


      // -- ��ŷ ��ư -------
      JButton rankBtn = new JButton("��ŷ");
      rankBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
      rankBtn.setBounds(662, 430, 210, 100);
      rankBtn.setBackground(Color.PINK);
      contentPane.add(rankBtn);

      rankBtn.addActionListener(new RankBtnAction());
      // -- ��ŷ ��ư �� -------

      // -- ������ ��ư -------
      JButton exitBtn = new JButton("��������");
      exitBtn.setFont(new Font("�޸յձ�������", Font.BOLD, 16));
      exitBtn.setBounds(662, 537, 210, 67);
      exitBtn.setBackground(Color.lightGray);
      contentPane.add(exitBtn);

      exitBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            new LobbyDAO().delete(cn.id);

            System.exit(0);
            
            dData = new DDongData(); 
            dData.type = "�κ�";
            cn.send(dData);
         }
      });
      // -- ������ ��ư -------



      // == ȭ�� �Ʒ��κ� �� =====================

      LobbyDAO dao = new LobbyDAO();

      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //dao.delete(cn.id); // Ŭ��ũ�� ������ �κ񸮽�Ʈ ��񿡼� �����ǰ� �����ϱ�

      addWindowListener(this); // ������â�� x�� ������ ������ �Ѵ�

      DDongData data = new DDongData();
      data.type = "�κ�";
      cn.send(data);

   }


   void roomBtn() { // ���ư


      if(js!=null) {

         roomList.removeAll();
         js.removeAll();
         contentPane.remove(js);
      }

//      userList.init();

      roomList = new JPanel();
      roomList.setPreferredSize(new Dimension(582, 600));
      //      roomList.setBounds(30,30,770,1200);
      js = new JScrollPane(roomList);
      js.setBounds(25, 20, 618, 400);
      roomList.setLayout(null);
      //      js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      //      js.setLayout(null);
      contentPane.add(js);


      for (int i = 1; i <19; i++) {

         Integer roomN = i;
         int btnX = 0;
         int btnY = 0;

         if(i%3==1) {

            btnY += 100*(i/3);
         }else if(i%3==2) {
            btnX = 206;
            btnY += 100*(i/3);
         }else if(i%3==0) {
            btnX = 412;
            btnY += 100*(i/3-1);
         }

         JButton roombtn = new JButton();
         roombtn.setBounds(btnX, btnY, 206, 100);
         roombtn.setBackground(Color.yellow);
         JLabel btnName = new JLabel();
         btnName.setFont(new Font("�������",Font.BOLD, 14));
         ttt = "<html>NO. " + roomN + " (0 / 2)<br> >>�游��� << </html>";
         btnName.setText(ttt);
         roombtn.add(btnName);
         roomList.add(roombtn);


         // ���� ������Ʈ ������ �۵� �� ����
         if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
               new GameRoomDAO().detailroom(roomN).getUser2()==null) {

            roombtn.setBackground(Color.cyan);
            ttt = "<html>NO. " + roomN + "  (1 / 2)<br> >> �����ϱ�</html>";
            btnName.setText(ttt);

         } else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
               new GameRoomDAO().detailroom(roomN).getUser2()!=null) {

            roombtn.setBackground(Color.MAGENTA);
            ttt = "<html>NO. " + roomN + " (2 / 2)<br>- �ο���<br>  ���� �� �ֽ��ϴ� -</html>";
            btnName.setText(ttt);
         }

         //���ư�� ��������
         roombtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

               GameRoomDTO dto = new GameRoomDTO();

               dData = new DDongData();

               if(new GameRoomDAO().detailroom(roomN).getUser1()==null &&
                     new GameRoomDAO().detailroom(roomN).getUser2()==null) {

                  roombtn.setBackground(Color.cyan);
                  ttt = "<html>NO. " + roomN + " (1 / 2)<br> >> �����ϱ� </html>";
                  btnName.setText(ttt);


                  System.out.println(roomN+"���� ������ ���� ���� ��������");
                  dto.setNo(roomN);
                  dto.setUser1(cn.id);

                  new GameRoomDAO().modifyUser1(dto);

                  new LobbyDAO().delete(cn.id);

                  //                  System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");

                  WaitRoom goGame = new WaitRoom(cn.id,roomN); // ���⼭ ���ӻ���
                  goGame.cn = cn;
                  cn.ddInter = goGame;
                  
                  dData = new DDongData(); 
                  dData.type = "����"; // 
                  cn.send(dData);
                  System.out.println("�̾� ����"+dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");

                  dispose();

               } else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
                     new GameRoomDAO().detailroom(roomN).getUser2()==null) {

                  roombtn.setBackground(Color.MAGENTA);
                  ttt = "<html>NO. " + roomN + "(2 / 2)<br>- �ο���<br>  ���� �� �ֽ��ϴ� -</html>";
                  btnName.setText(ttt);

                  System.out.println(roomN+"���� ������ 1�� �ִ°��� ��������");

                  dto.setNo(roomN);
                  dto.setUser2(cn.id);

                  new GameRoomDAO().modifyUser2(dto);

                  new LobbyDAO().delete(cn.id);

                  WaitRoom goGame = new WaitRoom(cn.id,roomN); // ���⼭ ���ӻ���
                  goGame.cn = cn;
                  cn.ddInter = goGame;

                  dData = new DDongData(); 
                  dData.type = "����"; // ���ӹ濡�� DB������Ʈ ������ �˷��ֱ� ���ؼ� ���
                  cn.send(dData);
                  System.out.println(dData.type.toString()+","+dData.toString()+"���� �ι� �������� Ÿ��,������1");
                  dispose();

               }else if(new GameRoomDAO().detailroom(roomN).getUser1()!=null &&
                     new GameRoomDAO().detailroom(roomN).getUser2()!=null) {

                  System.out.println(roomN+"���� �ִ°��� ��������");
                  JOptionPane.showMessageDialog(null, "���� �� á���ϴ�.\n�ٸ����� �̿��� �ּ���!!");
               }

            }
         });
      }

      js.setVisible(false);
      js.setVisible(true);
   }


   // == ��ŷ��ư ������ ===
   class RankBtnAction implements ActionListener{ // ��ŷ ��ư ������

      public RankBtnAction() {

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         JFrame rankPop = new JFrame();
         rankPop.setBounds(500, 75, 536, 700);
         rankPop.setTitle("��ŷ");
         rankPop.setIconImage(new ImageIcon("./img/logo.png").getImage());
         rankPop.getContentPane().add(new RankMain_GUI());
         rankPop.setVisible(true);
      }
   }
   // == ��ŷ��ư ������ �� ===

   class ChatUser extends JPanel {

      public ChatUser() { // ������

         try {

            setBounds(0, 0, 600, 100);
            setLayout(null);

            // chatArea - ��ȭâ
            textArea = new JTextArea(); // ��ȭâ
            textArea.setEnabled(false); // ��ȭ�� �ԷµǴ� �����̹Ƿ� �ؽ�Ʈ�� �Է����� ���ϰ� ���´�
            JScrollPane js = new JScrollPane(textArea);
            js.setBounds(0, 0, 618, 135);
            textArea.setBackground(new Color(250, 250, 250));
            textArea.setFont(new Font("�����ٸ����", Font.BOLD, 15));
            textArea.setForeground(Color.black);
            add(js);
            // == ��ȭâ swing ==============

            // wrArea - �޼��� �Է�â
            wrArea = new JTextField();
            wrArea.setBounds(0, 135, 618, 40);
            //            wrArea.setBackground(Color.magenta);
            wrArea.setFont(new Font("�����ٸ����", Font.BOLD, 16));
            add(wrArea);
            // == �޼��� �Է�â swing ==============

            wrArea.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {

                  try {

                     dData = new DDongData();
                     dData.type = "ä��";
                     dData.data = wrArea.getText();
                     cn.send(dData);

                     wrArea.setText(""); // �޼����� �Է��� �޼��� â�� ����ش�
                     wrArea.setFocusable(true); // Ŀ���� �Ǿ����� �����ش�

                  } catch (Exception e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
               }
            });

         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      } // == ChatUser_test() �� =====================
   }


   @Override
   public void reciver(DDongData dd) {

      System.out.println("Tq ������ �޴´�" + dd);

      if(dd.type.equals("ä��")) {

         System.out.println(dd.data.toString()+" - ����Ʈ���ִ°��ε� ������Ÿ�Թ���");
         textArea.append(" ["+dd.src+"]  "+dd.data+"\n"); // ���⼭ �������µ�
         // �о� �� ������ ��ȭâ�� �Է����ְ� �ٹٲ��� ���ش�
         textArea.setCaretPosition(textArea.getDocument().getLength());
         // ��ũ���� �� ������ ��ȭ�ʿ� ��ġ���ش�

      } else if(dd.type.equals("�κ�")||dd.type.equals("����")) {
         
         System.out.println("����� �κ�");
         UserList_Main();
         roomBtn();
      }

   }


   @Override
   public void windowActivated(WindowEvent arg0) {
      // TODO Auto-generated method stub

   }


   @Override
   public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub

   }


   @Override
   public void windowClosing(WindowEvent e) {
      // TODO Auto-generated method stub

      dispose();
      new LobbyDAO().delete(cn.id);
      dData = new DDongData(); 
      dData.type = "�κ�";
      cn.send(dData);
   }


   @Override
   public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub

   }


   @Override
   public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub

   }


   @Override
   public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub

   }


   @Override
   public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub

   }
}