package hong.table;

import java.util.HashMap;


//�� ���̺��� �Ѱ� �������� ��Ƶδ� ��ü
public class TableInform {
	//���̺��� ����
	Boolean lock=true; //�̰� ���� �۵����� default�� faulse�� �ؾ��Ѵ�!
	String tableName="���̺�_1"; //��� ������ TableMainInform���� �ٲ� �� �ֵ���
	String tableNickName;
	String tableConcept;
	int manCnt;
	int womanCnt;
	//
	public TableInform(String tableName, String tableNickName, String tableConcept, int manCnt, int womanCnt) {
		this.tableName=tableName;
		this.tableNickName=tableNickName;
		this.tableConcept=tableConcept;
		this.manCnt=manCnt;
		this.womanCnt=womanCnt;
	}
	
	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public String getTableName() {
		return tableName;
	}
	public String getTableNickName() {
		return tableNickName;
	}
	public void setTableNickName(String tableNickName) {
		this.tableNickName = tableNickName;
	}
	public String getTableConcept() {
		return tableConcept;
	}
	public void setTableConcept(String tableConcept) {
		this.tableConcept = tableConcept;
	}
	public int getManCnt() {
		return manCnt;
	}
	public void setManCnt(int manCnt) {
		this.manCnt = manCnt;
	}
	public int getWomanCnt() {
		return womanCnt;
	}
	public void setWomanCnt(int womanCnt) {
		this.womanCnt = womanCnt;
	}
	public void setAll(String tableNickName,String tableConcept,int manCnt,int womanCnt) {
		this.tableNickName=tableNickName;
		this.tableConcept=tableConcept;
		this.manCnt=manCnt;
		this.womanCnt=womanCnt;
	}
	
}
