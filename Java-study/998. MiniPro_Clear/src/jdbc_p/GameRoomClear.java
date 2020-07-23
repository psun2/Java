package jdbc_p;

public class GameRoomClear {

	public static void main(String[] args) {
		
		GameRoomDTO dto = new GameRoomDTO();
		
		for (int i = 1; i < 19 ; i++) {
			
			dto.setNo(i);
			dto.setUser1(null);
			dto.setUser2(null);
			
			new GameRoomDAO().reset(dto);
		}

	}

}
