package project;

<<<<<<< HEAD
public class Purchase {
	private String invoiceID;
	private int cusID;
	private String orderID;
=======
import javafx.beans.property.*;

public class Purchase {
	private int invoiceID;
	private int cusID;
	private int orderID;
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
	private double totalPrice;
    private double taxRate;
    private String couponCodes;

	
	
<<<<<<< HEAD
	public Purchase(String invoiceID, String orderID, int cusID, double totalPrice, double taxRate, String couponCodes) {
		this.invoiceID = invoiceID;
        this.cusID = cusID;
        this.orderID = orderID;
=======
	public Purchase(int invoiceID, int cusID, int orderID, double totalPrice, double taxRate, String couponCodes) {
		this.invoiceID = invoiceID;
        this.cusID = cusID
        this.orderID = orderID
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
		this.totalPrice = totalPrice;
		this.taxRate = taxRate;
		this.couponCodes = couponCodes;
	}

<<<<<<< HEAD
	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
=======
	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
		this.invoiceID = invoiceID;
	}

    public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

<<<<<<< HEAD
    public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
=======
    public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
>>>>>>> 7003cf9d628bb95bc423a9f8e613558588742dfc
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