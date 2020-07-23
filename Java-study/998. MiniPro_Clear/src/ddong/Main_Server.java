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



public class Main_Server {
	

	ArrayList<String> userList;
	ArrayList<String> info;	
	ArrayList<ObjectOutputStream> datalist;
	
	
	boolean chk = false;
	boolean chkk = false;
	String robiid;
	String removeid ="";
	String userid ="";	
	HashMap<String, ObjectOutputStream> userdata;
	
	public Main_Server() {
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
				System.out.println("��������");
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
			
				
				System.out.println(userid);
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
				//datalist.add(oos);
				removeid = data.src;
				userid = (String)data.data;
				//System.out.println(data.src);
			
				userdata.put(userid,oos);
				
				while(ois!=null)
	            {    
					DDongData  dataois = (DDongData)ois.readObject();
					
				if(dataois.type.equals("ä��") ) {
					System.out.println("ä�� ���Ϳ�");
					sendtoChat(dataois); 
				}else if(dataois.type.equals("����") ){
					System.out.println("���� ���Ϳ�");
					sendSelect(dataois);
				}else if(dataois.type.equals("�κ�") ) {
					System.out.println("�κ� ����");
					sendRoom(dataois);
				}
			}	    
				   
	            }catch (Exception e) {
					e.printStackTrace();
				}finally{
					
	            	System.out.println("����������");
	            	new LobbyDAO().delete(userid);
	            	userdata.remove(userid, oos);
	            	
	            	
	           
	            }
			 
	        }

		//ä�� 
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
		 
		 //���� ���°�
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
			
							System.out.println("�ߺ������� ������");
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
		 
		//��޼���
		public void sendRoom(DDongData dataois){
			try {
				oos.writeObject("�κ� �ȳ�");
				oos.flush();
				oos.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) {

		new Main_Server();
	}

}
