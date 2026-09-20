package application;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderService {

	public static int findCustomerIdByName(String customerName) throws SQLException {
		String sql = "SELECT Customer_id FROM Customer WHERE Customer_name = ?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, customerName.trim());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
				throw new SQLException("Customer not found.");
			}
		}
	}

	public static int createOrder(String customerName, int branchId, List<CartItem> cart) throws SQLException {
		return createOrder(customerName, branchId, cart, null);
	}

	public static int createOrder(String customerName, int branchId, List<CartItem> cart, Offer offer)
			throws SQLException {
		if (cart == null || cart.isEmpty())
			throw new SQLException("Cart is empty.");

		int customerId = findCustomerIdByName(customerName);

		double subtotal = 0.0;
		for (CartItem item : cart)
			subtotal += item.getLineTotal();

		double rate = (offer == null) ? 0.0 : offer.getDiscountRate();
		double discount = subtotal * (rate / 100.0);
		double totalAfter = subtotal - discount;

		String insertOrder = "INSERT INTO Orders (Order_date, Branch_id, Customer_id, Total) VALUES (?,?,?,?)";
		String insertDetails = "INSERT INTO OrderDetails (Order_id, Product_id, Quantity, Price) VALUES (?,?,?,?)";

		try (Connection con = DBConnection.connect()) {
			con.setAutoCommit(false);

			int orderId;
			try (PreparedStatement ps = con.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
				ps.setDate(1, Date.valueOf(LocalDate.now()));
				ps.setInt(2, branchId);
				ps.setInt(3, customerId);
				ps.setDouble(4, totalAfter);
				ps.executeUpdate();

				try (ResultSet keys = ps.getGeneratedKeys()) {
					if (!keys.next())
						throw new SQLException("Failed to generate Order ID.");
					orderId = keys.getInt(1);
				}
			}

			try (PreparedStatement psd = con.prepareStatement(insertDetails)) {
				for (CartItem item : cart) {
					psd.setInt(1, orderId);
					psd.setInt(2, item.getProduct().getProductId());
					psd.setInt(3, item.getQuantity());
					psd.setDouble(4, item.getProduct().getPrice());
					psd.addBatch();
				}
				psd.executeBatch();
			}

			con.commit();
			con.setAutoCommit(true);
			return orderId;
		}
	}
}