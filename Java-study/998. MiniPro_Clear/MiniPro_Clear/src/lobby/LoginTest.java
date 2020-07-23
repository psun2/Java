package lobby;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;

import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;


public class LoginTest extends JFrame implements DDongInter {
   boolean login_chk = false;
   	GameUserDTO dto = new GameUserDTO();
   	GameUserDAO dao = new GameUserDAO();
	
   
   Container contentPane;
   JTextField idText;
   JPasswordField pwText;
   
   
   ArrayList<String> idstr;
   
   DDongData ddos;
   JLabel lbId;
   JLabel lbPw;
   JButton btnLogin;
   JButton btnJoin;
   JButton btnFindingInfo;
   JButton btnCloseGame;
   
   ClientNetWork cn;
   
   
   boolean lochk;

   public LoginTest() {
      
      
      
      setTitle("회원가입");
      contentPane = getContentPane();
      setSize(455, 615);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);
      setResizable(false);
      
      
      lbId = new JLabel("Id");
      lbId.setBounds(69, 206, 74, 44);
      getContentPane().add(lbId);
      
      lbPw = new JLabel("Pw");
      lbPw.setBounds(69, 246, 74, 44);
      getContentPane().add(lbPw);
      
      btnLogin = new JButton("Login");
      btnLogin.setBackground(Color.LIGHT_GRAY);
      btnLogin.setBounds(182, 297, 107, 29);
      getContentPane().add(btnLogin);
      btnLogin.addActionListener(actBut);
      
      btnJoin = new JButton("회원가입");
      btnJoin.setBackground(Color.LIGHT_GRAY);
      btnJoin.setBounds(39, 337, 107, 19);
      getContentPane().add(btnJoin);
      btnJoin.addActionListener(actBut); 
      
      btnFindingInfo = new JButton("계정 찾기");
      btnFindingInfo.setBackground(Color.LIGHT_GRAY);
      btnFindingInfo.setBounds(143, 337, 154, 19);
      getContentPane().add(btnFindingInfo);
      
      idText = new JTextField();
      idText.setBounds(160, 215, 166, 27);
      getContentPane().add(idText);
   
      idText.setColumns(10);
      
      pwText = new JPasswordField();
      pwText.setBounds(160, 255, 166, 27);
      getContentPane().add(pwText);
      btnFindingInfo.addActionListener(actBut);

      
      btnCloseGame = new JButton("나가기");
      btnCloseGame.setBackground(Color.LIGHT_GRAY);
      btnCloseGame.setBounds(295, 337, 107, 19);
      getContentPane().add(btnCloseGame);
      btnCloseGame.addActionListener(actBut);
      
      
         idstr = new ArrayList<String>();
      
         
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
   }
   
   Socket soc;
   ActionListener actBut = new ActionListener() {
   @Override
      public void actionPerformed(ActionEvent e) {
      
      
         if(e.getSource() == btnLogin) {
            try {
            	
            	
            	if(dao.Login(idText.getText(), pwText.getText())) {
            		
					JOptionPane.showMessageDialog(null, "로그인 성공");
					LobbyDTO lodto = new LobbyDTO();
               	 	lodto.setId(idText.getText());
               	 	new LobbyDAO().insert(lodto);
             
               	 
               	 
               	 	cn = new ClientNetWork(idText.getText()); 
               	 Lobby_Main_Clear mm = new Lobby_Main_Clear();
                    mm.cn = cn;
                    cn.ddInter = mm;
       
               	 System.out.println(idText.getText());
                    System.out.println("접속성공");
                 	 //dispose();
				}else if(dao.Login(idText.getText(), pwText.getText())==false)
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 확인해주세요");
				
               
               
            }catch (Exception e1) {
            
            }
      }
         
         if(e.getSource() == btnJoin) {
        
        	 new JoinTest();
        	 dispose();
        	 
         }
         
         if(e.getSource() == btnFindingInfo) {
            setVisible(false);
         }
         
         if(e.getSource() == btnCloseGame) {
            System.exit(0);
         }
      }
   };
   
   
   
//   
   
   
   
   
   
   
   public static void main(String[] args) {
      
      new LoginTest();

      
   }





	@Override
	public void reciver(DDongData dd) {
	
	}
   
   

}