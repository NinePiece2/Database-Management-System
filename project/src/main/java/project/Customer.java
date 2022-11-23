package project;

public class Customer {
	private int cusID;
	private String name;
	private String email;
	private	int phoneNum;
	private String address;
    private String pcCard;
    private int savedCard;
	
	
	public Customer(int cusID, String name, String email, int phoneNum, String address, String pcCard, int savedCard) {
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

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public String getPCCard() {
		return pcCard;
	}

	public void setPCCard(String pcCard) {
		this.pcCard = pcCard;
	}

    public int getSavedCard() {
		return savedCard;
	}

	public void setSavedCard(int savedCard) {
		this.savedCard = savedCard;
	}

	
}	