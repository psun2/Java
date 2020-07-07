package exam;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class TCPSingleReceiver extends Thread {
	
	DataInputStream dis;

	public TCPSingleReceiver(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}