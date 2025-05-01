package teamproject;

public class UserInfo {
	//변수
	private String id;
	private String pwd;
	private String name;
	
	public UserInfo() {
		this(null,null,null);
	
	}
	public UserInfo(String id, String pwd,String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
	
	
	
	
}
