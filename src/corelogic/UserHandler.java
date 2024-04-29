package corelogic;
import java.io.*;

/**
 * A class that manages the Artist class
 * @author TM Monare 221022037
 *
 */
public class UserHandler{
	
	//private File usersfile;
	private String filePath = "data/users_.txt";
	

	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	public boolean RegisterNewUser(User user) {
		//Write Artist to a binary file
		return WriteUser(user);
	}
	
	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	private boolean WriteUser(User user) {
		//File usersfile = new File(filePath);
		
		boolean status = false;

		 
		try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            String userData = user.getUserType()+ "," + user.getName() + "," + user.getSurname() + ","
                    + user.getEmail() + "," + user.getPassword() + "," + user.getPrivateKey() + ","
                    + user.getPublicKey() + "," + user.getAddress();
            
            fileWriter.write(userData);
            fileWriter.write(System.lineSeparator());
            System.out.println("User data written to the file successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
		return status;
	}
	
	
	/**
	 * 
	 * @param publicKey a public key used to uniquely identify an artist
	 * @return artist an artist object read from a binary file
	 */
	public User GetUser(String address) {
		//Reading arist data from a binary File
		return ReadUser(address);
	}
	
	public User GetUser(String email, String password) {
		//Reading user data from a binary File
		return ReadUser(email, password);
	}
	
	/**
	 * 
	 * @param address a public key used to uniquely identify a user
	 * @return user a user object read from a binary file
	 */
	private User ReadUser(String address) {
		User user = null;
		
		try(FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream obj_is = new ObjectInputStream(fis)) {
			
			
			while (true) {
			    try{
			    	Object obj = obj_is.readObject();
			    	if(obj instanceof User) {
						User tempUser = (User)obj;
						if(tempUser.getAddress().equals(address)) {
							user = tempUser;
							break;
						}
					}
			    } catch (EOFException e) {
			      break;
			    }
			}
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param email user email address
	 * @param password user password
	 * @return
	 */
	private User ReadUser(String email, String password) {
		User user = null;
		
		 try (FileReader fileReader = new FileReader(filePath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String userData = bufferedReader.readLine();
		            if (userData != null) {
		                String[] userFields = userData.split(",");
		                if (userFields.length >= 2) {
		                	String usertype = userFields[0];
		                	String name = userFields[1];
		                	String surname = userFields[2];
		                	String email_ = userFields[3];
		                	String password_ = userFields[4];
		                	String PRIVATE_KEY = userFields[5];
		                	String PUBLIC_KEY = userFields[6];
		                	String ADDRESS = userFields[7];


		                    if(email_.equals(email) && password_.equals(password)) {
		                    	user = new User(usertype, name, surname, email_, password_);
			                    user.setPRIVATE_KEY(PRIVATE_KEY);
			                    user.setPUBLIC_KEY(PUBLIC_KEY);
			                    user.setADDRESS(ADDRESS);
		                    return user;	
		                    }
		                    
		                 
		                    
		                } else {
		                    System.out.println("Invalid user data format in the file.");
		                }
		            } else {
		                System.out.println("The file is empty.");
		                return user;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return user;
	}
	
}
