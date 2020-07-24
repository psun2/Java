package netdata;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetData {

	public static int port = 7777;

	public static String host = "192.168.1.6";

	static void getIp() {
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			System.out.println(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		getIp();
	}
}
