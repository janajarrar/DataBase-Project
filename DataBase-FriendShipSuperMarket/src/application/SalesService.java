package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesService {

	public static List<Sale> getAllSales() throws SQLException {
		List<Sale> list = new ArrayList<>();
		String sql = "SELECT * FROM Sales ORDER BY saleId DESC";
		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Date d = rs.getDate("saleDate");
				list.add(new Sale(rs.getInt("saleId"), d == null ? null : d.toLocalDate(), rs.getDouble("totalAmount"),
						rs.getInt("Branch_id")));
			}
		}
		return list;
	}

	public static void addSale(java.time.LocalDate date, double total, int branchId) throws SQLException {
		String sql = "INSERT INTO Sales (saleDate, totalAmount, Branch_id) VALUES (?,?,?)";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, date == null ? null : Date.valueOf(date));
			ps.setDouble(2, total);
			ps.setInt(3, branchId);
			ps.executeUpdate();
		}
	}

	public static boolean updateSale(int saleId, java.time.LocalDate date, double total, int branchId)
			throws SQLException {
		String sql = "UPDATE Sales SET saleDate=?, totalAmount=?, Branch_id=? WHERE saleId=?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, date == null ? null : java.sql.Date.valueOf(date));
			ps.setDouble(2, total);
			ps.setInt(3, branchId);
			ps.setInt(4, saleId);
			return ps.executeUpdate() == 1;
		}
	}

	public static boolean deleteSale(int saleId) throws SQLException {
		String sql = "DELETE FROM Sales WHERE saleId=?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, saleId);
			return ps.executeUpdate() == 1;
		}

	}
}