package excep_p;

import java.util.Scanner;

public class UserExceptionMain {

	public static void main(String[] args) {

		String uid = "aaa", upw = "1234";

		Scanner sc = new Scanner(System.in);

		String id, pw;

		while (true) {
			try {

				System.out.println("id : ");
				id = sc.nextLine();
				if (!id.equals(uid))
					throw new Exception("id Error");

				System.out.println("password : ");
				pw = sc.nextLine();
				if (!pw.equals(upw))
					throw new Exception("password" + " Error");

				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		sc.close();

		System.out.println("id : " + id + ", pw : " + pw);

	}

}
