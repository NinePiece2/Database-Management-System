package dbms;

import javafx.beans.property.*;

public class Item {
	private String itemID;
	private String itemName;
	private double price;
	private	int rating;
	private String itemCategory;
	private int inventory;
	private BooleanProperty select;
	
	public Item(String itemID, String itemName, double price, int rating, String itemCategory, int inventory) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
		this.rating = rating;
		this.itemCategory = itemCategory;
		this.inventory = inventory;
		this.select = new SimpleBooleanProperty(false);
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public BooleanProperty getSelect() {
		return select;
	}

	public void setSelect(BooleanProperty select) {
		this.select = select;
	}

	
}	
	
