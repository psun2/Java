package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");

		System.out.println("chatListServlet fromID : " + fromID);
		System.out.println("chatListServlet toID : " + toID);
		System.out.println("chatListServlet listType : " + listType);

		fromID = URLDecoder.decode(fromID, "UTF-8");
		toID = URLDecoder.decode(toID, "UTF-8");
		listType = URLDecoder.decode(listType, "UTF-8");

		System.out.println("chatListServlet fromID : " + fromID);
		System.out.println("chatListServlet toID : " + toID);
		System.out.println("chatListServlet listType : " + listType);

		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || listType == null
				|| listType.equals("")) {
			response.getWriter().write(""); // 오류 발생
		} else if (listType.equals("ten"))
			// URLDecoder.decode 사용이유 : 한글일수 있기 때문입니다.
			response.getWriter().write(getTen(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8")));
		else {
			try {
				// 본인 말고 다른사람의 메시지 함은 볼수 없게 막습니다
				// 로그인한 사용자와 정보를 요청하는 사용자가 정확히 일치할때 정보를 보내 줄수 있도록 합니다.
				HttpSession session = request.getSession();
				if (!fromID.equals((String) session.getAttribute("userID"))) {
					// 로그인 되어있는 아이디와 현재 서블릿으로 넘어온 아이디가 같은지 검증
//					session.setAttribute("messageType", "오류 메시지");
//					session.setAttribute("messageContent", "접근할 수 없습니다.");
//					response.sendRedirect("index.jsp");

					// 서블릿은 위와 같은 문법을 사용 할 수 없다고 합니다.
					// 그러므로 공백을 출력 해서 요청을 받을때 공백을 출력 해서 아무 행동도 일어나지 않게 합니다.
					response.getWriter().write("");
					return;
				}
				response.getWriter().write(getID(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8"),
						URLDecoder.decode(listType, "UTF-8")));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				response.getWriter().write(""); // 오류 발생
			}
		}

	}

	public String getTen(String fromID, String toID) {
		
		StringBuffer result = new StringBuffer("");
		// JSON 형태 사용
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100); // 최근 100개까지 채팅데이터를 가져 올 수 있습니다.
		if (chatList.size() == 0)
			return "";
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime() + "\"}]");

			// 마지막 원소가 아니라면
			if (i != chatList.size() - 1)
				result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		System.out.println(result.toString());

		// 반환전 즉 반환되면 읽음으로서 읽음처리 실행
		chatDAO.readChat(fromID, toID);

		return result.toString();
	}

	public String getID(String fromID, String toID, String chatID) {

		StringBuffer result = new StringBuffer("");
		// JSON 형태 사용
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		if (chatList.size() == 0)
			return "";
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime() + "\"}]");

			// 마지막 원소가 아니라면
			if (i != chatList.size() - 1)
				result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t sadasujdajfkalfjklahsjkrfhk");
		System.out.println(result.toString());

		// 반환전 즉 반환되면 읽음으로서 읽음처리 실행
		chatDAO.readChat(fromID, toID);

		return result.toString();
	}

}
