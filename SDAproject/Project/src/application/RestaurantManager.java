package application;

public class RestaurantManager {

	private String userName;
	private String password;
	public RestaurantManager(String username, String password) {
		this.userName= username;
		this.password= password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
