package theory;

import java.net.InetAddress;

public class InUrlMain {

	public static void main(String[] args) {

		try {

			// 해당 url 에 해당하는 랜덤한 서버 주소 반환
			InetAddress ip = InetAddress.getByName("www.naver.com");

			System.out.println(ip);
			// www.naver.com/210.89.164.90

			System.out.println("----------------------------------");

			// 해당 url 이 가지고 있는 모든 서버 주소를 반환
			InetAddress[] arr = InetAddress.getAllByName("www.naver.com");

			for (InetAddress inetAddress : arr) {
				System.out.println(inetAddress);
			}
			// www.naver.com/210.89.164.90
			// www.naver.com/210.89.160.88

			System.out.println("-----------------------------");

			// 내 IP 주소
			System.out.println(InetAddress.getLocalHost());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
