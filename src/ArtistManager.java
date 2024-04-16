import java.io.*;

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
			
			Object object = obj_is.readObject(); 
			
			if(object instanceof Artist) {
				artist = (Artist)object;
			}
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return artist;
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
