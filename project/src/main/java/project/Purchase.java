package project;

import javafx.beans.property.*;

public class Purchase {
	private int invoiceID;
	private int cusID;
	private int orderID;
	private double totalPrice;
    private double taxRate;
    private String couponCodes;

	
	
	public Purchase(int invoiceID, int cusID, int orderID, double totalPrice, double taxRate, String couponCodes) {
		this.invoiceID = invoiceID;
        this.cusID = cusID
        this.orderID = orderID
		this.totalPrice = totalPrice;
		this.taxRate = taxRate;
		this.couponCodes = couponCodes;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

    public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

    public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public String getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(String couponCodes) {
		this.couponCodes = couponCodes;
	}

	
}	