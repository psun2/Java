package hwi.game;
import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;


//���� ��Ʈ�� �׽�Ʈ��

class TestDefaultClient implements ReceiverObjFromClient{
	String clientName="temp";
	
	TestDefaultClient()	{
		//���� �����ϱ� -�ݵ�� ���ٰ�!
		//1. DefaultClient�� ��������� �����մϴ�.
//		DefaultClient dc = new DefaultClient("table1",this,"192.168.1.19", 7777);
//		GameJF_2 gj = new GameJF_2(dc, "table2");
//		
		DefaultClient dc = new DefaultClient("table2",this,InitData.ip, 7777);
		GameJF_2 gj = new GameJF_2(2,dc,"table2", "table1");
		dc.receiverObjFromClients.add(gj);
		
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





public class TableOrderMain{
	

	public static void main(String[] args) {
		
		
		
		new TestDefaultClient();
//		DefaultClient dc = new DefaultClient("table2",this,"192.168.1.19", 7777);
//		GameJF_2 gj = new GameJF_2(dc, "table1");
//		
//		dc.receiverObjFromClient = gj;
//		

	}

}
