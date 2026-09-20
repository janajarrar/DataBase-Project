package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

	public static List<ReportRow> revenueByBranch() throws SQLException {
		List<ReportRow> list = new ArrayList<>();
		String sql = "SELECT b.Branch_name AS label, " + "       COALESCE(SUM(o.Total),0) AS total, "
				+ "       COUNT(o.Order_id) AS cnt " + "FROM Branch b "
				+ "LEFT JOIN Orders o ON b.Branch_id = o.Branch_id " + "GROUP BY b.Branch_name "
				+ "ORDER BY total DESC";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new ReportRow(rs.getString("label"), rs.getDouble("total"), rs.getInt("cnt")));
			}
		}
		return list;
	}

	public static List<ReportRow> revenueByDay() throws SQLException {
		List<ReportRow> list = new ArrayList<>();
		String sql = "SELECT Order_date AS label, " + "       COALESCE(SUM(Total),0) AS total, "
				+ "       COUNT(Order_id) AS cnt " + "FROM Orders " + "GROUP BY Order_date "
				+ "ORDER BY Order_date DESC";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new ReportRow(String.valueOf(rs.getDate("label")), rs.getDouble("total"), rs.getInt("cnt")));
			}
		}
		return list;
	}

	public static List<TopProductRow> topProducts() throws SQLException {
		List<TopProductRow> list = new ArrayList<>();
		String sql = "SELECT p.Product_id, p.Product_name, SUM(od.Quantity) AS totalQty " + "FROM OrderDetails od "
				+ "JOIN Products p ON od.Product_id = p.Product_id " + "GROUP BY p.Product_id, p.Product_name "
				+ "ORDER BY totalQty DESC";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new TopProductRow(rs.getInt("Product_id"), rs.getString("Product_name"),
						rs.getInt("totalQty")));
			}
		}
		return list;
	}

	public static List<InventoryReportRow> inventoryRemaining() throws SQLException {
		List<InventoryReportRow> list = new ArrayList<>();
		String sql = "SELECT p.Product_id, p.Product_name, b.Branch_name, COALESCE(i.Total_quantity,0) AS Total_quantity "
				+ "FROM Inventory i " + "JOIN Products p ON i.Product_id = p.Product_id "
				+ "JOIN Branch b ON i.Branch_id = b.Branch_id " + "ORDER BY Total_quantity ASC";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new InventoryReportRow(rs.getInt("Product_id"), rs.getString("Product_name"),
						rs.getString("Branch_name"), rs.getInt("Total_quantity")));
			}
		}
		return list;
	}
}