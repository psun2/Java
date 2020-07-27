package login_p;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.RankDAO;
import jdbc_p.RankDTO;
import login_p.LoginTest;
import java.awt.Font;

public class JoinTest extends JFrame {

   public ArrayList<String> data = new ArrayList<String>(); //id, pw, name, email, birth ���� ���� �ؼ� main���� �Ѱ���

   GameUserDTO dto = new GameUserDTO();
   GameUserDAO dao = new GameUserDAO();

   String[] regex = { 
         "^[a-zA-Z0-9_]{5,12}$", // ���̵� ����� ���ڸ� 5���ڿ��� 12���� ����
         "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // ��������Ư������ ���� 8�� �̻�
         "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // ��������Ư������ ���� 8�� �̻�
         "^[��-�R]{2,4}$", // �̸��ѱ� 2~4��
         "^[0-9_]{1,6}$", // 
         "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$" // �̸��� ��ȿ�� �˻�
   };

   String idc = "[a-zA-Z0-9]*(([a-zA-Z][0-9]*)|([0-9][a-zA-Z]*))";

   public String id;         // id ��Ƶ� ����
   public String pw;         // pw ��Ƶ� ����
   public String pw2;         // pwȮ�ζ� 
   public String name;      // �̸�
   public String email;      // ����
   public String birth;      // ����

   JTextField JoinIdText;
   JPasswordField passwordField;
   JPasswordField passwordField_1;
   JTextField JoinBirthText;
   JTextField JoinNameText;
   JTextField JoinEmailText;
   JFrame jf;

   JButton btnCancelMake;   //���� ��ư
   JButton btnMakeMember;   //ȸ�����Խ� �Ϸ� ��ư
   JButton btnIdChk;      //���̵� �ߺ� �˻�
   

   public JoinTest() {
   	getContentPane().setForeground(Color.RED);   // ȸ�� ���� guiâ

      setTitle("ȸ������");
      setIconImage(new ImageIcon("./img/logo.png").getImage());
      getContentPane().setLayout(null);
      setSize(455, 615);            
      setLocationRelativeTo(null);   //gui ȭ�� �� �߾����� ������
      getContentPane().setLayout(null);
      setResizable(false);   //gui ������ ���� �Ұ�
 

      JLabel lbJoinId = new JLabel("���̵�");
      lbJoinId.setBounds(17, 30, 74, 27);
      getContentPane().add(lbJoinId);

      
      JLabel lbJoinPw = new JLabel("��й�ȣ");
      lbJoinPw.setBounds(17, 80, 74, 27);
      getContentPane().add(lbJoinPw);

      JLabel lbJoinPwC = new JLabel("��й�ȣ Ȯ��");
      lbJoinPwC.setBounds(17, 130, 122, 27);
      getContentPane().add(lbJoinPwC);
      
      JLabel lbName = new JLabel("�̸�");
      lbName.setBounds(17, 180, 74, 27);
      getContentPane().add(lbName);


      JLabel lbJoinBirth = new JLabel("�������");
      lbJoinBirth.setBounds(17, 230, 74, 27);
      getContentPane().add(lbJoinBirth);

      JLabel lbJoinEmail = new JLabel("�̸���");
      lbJoinEmail.setBounds(17, 280, 74, 27);
      getContentPane().add(lbJoinEmail);

      JoinIdText = new JTextField();

      JoinIdText.setBounds(142, 30, 187, 27);
      JoinIdText.setColumns(10);
      getContentPane().add(JoinIdText);

      passwordField = new JPasswordField();
      passwordField.setBounds(142, 80, 187, 27);
      getContentPane().add(passwordField);

      passwordField_1 = new JPasswordField();
      passwordField_1.setBounds(142, 130, 187, 27);
      getContentPane().add(passwordField_1);

      JoinNameText = new JTextField();
      JoinNameText.setColumns(10);
      JoinNameText.setBounds(142, 180, 187, 27);
      getContentPane().add(JoinNameText);

      JoinBirthText = new JTextField();
      JoinBirthText.setColumns(10);
      JoinBirthText.setBounds(142, 230, 187, 27);
      getContentPane().add(JoinBirthText);

      JoinEmailText = new JTextField();
      JoinEmailText.setColumns(10);
      JoinEmailText.setBounds(142, 280, 187, 27);
      getContentPane().add(JoinEmailText);

      btnCancelMake = new JButton("����");
      btnCancelMake.setBackground(Color.LIGHT_GRAY);
      btnCancelMake.setBounds(236, 330, 129, 29);
      getContentPane().add(btnCancelMake);
      btnCancelMake.addActionListener(butAct);

      btnMakeMember = new JButton("ȸ������");
      btnMakeMember.setBackground(Color.LIGHT_GRAY);
      btnMakeMember.setBounds(89, 330, 129, 29);
      getContentPane().add(btnMakeMember);
      btnMakeMember.addActionListener(butAct);

      btnIdChk = new JButton("�ߺ�Ȯ��");
      btnIdChk.setBackground(Color.LIGHT_GRAY);
      btnIdChk.setBounds(346, 30, 86, 27);
      getContentPane().add(btnIdChk);
      
      JLabel lblNewLabel = new JLabel("\uC601\uBB38 \uB610\uB294 \uC22B\uC790\uB85C 5\uAE00\uC790 \uC774\uC0C1");
      lblNewLabel.setFont(new Font("����", Font.PLAIN, 11));
      lblNewLabel.setForeground(Color.BLACK);
      lblNewLabel.setBounds(142, 55, 187, 21);
      getContentPane().add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("\uC601\uBB38,\uC22B\uC790,\uD2B9\uC218\uBB38\uC790 \uC870\uD569 8\uAE00\uC790 \uC774\uC0C1");
      lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 11));
      lblNewLabel_1.setForeground(Color.BLACK);
      lblNewLabel_1.setBounds(142, 105, 187, 21);
      getContentPane().add(lblNewLabel_1);
      
      JLabel lblNewLabel_3 = new JLabel("2~4\uAE00\uC790");
      lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 11));
      lblNewLabel_3.setForeground(Color.BLACK);
      lblNewLabel_3.setBounds(142, 205, 187, 21);
      getContentPane().add(lblNewLabel_3);
      
      JLabel lblNewLabel_4 = new JLabel("ex) 901211");
      lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 11));
      lblNewLabel_4.setForeground(Color.BLACK);
      lblNewLabel_4.setBounds(142, 255, 187, 21);
      getContentPane().add(lblNewLabel_4);
      
      JLabel lblNewLabel_5 = new JLabel("ex) \uC544\uC774\uB514@\uB3C4\uBA54\uC778 \uC8FC\uC18C");
      lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 11));
      lblNewLabel_5.setForeground(Color.BLACK);
      lblNewLabel_5.setBounds(142, 305, 187, 21);
      getContentPane().add(lblNewLabel_5);
      btnIdChk.addActionListener(butAct);
      
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
   
   
   
   

   ActionListener butAct = new ActionListener() { // �׼Ǹ���Ʈ

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancelMake) { // ���� ��ư ���� �� �α��� â���� ȭ�� ��ȯ

            LoginTest lt = new LoginTest();
            dispose();
   
         }

         if (e.getSource() == btnIdChk) { // �ߺ� �˻�
            
               String jt = JoinIdText.getText().trim();
               if("".equals(jt) || !Pattern.matches(regex[0], jt)) {
                  JOptionPane.showMessageDialog(null, " ���̵� Ȯ�� ���ּ���");
               
               }else if(dao.idChk(jt)==0) {
                  JOptionPane.showMessageDialog(null, JoinIdText.getText() + " �ߺ��Դϴ�.");
               }else if(dao.idChk(jt)==1 && Pattern.matches(regex[0], jt)) {
                  JOptionPane.showMessageDialog(null, JoinIdText.getText() + " ��밡���մϴ�.");
               }
            
         }

         if (e.getSource() == btnMakeMember) {   //ȸ�� ���� 
            id = JoinIdText.getText();   
            pw = passwordField.getText();
            pw2 = passwordField_1.getText();
            name = JoinNameText.getText();
            birth = JoinBirthText.getText();
            email = JoinEmailText.getText();
            
            String[] joinChk = { id, pw, pw2, name, birth, email }; // ��ȿ�� �˻縦 ���� �迭��
            String[] pppChk = { "id�� Ȯ�� ���ּ���", "��й�ȣ�� Ȯ���� �ּ���", "��й�ȣ�� Ȯ���� �ּ���", "�̸��� Ȯ���� �ּ���", "������ Ȯ���� �ּ���",
                  "email�� Ȯ���� �ּ���" }; // ��������

            while (true) { //while ���� ������ ��ȿ�� �˻� ����
               int i = 0;   //id�� ��ȿ�� �˻� �迭, ���� �˸��޼��� �迭 �ε����� ǥ�� �� ����
               int j = -1;
               if(true) {
               if (Pattern.matches(regex[i], joinChk[i])) { //������ ��ġ�ϸ� data�� ����
//                  data.add(id);
                  dto.setId(id);
               } else { //��ġ ���� ���� �� idȮ�� �޼��� â 
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  j--;
                  break;
               }
               i++;

               if (Pattern.matches(regex[i], joinChk[i])) { //������ ��ġ�ϸ� data�� ����
                  
               } else { //��ġ ���� ���� �� idȮ�� �޼��� â 
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               i++;   //id ����� i�� �������� ���� �迭 ���� ����
               if (Pattern.matches(regex[i], joinChk[i])) {
                  if (pw2.equals(pw))
//                     data.add(pw);
                  dto.setPw(pw);
               } else {
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               i++;
               if (Pattern.matches(regex[i], joinChk[i])) {
//                  data.add(name);
                  dto.setName(name);
               } else {
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               i++;

               if (Pattern.matches(regex[i], joinChk[i])) {
//                  data.add(birth);
                  dto.setBirth(birth);
               } else {
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               i++;
               
               if (Pattern.matches(regex[i], joinChk[i])) {
//                  data.add(email);
                  dto.setEmail(email);
               } else {
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               
               }

               System.out.println(data); //�Ϸ�� �����Ͱ� Ȯ��
               //System.out.println(new GameUserDAO().insert(dto)); //db��  insert 
               new GameUserDAO().insert(dto);
               JOptionPane.showMessageDialog(null, name + "�� ȸ������ �Ϸ�");
               
               new RankDAO().insertId(id);
               
               LoginTest lt = new LoginTest();
               dispose();
            //   lt.setVisible(true);
               break;

            }

         }

      }
   };


   public static void main(String[] args) {
//      new JoinTest();

   }
}
   