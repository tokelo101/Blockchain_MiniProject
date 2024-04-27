package gui;

import corelogic.Artist;
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
	
	
	public SongsView(Pane content, Stage primaryStage, Artist user) {
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

        TextField txtSongTitle = new TextField();
        songDetails.add(txtSongTitle, 1, 1);
                
        Label lbComposer = new Label("Composer:" );
        songDetails.add(lbComposer, 0, 2);

        TextField txtComposer = new TextField();
        songDetails.add(txtComposer, 1, 2);
        

        Label lbLyricist = new Label("Lyricist:");
        songDetails.add(lbLyricist, 0, 3);

        TextField txtLyricist = new TextField();
        songDetails.add(txtLyricist, 1, 3);

        Label lbReleaseDate = new Label("Release Date:");
        songDetails.add(lbReleaseDate, 0, 4);

        DatePicker dtReleaseDate = new DatePicker();
        songDetails.add(dtReleaseDate, 1, 4);

        Label lbPublisher = new Label("Publisher:");
        songDetails.add(lbPublisher, 0, 5);

        TextField txtPublisher = new TextField();
        songDetails.add(txtPublisher, 1, 5);
        
        Label lbISRC = new Label("ISRC:");
        songDetails.add(lbISRC, 0, 6);

        TextField txtISRC = new TextField();
        songDetails.add(txtISRC, 1, 6);        
        
        
        
        
		//put songs on the list view
        songList.setItems(items);
		
		container.getChildren().addAll(songList, songDetails);
		//content.getChildren().addAll(container);
		this.getChildren().addAll(container);
	}
}
