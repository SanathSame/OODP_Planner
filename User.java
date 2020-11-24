package P1;

import java.io.Serializable;

/**
 * A entity class, represents a user in the starPlanner system, student or an administrator.
 * Implements Serializable interface to enable serialized storing of the class objects.
 * 
 */
public class User implements Serializable {
	/**
	 * The name of the User.
	 * Protected visibility to allow access for the child classes
	 */
	protected String name;
	/**
	 * The username of the User, used to login.
	 */
	protected String username;
	/**
	 * The password of the User account, used to login.
	 */
	protected String password;
	/**
	 * boolean that shows if the User is an administration or not.
	 * true for administrator, false for students
	 */
	private boolean admin;
	/**
	 * No-argument constructor to create a User object.
	 */
	public User() {
		name=null;
		username =null;
		password = null;
		admin = false;
	}
	/**
	 * Parameterized constructor to create a User object, to initialize the attributes.
	 * 
	 * @param name     : name of the User, whose object is being created
	 * @param username : username assigned to the User
	 * @param password : password of the User account
	 * @param admin    : true for admin User, false for student User
	 */
	public User(String name,String username, String password, boolean admin) {
		this.name=name;
		this.username =username;
		this.password = password;
		this.admin = admin;
	}
	
	/**
	 * Getter method to get the name of the User.
	 * 
	 * @return name of the User
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method to change the name of the User.
	 * 
	 * @param name :the new name of the User 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method to change the username of the User.
	 * 
	 * @return username of the User
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter method to change the username of the User.
	 * 
	 * @param username : the new username of the User 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter method to get the password of the User.
	 * 
	 * @return password of the User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method to change the password of the User.
	 * 
	 * @param username : the new password of the User 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter method to get the boolean value of admin attribute.
	 * 
	 * @return admin value of the User
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Setter method to change the account type of the User.
	 * 
	 * @param username : the new boolean value to be set to the admin attribute. 
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
