package dbms;

public class Purchase {
	private String invoiceID;
	private int cusID;
	private String orderID;
	private double totalPrice;
    private double taxRate;
    private String couponCodes;

	
	
	public Purchase(String invoiceID, String orderID, int cusID, double totalPrice, double taxRate, String couponCodes) {
		this.invoiceID = invoiceID;
        this.cusID = cusID;
        this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.taxRate = taxRate;
		this.couponCodes = couponCodes;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

    public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

    public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
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