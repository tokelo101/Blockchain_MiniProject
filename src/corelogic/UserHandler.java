package corelogic;
import java.io.*;

import blockchain.*;

/**
 * A class that manages the Artist class
 * @author TM Monare 221022037
 *
 */
public class UserHandler{
	
	private static String filePath = "data/users.txt";
	
	private KeyPairs<String, String> KEYS;
	private RSAKeyGenerator<String, String> keyGenerator;
	/**
	 * This method Generates the Private and Public Key when a new user is created
	 */
	private void GenerateKeys(User user) {
		keyGenerator = new RSAKeyGenerator<String, String>();
		KEYS = keyGenerator.GenerateKeys();
		
		user.setPUBLIC_KEY(KEYS.getPublicKey());
		user.setPRIVATE_KEY(KEYS.getPrivateKey());
		
	}
	
	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	public boolean RegisterNewUser(User user) {
		//Write Artist to a binary file
		GenerateKeys(user);
		return WriteUser(user);
	}
	
	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	private boolean WriteUser(User user) {
		
		boolean status = false;

		 
		try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            String userData = user.getUserType()+ "," + user.getName() + "," + user.getSurname() + ","
                    + user.getEmail() + "," + user.getPassword() + ","+ user.getPublicKey() + "," + user.getPrivateKey();
            
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
	public static User GetUser(String publicKey) {
		//Reading arist data from a binary File
		return ReadUser(publicKey);
	}
	public static Artist GetArtist(String publicKey) {
		//Reading user data from a binary File
		User user = GetUser(publicKey);
		Artist artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
	    artist.setPUBLIC_KEY(user.getPublicKey());
	    artist.setPRIVATE_KEY(user.getPrivateKey());
	
	    return artist;
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
	private static User ReadUser(String publicKey) {
		User user = null;
		
		 try (FileReader fileReader = new FileReader(filePath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String userData = bufferedReader.readLine();
		            if (userData != null) {
		                String[] userFields = userData.split(",");
		                if (userFields.length >= 6) {
		                	String usertype = userFields[0];
		                	String name = userFields[1];
		                	String surname = userFields[2];
		                	String email_ = userFields[3];
		                	String password_ = userFields[4];
		                	String PUBLIC_KEY = userFields[5];
		                	String PRIVATE_KEY = userFields[6];

		                    if(PUBLIC_KEY.equals(publicKey)) {
		                    	System.out.println("Matchig Key Found");
		                    	user = new User(usertype, name, surname, email_, password_);
			                    user.setPUBLIC_KEY(PUBLIC_KEY);
			                    user.setPRIVATE_KEY(PRIVATE_KEY);
			                 return user;	
		                    }else {
		                    	System.out.println("Matchig Key Not Found");
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
	
	/**
	 * 
	 * @param email user email address
	 * @param password user password
	 * @return
	 */
	private static User ReadUser(String email, String password) {
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
		                	String PUBLIC_KEY = userFields[5];
		                	String PRIVATE_KEY = userFields[6];
		                	


		                    if(email_.equals(email) && password_.equals(password)) {
		                    	user = new User(usertype, name, surname, email_, password_);
		                    	user.setPUBLIC_KEY(PUBLIC_KEY);
		                    	user.setPRIVATE_KEY(PRIVATE_KEY);
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
