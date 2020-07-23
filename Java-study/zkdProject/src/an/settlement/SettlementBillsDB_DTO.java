package an.settlement;

public class SettlementBillsDB_DTO {
	
	String ordermenu, tablenum;
	
	public String getTablenum() {
		return tablenum;
	}

	public void setTablenum(String tablenum) {
		this.tablenum = tablenum;
	}

	Integer count,price, index;
	
	

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getOrdermenu() {
		return ordermenu;
	}

	public void setOrdermenu(String ordermenu) {
		this.ordermenu = ordermenu;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "an_settlementBillsDB_DTO [ordermenu=" + ordermenu + ", tablenum=" + tablenum + ", count=" + count
				+ ", price=" + price + ", index=" + index + "]";
	}

	
	
	
	

}
