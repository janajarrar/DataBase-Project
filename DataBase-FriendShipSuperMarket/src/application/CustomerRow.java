package application;

public class CustomerRow {
	private int id;
	private String name;
	private String phone;
	private String password;

	public CustomerRow(int id, String name, String phone, String password) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}
}