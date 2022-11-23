package project;

import javafx.beans.property.*;

public class EmpLogin {
	private String userID;
	private String password;
	private String email;
	
	
	
	public EmpLogin(int userID, String password, String email) {
		this.userID = userID;
		this.password = password;
		this.email = email;
		
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}	
