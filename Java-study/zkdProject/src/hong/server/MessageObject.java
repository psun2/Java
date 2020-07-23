package hong.server;

import java.io.Serializable;
import java.util.ArrayList;

//�޼��� ���
public class MessageObject implements Serializable{
	String sender;
	ArrayList<String> receivers=new ArrayList<String>(); //�����ε�
	String type=""; //�޼��� Ÿ��
	Object messageMain; //�޼��� ���� ��ü

	public ArrayList<String> getreceiver() {
		return receivers;
	}
	public void setReceiver(ArrayList<String> receivers) {
		this.receivers = receivers;
	}
	public String getType() {
		return type;
	}
	public String getSender() {
		return sender;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getMessageMain() {
		return messageMain;
	}
	public void setMessageMain(Object messageMain) {
		this.messageMain = messageMain;
	}
	
	//�Ϲ� �޼��� ������ : ����� �ľ� ��, ������ٰ�!
	//1. �޼���, Ÿ��, �۽���, �����ε�(�迭��)
	public MessageObject(Object messageMain, String type, String senderName,String ...receivers){
		init(messageMain,type,senderName);
		setReceiver(receivers);
	}
	//2. �޼���, Ÿ��, �۽���, �����ε�(��̸���Ʈ��)
	public MessageObject(Object messageMain, String type, String senderName,ArrayList<String> receivers){
		init(messageMain, type, senderName);
		this.receivers=receivers;
	}
	void init(Object messageMain, String type, String senderName) {
		this.sender=senderName;
		this.messageMain=messageMain;
		this.type=type;
	}
	void setReceiver(String ...receivers) {
		for(String r:receivers) {
			this.receivers.add(r);
		}
	}
	@Override
	public String toString() {
		String str="";
		str+="messageMain:"+messageMain;
		return str;
	}
}
