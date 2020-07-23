package an.settlement;

import java.io.Reader;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SettlementDTO {
	
	
	
	Integer bills_INDEX, bills_PRICE;
	
	Date bills_time;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//	public Integer getBills_INDEX() {
//		return bills_INDEX;
//	}
//
//	public void setBills_INDEX(Integer bills_INDEX) {
//		this.bills_INDEX = bills_INDEX;
//	}

	public Integer getBills_PRICE() {
		return bills_PRICE;
	}

	public void setBills_PRICE(Integer bills_PRICE) {
		this.bills_PRICE = bills_PRICE;
	}

	public Date getBills_time() {
		return bills_time;
	}

	public void setBills_time(Date bills_time) {
		this.bills_time = bills_time;
	}

	@Override
	public String toString() {
		return " bills_PRICE=" + bills_PRICE + ", bills_time="
				+ bills_time + ", sdf=" + sdf + "]";
	}




	

}
