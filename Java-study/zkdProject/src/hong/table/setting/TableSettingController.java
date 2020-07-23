package hong.table.setting;

import hong.table.*;

public class TableSettingController {
	TableMainInform myTableMainInform;
	TableInform myTableInform;
	TableSetUpFrame tableSetUpFrame;
	TableMainController tableMainController;
	public TableSettingController(TableMainController tableMainController) {
		myTableMainInform=tableMainController.getTableMainInform();
		myTableInform=myTableMainInform.getMyTableInform();
		tableSetUpFrame=new TableSetUpFrame(this);
		this.tableMainController=tableMainController;
	}
	public void modifyMyTableInform(String nickName,String concept) {
		myTableInform.setTableNickName(nickName);
		myTableInform.setTableConcept(concept);
		tableMainController.getTableFrame().refreshMyTableSetting();
	}
}
