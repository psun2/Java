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
	TableFrame TableFrame; //��
	TableMainInform tableMainInform;
	MessageReceiver messageReceiver;
	String clientName="���̺�_1";			//���̺� �̸� �Է�
		//���� �ּ� �Է�
	//String DBIP="localhost"; 			//DB�ּ� �Է�
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
		tableMainInform = new TableMainInform(clientName, "Guest", "������st", 0, 0);//default ���� ����
		messageReceiver=new MessageReceiver(); //���α׷��� ������ ������ �ڵ� ����
		TableFrame =new TableFrame(this);
	}
	//�׽�Ʈ��
	public TableMainController(String clientName) {
		this.clientName=clientName;
		tableMainInform = new TableMainInform(clientName, "Guest", "������st", 0, 0);//default ���� ����
		messageReceiver=new MessageReceiver(); //���α׷��� ������ ������ �ڵ� ����
		TableFrame =new TableFrame(this);
		billsControl = new BillsControl(clientName,InitData.ip, this);
	}

//	
	//���̺� ����
	void tableSetting() {
		new TableSettingController(this);
	}
	//���� ȣ��
	void callWaiter() {
		defaultClient.sendMessage(clientName, "ȣ��", "ī����");
	}
	//�޴���
	void orderMenu() {
//		new MenuMainController(tableMainInform.getTableName(), defaultClient);
		new MenuMainController(clientName, defaultClient, this);
	}
	//��꼭
	void openBill() {
		if(billsControl == null) {
			billsControl.billsMain.setVisible(true);
		}else {
			billsControl.billsMain.setVisible(true);
		}
	}
	//��ü ���̺� ���� ����
	void refreshTables(Object msgMain) {
		//����, ����, ������ ������ ��
		TableSetMessage tableSetMessage=(hong.table.setting.TableSetMessage)msgMain;
		switch (tableSetMessage.getOrder()){
			case "����":
				//db�� �����ؼ� �ش� Ű�� ��ü�� �޾ƿ� HashMap�� �ִ´�. �ش� Ű�� ��ü�� pane���� �����Ѵ�.
				break;
			case "����":
				//�ش�Ű�� ��ü�� pane��, TableMainInform�� HashMap������ �������ش�.
				break;
			case "����":
				//db�� �����ؼ� �ش� Ű�� ��ü�� �޾ƿ� HashMap���� �ٲ�ġ�� ���ش�. �ش� Ű�� pane�� �����Ѵ�.
				break;
		}
	}
//	DB�� Ȯ���ؾ� �ϴ� �۾��� �䱸 �޾��� ��=>1.����� DB ��ɾ� 2.type(�̰ɷ� �ؾ��� ���� '��ü������' ���н�ų ��!) 3.���� ��� 
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
			//���� �޼��� type ���� ������ �Լ� ���� �Ұ�.
			switch (msgObject.getType()) {
			case "":
				System.out.println(msgObject.getMessageMain());
				break;
			case "ä�ý�û"://ä�ý�û�� ����
				//~ defaultClient�� �ι�° �Ű������� �ణ�� ������ �ִ�! => ��� ����Ʈ�� �ٲ��ٰ�.
				break;
			case "���ӽ�û":
				/// ���� / ���� â�� �־�� ��
				// ���� ������ ���濡�Ե� ȸ���ؾ� �� 
				//~ �����ߴ� ġ�� �ϴ� �ٷ� ����â ����
				GameAcceptMain gm = new GameAcceptMain(defaultClient, clientName,msgObject);
				gm.dc = defaultClient;
				break;
			case "���̺���º���"://���̺��� ���¸� �����Ų��. DB���� hashMap �ʱ�ȭ, ���̺��� ����Ʈ ���� ����? ����?
				//1.���̺� �̸�-Ű(������ �˻��� ���)   2.����:����.����.����	=>DB ã�ư��� hashMap�� �����ϰ�, gui ���� �����Ŵ.
				refreshTables(msgObject.getMessageMain());
				break;
			case "�����Ϸ�":
				//���α׷��� ����ý�Ų��.
			case "�ֹ�":
				billsControl.receiveMessage(msgObject);
				break;
			}
			
			
		}
	}
}
