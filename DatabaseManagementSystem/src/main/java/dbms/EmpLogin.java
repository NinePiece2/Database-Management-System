package dbms;

public class EmpLogin {
	private int userID;
	private String password;
	private String email;
	
	
	
	public EmpLogin(int userID, String password, String email) {
		this.userID = userID;
		this.password = password;
		this.email = email;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}	
