package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
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
import game_p.GameRoom_GUI.ExitBtn;
import game_p.GameRoom_GUI.ReadyBtn;

public class PuyoFrame extends JFrame implements DDongInter {

   public ClientNetWork cn;

   final int width = 806;
//   final int height = 679;
   final int height = 679 + 50;

   MePuyoPanel me;
   YouPuyoPanel you;

   JPanel state;

   JLabel meLb, youLb;

   DDongData data;

   String meId, enemyId;

   ExecutorService threadPool;

   public PuyoFrame(DDongData data) {
      // TODO Auto-generated constructor stub

      this.data = data;
      this.threadPool = Executors.newCachedThreadPool();

      System.out.println("Frame ���� == ���� ����");
      setSize(width, height); // ������ ������
      setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
      setResizable(false); // ������ ������ ���� �� �� ����
      getContentPane().setLayout(null); // ����2�ƿ�
      setTitle("�ѿ�ѿ�"); // Ÿ��Ʋ
      setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
      getContentPane().setBackground(Color.blue);

      me = new MePuyoPanel();
      me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
      add(me);
      addKeyListener(new ActionKey(me));

      int labelH = 50;
      this.meLb = new JLabel("ME");
      meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
      meLb.setHorizontalAlignment(JLabel.CENTER);
      add(meLb);

//      state = new JPanel();
//      state.setLayout(null);
//      state.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 4, Puyo.PUYOSIZE * 13);
//      state.setBackground(Color.red);
//      add(state);
//
//      JToggleButton ready = new JToggleButton("�غ�");
//      JButton exit = new JButton("������");
//
//      // ��ư ���� 50;
//      // ��ư �� ���� 130;
//      // ���� ���� 520;
//      // 520 - 130 / 2
//      int btnSizeW = 100;
//      int btnSizeH = 40;
//      int x = (state.getSize().width - btnSizeW) / 2;
//      // System.out.println(x);
//      int gap = 50;
//      int y = (state.getSize().height - (btnSizeH * 2)) / 2;
//      // System.out.println(y);
//
//      // ready.setBackground(null);
//      ready.setBorderPainted(false);
//      // ready.setBorder(null);
//
//      exit.setBorderPainted(false);
//
//      ready.setBounds(x, y, btnSizeW, btnSizeH);
//      exit.setBounds(x, y + gap, btnSizeW, btnSizeH);
//
//      ready.addActionListener(new ReadyBtn());
//      exit.addActionListener(new ExitBtn());
//
//      state.add(ready);
//      state.add(exit);

      you = new YouPuyoPanel();
      you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
      add(you);

      this.youLb = new JLabel("YOU");
      youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
      youLb.setHorizontalAlignment(JLabel.CENTER);
      add(youLb);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �ݱ� �ɼ�
      setVisible(true); // �������� ������

      update();

   }

   void update() {

      System.out.println("update");

      Runnable thread = new Runnable() {

         @Override
         public void run() {
            // TODO Auto-generated method stub

            while (true) {

               // System.out.println("asdasdasdasdasd");

               if (data != null) {

                  // System.out.println("������Ʈ ����");
                  try {
                     data.src = "���̵�";
                     data.type = "����";
                     data.data = me.meInfo;
//                  System.out.println(data.type);
//                  System.out.println(me.meInfo);
                     cn.send(PuyoFrame.this.data);
//                  Thread.sleep(33);
                     Thread.sleep(1000);
                  } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }

         }
      };
      this.threadPool.submit(thread);

   }

   @Override
   public void reciver(DDongData dd) {
      // TODO Auto-generated method stub

      if (dd.src.equals(enemyId)) {
         you.paint((MeGameInfo) dd.data);
      }

//      
//      if (dd.type.equals("����")) {
//
//         if (dd.src.equals(cn.id)) {
//            this.data = dd;
//            for (String id : dd.dst) {
//               if (!cn.id.equals(id))
//                  this.enemy = id;
//            }
//         }
//
//         if (this.enemy != null && dd.src.equals(enemy)) {
//            you.paint((MeGameInfo) dd.data);
//         }
//
//      }

   }

   class ExitBtn implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub

         PuyoFrame.this.dispose();

      }

   }

}