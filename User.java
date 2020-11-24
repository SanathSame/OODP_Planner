package P1;

import java.io.Serializable;


public class User implements Serializable {
	protected String name;
	protected String username;
	protected String password;
	private boolean admin;
	
	public User() {
		name=null;
		username =null;
		password = null;
		admin = false;
	}
	
	public User(String nam,String un, String pw, boolean ad) {
		name=nam;
		username =un;
		password = pw;
		admin = ad;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
