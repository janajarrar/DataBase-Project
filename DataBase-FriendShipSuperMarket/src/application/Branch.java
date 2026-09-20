package application;

public class Branch {
	private int id;
	private String name;
	private String address;
	private int managerId;

	public Branch(int id, String name, String address, int managerId) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getManagerId() {
		return managerId;
	}
}