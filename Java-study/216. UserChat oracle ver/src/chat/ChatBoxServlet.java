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

@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

//		System.out.println(request.getReader().read());
//		System.out.println(request.getReader().readLine());
		String requestQuery = (String) request.getReader().readLine();
		System.out.println("requestQuery : " + requestQuery); // requestQuery : {"userID":"admin","toID":"박성언"}
		String[] arr = requestQuery.replaceAll(" ", "").trim().split("[\",}{:]");
//		String[] arr = ((String) request.getReader().readLine()).split("[\",}{:]");
		System.out.println("Arrays.toString(arr) : " + Arrays.toString(arr));
		// Arrays.toString(arr) : [, , userID, , , admin, , , toID, , , 박성언]

		String target = "key";
		String temp = "";
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[i] : " + arr[i]);
			if (!arr[i].equals("") && arr[i] != null) {
				if (target.equals("key")) {
					temp = arr[i];
					target = "value";
				} else if (target.equals("value")) {
					map.put(temp, arr[i]);
					target = "key";
				}
			}
		}

		System.out.println("arr.length : " + arr.length); // arr.length : 12
		System.out.println("map.size() : " + map.size()); // map.size() : 2

		for (Entry<String, String> en : map.entrySet()) {
			System.out.println("key : " + en.getKey() + ", value : " + en.getValue());
		}	
//		key : userID, value : admin
//		key : toID, value : myName

		String UID = map.get("userID");
		System.out.println("userID : " + UID); // userID : admin

		String userID = request.getParameter("userID");

		if (userID == null || userID.equals("")) {
			response.getWriter().write("0");
		} else {
			try {
				userID = URLDecoder.decode(userID, "UTF-8");
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
		int i = 0;
		for (ChatDTO chatDTO : chatList) {
			result.append("[{\"fromID\":\"" + chatDTO.getFromID() + "\"},");
			result.append("{\"toID\":\"" + chatDTO.getToID() + "\"},");
			result.append("{\"chatContent\":\"" + chatDTO.getChatContent() + "\"},");
			result.append("{\"chatTime\":\"" + chatDTO.getTime() + "\"}]");
			if (i != chatList.size() - 1)
				result.append(",");
			i++;
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		System.out.println(result.toString());

		return result.toString();
	}
}
