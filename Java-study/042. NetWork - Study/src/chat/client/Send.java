package chat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Send { // 엔터시에만 쓰여져도 되기 때문에 Thread가 돌아갈 필요가 없다고 생각합니다.

	OutputStream out;

	public Send(Socket socket, String message) {
		// TODO Auto-generated constructor stub

		System.out.println("샌드 생성");

		try {

			this.out = socket.getOutputStream();

			byte[] buffer = message.getBytes();

			System.out.println(Arrays.toString(buffer));

			out.write(buffer);

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
