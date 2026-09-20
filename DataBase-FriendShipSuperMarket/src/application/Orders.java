package application;

public class Orders {
	private int orderId;
	private String orderDate;
	private int branchId;
	private int customerId;
	private double total;

	public Orders(int orderId, String orderDate, int branchId, int customerId, double total) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.branchId = branchId;
		this.customerId = customerId;
		this.total = total;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public int getBranchId() {
		return branchId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public double getTotal() {
		return total;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}