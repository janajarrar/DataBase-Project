package application;

public class Employee {
	private int id;
	private String name;
	private String role;
	private double salary;
	private int branchId;

	public Employee(int id, String name, String role, double salary, int branchId) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.salary = salary;
		this.branchId = branchId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public double getSalary() {
		return salary;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}