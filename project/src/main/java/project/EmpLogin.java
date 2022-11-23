package project;

<<<<<<< HEAD
public class EmpLogin {
	private int userID;
=======
import javafx.beans.property.*;

public class EmpLogin {
	private String userID;
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
	private String password;
	private String email;
	
	
	
	public EmpLogin(int userID, String password, String email) {
		this.userID = userID;
		this.password = password;
		this.email = email;
		
	}

<<<<<<< HEAD
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
=======
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
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
