package hong.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

//����
public class WholeServer {

	HashMap<String,ObjectOutputStream> clients;

	public WholeServer()  {
		try {
			clients=new HashMap<String, ObjectOutputStream>(); //���� ���� ����Ʈ.. ����ȭ�� ����.
			Collections.synchronizedMap(clients);//������, clients�� ����ְ� ������.(������� ���� ���⼺�� sychronized�Ͽ� �浹�� ���� ����!)

			ServerSocket server = new ServerSocket(7777);
			System.out.println("��������");

			while(true) {
				Socket client = server.accept();
				System.out.println("Ŭ���̾�Ʈ ����");
				new Receiver(client).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	class Receiver extends Thread{

		String senderAddress; //�۽��� �ּ�
		String sender;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		//������ MessageObject��ü�� �ű� ��. name�� address�� �۽����� ������!
		public Receiver(Socket socket) {
			senderAddress = ""+socket.getInetAddress(); //�۽��� ip
			senderAddress=senderAddress.substring(1);
			//����ó�� : Ư�����ڳ� �̹��� ���°� ������ ��!
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println(oos);
				ois = new ObjectInputStream(socket.getInputStream()); //messageObject�� ��Ʈ���� ����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				System.out.println("<<"+senderAddress+" ����>>");
	
				System.out.println(senderAddress+"�� "+oos);
				while(ois!=null) {
					
					//�۽�, ����, Ÿ��, ��ü �� ����
					Object object = ois.readObject();//���⼭ ����
					//���⼭ �ٿ� ĳ������ ���� �߿�!!!!!!!!!!!!!! Ŭ���̾�Ʈ������ ��Ű�� ����� ���� ���ٰ�!!
					MessageObject msgObject=(hong.server.MessageObject) object; 
					String type=msgObject.type;	// �̰ɷ� ���н�Ű�� �뵵�� ����!
					sender=msgObject.sender;
					System.out.println("���� �� : "+sender);
					System.out.print("�޴� �� : ");
					for(String r:msgObject.receivers) {
						System.out.print(r +" ");
					}
					System.out.println("\n�޼���  : "+msgObject.messageMain);
					System.out.println("Ÿ ��   : "+type);
					
					//�޼����� type�� ""ConnectServer""�̸�, oos�� clients�� ����.
					if(type.equals("ConnectServer")) { //���⼭ ù ���Ӱ� Ŭ���̾�Ʈ �ֱⰡ �̷�����!
						
						clients.put(sender, oos);//���⿡�� client�� ����
						System.out.println(clients.get(sender)+"�� oos�� ��");
					}
					
					//������ �Լ� ȣ��
					System.out.println(msgObject.messageMain);
					sendToReceiver(msgObject);

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch(UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				clients.remove(sender);
				System.out.println(sender+"����");
			}
		}
	}

	/////////<receiver �鿡�� msgObject�� �۽��ϴ� �Լ�>/////////
	//arrayList�� �ִ� receiver���� ���� Ž��
	void sendToReceiver(MessageObject msgObject) {
		for(String r:msgObject.receivers) {
			sendToReceiver(msgObject, r);
		}
	}
	//��ü. ���� receiver���� ������
	//�ȿ��� address�� ��� ����ó�� �ʿ�!
	void sendToReceiver(MessageObject msgObject, String receiver) {		
		try {
			ObjectOutputStream oos=clients.get(receiver); //���⼭ ����
			System.out.println("=>sendToReceiver���� ["+oos+"]��  "+receiver +"���� ����");

			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.out.println(receiver+"�� ���������� ����");
			sendToReceiver(new MessageObject(receiver+"�� ���������� ����", msgObject.getType(), msgObject.getSender(), msgObject.getSender()));
		}
	}

	public static void main(String[] args) {
		new WholeServer();
	}
}
