package P1;

public class Admin extends User
{
	String admin_id;
	
	public Admin() 
	{
		super(null,null,null,true);
		admin_id = null;
		/*
		accessPeriod_Start=null;
		accessPeriod_End=null;
		registered_index= new ArrayList<Index>();
		waitlisted_index= new ArrayList<Index>();
		notifications=new ArrayList<String>();
		*/
	}
	
	public Admin(String name,String un,String pw, String id) 
	{
		super(name,un,pw,true);
		admin_id = id;
	}
	
	public String getName() {
		return super.name;
	}

	public void setName(String name) {
		super.name=name;
	}
	
	public String getUsername() {
		return super.username;
	}

	public void setUsername(String uname) {
		super.username=uname;
	}
		
	public String getStudentId() {
		return admin_id;
	}

	public void setStudentId(String aId) {
		admin_id=aId;
	}
		
}
