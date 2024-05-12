package corelogic.users;
/**
 * 
 * @author TM Monare 221022037
 *
 */
public class User{

	private String usertype;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String PRIVATE_KEY;
	private String PUBLIC_KEY;
	private double availableBalance;
	
	public User(String usertype, String name, String surname, String email, String password) {
		super();
		this.usertype = usertype;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		
	}
	

	public void setPRIVATE_KEY(String PRIVATE_KEY) {
		this.PRIVATE_KEY = PRIVATE_KEY;
	}

	public void setPUBLIC_KEY(String pUBLIC_KEY) {
		PUBLIC_KEY = pUBLIC_KEY;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return usertype;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPrivateKey() {
		return PRIVATE_KEY;
	}
	
	public String getPublicKey() {
		return PUBLIC_KEY;
	}
	
	/**
	 * 
	 * @return availableFund returns the value of funds in the account
	 */
	public double getAvailableBalance() {
		return availableBalance;
	}
	
	public double updateBalance(double balance) {
		return availableBalance += balance;
	}
	
	
	
	//Test Purposes
	public void PrintUser() {
		
		System.out.println("-----------Printing Artist------------");
		System.out.println("Private Key: "+this.getPrivateKey());
		System.out.println("Public Key: "+this.getPublicKey());
		System.out.println("User Type: "+this.getUserType());
		System.out.println("First Name: "+this.getName());
		System.out.println("Last Name: "+this.getSurname());
		System.out.println("Email: "+this.getEmail());
		System.out.println("Password: "+this.getPassword());
	}
}
