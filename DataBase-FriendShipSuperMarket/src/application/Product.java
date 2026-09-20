package application;

import java.sql.Date;

public class Product {
	private int productId;
	private String productName;
	private double price;
	private Date expiryDate;
	private int supplierId;
	private String category;

	public Product(int productId, String productName, double price, Date expiryDate, int supplierId, String category) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.expiryDate = expiryDate;
		this.supplierId = supplierId;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public String getCategory() {
		return category;
	}
}