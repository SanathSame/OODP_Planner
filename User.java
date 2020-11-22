package P1;

import java.io.Serializable;


public class User implements Serializable {
	String name;
	String username;
	String password;
	boolean admin;
	
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String un) {
		 username=un;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nam) {
		 name=nam;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String pw) {
		password=pw;
	}
	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean ad) {
		admin=ad;
	}
	
}
