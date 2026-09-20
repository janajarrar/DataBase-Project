package application;

import java.time.LocalDate;

public class Sale {
	private int saleId;
	private LocalDate saleDate;
	private double totalAmount;
	private int branchId;

	public Sale(int saleId, LocalDate saleDate, double totalAmount, int branchId) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.totalAmount = totalAmount;
		this.branchId = branchId;
	}

	public int getSaleId() {
		return saleId;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public int getBranchId() {
		return branchId;
	}
}