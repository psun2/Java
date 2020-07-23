package hong.table.setting;

public class TableSetMessage {
	String order; //생성, 수정, 삭제 
	String tableName; //테이블 이름
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
