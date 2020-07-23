package hong.table;

import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import hong.table.setting.TableSetMessage;
import hong.table.setting.TableSettingController;
import hwi.game.GameAcceptMain;
import sup.bills.BillsControl;
import sup.menu.MenuMainController;

public class TableMainController {
	TableFrame TableFrame; //뷰
	TableMainInform tableMainInform;
	MessageReceiver messageReceiver;
	String clientName="테이블_1";			//테이블 이름 입력
		//서버 주소 입력
	//String DBIP="localhost"; 			//DB주소 입력
	DefaultClient defaultClient;
	public BillsControl billsControl;
	public TableFrame getTableFrame() {
		return TableFrame;
	}

	public void setTableFrame(TableFrame tableFrame) {
		TableFrame = tableFrame;
	}

	public TableMainInform getTableMainInform() {
		return tableMainInform;
	}

	public void setTableMainInform(TableMainInform tableMainInform) {
		this.tableMainInform = tableMainInform;
	}

	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	

	

	public DefaultClient getDefaultClient() {
		return defaultClient;
	}

//
	public TableMainController() {
		tableMainInform = new TableMainInform(clientName, "Guest", "저같드st", 0, 0);//default 정보 생성
		messageReceiver=new MessageReceiver(); //프로그램이 켜지면 서버와 자동 연결
		TableFrame =new TableFrame(this);
	}
	//테스트용
	public TableMainController(String clientName) {
		this.clientName=clientName;
		tableMainInform = new TableMainInform(clientName, "Guest", "저같드st", 0, 0);//default 정보 생성
		messageReceiver=new MessageReceiver(); //프로그램이 켜지면 서버와 자동 연결
		TableFrame =new TableFrame(this);
		billsControl = new BillsControl(clientName,InitData.ip, this);
	}

//	
	//테이블 세팅
	void tableSetting() {
		new TableSettingController(this);
	}
	//직원 호출
	void callWaiter() {
		defaultClient.sendMessage(clientName, "호출", "카운터");
	}
	//메뉴판
	void orderMenu() {
//		new MenuMainController(tableMainInform.getTableName(), defaultClient);
		new MenuMainController(clientName, defaultClient, this);
	}
	//계산서
	void openBill() {
		if(billsControl == null) {
			billsControl.billsMain.setVisible(true);
		}else {
			billsControl.billsMain.setVisible(true);
		}
	}
	//전체 테이블 상태 변경
	void refreshTables(Object msgMain) {
		//생성, 삭제, 수정을 구분할 것
		TableSetMessage tableSetMessage=(hong.table.setting.TableSetMessage)msgMain;
		switch (tableSetMessage.getOrder()){
			case "생성":
				//db에 접속해서 해당 키를 객체로 받아와 HashMap에 넣는다. 해당 키의 객체를 pane에도 생성한다.
				break;
			case "삭제":
				//해당키의 객체를 pane도, TableMainInform의 HashMap에서도 삭제해준다.
				break;
			case "수정":
				//db에 접속해서 해당 키를 객체로 받아와 HashMap에서 바꿔치기 해준다. 해당 키의 pane도 수정한다.
				break;
		}
	}
//	DB를 확인해야 하는 작업을 요구 받았을 때=>1.사용할 DB 명령어 2.type(이걸로 해야할 일을 '구체적으로' 구분시킬 것!) 3.받을 사람 
	class MessageReceiver implements ReceiverObjFromClient{
//		DefaultClient defaultClient;
		
		public MessageReceiver() {
			defaultClient = new DefaultClient(clientName, this, InitData.ip, 7777);
		}
		void sendMessage(Object msgMain,String type,String ...receivers) {
			defaultClient.sendMessage(msgMain, type, receivers);
		}
		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			//받은 메세지 type 별로 나누어 함수 실행 할것.
			switch (msgObject.getType()) {
			case "":
				System.out.println(msgObject.getMessageMain());
				break;
			case "채팅신청"://채팅신청이 들어옴
				//~ defaultClient의 두번째 매개변수에 약간의 문제가 있다! => 어레이 리스트로 바꿔줄것.
				break;
			case "게임신청":
				/// 수락 / 거절 창이 있어야 함
				// 수락 거절시 상대방에게도 회신해야 함 
				//~ 수락했다 치고 일단 바로 게임창 열림
				GameAcceptMain gm = new GameAcceptMain(defaultClient, clientName,msgObject);
				gm.dc = defaultClient;
				break;
			case "테이블상태변경"://테이블의 상태를 변경시킨다. DB에서 hashMap 초기화, 테이블의 리스트 전부 변경? 수정?
				//1.테이블 이름-키(생성과 검색에 사용)   2.구분:생성.삭제.수정	=>DB 찾아가서 hashMap을 변경하고, gui 상태 변경시킴.
				refreshTables(msgObject.getMessageMain());
				break;
			case "결제완료":
				//프로그램을 재부팅시킨다.
			case "주문":
				billsControl.receiveMessage(msgObject);
				break;
			}
			
			
		}
	}
}
