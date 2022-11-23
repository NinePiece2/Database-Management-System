package project;

import javafx.beans.property.*;

public class CusLogin {
	private int userID;
	private String password;
	private String accountType;
	
	
	
	public CusLogin(int userID, String password, String accountType) {
		this.userID = userID;
		this.password = password;
		this.accountType = accountType;
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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