package com.test.user;

public class UserDTO {

	private String userName;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + "]";
	}

}
