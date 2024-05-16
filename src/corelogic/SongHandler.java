package corelogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import corelogic.users.Artist;
import corelogic.users.Distributor;
import corelogic.users.User;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class SongHandler {
	private String SongsPath = "data/songs.txt"; //Will be uniquely identified by the International Standard Recording Code
	private User user;
	private Artist artistUser;
	private Distributor distributerUser;
	public SongHandler(User user) {
		this.user = user;
	}
	
	/**
	 * 
	 * @param song the song to be written to file
	 * @return returns boolean value on whether writing was successful or not
	 */
	public boolean WriteSong(Song song) {
		//Add song to a list of user's song list	
		boolean status = false;
		try (FileWriter fileWriter = new FileWriter(SongsPath, true)) {
            String songData = song.getSongTitle()+"," + song.getArtist()+","+song.getComposer()
            				+","+song.getLyricist()+","+song.getReleaseDate() +","+song.getPublisher()+","+song.getISRC()
            				+","+song.getCopyrightsHolder()+","+song.getCopyrightRegNumber()
            				+","+song.getCopyRights_price()+","+song.getSyncronizationRights_price()+","+song.getPerfomanceRights_price()
            				+","+song.getMechanicalRights_price()+","+song.getMastersRights_price()
            				+","+song.getArtistAddress();
            
            fileWriter.write(songData);
            fileWriter.write(System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
	
	return status;
	
	}
	
	/**
	 * 
	 * @param ISRC Internationl Standard Recording Code, the unique song identifier
	 * @return returns Song if found
	 */
	public Song ReadSong(String ISRC) {
		Song song = null;
		
		try (FileReader fileReader = new FileReader(SongsPath);
	         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	        while(true) {
	            String songData = bufferedReader.readLine();
		        if (songData != null) {
		        String[] songFields = songData.split(",");
		        	if (songFields.length >= 14) {
		                String songTitle = songFields[0];
		                String artist = songFields[1];
		                String composer = songFields[2];
		                String lyricist = songFields[3];
		                String releaseDate = songFields[4];
		                String publisher = songFields[5];
		                String ISRC_ = songFields[6];
		            	String copyrightsHolder = songFields[7];
		            	String copyrightRegNumber = songFields[8];
		            	String copyRights_Price = songFields[9];
		            	String syncronizationRights_Price = songFields[10];
		            	String performanceRights_Price = songFields[12];
		            	String mechanicalRights_Price = songFields[11];
		            	String mastersRights_Price = songFields[13];
		            	String artistAddress = songFields[14];
		            	
		                if( ISRC_.equals(ISRC)) {
		                    song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber,UserHandler.GetArtist(artistAddress));
			                song.setArtist(artist);
			                song.setComposer(composer);
			                song.setLyricist(lyricist);
			                song.setArtistAddress(artistAddress);
			                //prices
			                song.setCopyRights_price(Double.parseDouble(copyRights_Price));
			                song.setSyncronizationRights_price(Double.parseDouble(syncronizationRights_Price));
			                song.setPerformanceRights_price(Double.parseDouble(performanceRights_Price));
			                song.setMechanicalRights_price(Double.parseDouble(mechanicalRights_Price));
			                song.setMastersRights_price(Double.parseDouble(mastersRights_Price));

			               
			                return song;	
		                 }
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {
		                return song;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return song;

	}
	
	/**
	 * 
	 * @return array of songs read from file
	 */
	public ArrayList<Song> ReadAllSongs(){
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try (FileReader fileReader = new FileReader(SongsPath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String songData = bufferedReader.readLine();
		            if (songData != null) {
		                String[] songFields = songData.split(",");
		                if (songFields.length >= 14) {
		                	
		                	String songTitle = songFields[0];
		                	String artist = songFields[1];
		                	String composer = songFields[2];
		                	String lyricist = songFields[3];
		                	String releaseDate = songFields[4];
		                	String publisher = songFields[5];
		                	String ISRC_ = songFields[6];
		            		String copyrightsHolder = songFields[7];
		            		String copyrightRegNumber = songFields[8];
		            		String copyRights_Price = songFields[9];
			            	String syncronizationRights_Price = songFields[10];
			            	String performanceRights_Price = songFields[12];
			            	String mechanicalRights_Price = songFields[11];
			            	String mastersRights_Price = songFields[13];
			            	String artistAddress = songFields[14];
			            	

		                    	Song song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber, UserHandler.GetArtist(artistAddress));
			                    song.setArtist(artist);
			                    song.setComposer(composer);
			                    song.setLyricist(lyricist);
			                    song.setArtistAddress(artistAddress);
			                  //prices
				                song.setCopyRights_price(Double.parseDouble(copyRights_Price));
				                song.setSyncronizationRights_price(Double.parseDouble(syncronizationRights_Price));
				                song.setPerformanceRights_price(Double.parseDouble(performanceRights_Price));
				                song.setMechanicalRights_price(Double.parseDouble(mechanicalRights_Price));
				                song.setMastersRights_price(Double.parseDouble(mastersRights_Price));
			                    songs.add(song);
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {
		                return songs;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return songs;

	}
	
	
	/**
	 * 
	 * @param Address the artist's public address
	 * @return the array of songs read for the artist
	 */
	public ArrayList<Song> readArtistSongs(String Address){
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try (FileReader fileReader = new FileReader(SongsPath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String songData = bufferedReader.readLine();
		            if (songData != null) {
		                String[] songFields = songData.split(",");
		                if (songFields.length >= 14) {
		                	
		                	String songTitle = songFields[0];
		                	String artist = songFields[1];
		                	String composer = songFields[2];
		                	String lyricist = songFields[3];
		                	String releaseDate = songFields[4];
		                	String publisher = songFields[5];
		                	String ISRC_ = songFields[6];
		            		String copyrightsHolder = songFields[7];
		            		String copyrightRegNumber = songFields[8];
		            		String copyRights_Price = songFields[9];
			            	String syncronizationRights_Price = songFields[10];
			            	String performanceRights_Price = songFields[12];
			            	String mechanicalRights_Price = songFields[11];
			            	String mastersRights_Price = songFields[13];
			            	String artistAddress = songFields[14];
			            	
		                    	if(Address.equals(artistAddress)) {
		                    		Song song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber, UserHandler.GetArtist(artistAddress));
				                    song.setArtist(artist);
				                    song.setComposer(composer);
				                    song.setLyricist(lyricist);
				                  //prices
					                song.setCopyRights_price(Double.parseDouble(copyRights_Price));
					                song.setSyncronizationRights_price(Double.parseDouble(syncronizationRights_Price));
					                song.setPerformanceRights_price(Double.parseDouble(performanceRights_Price));
					                song.setMechanicalRights_price(Double.parseDouble(mechanicalRights_Price));
					                song.setMastersRights_price(Double.parseDouble(mastersRights_Price));
				                    songs.add(song);
		                    	}
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {

		                return songs;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return songs;

	}
}
