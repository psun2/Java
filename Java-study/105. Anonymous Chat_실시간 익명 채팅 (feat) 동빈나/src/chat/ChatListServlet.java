package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatSubmitServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 어떠한 메세지를 파라미터로 받아서 처리 할 수 있도록 합니다.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String listType = request.getParameter("listType");

		if (listType == null || listType.equals(""))
			response.getWriter().write("");
		else if (listType.equals("today"))
			response.getWriter().write(getToday());
		else if (listType.equals("ten"))
			response.getWriter().write(getTen());
		else {
			try {
				Integer.parseInt(listType);
				response.getWriter().write(getID(listType));
			} catch (Exception e) {
				// TODO: handle exception
				// e.printStackTrace();
				// System.out.println("여기야 ?");
				response.getWriter().write("");
			}
		}

	}

	// JSON 사용
	// 오늘에 대한 어떠한 결과를 출력해서 사용자에게 돌려줍니다.
	public String getToday() {

		StringBuffer result = new StringBuffer("");

		result.append("{\"result\":[");

		ChatDAO chatDAO = new ChatDAO();

		ArrayList<ChatDTO> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		System.out.println("여긴 서블릿 + " + chatList);
		System.out.println(chatList.size());

		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");

			// i가 마지막 원소가 아니라면
			if (i != chatList.size() - 1)
				result.append(",");
		}
		// result.append("]}");
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		System.out.println(
				"chatList.get(chatList.size() - 1).getChatID() : " + chatList.get(chatList.size() - 1).getChatID());

		System.out.println("JSON은 어떻게 생겼을까 ? : " + result);
		System.out.println("JSON은 어떻게 생겼을까 ? : " + result.toString());

		return result.toString();

	}

	// 최근을 기준으로 몇개만큼의 메시지를 가져오는 함수
	public String getTen() {

		StringBuffer result = new StringBuffer("");

		result.append("{\"result\":[");

		ChatDAO chatDAO = new ChatDAO();

		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(10);

		System.out.println("여긴 서블릿 + " + chatList);

		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");

			// i가 마지막 원소가 아니라면
			if (i != chatList.size() - 1)
				result.append(",");
		}
		// result.append("]}");
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		System.out.println("JSON은 어떻게 생겼을까 ? : " + result);
		System.out.println("JSON은 어떻게 생겼을까 ? : " + result.toString());

		return result.toString();
	}

	// 특정한 아이디보다 큰 값의 메시지만 추출
	public String getID(String chatID) {

		StringBuffer result = new StringBuffer("");

		result.append("{\"result\":[");

		ChatDAO chatDAO = new ChatDAO();

		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(chatID);

		System.out.println("여긴 서블릿 + " + chatList);

		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");

			// i가 마지막 원소가 아니라면
			if (i != chatList.size() - 1)
				result.append(",");
		}
		// result.append("]}");
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		System.out.println("JSON은 어떻게 생겼을까 ? : " + result);
		System.out.println("JSON은 어떻게 생겼을까 ? : " + result.toString());

		return result.toString();
	}

}
