package hwi.game;
import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;


//���� ��Ʈ�� �׽�Ʈ��

public class GameAcceptMain  implements ReceiverObjFromClient{
	String clientName="temp";
	public DefaultClient dc ;
	public GameAcceptMain(DefaultClient dc,String me, MessageObject msgObject)	{
		//���� �����ϱ� -�ݵ�� ���ٰ�!
		//1. DefaultClient�� ��������� �����մϴ�.
//		DefaultClient dc = new DefaultClient("table1",this,"192.168.1.19", 7777);
//		GameJF_2 gj = new GameJF_2(dc, "table2");
//		

		GameJF_2 gj = new GameJF_2((int)msgObject.getMessageMain(),dc,me, msgObject.getSender());
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







	

	
		
//		GameJF_2 gj = new GameJF_2(dc, "table1");
//		
//		dc.receiverObjFromClient = gj;
//		

	

}
