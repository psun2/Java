package hwi.chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import hong.InitData;
import hong.server.MessageObject;


//������ �޴� ��Ʈ 7777


///////////////////////////////
//Ŭ���̾�Ʈ ���
class DefaultClient{
	
	int serverPort;
	ObjectOutputStream oos;

	public DefaultClient( int serverPort) {
		setServer( serverPort);
		try {
			//�����κ����� ����
			Socket socket = new Socket(InitData.ip,serverPort);
			new Receiver(socket).start();
			oos=new ObjectOutputStream(socket.getOutputStream());//������ ��Ʈ�� ����
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void setServer(	int serverPort) {
		
		this.serverPort=serverPort;
	}

	//
	class Receiver extends Thread{
		String sender; //�۽��� ip
		ObjectInputStream ois;

		//������ MessageObject��ü�� �ű� ��. name�� address�� �۽����� ������!
		public Receiver(Socket socket) { 
			try {
				ois=new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			System.out.println("���� ���");
			while(ois!=null) {
				try {				
					//���⼭ ���� �� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					MessageObject msgObject =(hong.server.MessageObject) ois.readObject();//���⼭ ����
					//���⼭ object�� ������ ������!!!!! �ݵ�� �ٿ�ĳ�����ؼ� ����Ұ�!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println(sender+"���� �Ϸ�"+(msgObject.getMessageMain()));
				} catch (Exception e) {
				}
			}
		}
	}

	//���� �޼����� ���� ������� �ּҸ� �Ű������� �޴´�. �̰ɷ� �޼����� ���ϴ� ���� ������ �˴ϴ�!!!
	void sendMessage(Object msgMain,String type, ArrayList<String> receivers) {
		String [] receiversArr=new String[receivers.size()];
		for(int i=0;i<receivers.size();i++) {
			receiversArr[i]=receivers.get(i);
		}
		sendMessage(msgMain, type, receiversArr);
	}
	void sendMessage(Object msgMain,String type, String ...receivers) {
		try {
			System.out.println("myName:"+InetAddress.getLocalHost().getHostAddress());
			MessageObject msgObject=new MessageObject(msgMain, type, InetAddress.getLocalHost().getHostAddress(), receivers);
			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

//���� �׽�Ʈ
public class ChatClient {

	public static void main(String[] args) {
		DefaultClient a=new DefaultClient(7777);
		
		String msgMain="�̾�";
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.sendMessage(msgMain, "",InitData.ip);
	}

}
