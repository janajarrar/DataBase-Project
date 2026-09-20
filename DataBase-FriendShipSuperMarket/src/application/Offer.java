package application;

public class Offer {
	private int offerId;
	private String description;
	private double discountRate;

	public Offer(int offerId, String description, double discountRate) {
		this.offerId = offerId;
		this.description = description;
		this.discountRate = discountRate;
	}

	public int getOfferId() {
		return offerId;
	}

	public String getDescription() {
		return description;
	}

	public double getDiscountRate() {
		return discountRate;
	}
}