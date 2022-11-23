package project;

import javafx.beans.property.*;

public class CusLogin {
	private String userID;
	private String password;
	private String accountType;
	
	
	
	public EmpLogin(int userID, String password, String accountType) {
		this.userID = userID;
		this.password = password;
		this.accountType = accountType;
		
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String email) {
		this.accountType = accountType;
	}

	
}	