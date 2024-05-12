package gui;

import acsse.csc03a3.Transaction;
import blockchain.BlockHandler;
import blockchain.SongTransaction;
import corelogic.Artist;
import corelogic.Distributor;
import corelogic.Song;
import corelogic.User;
import corelogic.UserHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomePane<K, V, T> extends StackPane {
    
	private Stage primaryStage;
    private User user;
    private Artist artist;
	private Song selectedSong;
	private BlockHandler<T> blockhandler;
	
    //User Funds Account
    private HBox fundsBox;
    private VBox accountBox;
    private Label lbfunds;
    private Button btnLoadFunds;
    private Label lbbalance;
    
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
    private Button navBuy_CopyRights;
    private Button navBuy_SyncronizatoinRights;
    private Button navBuy_Performance_Rights;
    private Button navBuy_Mechanical_Rights;
    private Button navBuy_Masters_Rights;
    
    //Navigation Contents [Panes]
    //private RegisterPane register;
    private UserAuthentication login;
    private UploadSong uploadsong;
    private SongsView<K,V> songsView;
    
    public HomePane(Stage primaryStage, User user) {
    	this.user = user;
    	if(user.getUserType().equals("Artist")) {
    		
    		
    		artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		    artist.setPUBLIC_KEY(user.getPublicKey());
		    artist.setPRIVATE_KEY(user.getPrivateKey());	
    	
		    System.out.println("UserType in Homepane Constructor is Artist" + user.getPublicKey());
		    
	    	artist.PrintUser();
	    	System.out.println("UserType in Homepane Constructor is Artist" + user.getPrivateKey());
    	}
    	
    	this.primaryStage = primaryStage;
    	
    	
    	System.out.println("User in Homepane Constructor");
    	user.PrintUser();
    	
    	blockhandler = new BlockHandler<T>(user);
    	
    	
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
        	uploadsong = new UploadSong(content, primaryStage, artist);
        	this.getChildren().remove(0);
        	content.getChildren().clear();
        	content.getChildren().addAll(uploadsong);
        	this.getChildren().add(mainBox);
        	
        });
        
         navSongList.setOnAction(event->{
        	System.out.println("-------Nav Song List-------");
        	user.PrintUser();
        	songsView = new SongsView<K, V>(content, primaryStage, user);
        	this.getChildren().remove(0);
        	content.getChildren().clear();
        	content.getChildren().addAll(songsView);
        	this.getChildren().add(mainBox);
        	
        });
    }
    
    /**
     * Distributor/Publisher/Record Label Navigation Bar
     */
    private void setClientsNav() {
    	
    	fundsBox = new HBox();
        accountBox = new VBox();
    	lbbalance = new Label("Available Funds");
    	lbfunds = new Label("R 5000.00");
        lbfunds.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lbfunds.setTextFill(Color.GREEN);
        btnLoadFunds = new Button("Load Funds");
    	btnLoadFunds.setPrefWidth(200);
    	
        fundsBox.setSpacing(10);
        fundsBox.getChildren().addAll(lbbalance, lbfunds);
        accountBox.setSpacing(10);
        accountBox.getChildren().addAll(fundsBox ,btnLoadFunds);
    	
    	navSongList = new Button("Song List");
        navSongList.setPrefWidth(200);
        
    	navBuy_CopyRights = new Button("Buy Copy Rights");
    	navBuy_CopyRights.setPrefWidth(200);
    	
        navBuy_SyncronizatoinRights = new Button("Buy Sysncronization Rights");
        navBuy_SyncronizatoinRights.setPrefWidth(200);
        
        navBuy_Performance_Rights = new Button(" Buy Performance Rights");
        navBuy_Performance_Rights.setPrefWidth(200);
        
        navBuy_Mechanical_Rights = new Button("Buy Mechanical Rights");
        navBuy_Mechanical_Rights.setPrefWidth(200);
        
        navBuy_Masters_Rights = new Button("Buy Masters Rights");
        navBuy_Masters_Rights.setPrefWidth(200);
        
        
        navBox.getChildren().addAll(navSongList,navBuy_CopyRights, navBuy_SyncronizatoinRights, navBuy_Performance_Rights,navBuy_Mechanical_Rights, navBuy_Masters_Rights, navLogout);
        
        content.setPrefWidth(500);
        content.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        subBox.getChildren().addAll(navBox, content);
        
        mainBox.getChildren().addAll(searchBox, subBox, accountBox);
        this.getChildren().addAll(mainBox);
        
        Label tempField = new Label();
        content.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
        
        
        navSongList.setOnAction(event->{
        	songsView = new SongsView<K,V>(content, primaryStage, user);
        	this.getChildren().remove(0);
        	content.getChildren().remove(0);
        	content.getChildren().addAll(songsView);
        	this.getChildren().add(mainBox);
        });
        
        
        navBuy_CopyRights.setOnAction(event->{
        	
        	//get selected song from SongView Class
        	selectedSong = SongsView.selectedSong();
        	if(selectedSong == null) {
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase Transaction");
                alert.setHeaderText("Transaction Falied");
                alert.setContentText("You have to select a song first!");
                alert.showAndWait();
        	}else {
            	String artistAddress  = selectedSong.getArtistAddress() ;        		
        		SongTransaction<T> songTransaction = new SongTransaction<>(selectedSong, "CopyRights",user.getPublicKey(),artistAddress, 94);
        		
        		boolean TransactionAdded = blockhandler.addTransaction(songTransaction);
            	
        		if(TransactionAdded==true) {
        			BuyView buyview= new BuyView();
                	this.getChildren().remove(0);
                	content.getChildren().remove(0);
                	content.getChildren().addAll(buyview);
                	this.getChildren().add(mainBox);
        		}else {
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Purchase Transaction");
                    alert.setHeaderText("Transaction Falied");
                    alert.setContentText("Something went wrong, Please Try Again!");
                    alert.showAndWait();
        		}
            	
        	}
        	selectedSong = null;
        });
        
        
    }
    
	
}

