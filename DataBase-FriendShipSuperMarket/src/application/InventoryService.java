package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

	public static List<InventoryRow> getInventoryView() throws SQLException {

		List<InventoryRow> list = new ArrayList<>();

		String sql = "SELECT i.Inventory_id, b.Branch_name, p.Product_name, i.Total_quantity " + "FROM Inventory i "
				+ "JOIN Branch b ON i.Branch_id = b.Branch_id " + "JOIN Products p ON i.Product_id = p.Product_id "
				+ "ORDER BY b.Branch_name";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				list.add(new InventoryRow(rs.getInt("Inventory_id"), rs.getString("Branch_name"),
						rs.getString("Product_name"), rs.getInt("Total_quantity")));
			}
		}

		return list;
	}
}