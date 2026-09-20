package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferService {

	public static List<Offer> getAllOffers() throws SQLException {
		List<Offer> list = new ArrayList<>();
		String sql = "SELECT * FROM Offers";

		try (Connection con = DBConnection.connect();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				list.add(new Offer(rs.getInt("offerId"), rs.getString("description"), rs.getDouble("discountRate")));
			}
		}
		return list;
	}

	public static boolean addOffer(int id, String desc, double rate) throws SQLException {
		String sql = "INSERT INTO Offers (offerId, description, discountRate) VALUES (?,?,?)";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.setString(2, desc);
			ps.setDouble(3, rate);
			return ps.executeUpdate() == 1;
		}
	}

	public static boolean updateOffer(int id, String desc, double rate) throws SQLException {
		String sql = "UPDATE Offers SET description=?, discountRate=? WHERE offerId=?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, desc);
			ps.setDouble(2, rate);
			ps.setInt(3, id);
			return ps.executeUpdate() == 1;
		}
	}

	public static boolean deleteOffer(int id) throws SQLException {
		String sql = "DELETE FROM Offers WHERE offerId=?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() == 1;
		}
	}
}