package corelogic;

public class Distributor extends User{
	private double availableFunds;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Distributor(String usertype, String name, String surname, String email, String password) {
		super(usertype, name, surname, email, password);
		
	}
	
	public double getAvailableFunds() {
		return availableFunds;
	}

}
