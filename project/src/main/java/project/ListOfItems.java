package project;

public class ListOfItems {
	private int listIndex; 
	private String orderID;
	private String itemIDs;
	
	public ListOfItems(int listIndex, String orderID, String itemIDs) {
		this.listIndex = listIndex;
		this.orderID = orderID;
		this.itemIDs = itemIDs;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getItemIDs() {
		return itemIDs;
	}

	public void setItemIDs(String itemIDs) {
		this.itemIDs = itemIDs;
	}

	public int getListIndex() {
		return listIndex;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}
	
	
	
}
