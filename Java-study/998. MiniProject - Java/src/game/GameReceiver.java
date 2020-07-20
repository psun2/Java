package game;

import java.io.ObjectInputStream;
import java.net.Socket;

public class GameReceiver extends Thread{

	ObjectInputStream ois;

	public GameReceiver(Socket socket) {
		// TODO Auto-generated constructor stub

		try {

			ois = new ObjectInputStream(socket.getInputStream());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(true) {
			
			ser
			
			
			
			
		}
		
		
		
	}

	
}
