package POJO;

public class User {
	
	//Step#1: Declare Private variables of JSON 
	private String name;
	private String gender;
	private String email;
	private String status;
	
	
	//Step#2: Create a constructor 
	public User(String name, String gender, String email, String status) {		
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}

	//Step#3: Create getters and setters 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
