package user;

public class userDTO {

	private String userID, userPassword, userName, userGender, userEmail, userProfile;
	private int userAge;

	public userDTO() {
		// TODO Auto-generated constructor stub
	}

	public userDTO(String userID, String userPassword, String userName, int userAge, String userGender,
			String userEmail, String userProfile) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userEmail = userEmail;
		this.userProfile = userProfile;
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

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
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

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "userDTO [userID=" + userID + ", userPassword=" + userPassword + ", userName=" + userName + ", userAge="
				+ userAge + ", userGender=" + userGender + ", userEmail=" + userEmail + ", userProfile=" + userProfile
				+ "]";
	}

}
