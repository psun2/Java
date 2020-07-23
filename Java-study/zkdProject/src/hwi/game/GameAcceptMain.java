package hwi.game;
import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;


//게임 컨트롤 테스트용

public class GameAcceptMain  implements ReceiverObjFromClient{
	String clientName="temp";
	public DefaultClient dc ;
	public GameAcceptMain(DefaultClient dc,String me, MessageObject msgObject)	{
		//서버 연결하기 -반드시 해줄것!
		//1. DefaultClient를 멤버변수로 생성합니다.
//		DefaultClient dc = new DefaultClient("table1",this,"192.168.1.19", 7777);
//		GameJF_2 gj = new GameJF_2(dc, "table2");
//		

		GameJF_2 gj = new GameJF_2((int)msgObject.getMessageMain(),dc,me, msgObject.getSender());
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







	

	
		
//		GameJF_2 gj = new GameJF_2(dc, "table1");
//		
//		dc.receiverObjFromClient = gj;
//		

	

}
