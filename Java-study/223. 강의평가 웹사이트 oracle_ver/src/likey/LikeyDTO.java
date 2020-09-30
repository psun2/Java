package likey;

public class LikeyDTO {

	private String userID, userIP;
	private int evalueationID;

	public LikeyDTO() {
		// TODO Auto-generated constructor stub
	}

	public LikeyDTO(String userID, int evalueationID, String userIP) {
		super();
		this.userID = userID;
		this.evalueationID = evalueationID;
		this.userIP = userIP;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getEvalueationID() {
		return evalueationID;
	}

	public void setEvalueationID(int evalueationID) {
		this.evalueationID = evalueationID;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	@Override
	public String toString() {
		return "LikeyDTO [userID=" + userID + ", evalueationID=" + evalueationID + ", userIP=" + userIP + "]";
	}

}
