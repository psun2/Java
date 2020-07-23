package hong.client;

import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import hong.server.MessageObject;


//������ �޴� ��Ʈ 7777


///////////////////////////////
//Ŭ���̾�Ʈ ���
public class DefaultClient{
	String clientName;
	String serverIP;
	int serverPort;
	ObjectOutputStream oos;
	MessageObject messageObject;//Ŭ���̾�Ʈ�� ��������� �޴� ��ü
	
	ReceiverObjFromClient receiverObjFromClient;//�� ��ü�� �̿��ϴ� ��ü�� ���� ����
	public ArrayList<ReceiverObjFromClient> receiverObjFromClients=new ArrayList<ReceiverObjFromClient>();//�� ��ü�� �̿��ϴ� ��ü�� ���� ����

	public DefaultClient(String clientName,ReceiverObjFromClient receiverObjFromClient, String serverIP, int serverPort) {
		this.clientName=clientName;
		this.receiverObjFromClient=receiverObjFromClient;
		receiverObjFromClients.add(receiverObjFromClient);
		try {
			System.out.println("myAddress:"+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		setServer(serverIP, serverPort);
		try {
			//�����κ����� ����
			Socket socket = new Socket(serverIP,serverPort);
			new Receiver(socket).start();
			oos=new ObjectOutputStream(socket.getOutputStream());//������ ��Ʈ�� ����

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("client error : �� �� ���� ȣ��Ʈ");
		} catch (IOException e) {
			System.out.println("client error : ������ ã�� �� �����ϴ�!");
		}
		try {
			//ó�� ����� ������ ���� ��� �޼���
			sendMessage("�������� �Ϸ�", "ConnectServer", clientName);
		} catch (Exception e) {
			System.out.println("client error : Ŭ���̾�Ʈ ����");
		}
	}
	void setServer(String serverIP,	int serverPort) {
		this.serverIP=serverIP;
		this.serverPort=serverPort;
	}
	
	//���ù�
	class Receiver extends Thread{
		String sender; //�۽��� ip
		ObjectInputStream ois;

		//������ MessageObject��ü�� �ű� ��. name�� address�� �۽����� ������!
		public Receiver(Socket socket) { 
			try {
				ois=new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("client error : ������ ���� �� �����ϴ�!");
			}
		}

		@Override
		public void run() {
			System.out.println("���� ���");
			while(ois!=null) {
				try {				
					//���⼭ ���� �� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					messageObject =(hong.server.MessageObject) ois.readObject();//���⼭ ����
					//���⼭ object�� ������ ������!!!!! �ݵ�� �ٿ�ĳ�����ؼ� ����Ұ�!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println(messageObject.getSender()+"�κ��� ���� �Ϸ� :"+(messageObject.getMessageMain()));
					////////////////////////���⼭ �ʿ�
					for(ReceiverObjFromClient receiverObjFromClient:receiverObjFromClients) {
						receiverObjFromClient.getMsgObjectFromClient(messageObject);
					}
				} catch (Exception e) {
					System.out.println("client error : �޼����� ���� �� �����ϴ�!");
				}
			}
		}
	}

	//���� �޼����� ���� ������� �ּҸ� �Ű������� �޴´�. �̰ɷ� �޼����� ���ϴ� ���� ������ �˴ϴ�!!!
	public void sendMessage(Object msgMain,String type, ArrayList<String> receivers) {
		String [] receiversArr=new String[receivers.size()];
		for(int i=0;i<receivers.size();i++) {
			receiversArr[i]=receivers.get(i);
		}
		sendMessage(msgMain, type, receiversArr);
	}
	//����
	public void sendMessage(Object msgMain,String type, String ...receivers) {
		try {
			MessageObject msgObject=new MessageObject(msgMain, type, clientName, receivers);
			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		}catch (Exception e) {
			System.out.println("client error : oos�� ã�� �� �����ϴ�!");
		}		
	}
	//�̹��� ��ü�� ���� ��
	public void sendMessage(Image image,String type, String ...receivers) {
		Object msgMain=arrayToArrayList(objectToByteArr(image));
		sendMessage(msgMain, type, receivers);	
	}
	//�迭�� ��ü�� �������
	public ArrayList arrayToArrayList(Object...objects) {
		ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Object o:objects) {
			arrayList.add(o);
		}
		return arrayList;
	}
	//��Ÿ serializable�� �ȵǴ� ��ü�� byteArr�� ������ִ� �Լ�
	public byte[] objectToByteArr(Object o) {
		byte[] serializedObject = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			serializedObject = baos.toByteArray();
			System.out.println(serializedObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serializedObject;
	}
	
	//�̹����� �������� �޾ƾ� �� ��� �� �Լ��� ����� �ٰ�!
	//byteArr�� �� ��Ÿ serializable�� �ȵǴ� ��ü�� �ٽ� Object�� ������ִ� �Լ�(gui�� ���)
	public Object byteArrToObject(byte ...byteArr) {
		Object object = null;
		try {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
		ObjectInputStream ois = new ObjectInputStream(bais);
			object = ois.readObject();
			System.out.println(object);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
}


