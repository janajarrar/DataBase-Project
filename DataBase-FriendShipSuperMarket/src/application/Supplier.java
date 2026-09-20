package application;

public class Supplier {
	private int id;
	private String name;
	private String phone;
	private String address;
	private String city;

	public Supplier(int id, String name, String phone, String address, String city) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
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

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}
}