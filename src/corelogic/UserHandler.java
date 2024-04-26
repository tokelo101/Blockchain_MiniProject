package corelogic;
import java.io.*;

/**
 * A class that manages the Artist class
 * @author TM Monare 221022037
 *
 */
public class UserHandler{
	
	//private File usersfile;
	private String filePath = "data/users_.bin";
	

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
//		 if (!usersfile.exists()) {
//	            try {
//					usersfile.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//	        }
		 
		try(FileOutputStream fos = new FileOutputStream(filePath, true);
			ObjectOutputStream obj_os = new ObjectOutputStream(fos)){
			obj_os.writeObject(user);
			status = true;
		}catch(IOException ioe) {
			ioe.printStackTrace();
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
		//Reading arist data from a binary File
		return ReadUser(email, password);
	}
	
	/**
	 * 
	 * @param publicKey a public key used to uniquely identify an artist
	 * @return artist an artist object read from a binary file
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
	 * @param publicKey a public key used to uniquely identify an artist
	 * @return artist an artist object read from a binary file
	 */
	private User ReadUser(String email, String password) {
		User user = null;
		
		try(FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream obj_is = new ObjectInputStream(fis)) {
			
			
			while (true) {
			    try{
			    	Object obj = obj_is.readObject();
			    	if(obj instanceof User) {
						User tempUser = (User)obj;
						if(tempUser.getEmail().equals(email) && tempUser.getPassword().equals(password)){
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
	
}
