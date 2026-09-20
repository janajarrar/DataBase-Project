package application;

import java.util.HashMap;
import java.util.Map;

public class SelectedOfferStore {
	private static final Map<String, Offer> selected = new HashMap<>();

	public static void setSelected(String customerName, Offer offer) {
		selected.put(customerName, offer);
	}

	public static Offer getSelected(String customerName) {
		return selected.get(customerName);
	}

	public static void clear(String customerName) {
		selected.remove(customerName);
	}
}