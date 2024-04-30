package gui;

import corelogic.Artist;
import corelogic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SongsView extends StackPane{
	
	private HBox container;
	private ListView<Label> songList = new ListView<>();
	private GridPane songDetails;
	private TextField txtName;
	private TextField txtSurname;
	private TextField txtEmail;
	private PasswordField password;
	
	
	public SongsView(Pane content, Stage primaryStage, User user) {
		container = new HBox();
		songDetails = new GridPane();
		//Read Songs from File
		 // Create an ObservableList to store the data
        ObservableList<Label> items = FXCollections.observableArrayList();

        // Add items to the ObservableList
        
        Label lbSongList = new Label("Song List");
        lbSongList.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        items.add(lbSongList);
        items.add(new Label("Sweet Melodies"));
        items.add(new Label("Train to Jozi"));
        items.add(new Label("Bekezela "));
		
        
        
        
        //Song Details
        
        Label lbTitle = new Label("Song Details");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        songDetails.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        Label lbSongTitle = new Label("Song Title:");
        songDetails.add(lbSongTitle, 0, 1);

        Label lbSongTitle_value = new Label("Sweet Melodies");
        songDetails.add(lbSongTitle_value, 1, 1);
                
        Label lbComposer = new Label("Composer:" );
        songDetails.add(lbComposer, 0, 2);

        Label lbComposer_value = new Label("DJ Ace");
        songDetails.add(lbComposer_value, 1, 2);
        

        Label lbLyricist = new Label("Lyricist:");
        songDetails.add(lbLyricist, 0, 3);

        Label lbLyricist_value = new Label("DJ Ace");
        songDetails.add(lbLyricist_value, 1, 3);

        Label lbReleaseDate = new Label("Release Date:");
        songDetails.add(lbReleaseDate, 0, 4);

        Label dtReleaseDate = new Label("27-04-2024");
        songDetails.add(dtReleaseDate, 1, 4);

        Label lbPublisher = new Label("Publisher:");
        songDetails.add(lbPublisher, 0, 5);

        Label lbPublisher_value = new Label("Ace Studios");
        songDetails.add(lbPublisher_value, 1, 5);
        
        Label lbISRC = new Label("ISRC:");
        songDetails.add(lbISRC, 0, 6);

        Label lbISRC_value = new Label("RSA-24-Ace-0001");
        songDetails.add(lbISRC_value, 1, 6);        
        
        
		//put songs on the list view
        songList.setItems(items);
		
		container.getChildren().addAll(songList, songDetails);
		//content.getChildren().addAll(container);
		this.getChildren().addAll(container);
	}
}
