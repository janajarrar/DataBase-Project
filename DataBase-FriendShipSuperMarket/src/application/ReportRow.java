package application;

public class ReportRow {
	private String label;
	private double total;
	private int count;

	public ReportRow(String label, double total, int count) {
		this.label = label;
		this.total = total;
		this.count = count;
	}

	public String getLabel() {
		return label;
	}

	public double getTotal() {
		return total;
	}

	public int getCount() {
		return count;
	}
}