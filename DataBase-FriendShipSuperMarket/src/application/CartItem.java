package application;

public class CartItem {
	private final Product product;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = Math.max(1, quantity);
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		this.quantity = Math.max(1, q);
	}

	public double getLineTotal() {
		return product.getPrice() * quantity;
	}
}