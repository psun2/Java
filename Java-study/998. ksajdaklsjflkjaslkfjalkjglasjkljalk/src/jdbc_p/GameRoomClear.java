package jdbc_p;

public class GameRoomClear {

	public static void main(String[] args) {
		
		String nn = null;
		
		GameRoomDTO dto = new GameRoomDTO();
		
		for (int i = 1; i < 19 ; i++) {
			
			dto.setNo(i);
			dto.setUser1(nn);
			dto.setUser2(nn);
			
			new GameRoomDAO().reset(dto);
		}

	}

}
