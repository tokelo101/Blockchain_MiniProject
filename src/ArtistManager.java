import java.io.*;
import java.util.List;

/**
 * A class that manages the Artist class
 * @author TM Monare 221022037
 *
 */
public class ArtistManager {
	
	private File artistsfile;
	private String filePath = "data/artists.bin";
	

	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	public String RegisterNewArtist(Artist artist) {
		//Write Artist to a binary file
		return WriteArtist(artist);
	}
	
	/**
	 * A method for registering a new Artist to the system
	 * @param artist an artist object to be written to a binary file
	 * @return status returns a status based on whether the file was written successfully or not
	 */
	private String WriteArtist(Artist artist) {
		artistsfile = new File(filePath);
		String status = "Artist Regiatration Failed!";
		try(FileOutputStream fos = new FileOutputStream(artistsfile);
			ObjectOutputStream obj_os = new ObjectOutputStream(fos)){
			obj_os.writeObject(artist);
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
	public Artist GetArtist(String publicKey) {
		//Reading arist data from a binary File
		return ReadArtist(publicKey);
	}
	
	/**
	 * 
	 * @param publicKey a public key used to uniquely identify an artist
	 * @return artist an artist object read from a binary file
	 */
	private Artist ReadArtist(String publicKey) {
		Artist artist = null;
		
		try(FileInputStream fis = new FileInputStream(artistsfile);
			ObjectInputStream obj_is = new ObjectInputStream(fis)) {
			
			//List<Object> objects = (List<Object>) obj_is.readObject(); 
			List<Object> objects = (List<Object>) obj_is.readObject();
			
			for(Object object: objects) {
				if(object instanceof Artist) {
					artist = (Artist)object;
					//Test Purposes
					System.out.println("-----------Printing Artist------------");
					System.out.println("Private Key: "+artist.getPrivateKey());
					System.out.println("Public Key: "+artist.getPublicKey());
					System.out.println("Stage Name: "+artist.getStagename());
					System.out.println("First Name: "+artist.getName());
					System.out.println("Last Name: "+artist.getSurname());
					System.out.println("Email: "+artist.getEmail());
					System.out.println("Password: "+artist.getPassword());
					if(((Artist) object).getPublicKey().equals(publicKey)) {
						System.out.println("Match Found! \n Artist: " +artist.getName() + "Given Key: "+ publicKey  + " == " + artist.getPublicKey());
						return artist;
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
