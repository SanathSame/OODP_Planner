package P1;

public class Admin extends User
{
	private String admin_id;
	
	public Admin() 
	{
		super(null,null,null,true);
		admin_id = null;
	}
	
	public Admin(String name,String un,String pw, String id) 
	{
		super(name,un,pw,true);
		admin_id = id;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
			
}
