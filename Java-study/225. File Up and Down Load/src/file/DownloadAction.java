package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

@WebServlet("/DownloadAction")
public class DownloadAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html charset=UTF-8");

		String fileName = URLDecoder.decode(request.getParameter("file"), "UTF-8");

		String savePath = request.getServletContext().getRealPath("/upload");
		File file = new File(savePath, fileName);

		// 어떠한 정보를 주고 받을 것인지 설정
		// 마임타입에 파일이란것을 알려줄수 있도록합니다.
		String mimeType = getServletContext().getMimeType(file.toString());

		if (mimeType == null)
			response.setContentType("application/octet-stream");

		String downloadName = null;
		// MSIE = 익스플로러를 의미....
		// 값이 -1 이면 찾을 수 없어 즉 익스플러가 아닙니다.
		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
		} else { // 익스플로러 인 경우
			downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		}

		response.setHeader("Content-Disposition", "attachment; fileName=\"" + downloadName + "\";");

		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = response.getOutputStream();

		byte[] buffer = new byte[1024];
		int data = 0;

		while ((data = (fis.read(buffer, 0, buffer.length))) != -1) {
			sos.write(buffer, 0, data);
		}

		sos.flush();

		sos.close();
		fis.close();

	}

}
