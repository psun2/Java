import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommandRESTTEST implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		WriteDTO[] arr = null;
		StringBuffer result = new StringBuffer();
		try {
			// 트랜잭션 수행
			arr = dao.select();

			result.append("[");

			for (int i = 0; i < arr.length; i++) {
				result.append("{");
				result.append("\"uid\":" + arr[i].getUid() + ",");
				result.append("\"subject\":\"" + arr[i].getSubject() + "\",");
				result.append("\"content\":\"" + arr[i].getContent() + "\",");
				result.append("\"name\":\"" + arr[i].getName() + "\",");
				result.append("\"viewcnt\":" + arr[i].getViewcnt() + ",");
				result.append("\"regDate\":\"" + arr[i].getRegDate() + "\"");
				result.append("}");
				if (i != arr.length - 1)
					result.append(",");
			}
			result.append("]");

			// "list" 란 name 으로 request 에 arr 값 저장
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈

			request.setAttribute("REST", result.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end execute()

} // end Command
