package hong.client;

import hong.InitData;
import hong.server.MessageObject;

class TestDefaultClient implements ReceiverObjFromClient{
	String clientName="temp";
	
	TestDefaultClient()	{
		//���� �����ϱ� -�ݵ�� ���ٰ�!
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
