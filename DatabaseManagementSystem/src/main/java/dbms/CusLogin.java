package dbms;

public class CusLogin {
	private int userID;
	private String password;
	private char accountType;
	
	
	
	public CusLogin(int userID, char accountType,  String password) {
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

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	
}	