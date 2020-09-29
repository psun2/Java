package user;

public class UserDTO {

	private String userID, userPassword;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String userID, String userPassword) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserDTO [userID=" + userID + ", userPassword=" + userPassword + "]";
	}

}
