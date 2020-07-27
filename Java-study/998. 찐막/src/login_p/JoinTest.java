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

   public ArrayList<String> data = new ArrayList<String>(); //id, pw, name, email, birth 값을 저장 해서 main으로 넘겨줌

   GameUserDTO dto = new GameUserDTO();
   GameUserDAO dao = new GameUserDAO();

   String[] regex = { 
         "^[a-zA-Z0-9_]{5,12}$", // 아이디 영어랑 숫자만 5글자에서 12글자 사이
         "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // 영문숫자특수문자 포함 8자 이상
         "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", // 영문숫자특수문자 포함 8자 이상
         "^[가-힣]{2,4}$", // 이름한글 2~4자
         "^[0-9_]{1,6}$", // 
         "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$" // 이메일 유효성 검사
   };

   String idc = "[a-zA-Z0-9]*(([a-zA-Z][0-9]*)|([0-9][a-zA-Z]*))";

   public String id;         // id 담아둘 공간
   public String pw;         // pw 담아둘 공간
   public String pw2;         // pw확인란 
   public String name;      // 이름
   public String email;      // 메일
   public String birth;      // 생일

   JTextField JoinIdText;
   JPasswordField passwordField;
   JPasswordField passwordField_1;
   JTextField JoinBirthText;
   JTextField JoinNameText;
   JTextField JoinEmailText;
   JFrame jf;

   JButton btnCancelMake;   //종료 버튼
   JButton btnMakeMember;   //회원가입시 완료 버튼
   JButton btnIdChk;      //아이디 중복 검사
   

   public JoinTest() {
   	getContentPane().setForeground(Color.RED);   // 회원 가입 gui창

      setTitle("회원가입");
      setIconImage(new ImageIcon("./img/logo.png").getImage());
      getContentPane().setLayout(null);
      setSize(455, 615);            
      setLocationRelativeTo(null);   //gui 화면 정 중앙으로 맞춰줌
      getContentPane().setLayout(null);
      setResizable(false);   //gui 사이즈 수정 불가
 

      JLabel lbJoinId = new JLabel("아이디");
      lbJoinId.setBounds(17, 30, 74, 27);
      getContentPane().add(lbJoinId);

      
      JLabel lbJoinPw = new JLabel("비밀번호");
      lbJoinPw.setBounds(17, 80, 74, 27);
      getContentPane().add(lbJoinPw);

      JLabel lbJoinPwC = new JLabel("비밀번호 확인");
      lbJoinPwC.setBounds(17, 130, 122, 27);
      getContentPane().add(lbJoinPwC);
      
      JLabel lbName = new JLabel("이름");
      lbName.setBounds(17, 180, 74, 27);
      getContentPane().add(lbName);


      JLabel lbJoinBirth = new JLabel("생년월일");
      lbJoinBirth.setBounds(17, 230, 74, 27);
      getContentPane().add(lbJoinBirth);

      JLabel lbJoinEmail = new JLabel("이메일");
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

      btnCancelMake = new JButton("종료");
      btnCancelMake.setBackground(Color.LIGHT_GRAY);
      btnCancelMake.setBounds(236, 330, 129, 29);
      getContentPane().add(btnCancelMake);
      btnCancelMake.addActionListener(butAct);

      btnMakeMember = new JButton("회원가입");
      btnMakeMember.setBackground(Color.LIGHT_GRAY);
      btnMakeMember.setBounds(89, 330, 129, 29);
      getContentPane().add(btnMakeMember);
      btnMakeMember.addActionListener(butAct);

      btnIdChk = new JButton("중복확인");
      btnIdChk.setBackground(Color.LIGHT_GRAY);
      btnIdChk.setBounds(346, 30, 86, 27);
      getContentPane().add(btnIdChk);
      
      JLabel lblNewLabel = new JLabel("\uC601\uBB38 \uB610\uB294 \uC22B\uC790\uB85C 5\uAE00\uC790 \uC774\uC0C1");
      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 11));
      lblNewLabel.setForeground(Color.BLACK);
      lblNewLabel.setBounds(142, 55, 187, 21);
      getContentPane().add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("\uC601\uBB38,\uC22B\uC790,\uD2B9\uC218\uBB38\uC790 \uC870\uD569 8\uAE00\uC790 \uC774\uC0C1");
      lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 11));
      lblNewLabel_1.setForeground(Color.BLACK);
      lblNewLabel_1.setBounds(142, 105, 187, 21);
      getContentPane().add(lblNewLabel_1);
      
      JLabel lblNewLabel_3 = new JLabel("2~4\uAE00\uC790");
      lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 11));
      lblNewLabel_3.setForeground(Color.BLACK);
      lblNewLabel_3.setBounds(142, 205, 187, 21);
      getContentPane().add(lblNewLabel_3);
      
      JLabel lblNewLabel_4 = new JLabel("ex) 901211");
      lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 11));
      lblNewLabel_4.setForeground(Color.BLACK);
      lblNewLabel_4.setBounds(142, 255, 187, 21);
      getContentPane().add(lblNewLabel_4);
      
      JLabel lblNewLabel_5 = new JLabel("ex) \uC544\uC774\uB514@\uB3C4\uBA54\uC778 \uC8FC\uC18C");
      lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 11));
      lblNewLabel_5.setForeground(Color.BLACK);
      lblNewLabel_5.setBounds(142, 305, 187, 21);
      getContentPane().add(lblNewLabel_5);
      btnIdChk.addActionListener(butAct);
      
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
   
   
   
   

   ActionListener butAct = new ActionListener() { // 액션리스트

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancelMake) { // 종료 버튼 누를 시 로그인 창으로 화면 전환

            LoginTest lt = new LoginTest();
            dispose();
   
         }

         if (e.getSource() == btnIdChk) { // 중복 검사
            
               String jt = JoinIdText.getText().trim();
               if("".equals(jt) || !Pattern.matches(regex[0], jt)) {
                  JOptionPane.showMessageDialog(null, " 아이디를 확인 해주세요");
               
               }else if(dao.idChk(jt)==0) {
                  JOptionPane.showMessageDialog(null, JoinIdText.getText() + " 중복입니다.");
               }else if(dao.idChk(jt)==1 && Pattern.matches(regex[0], jt)) {
                  JOptionPane.showMessageDialog(null, JoinIdText.getText() + " 사용가능합니다.");
               }
            
         }

         if (e.getSource() == btnMakeMember) {   //회원 가입 
            id = JoinIdText.getText();   
            pw = passwordField.getText();
            pw2 = passwordField_1.getText();
            name = JoinNameText.getText();
            birth = JoinBirthText.getText();
            email = JoinEmailText.getText();
            
            String[] joinChk = { id, pw, pw2, name, birth, email }; // 유효성 검사를 위해 배열형
            String[] pppChk = { "id를 확인 해주세요", "비밀번호를 확인해 주세요", "비밀번호를 확인해 주세요", "이름을 확인해 주세요", "생일을 확인해 주세요",
                  "email을 확인해 주세요" }; // 에러내용

            while (true) { //while 문을 돌려서 유효성 검사 진행
               int i = 0;   //id와 유효성 검사 배열, 에러 알림메세지 배열 인덱스값 표현 용 변수
               int j = -1;
               if(true) {
               if (Pattern.matches(regex[i], joinChk[i])) { //패턴이 일치하면 data에 저장
//                  data.add(id);
                  dto.setId(id);
               } else { //일치 하지 않을 시 id확인 메세지 창 
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  j--;
                  break;
               }
               i++;

               if (Pattern.matches(regex[i], joinChk[i])) { //패턴이 일치하면 data에 저장
                  
               } else { //일치 하지 않을 시 id확인 메세지 창 
                  JOptionPane.showMessageDialog(null, pppChk[i]);
                  break;
               }
               i++;   //id 통과시 i를 증가시켜 다음 배열 순서 진행
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

               System.out.println(data); //완료시 데이터값 확인
               //System.out.println(new GameUserDAO().insert(dto)); //db에  insert 
               new GameUserDAO().insert(dto);
               JOptionPane.showMessageDialog(null, name + "님 회원가입 완료");
               
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
   