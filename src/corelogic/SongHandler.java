package corelogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SongHandler {
	private String SongsPath = "data/songs.txt"; //Will be uniquely identified by the International Standard Recording Code
	private User user;
	private Artist artistUser;
	private Distributor distributerUser;
	public SongHandler(User user) {
		this.user = user;
	}
	
	public boolean WriteSong(Song song) {
		//Add song to a list of user's song list	
		boolean status = false;
		try (FileWriter fileWriter = new FileWriter(SongsPath, true)) {
            String songData = song.getSongTitle()+"," + song.getArtist()+","+song.getComposer()
            				+","+song.getLyricist()+","+song.getReleaseDate() +","+song.getPublisher()+","+song.getISRC()
            				+","+song.getCopyrightsHolder()+","+song.getCopyrightRegNumber()+","+song.getArtistAddress();
            
            fileWriter.write(songData);
            fileWriter.write(System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
	
	return status;
	
	}
	
	public Song ReadSong(String ISRC) {
		Song song = null;
		
		try (FileReader fileReader = new FileReader(SongsPath);
	         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	        while(true) {
	            String songData = bufferedReader.readLine();
		        if (songData != null) {
		        String[] songFields = songData.split(",");
		        	if (songFields.length >= 2) {
		                String songTitle = songFields[0];
		                String artist = songFields[1];
		                String composer = songFields[2];
		                String lyricist = songFields[3];
		                String releaseDate = songFields[4];
		                String publisher = songFields[5];
		                String ISRC_ = songFields[6];
		            	String copyrightsHolder = songFields[7];
		            	String copyrightRegNumber = songFields[8];
		            	String artistAddress = songFields[9];

		                if( ISRC_.equals(ISRC)) {
		                    song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber,UserHandler.GetArtist(artistAddress));
			                song.setArtist(artist);
			                song.setComposer(composer);
			                song.setLyricist(lyricist);
			                song.setArtistAddress(artistAddress);
			                return song;	
		                 }
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {
		                System.out.println("The file is empty.");
		                return song;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return song;

	}
	
	
	public ArrayList<Song> ReadAllSongs(){
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try (FileReader fileReader = new FileReader(SongsPath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String songData = bufferedReader.readLine();
		            if (songData != null) {
		                String[] songFields = songData.split(",");
		                if (songFields.length >= 9) {
		                	
		                	String songTitle = songFields[0];
		                	String artist = songFields[1];
		                	String composer = songFields[2];
		                	String lyricist = songFields[3];
		                	String releaseDate = songFields[4];
		                	String publisher = songFields[5];
		                	String ISRC_ = songFields[6];
		            		String copyrightsHolder = songFields[7];
		            		String copyrightRegNumber = songFields[8];
		            		String artistAddress = songFields[9];


		                    	Song song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber, UserHandler.GetArtist(artistAddress));
			                    song.setArtist(artist);
			                    song.setComposer(composer);
			                    song.setLyricist(lyricist);
			                    song.setArtistAddress(artistAddress);
			                    songs.add(song);
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {
		                System.out.println("The file is empty.");
		                return songs;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return songs;

	}
	
	
	public ArrayList<Song> readArtistSongs(String Address){
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try (FileReader fileReader = new FileReader(SongsPath);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	            while(true) {
	            	String songData = bufferedReader.readLine();
		            if (songData != null) {
		                String[] songFields = songData.split(",");
		                if (songFields.length >= 2) {
		                	
		                	String songTitle = songFields[0];
		                	String artist = songFields[1];
		                	String composer = songFields[2];
		                	String lyricist = songFields[3];
		                	String releaseDate = songFields[4];
		                	String publisher = songFields[5];
		                	String ISRC_ = songFields[6];
		            		String copyrightsHolder = songFields[7];
		            		String copyrightRegNumber = songFields[8];
		            		String artistAddress = songFields[9];

		                    	if(Address.equals(artistAddress)) {
		                    		Song song = new Song( ISRC_, publisher, songTitle, releaseDate, copyrightsHolder, copyrightRegNumber, UserHandler.GetArtist(artistAddress));
				                    song.setArtist(artist);
				                    song.setComposer(composer);
				                    song.setLyricist(lyricist);
				                    
				                    songs.add(song);
		                    	}
		                    
		                } else {
		                    System.out.println("Invalid song data format in the file.");
		                }
		            } else {
		                System.out.println("The file is empty.");
		                return songs;
		            }
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading the file: " + e.getMessage());
	        }
		
		return songs;

	}
}
