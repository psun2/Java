package login;

public class GameMain {

	public static void main(String[] args) {

		JoinTest jt = new JoinTest();
		GameDTO dto = new GameDTO();

		dto.setId(jt.id);

		dto.setPw(jt.pw);

		dto.setName(jt.name);

		dto.setBirth(jt.birth);

		dto.setEmail(jt.email);

		for (GameDTO mem : new GameDAO().list()) {
			System.out.println(mem);
		}
		System.out.println("------------------------------");

		System.out.println(new GameDAO().detail(""));
	}

}
