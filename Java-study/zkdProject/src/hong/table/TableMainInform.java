package hong.table;

import java.io.Serializable;
import java.util.HashMap;

public class TableMainInform {
	String tableName="���̺�_1";
	TableInform myTableInform;
	String tableStatus; //ä����, ������ ��..
	//�ٸ� ���̺��� ���� <���̺��̸�,���̺�>
	HashMap<String, TableInform> otherTableMap=new HashMap<String, TableInform>();
	
	
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TableInform getMyTableInform() {
		return myTableInform;
	}

	public void setMyTableInform(TableInform myTableInform) {
		this.myTableInform = myTableInform;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	public TableMainInform(String tableName, String tableNickName, String tableConcept, int manCnt, int womanCnt) {
		myTableInform=new TableInform(tableName, tableNickName, tableConcept, manCnt, womanCnt);
	}
	
	//���̺� �߰�(���̺� ��Ȳ�ǿ�)
	void addOtherTable(String tableName,TableInform tableInform) {
		otherTableMap.put(tableName, tableInform);
	}
	//����
	void deleteOtherTable(String tableName) {
		otherTableMap.remove(tableName);
	}
	//����
	void modifyOtherTable(String tableName,TableInform tableInform) {
		otherTableMap.remove(tableName);
		otherTableMap.put(tableName, tableInform);
	}
}
