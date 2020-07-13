package theory.udtSingleChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReciever { // 서버는 아니지만 서버의 역할을 하는 ....reciever

	public static void main(String[] args) {

		try {
			DatagramSocket ds = new DatagramSocket(7777);

			byte[] arr = new byte[1024];

			DatagramPacket dp = new DatagramPacket(arr, arr.length);

			ds.receive(dp);

			ds.close();

			System.out.println(dp.getAddress());

			System.out.println(new String(arr));

			System.out.println("수신완료");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}