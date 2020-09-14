package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//
////		System.out.println(request.getReader().read());
////		System.out.println(request.getReader().readLine());
//		String requestQuery = (String) request.getReader().readLine();
//		System.out.println("requestQuery : " + requestQuery); // requestQuery : {"userID":"admin","toID":"박성언"}
//		String[] arr = requestQuery.replaceAll(" ", "").trim().split("[\",}{:]");
////		String[] arr = ((String) request.getReader().readLine()).split("[\",}{:]");
//		System.out.println("Arrays.toString(arr) : " + Arrays.toString(arr));
//		// Arrays.toString(arr) : [, , userID, , , admin, , , toID, , , 박성언]
//
//		String target = "key";
//		String temp = "";
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println("arr[i] : " + arr[i]);
//			if (!arr[i].equals("") && arr[i] != null) {
//				if (target.equals("key")) {
//					temp = arr[i];
//					target = "value";
//				} else if (target.equals("value")) {
//					map.put(temp, arr[i]);
//					target = "key";
//				}
//			}
//		}
//
//		System.out.println("arr.length : " + arr.length); // arr.length : 12
//		System.out.println("map.size() : " + map.size()); // map.size() : 2
//
//		for (Entry<String, String> en : map.entrySet()) {
//			System.out.println("key : " + en.getKey() + ", value : " + en.getValue());
//		}	
////		key : userID, value : admin
////		key : toID, value : myName
//
//		String UID = map.get("userID");
//		System.out.println("UID : " + UID); // userID : admin

		String userID = request.getParameter("userID");
		System.out.println("userID : " + userID);

		if (userID == null || userID.equals("")) {
			response.getWriter().write("0");
		} else {
			try {
				userID = URLDecoder.decode(userID, "UTF-8");

				HttpSession session = request.getSession();
				if (!userID.equals((String) session.getAttribute("userID"))) {
					response.getWriter().write("");
					return;
				}

				response.getWriter().write(getBox(userID));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String getBox(String userID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getBox(userID);
		if (chatList.size() == 0)
			return "0";

		for (int i = 0; i < chatList.size(); i++) {
			System.out.println(chatList.get(i));
		}

//		int i = 0;
//		for (ChatDTO chatDTO : chatList) { // 가장 최신의 메시지가 상위에 떠야 하기때문에 역순으로 바꾸어 줄수 있도록 for 문 사용 합니다.
		for (int i = 0; i < chatList.size(); i++) {
//		for (int i = chatList.size() - 1; i >= 0; i--) {
			String unread = "";
			if (userID.equals(chatList.get(i).getToID())) {
				unread = chatDAO.getUnreadChat(chatList.get(i).getFromID(), userID) + "";
				if (unread.equals("0"))
					unread = ""; // 안 읽은 메시지가 없을때 (최신의 메시지까지 다 읽은 경우)
			}
			result.append("[{\"fromID\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"toID\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"chatContent\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"chatTime\":\"" + chatList.get(i).getTime() + "\"},");
			result.append("{\"chatUnread\":\"" + unread + "\"}]"); // 자신이 받는 사람에 한해서 현재 읽지 않은 메시지 갯수 출력
			if (i != chatList.size() - 1)
//			if (i != 0)
				result.append(",");
//			i++;
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		System.out.println(result.toString());

		return result.toString();
	}
}
