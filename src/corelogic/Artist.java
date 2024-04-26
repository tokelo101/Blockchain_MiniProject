package corelogic;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import acsse.csc03a3.Transaction;

public class Artist extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath = "data/songs_.bin"; //Will be uniquely identified by the International Standard Recording Code
	
	public Artist(String usertype, String name, String surname, String email, String password) {
		super(usertype, name, surname, email, password);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param song a song object to be written to the block
	 * @return status returns a status on whether the file has been written or not
	 */
	public <T> boolean UploadSong(Song song) {
		
		//Add song to a list of user's song list	
			boolean status = false;
			try(FileOutputStream fos = new FileOutputStream(filePath, true);
				ObjectOutputStream obj_os = new ObjectOutputStream(fos)){
				obj_os.writeObject(song);
				status = true;
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		
		return status;
	}

	/**
	 * 
	 * @param ISRC International Standard Recording Code is a Unique Identifier of a song
	 * @return song the song to be returned
	 */
	public Song GetSong(String ISRC){
		
		Song song = null;
		
		try(FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream obj_is = new ObjectInputStream(fis)) {
				
				
				while (true) {
				    try{
				    	Object obj = obj_is.readObject();
				    	if(obj instanceof Song) {
							Song tempSong = (Song)obj;
							if(tempSong.getISRC().equals(ISRC)) {
								song = tempSong;
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
		
		return song;
	}
	
	
public ArrayList<Song> GetAllSongs(){
		
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try(FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream obj_is = new ObjectInputStream(fis)) {
				
				
				while (true) {
				    try{
				    	Object obj = obj_is.readObject();
				    	if(obj instanceof Song) {
							Song tempSong = (Song)obj;
							songs.add(tempSong);
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
		
		return songs;
	}
	
	
	/**
	 * 
	 * @param <T>
	 * @param ISRC International Standard Recording Code, Unique Song Identifier
	 * @return return status based on success or failure
	 */
	public <T> boolean UpdateLicenceTerms(String ISRC, File licenceTerms) {
		
		boolean status = false;
		
		//get a song from a file
		
		Song song = GetSong(ISRC);
		if(song==null) {
			return false;
		}
		
		String isrc = song.getISRC();
		String publisher = song.getPublisher();
		String title = song.getSongTitle();
		String releaseDate = song.getReleaseDate();
		String copyrightHolder = song.getCopyrightsHolder();
		String copyrightRegNo = song.getCopyrightRegNumber();
		
		//Create a Clone of the song.
		Song updatedSong = new Song(isrc, publisher, title, releaseDate, copyrightHolder, copyrightRegNo); 
		//Update License terms
		updatedSong.setLicesnseAndTerms(licenceTerms);
		//Create a transaction
		Transaction<Song> transaction = new Transaction<Song>(this.getAddress(), null, updatedSong);   //NOTE: update licenseTerms can't be a transaction between sender and receiver 
		updatedSong.addTransaction(transaction);
		//Add to temporary transaction list file
			//send updated transaction list to peers on the network
			//if there's more than 5 transactions
				//Create a block
				//Send a Block to peers
				//if the block is valid
				//Add block to Blockchain
		
		
		return status;
	}
}
