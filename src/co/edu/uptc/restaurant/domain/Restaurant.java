package co.edu.uptc.restaurant.domain;

public class Restaurant {
	
	private String name;
	private float qualification;
	private String address;
	private int id;
	private String phoneNumber;
	private int numberTables;
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name, float qualification, String address, int id, String phoneNumber, int numberTables) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.address = address;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.numberTables = numberTables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getQualification() {
		return qualification;
	}

	public void setQualification(float qualification) {
		this.qualification = qualification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getNumberTables() {
		return numberTables;
	}

	public void setNumberTables(int numberTables) {
		this.numberTables = numberTables;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", qualification=" + qualification + ", address=" + address + ", id=" + id
				+ ", phoneNumber=" + phoneNumber + ", numberTables=" + numberTables + "]";
	}
	
	
}
