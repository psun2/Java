package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import lobby_p.Lobby_Main_Clear;

public class GameRoom_GUI extends JFrame implements DDongInter {

   final int width = 806;
   final int height = 679 + 50;

   boolean readyChk;

   public ClientNetWork cn;
   JToggleButton ready;
   JPanel meP, youP, state;
   JLabel meLb, youLb;

   public Integer roomNum;
   public String id;
   String enenmy;

   DDongData data;

   HashSet<String> dst;

   ExecutorService threadPool;

   public GameRoom_GUI() {
      // TODO Auto-generated constructor stub

      this.dst = new HashSet<String>();
      this.readyChk = false;
      this.threadPool = Executors.newCachedThreadPool();
      this.dst.add(id);
      this.data = new DDongData();
      data.src = id;
      data.type = "����";

      System.out.println("Frame ���� == ���� ����");
      setSize(width, height); // ������ ������
      setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
      setResizable(false); // ������ ������ ���� �� �� ����
      getContentPane().setLayout(null); // ����2�ƿ�
      setTitle("�ѿ�ѿ�"); // Ÿ��Ʋ
      setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
      getContentPane().setBackground(Color.blue);

      meP = new JPanel();
      meP.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
      meP.setBackground(Color.black);
      add(meP);

      int labelH = 50;
      this.meLb = new JLabel("ME");
      meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
      meLb.setHorizontalAlignment(JLabel.CENTER);
      add(meLb);

      state = new JPanel();
      state.setLayout(null);
      state.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 4, Puyo.PUYOSIZE * 13);
      state.setBackground(Color.red);
      add(state);

      System.out.println(state.getSize()); // 200 , 650

      ready = new JToggleButton("�غ�");
      JButton exit = new JButton("������");

      // ��ư ���� 50;
      // ��ư �� ���� 130;
      // ���� ���� 520;
      // 520 - 130 / 2
      int btnSizeW = 100;
      int btnSizeH = 40;
      int x = (state.getSize().width - btnSizeW) / 2;
      // System.out.println(x);
      int gap = 50;
      int y = (state.getSize().height - (btnSizeH * 2)) / 2;
      // System.out.println(y);

      // ready.setBackground(null);
      ready.setBorderPainted(false);
      // ready.setBorder(null);

      exit.setBorderPainted(false);

      ready.setBounds(x, y, btnSizeW, btnSizeH);
      exit.setBounds(x, y + gap, btnSizeW, btnSizeH);

      ready.setEnabled(false);

      ready.addActionListener(new ReadyBtn());
      exit.addActionListener(new ExitBtn());

      state.add(ready);
      state.add(exit);

      youP = new JPanel();
      youP.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
      youP.setBackground(Color.green);
      add(youP);

      this.youLb = new JLabel("YOU");
      youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
      youLb.setHorizontalAlignment(JLabel.CENTER);
      add(youLb);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);

   }

   @Override
   public void reciver(DDongData dd) {
      // TODO Auto-generated method stub
      if (dd.src.equals(this.enenmy)) {

         System.out.println(dd);
         System.out.println("asdasdasd : " + dd.src);
         System.out.println("�� : " + this.enenmy);

         if (dd.chk && this.readyChk) {
            // ���� ���� ������
            System.out.println("dddddddddddddddddddddddddddddddddddddddd");
            PuyoFrame game = new PuyoFrame(dd);
            game.meId = id;
            game.enemyId = enenmy;
            game.cn = this.cn;
            cn.ddInter = game;
         }
      }

   }

   class ReadyBtn implements ActionListener { // �غ� ��ư

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub

//         JToggleButton temp = (JToggleButton) e.getSource();

         if (GameRoom_GUI.this.readyChk) {
            GameRoom_GUI.this.readyChk = false;
            data.chk = false;
            cn.send(data);
         } else {
            GameRoom_GUI.this.readyChk = true;
            data.chk = true;
            cn.send(data);
         }
      }

   }

   class ExitBtn implements ActionListener { // ������ ��ư

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub

         // �迭���� ������ ��ư ��Ȱ��ȭ
         GameRoom_GUI.this.dst.remove(GameRoom_GUI.this.enenmy);
         GameRoom_GUI.this.ready.setEnabled(true);

         // ��񿡼� ����2 ����
         GameRoomDTO dto = new GameRoomDTO();
         dto.setNo(GameRoom_GUI.this.roomNum);
         dto.setUser1(GameRoom_GUI.this.id);
         GameRoom_GUI.this.enenmy = null;
         dto.setUser2(GameRoom_GUI.this.enenmy);

         new GameRoomDAO().modifyUser2(dto);
         GameRoom_GUI.this.dispose();
         // �κ�� �ٽ� �Ѱ� �ֱ�

         new Lobby_Main_Clear(cn);
//         lobby.cn = GameRoom_GUI.this.cn;
//         cn.ddInter = lobby;
      }

   }

}