package application;

import java.util.*;

public class CartStore {

	private static final Map<String, List<CartItem>> carts = new HashMap<>();

	public static List<CartItem> get(String customerName) {
		if (!carts.containsKey(customerName))
			carts.put(customerName, new ArrayList<>());
		return carts.get(customerName);
	}

	public static void clear(String customerName) {
		get(customerName).clear();
	}

	public static void add(String customerName, Product product, int qty) {
		if (product == null)
			return;
		qty = Math.max(1, qty);

		List<CartItem> cart = get(customerName);

		for (CartItem it : cart) {
			if (it.getProduct().getProductId() == product.getProductId()) {
				it.setQuantity(it.getQuantity() + qty);
				return;
			}
		}
		cart.add(new CartItem(product, qty));
	}
}