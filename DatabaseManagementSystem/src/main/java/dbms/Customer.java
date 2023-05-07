package dbms;

public class Customer {
	private int cusID;
	private String name;
	private String email;
	private	String phoneNum;
	private String address;
    private String pcCard;
    private String savedCard;
	
	
	public Customer(int cusID, String name, String email, String phoneNum, String address, String pcCard, String savedCard) {
		this.cusID = cusID;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
        this.pcCard = pcCard;
        this.savedCard = savedCard;
	}


	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getPcCard() {
		return pcCard;
	}


	public void setPcCard(String pcCard) {
		this.pcCard = pcCard;
	}


	public String getSavedCard() {
		return savedCard;
	}


	public void setSavedCard(String savedCard) {
		this.savedCard = savedCard;
	}

 

	
}	