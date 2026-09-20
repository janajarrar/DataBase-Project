package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

	public static List<Product> getAllProducts() throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM Products";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("Product_id"), rs.getString("Product_name"), rs.getDouble("Price"),
						rs.getDate("Expiry_date"), rs.getInt("Supplier_id"), rs.getString("Category")));
			}
		}
		return list;
	}

	public static boolean addProduct(int id, String name, double price, java.sql.Date expiry, int supplierId,
			String category) throws SQLException {

		String sql = "INSERT INTO Products " + "(Product_id, Product_name, Price, Expiry_date, Supplier_id, Category) "
				+ "VALUES (?,?,?,?,?,?)";

		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, price);
			ps.setDate(4, expiry);
			ps.setInt(5, supplierId);
			ps.setString(6, category);

			return ps.executeUpdate() == 1;
		}
	}

	public static boolean updateProduct(int id, String name, double price, java.sql.Date expiry, int supplierId,
			String category) throws SQLException {

		String sql = "UPDATE Products SET Product_name=?, Price=?, Expiry_date=?, Supplier_id=?, Category=? "
				+ "WHERE Product_id=?";

		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setDate(3, expiry);
			ps.setInt(4, supplierId);
			ps.setString(5, category);
			ps.setInt(6, id);

			return ps.executeUpdate() == 1;
		}
	}

	public static boolean deleteProduct(int id) throws SQLException {
		String sql = "DELETE FROM Products WHERE Product_id=?";

		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() == 1;
		}
	}

	public static List<Product> getProductsByCategory(String category) throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM Products WHERE Category=?";

		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, category);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new Product(rs.getInt("Product_id"), rs.getString("Product_name"), rs.getDouble("Price"),
							rs.getDate("Expiry_date"), rs.getInt("Supplier_id"), rs.getString("Category")));
				}
			}
		}
		return list;
	}
}