package application;

public class OrderDetails {
	private int detailId;
	private int orderId;
	private int productId;
	private int quantity;
	private double price;

	public OrderDetails(int detailId, int orderId, int productId, int quantity, double price) {
		this.detailId = detailId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getDetailId() {
		return detailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}