package an.adminMain;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import an.adminLogin.an_adminLoginControl;
import hong.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class AdminMainControl {
	AdminMainView adminView;
	Receiver receiver;
	an_adminLoginControl adminControl;
//	String ip;
//	int port;
	public AdminMainControl(an_adminLoginControl adminControl) {
		this.adminControl = adminControl;
		adminView = new AdminMainView(this);
		receiver = new Receiver();
	}
	class Receiver implements ReceiverObjFromClient{
		DefaultClient client;
		public Receiver() {
			super();
			this.client = new DefaultClient("카운터", this, InitData.ip, 7777);
		}
		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			
			switch (msgObject.getType()) {
			case "조리완료": 
				adminView.tbc.completeOrder(msgObject);
				break;
			case "호출" :
				adminView.tbc.callStaff(msgObject);
				break;
			
			}
			System.out.println(msgObject.getMessageMain());
			
		} //디비로 받아서 자바로 데이터를 뽑아 온 다음에,
		
	}
	
	
}

