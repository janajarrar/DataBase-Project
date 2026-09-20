package application;

public class TopProductRow {
	private int productId;
	private String productName;
	private int totalQty;

	public TopProductRow(int productId, String productName, int totalQty) {
		this.productId = productId;
		this.productName = productName;
		this.totalQty = totalQty;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getTotalQty() {
		return totalQty;
	}
}