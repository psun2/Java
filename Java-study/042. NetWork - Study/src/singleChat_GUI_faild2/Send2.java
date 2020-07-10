package singleChat_GUI_faild2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send2 {

	OutputStream out;

	public Send2(Socket socket, String message) {
		// TODO Auto-generated constructor stub

//		System.out.println("진입"); // 확인 ok
//		System.out.println(socket); // 여기서도 살아 있음.
		
		System.out.println("샌드2 -> 생성자");
		System.out.println("나는 샌드 2" + message);

		try {
			this.out = socket.getOutputStream();

			byte[] buffer = message.getBytes("UTF-8");

			out.write(buffer);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
