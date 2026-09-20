package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/FriendshipSupermarket?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "root";

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}