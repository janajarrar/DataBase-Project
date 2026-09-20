package application;

public class InventoryReportRow {
	private int productId;
	private String productName;
	private String branchName;
	private int totalQuantity;

	public InventoryReportRow(int productId, String productName, String branchName, int totalQuantity) {
		this.productId = productId;
		this.productName = productName;
		this.branchName = branchName;
		this.totalQuantity = totalQuantity;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getBranchName() {
		return branchName;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}
}