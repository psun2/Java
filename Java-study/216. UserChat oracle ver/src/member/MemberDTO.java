package member;

public class MemberDTO {

	private String userID, userPassword, userName, userGender, userEmail;
	private int uerAge;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String userID, String userPassword, String userName, int uerAge, String userGender,
			String userEmail) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.uerAge = uerAge;
		this.userGender = userGender;
		this.userEmail = userEmail;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUerAge() {
		return uerAge;
	}

	public void setUerAge(int uerAge) {
		this.uerAge = uerAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "MemberDTO [userID=" + userID + ", userPassword=" + userPassword + ", userName=" + userName + ", uerAge="
				+ uerAge + ", userGender=" + userGender + ", userEmail=" + userEmail + "]";
	}

}
