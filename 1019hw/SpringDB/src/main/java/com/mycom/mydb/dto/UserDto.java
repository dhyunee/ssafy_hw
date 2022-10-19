package com.mycom.mydb.dto;

public class UserDto {
	private String userId;
	private String userName; 
	private String email; 
	private int userAge;
	
	public UserDto() {}

	public UserDto(String userId, String userName, String email, int userAge) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.userAge = userAge;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", email=" + email + ", userAge=" + userAge
				+ "]";
	}
	
}
