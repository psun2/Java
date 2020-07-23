package hong.testMains;

import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

class TestDefaultClient implements ReceiverObjFromClient{
	String clientName="temp2";
	
	TestDefaultClient()	{
		//���� �����ϱ� -�ݵ�� ���ٰ�!
		//1. DefaultClient�� ��������� �����մϴ�.
		DefaultClient a=new DefaultClient(clientName,this,InitData.ip,7777);


		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		//�̰ɷ� �޼����� ���ϴ� ������� ����!
		String senderName="temp";
		String msgMain="�迬�� �̿�!";
		a.sendMessage(msgMain, "",senderName);
	}

	//�䷸�� ReceiverObjFromClient�� implements�ؼ�,
	//getMsgObjectFromClient�� �����Ͽ�,
	//msg�� �����ø� �˴ϴ�!!!! 
	@Override
	public void getMsgObjectFromClient(MessageObject msgObject) {
		System.out.println("�޾Ҵ�");
		System.out.println(msgObject.getMessageMain());
	}
}

public class TestDefaultClientMain {

	public static void main(String[] args) {
		new TestDefaultClient();
	}

}
