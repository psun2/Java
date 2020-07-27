package ddong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;



public class Main_Server {
   

   ArrayList<String> userList;
   ArrayList<String> info;   
   ArrayList<ObjectOutputStream> datalist;
   GameRoomDTO dto = new GameRoomDTO();
   
   String removeid ="";
   String userid ="";   
   HashMap<String, ObjectOutputStream> userdata;
   
   
   void dbClear() {
      
      String nn = null;
      //GameRoomDTO dto = new GameRoomDTO();
      dto = new GameRoomDTO();
      for (int i = 1; i < 19 ; i++) {
         
         dto.setNo(i);
         dto.setUser1(nn);
         dto.setUser2(nn);
         
         new GameRoomDAO().reset(dto);
      }
      
      
      new LobbyDAO().deleteAll();
      
   }
   
   
   public Main_Server() {
      try {
         dbClear() ;
         ServerSocket server = new ServerSocket(7777);
         System.out.println(" ____________________________________");
         System.out.println("|                                    |");
         System.out.println("|   S T A R T S E R V E R            |");
         System.out.println("|             T E A M  P Y O P Y O   |");
         System.out.println("|____________________________________|");
         System.out.println();
   
         userdata = new HashMap<String, ObjectOutputStream>();   
         
         userList = new ArrayList<String>();      
         Collections.synchronizedList(userList); 

         LobbyDAO dao = new LobbyDAO();
         while(true) {
            Socket client = server.accept();   
            new Tcp_Server(client).start();   
         
         }
         
      } catch (Exception e) {
         System.out.println("���ӽ���");
      }
      
   }
   public class Tcp_Server extends Thread  {
      ObjectOutputStream oos;      
      ObjectInputStream ois;      
      public Tcp_Server(Socket soc) {
         try {
            
            oos = new ObjectOutputStream(soc.getOutputStream());
            ois = new ObjectInputStream(soc.getInputStream());
         
            
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
   
      @Override
      public void run() {
            try {
               
            //�α����� �����ϸ� map�� ���� ���� ���� , �����͸� �޴´�.
            DDongData data = (DDongData)ois.readObject();
            userid = (String)data.src;
         
            System.out.println(userid+":"+"�����մϴ�");
            userdata.put(userid,oos);
            
      
            while(ois!=null)
            {    
               
              DDongData  dataois = (DDongData)ois.readObject();
               
              if(dataois.type.equals("ä��") && dataois.dst ==null ){
                 
                 sendtoChat(dataois); 
              
               }else if(dataois.type.equals("�κ�") || dataois.type.equals("����")) {
                 System.out.println(dataois.type);
                  System.out.println(dataois.data+"�κ�1 �κ����� \n\n");
                  sendAll(dataois);
              
               }else if(dataois.type.equals("������") && dataois.dst !=null ) {
                  System.out.println(dataois.src+" - src  ");
                  System.out.println(dataois.data+"������2 �κ����� \n\n");
                  sendSelect(dataois);
               
               }else if(dataois.type.equals("�κ�����")) {
                  System.out.println(dataois.type);
                  System.out.println(dataois.src+" - src  ");
                  System.out.println(dataois.dst+" - dst, "+dataois.data+" - �κ�2  \n\n");
                  sendAll(dataois);
               }
            
            }       
            
            }catch (Exception e) {
               e.printStackTrace();
               System.out.println();
               System.out.println(userid+"  ������ ������ �ݾҽ��ϴ�");
            }finally{
                 System.out.println("����������");
                 System.out.println(userid);
                 userdata.remove(userid, oos);
                 new LobbyDAO().delete(userid);
           }
          
           }

      //ä�� 
      public void sendtoChat(DDongData dataois)   
        {
         for (ObjectOutputStream ost : userdata.values())
             {
            
                try {
                  ost.writeObject(dataois);
                  ost.flush();
                  ost.reset();
               } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                
            }
        }
       
       
     public void sendSelect(DDongData dataois)   
        {
        try {
               userdata.get(dataois.dst).writeObject(dataois);// ���� ����
               userdata.get(dataois.dst).flush();
               userdata.get(dataois.dst).reset();
         
               //System.out.println("�������� �����Ͱ� �ߺ�������");
               
            }catch (IOException e) {}   
         
       }
         
       
      //��޼���
     public void sendAll(DDongData dataois){
         try {
            for (ObjectOutputStream ooo : userdata.values()) {
                 ooo.writeObject(dataois);
                 ooo.flush();
                 ooo.reset();
         }
            
           
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      
     public void sendLogin(DDongData dataois){
         try {
            for (ObjectOutputStream ost : userdata.values())
             {
            
                try {
                  ost.writeObject(dataois);
                  ost.flush();
                  ost.reset();
               } catch (IOException e) {
                  e.printStackTrace();
               }
                
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
     
     
     
     public void goLogin(Object data) {
        
      for (ObjectOutputStream ost : userdata.values())
         {
            try {
              ost.writeObject(data);
              ost.flush();
              ost.reset();
           } catch (Exception e) {
              e.printStackTrace();
           }
         }
     }
     
     
     
     
     
     
   }
   public static void main(String[] args) {

      new Main_Server();
   }

}