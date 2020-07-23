package hwi.game;
import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;


//게임 컨트롤 테스트용

class TestDefaultClient implements ReceiverObjFromClient{
	String clientName="temp";
	
	TestDefaultClient()	{
		//서버 연결하기 -반드시 해줄것!
		//1. DefaultClient를 멤버변수로 생성합니다.
//		DefaultClient dc = new DefaultClient("table1",this,"192.168.1.19", 7777);
//		GameJF_2 gj = new GameJF_2(dc, "table2");
//		
		DefaultClient dc = new DefaultClient("table2",this,InitData.ip, 7777);
		GameJF_2 gj = new GameJF_2(2,dc,"table2", "table1");
		dc.receiverObjFromClients.add(gj);
		
	}

	//요렇게 ReceiverObjFromClient를 implements해서,
	//getMsgObjectFromClient를 구현하여,
	//msg를 받으시면 됩니다!!!! 
	@Override
	public void getMsgObjectFromClient(MessageObject msgObject) {
		System.out.println("받았당");
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
