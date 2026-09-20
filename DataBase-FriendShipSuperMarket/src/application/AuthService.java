package application;

import java.sql.*;

public class AuthService {

	public static boolean customerLogin(String name, String password) throws SQLException {
		String sql = "SELECT Customer_id FROM Customer WHERE Customer_name = ? AND Password = ?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name.trim());
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	public static boolean customerRegister(int customerId, String fullName, String email, String phone, String password)
			throws SQLException {
		String sql = "INSERT INTO Customer (Customer_id, Customer_name, Email, PhoneNumber, Password) VALUES (?,?,?,?,?)";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, customerId);
			ps.setString(2, fullName.trim());
			ps.setString(3, email.trim());
			ps.setString(4, phone.trim());
			ps.setString(5, password);

			return ps.executeUpdate() == 1;
		}
	}

	public static boolean employeeLogin(String fullName, int employeeId, String password) throws SQLException {
		String sql = "SELECT Employee_id FROM Employee WHERE Employee_id = ? AND Employee_name = ? AND Password = ?";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, employeeId);
			ps.setString(2, fullName.trim());
			ps.setString(3, password);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	public static boolean employeeRegister(int employeeId, String fullName, String role, double salary, int branchId,
			String password) throws SQLException {
		String sql = "INSERT INTO Employee (Employee_id, Employee_name, Role, Salary, Branch_id, Password) VALUES (?,?,?,?,?,?)";
		try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, employeeId);
			ps.setString(2, fullName.trim());
			ps.setString(3, role.trim());
			ps.setDouble(4, salary);
			ps.setInt(5, branchId);
			ps.setString(6, password);

			return ps.executeUpdate() == 1;
		}
	}
}