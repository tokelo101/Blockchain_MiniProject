package gui;

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
    private VBox mainBox;
    private HBox searchBox;
    private HBox subBox; //Contains the navigation Box and the Content Box 
    private VBox navBox;
    private Pane content;
    private TextField txtSearchBox;
    private Button btnSearchButton;
    
    //Navigation items
    private Button navSettings;
    private Button navUploadSong;
    private Button navSongList;
    private Button navUpdateLicenseTerms;
    private Button navRegister;
    private Button navLogout;
    private Button navLogin;
    
    //Navigation Contents [Panes]
    private RegisterPane register;
    private UploadSong uploadsong;
    
    public HomePane(Stage primaryStage) {
    	
    	mainBox = new VBox();
        mainBox.setAlignment(Pos.TOP_CENTER);
        
        subBox = new HBox();
        content = new Pane();
        
        searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(10,10,10,10));
        
        txtSearchBox = new TextField();
        txtSearchBox.setPromptText("search");
        
        btnSearchButton = new Button("Search Icon");
        searchBox.getChildren().addAll(txtSearchBox, btnSearchButton);
        
        
        //Navigation Bar
        navBox = new VBox();
        navBox.setPadding(new Insets(10,10,10,10));
        navBox.setSpacing(10);
        
        navRegister = new Button("Register");
        navRegister.setPrefWidth(200);
        navSettings = new Button("Settings");
        navSettings.setPrefWidth(200);
        navUploadSong = new Button("Upload Song");
        navUploadSong.setPrefWidth(200);
        navSongList = new Button("Song List");
        navSongList.setPrefWidth(200);
        navUpdateLicenseTerms = new Button("Update License Terms");
        navUpdateLicenseTerms.setPrefWidth(200);
        navLogin = new Button("Login");
        navLogin.setPrefWidth(200);
        navLogout = new Button("Logout");
        navLogout.setPrefWidth(200);
        
        navBox.getChildren().addAll(navRegister, navSettings, navUploadSong, navSongList, navUpdateLicenseTerms, navLogin, navLogout);
        
        content.setPrefWidth(500);
        content.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        subBox.getChildren().addAll(navBox, content);
        
        mainBox.getChildren().addAll(searchBox, subBox);
        this.getChildren().addAll(mainBox);
        
        Label tempField = new Label();
        content.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
        
        navRegister.setOnAction(event->{
        	register = new RegisterPane();
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(register);
        	this.getChildren().add(mainBox);
        });
        
        navUploadSong.setOnAction(event->{
        	uploadsong = new UploadSong(content, primaryStage);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(uploadsong);
        	this.getChildren().add(mainBox);
        	
        });
    }
	
	
}

