package POJO;

public class BookingAuthLogin {
	
	//Step#1: Create private  variables of JSON  body
	private String username;
	private String password;
	
	//Step#2: Create a Constructor. Constructor means a method with the same class name

	public BookingAuthLogin(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	
	//Step#3: Create getters and setters 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
