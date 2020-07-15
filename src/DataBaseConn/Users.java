package DataBaseConn;

public class Users {
private String name;
private String pass;
private String email;
private String phone;
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
boolean isAdmin;
	public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public boolean isAdmin() {
	return isAdmin;
}
public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
}
	public Users(String name,String email,String pass,boolean isAdmin,String phone,int id)
	{
		this.name=name;
		this.pass=pass;
		this.isAdmin=isAdmin;
		this.email=email;
		this.phone=phone;
		this.id=id;
	}
	public Users(String name,String email,String pass,boolean isAdmin,String phone)
	{
		this.name=name;
		this.pass=pass;
		this.isAdmin=isAdmin;
		this.email=email;
		this.phone=phone;
		
	}
	public Users(String name,String email,String phone)
	{
		this.name=name;
		this.email=email;
		this.phone=phone;
	}
}
