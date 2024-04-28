package gui;

import corelogic.Artist;
import corelogic.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomePane extends StackPane {
    
	private Stage primaryStage;
    private User user;
	
	private VBox mainBox;
    private HBox searchBox;
    private HBox subBox; //Contains the navigation Box and the Content Box 
    private VBox navBox;
    private Pane content;
    private TextField txtSearchBox;
    private Button btnSearchButton;
    
    //Navigation items [General]
    private Button navSettings;
    private Button navSongList;
    private Button navLogout;
    
    //Navigation items [Artist]
    private Button navUploadSong;
    private Button navUpdateLicenseTerms;
    
    
  //Navigation items [Distibutor]
    private Button Buy_CopyRights;
    private Button Buy_SyncronizatoinRights;
    private Button Buy_Performance_Rights;
    private Button Buy_Mechanical_Rights;
    private Button Buy_Masters_Rights;
    
    //Navigation Contents [Panes]
    //private RegisterPane register;
    private UserAuthentication login;
    private UploadSong uploadsong;
    private SongsView songsView;
    
    public HomePane(Stage primaryStage, User user) {
    	this.primaryStage = primaryStage;
    	this.user = user;
    	
    	mainBox = new VBox();
        mainBox.setAlignment(Pos.TOP_CENTER);
        
        subBox = new HBox();
        content = new Pane();
        
        searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(10,10,10,10));
        
        txtSearchBox = new TextField();
        txtSearchBox.setPromptText("search");
        
        btnSearchButton = new Button("Search 🔍");
        searchBox.getChildren().addAll(txtSearchBox, btnSearchButton);
        
        
        //Navigation Bar
        navBox = new VBox();
        navBox.setPadding(new Insets(10,10,10,10));
        navBox.setSpacing(10);
        
        navSettings = new Button("Settings");
        navSettings.setPrefWidth(200);
        
        navLogout = new Button("Logout");
        navLogout.setPrefWidth(200);
        
        
        //Set Navigation By user Type
        setNavigation(user.getUserType());
        
        
        navLogout.setOnAction(event->{
        	login = new UserAuthentication(primaryStage);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	this.getChildren().add(login);
        	
        });
    }
	
    /**
     * 
     * @param userType the navigation bar will be set depending on the user Type that has logged in.
     */
    private void setNavigation(String userType) {
    	
    	switch(userType){
    	case "Artist":{
    		setArtistNav();
    	}break;
    	case "Distributor":{
    		setClientsNav();
    	}break;
    	case "Publisher":{
    		setClientsNav();
    	}break;
    	case "Record Label":{
    		setClientsNav();
    	}break;
    	case "Validator":{
    		setClientsNav();
    	}break;
    	default:{
    		System.err.println("Invalid User Type");
    	}
    	}
    }
    
    /**
     * Artist Navigation Bar
     */
    private void setArtistNav() {
    	navUploadSong = new Button("Upload Song");
        navUploadSong.setPrefWidth(200);
        
        navSongList = new Button("Song List");
        navSongList.setPrefWidth(200);
        
        navUpdateLicenseTerms = new Button("Update License Terms");
        navUpdateLicenseTerms.setPrefWidth(200);
       
        
        navBox.getChildren().addAll(navSettings, navUploadSong, navSongList, navUpdateLicenseTerms,navLogout);
        
        content.setPrefWidth(500);
        content.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        subBox.getChildren().addAll(navBox, content);
        
        mainBox.getChildren().addAll(searchBox, subBox);
        this.getChildren().addAll(mainBox);
        
        Label tempField = new Label();
        content.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
        
        
        
        
    	
    	navUploadSong.setOnAction(event->{
        	uploadsong = new UploadSong(content, primaryStage, (Artist)user);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(uploadsong);
        	this.getChildren().add(mainBox);
        	
        });
        
         navSongList.setOnAction(event->{
        	songsView = new SongsView(content, primaryStage, (Artist)user);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(songsView);
        	this.getChildren().add(mainBox);
        	
        });
    }
    
    /**
     * Distributor/Publisher/Record Label Navigation Bar
     */
    private void setClientsNav() {
    	
    }
	
}

