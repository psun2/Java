package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadAction")
public class downloadAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = request.getParameter("file");

		// fileDownload.jsp
		// String directory = application.getRealPath("/upload/");
		// request.getContextPath()
		String directory = this.getServletContext().getRealPath("/upload/");
		File file = new File(directory + "/" + fileName);

		// 어떠한 데이터의 정보를 주고 받을 지 를 담슴니다.
		String mimeType = getServletContext().getMimeType(file.toString());
		if (mimeType == null) {
			response.setContentType("application/octet-stream");
		}

		// 실제로 다운로드 받을 다운로드 이름
		String downloadName = null;

		// 접속한 사용자의 브라우저에 따라 다르게 적용합니다.
		// MISE : 기본적으로 internet explorer 를 의미합니다.
		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			// 인터넷 익스플로러 접속자가 아니라면 utf-8 인코딩을 적용한 방식으로 얻어서 8859_1 형식으로 변환해 줍니다.
			// 8859_1 : 데이터의 깨짐을 방지합니다.
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
		} else {
			downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		}
		System.out.println(downloadName);

		response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\";");

		FileInputStream fileInputStream = new FileInputStream(file);

		ServletOutputStream servletOutputStream = response.getOutputStream();

		byte[] buffer = new byte[1024];
		int data = 0;

		while ((data = (fileInputStream.read(buffer, 0, buffer.length))) != -1) {
			servletOutputStream.write(buffer, 0, data);
		}

		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}

}
