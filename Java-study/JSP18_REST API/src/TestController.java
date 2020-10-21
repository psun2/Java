
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("actionDo() 호출");

		request.setCharacterEncoding("UTF-8");

		// URL로부터 URI, ContextPath, Command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		String conPath2 = request.getServletContext().getRealPath("/");

		// 테스트 출력
		System.out.println("uri: " + uri);
		System.out.println("conPath: " + conPath);
		System.out.println("conPath2: " + conPath2);
		System.out.println("com: " + com);
		// 컨트롤러는 아래 두가지를 결정해야 한다.
		Command command = null; // 1. 어떠한 로직을 수행할지 결정
		String viewPage = null; // 2. 어떠한 페이지를(뷰) 보여줄지 결정

		// 컨트롤러는 커맨드에 따라, 로직을 수핼 하고
		// 결과를 내보낼 view 를 결정 한다.
		// React 의 reducer 와 비슷한 역할
		switch (com) {
		case "/restTest.test":
			command = new ListCommandRESTTEST();
			command.execute(request, response);
			viewPage = "restTest.jsp";
			break;
		} // end switch

		// response 를 위해, 위에서 결정된 페이지(view) 에 forward 해줌
		response.getWriter().write((String) request.getAttribute("REST"));

	} // end actionDo()

} // end Controller
