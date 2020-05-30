package model;

public class UserModel {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String passwordConfirm;
	
	public UserModel() {
		
	}
	
	public UserModel(String email, char [] password) {
		this.email=email;
		this.password=new String (password);
	}
	
	public UserModel(String firstName, String lastName, String email, char [] password, char [] passwordConfirm) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = new String(password);
		this.passwordConfirm=new String(passwordConfirm);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = new String(password);
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(char[] passwordConfirm) {
		this.passwordConfirm = new String(passwordConfirm);
	}
	
	
}
