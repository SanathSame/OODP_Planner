package P1;

/**
 * Implementation of Admin subclass which extends the User parentclass. 
 *
 */
public class Admin extends User
{
	/**
	 * The private unique ID of this admin
	 */
	private String admin_id;
	
	/**
	 * Default constructor to create Admin object
	 */
	public Admin() 
	{
		super(null,null,null,true);
		admin_id = null;
	}
	/**
	 * Parameterized to specify the name, username, login password and unique ID of this Admin
	 * @param name : Name of this Admin
	 * @param un : Login username of this Admin
	 * @param pw : Login password of this Admin
	 * @param id : unique ID of this Admin
	 */
	public Admin(String name,String un,String pw, String id) 
	{
		super(name,un,pw,true);
		admin_id = id;
	}

	/**
	 * Method to return this Admin's unique ID
	 * @return This admin's ID
	 */
	public String getAdmin_id() {
		return admin_id;
	}
	
	/**
	 * Method to change this Admin's unique ID
	 * @param admin_id: New specified ID of this admin
	 */
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
			
}
