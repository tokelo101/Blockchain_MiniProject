package corelogic;
import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class User implements Serializable{

	private String usertype;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String PRIVATE_KEY;
	private String PUBLIC_KEY;
	private String ADDRESS;
	
	
	public User(String usertype, String name, String surname, String email, String password) {
		super();
		this.usertype = usertype;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		
		//Generate Keys
		GenerateKeys();
	}
	
	public String getPRIVATE_KEY() {
		return PRIVATE_KEY;
	}

	public void setPRIVATE_KEY(String pRIVATE_KEY) {
		PRIVATE_KEY = pRIVATE_KEY;
	}

	public String getPUBLIC_KEY() {
		return PUBLIC_KEY;
	}

	public void setPUBLIC_KEY(String pUBLIC_KEY) {
		PUBLIC_KEY = pUBLIC_KEY;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
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

	/**
	 * This method Generates the Private and Public Key when a new user is created
	 */
	public void GenerateKeys() {
		
		//Random keys [That do not repeat #note public key can't be used to ket private key yet] for now
		
		Random rand = new Random();
		int privateK = rand.nextInt(100);
		int publicK = rand.nextInt(100);
		this.PRIVATE_KEY = String.valueOf(privateK);
		this.PUBLIC_KEY = String.valueOf(publicK);
		this.ADDRESS = String.valueOf(publicK)+String.valueOf(privateK);
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
	
	public String getAddress() {
		return ADDRESS;
	}
	
	
	public void PrintUser() {
		//Test Purposes
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
