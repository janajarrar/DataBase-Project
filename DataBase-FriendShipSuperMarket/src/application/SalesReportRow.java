package application;

public class SalesReportRow {
	private String label;
	private double totalSales;
	private int ordersCount;

	public SalesReportRow(String label, double totalSales, int ordersCount) {
		this.label = label;
		this.totalSales = totalSales;
		this.ordersCount = ordersCount;
	}

	public String getLabel() {
		return label;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public int getOrdersCount() {
		return ordersCount;
	}
}