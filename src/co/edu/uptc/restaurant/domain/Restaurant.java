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
	
	
}
