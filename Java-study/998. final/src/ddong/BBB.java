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


import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;



public class BBB {
	

	ArrayList<String> userList;
	ArrayList<String> info;	
	ArrayList<ObjectOutputStream> datalist;
	
	
	boolean chk = false;
	boolean chkk = false;
	String robiid;
	String removeid ="";
	String userid ="";	
	HashMap<String, ObjectOutputStream> userdata;
	
	public BBB() {
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println(" ________________");
			System.out.println("|                |");
			System.out.println("|  start server  |");
			System.out.println("|                |");
			System.out.println("|________________|");
			System.out.println();
	
			userdata = new HashMap<String, ObjectOutputStream>();	
			
			userList = new ArrayList<String>();		
			Collections.synchronizedList(userList); 

			datalist = new ArrayList<ObjectOutputStream>();
			Collections.synchronizedCollection(datalist);
			
			LobbyDAO dao = new LobbyDAO();
			
			while(true) {
				Socket client = server.accept();	
				System.out.println("�쑀���젒�냽");
				new Tcp_Server(client).start();		
			}
			
		} catch (Exception e) {
			System.out.println("�젒�냽�떎�뙣");
			
			
		}
		
	}

	
	
	public class Tcp_Server extends Thread  {
		ObjectOutputStream oos;		
		ObjectInputStream ois;		
		public Tcp_Server(Socket soc) {
			try {
				
				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());
			
				
				System.out.println(userid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		@Override
		public void run() {
				try {
				   
				//濡쒓렇�씤�쓣 �꽦怨듯븯硫� map�쓣 �넻�빐 �쑀�� �젙蹂� , �뜲�씠�꽣瑜� 諛쏅뒗�떎.
				
					
				DDongData data = (DDongData)ois.readObject();
				//datalist.add(oos);
				removeid = data.src;
				userid = (String)data.data;
				//System.out.println(data.src);
			
				userdata.put(userid,oos);
				
				while(ois!=null)
	            {    
					DDongData  dataois = (DDongData)ois.readObject();
					
				if(dataois.type.equals("梨꾪똿") ) {
					System.out.println("梨꾪똿 �뱾�뼱���슂");
					sendtoChat(dataois); 
				}else if(dataois.type.equals("寃뚯엫") ){
					System.out.println("寃뚯엫 �뱾�뼱���슂");
					sendSelect(dataois);
				}else if(dataois.type.equals("濡쒕퉬") ) {
					System.out.println("濡쒕퉬 �엯�옣");
					sendRoom(dataois);
				}
			}	    
				   
	            }catch (Exception e) {
					e.printStackTrace();
				}finally{
					
	            	System.out.println("�쑀���굹媛��슂");
	            	new LobbyDAO().delete(userid);
	            	userdata.remove(userid, oos);
	            	
	            	
	           
	            }
			 
	        }

		//梨꾪똿 
		 public void sendtoChat(DDongData dataois)	
	     {
//			 System.out.println((String)dataois.data);
//			 System.out.println(userdata.keySet());
//			 System.out.println(userdata.values());
// 			 System.out.println((String)userdata.values());
		
			 
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
		 
		 //�쑀�� 媛��뒗嫄�
		 public void sendSelect(DDongData dataois)	
	     {
//			for (String dst : dataois.dst) { 
//				try {
//					userdata.get(dst).writeObject(dataois);
//					userdata.get(dst).flush();
//					userdata.get(dst).reset();
//				}catch (IOException e) {}	
//			}
//			
			 System.out.println(dataois.data);
			 for (ObjectOutputStream ost : userdata.values())
			 	{
				 System.out.println(ost);
			 		try {
			 			try {
			 				ost.writeObject(dataois);
							ost.flush();
							ost.reset();
			
							System.out.println("�옒蹂대궡�졇�슂 �뜲�씠�꽣");
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 		
				}
	     }
		 
		//猷몃찓�꽭吏�
		public void sendRoom(DDongData dataois){
			try {
				oos.writeObject("濡쒕퉬 �븞�뀞");
				oos.flush();
				oos.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) {

		new BBB();
	}

}
