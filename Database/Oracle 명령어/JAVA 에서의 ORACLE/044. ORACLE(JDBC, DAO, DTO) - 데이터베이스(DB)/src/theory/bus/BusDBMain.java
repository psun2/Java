package theory.bus;

public class BusDBMain {

	public static void main(String[] args) {

		for (BusDTO bus : new BusDAO().list()) {
			System.out.println(bus);
		}
		// BusDTO [no=7, type=빨강, district=강남, price=1700]
		// BusDTO [no=12, type=노랑, district=강북, price=1200]

	}

}

// => spl Select 를 안해줘서 생긴 오류 였습니다.
//java.sql.SQLException: 실행할 SQL 문은 비어 있거나 널일 수 없음
//at oracle.jdbc.driver.OracleSql.initialize(OracleSql.java:113)
//at oracle.jdbc.driver.OracleStatement.executeQuery(OracleStatement.java:1468)
//at oracle.jdbc.driver.OracleStatementWrapper.executeQuery(OracleStatementWrapper.java:392)
//at theory.BusDAO.list(BusDAO.java:43)
//at theory.BusDBMain.main(BusDBMain.java:7)