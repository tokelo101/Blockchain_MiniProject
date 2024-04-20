package corelogic;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages the Artist class
 * @author TM Monare 221022037
 *
 */
public class UserHandler {
	
	private File usersfile;
	private String filePath = "data/users.bin";
	

	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	public String RegisterNewArtist(User user) {
		//Write Artist to a binary file
		return WriteUser(user);
	}
	
	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	private String WriteUser(User user) {
		usersfile = new File(filePath);
		String status = "Artist Regiatration Failed!";
		try(FileOutputStream fos = new FileOutputStream(usersfile);
			ObjectOutputStream obj_os = new ObjectOutputStream(fos)){
			obj_os.writeObject(user);
			status = "Artist Registered Successfully!";
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
	public User GetArtist(String publicKey) {
		//Reading arist data from a binary File
		return ReadUser(publicKey);
	}
	
	/**
	 * 
	 * @param publicKey a public key used to uniquely identify an artist
	 * @return artist an artist object read from a binary file
	 */
	private User ReadUser(String publicKey) {
		User user = null;
		
		try(FileInputStream fis = new FileInputStream(usersfile);
			ObjectInputStream obj_is = new ObjectInputStream(fis)) {
			
			//List<Object> objects = (List<Object>) obj_is.readObject(); 
			ArrayList<Object> objects = new ArrayList<Object>();
			
			while (true) {
			    Object obj;
			    try{
			    	obj = obj_is.readObject();
			    	objects.add(obj);
			    } catch (EOFException e) {
			      break;
			    }
			}
			
			for(Object object: objects) {
				if(object instanceof User) {
					user = (User)object;
					//Test Purposes
					System.out.println("-----------Printing Artist------------");
					System.out.println("Private Key: "+user.getPrivateKey());
					System.out.println("Public Key: "+user.getPublicKey());
					System.out.println("Stage Name: "+user.getStagename());
					System.out.println("First Name: "+user.getName());
					System.out.println("Last Name: "+user.getSurname());
					System.out.println("Email: "+user.getEmail());
					System.out.println("Password: "+user.getPassword());
					if(((User) object).getPublicKey().equals(publicKey)) {
						System.out.println("Match Found! \n Artist: " +user.getName() + "Given Key: "+ publicKey  + " == " + user.getPublicKey());
						return user;
					}
					
				}
			}
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param song a song object to be written to the block
	 * @return status returns a status on whether the file has been written or not
	 */
	public String UploadSong(Song song) {
		//validate and save it to a file
		//and create a transaction
		
		return null;
	}
}
