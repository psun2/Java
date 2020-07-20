package game;

import java.util.ArrayList;

public class MeGameInfo {

	private static final long serialVersionUID = 1L;

	final static int PANG = 4;

	ArrayList<Puyo> puyos;

	boolean endGame;

	public MeGameInfo() {
		// TODO Auto-generated constructor stub
		this.puyos = new ArrayList<Puyo>();
		this.endGame = false;
	}

}
