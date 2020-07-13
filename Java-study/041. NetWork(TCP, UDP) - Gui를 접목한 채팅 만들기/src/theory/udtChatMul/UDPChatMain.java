package theory.udtChatMul;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

class UDPChatSender extends Thread {

	InetAddress addr;
	int port;

	public UDPChatSender(String addr, int port) {
		super();
		try {
			this.addr = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = port;
	}

	@Override
	public void run() {
		try {
			DatagramSocket ds = new DatagramSocket();

			Scanner sc = new Scanner(System.in);

			while (true) {

				String msg = sc.nextLine();

				DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);

				ds.send(dp);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class UDPChatReceiver extends Thread {

	int port;

	public UDPChatReceiver(int port) {
		super();
		this.port = port;
	}

	@Override
	public void run() {

		try {
			DatagramSocket ds = new DatagramSocket(port);

			while (true) {

				byte[] arr = new byte[1024];

				DatagramPacket dp = new DatagramPacket(arr, arr.length);

				ds.receive(dp);

				System.out.println(dp.getAddress() + " : " + new String(arr).trim());

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class UDPChatMain {

	public static void main(String[] args) {

		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// new UDPChatSender("192.168.1.226", 7777).start();
		new UDPChatSender("192.168.1.255", 7777).start();
		// 255 는 전체 통일 되어 다중 채팅이 됩니다.

		new UDPChatReceiver(7777).start();
	}

}