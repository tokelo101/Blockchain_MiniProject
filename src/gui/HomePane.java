package gui;

import java.io.File;

import blockchain.*;
import corelogic.Song;
import corelogic.UserHandler;
import corelogic.users.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomePane<K, V, T> extends StackPane {
    
	private Stage primaryStage;
    private User user;
    private Artist artist;
	private Song selectedSong;
	private BlockchainHandler<T> blockhandler;
	
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
    private UserAuthentication<String, String, Song> login;
    private UploadSong uploadsong;
    private Settings settings;
    private SongsView<K,V> songsView;
    
    /**
     * 
     * @param primaryStage the primary stage 
     * @param user user in session
     */
    public HomePane(Stage primaryStage, User user) {
    	this.user = user;
    	if(user.getUserType().equals("Artist")) {
    		    		
    		artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		    artist.setPUBLIC_KEY(user.getPublicKey());
		    artist.setPRIVATE_KEY(user.getPrivateKey());	
    	}
    	
    	this.primaryStage = primaryStage;
    	
    	//Blockchain handler
    	blockhandler = new BlockchainHandler<T>(user);
    	
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
        btnSearchButton.setDisable(true);
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
        	login = new UserAuthentication<String, String, Song>(primaryStage);
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
        content.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, null, null)));
        subBox.getChildren().addAll(navBox, content);
        
        mainBox.getChildren().addAll(searchBox, subBox);
        this.getChildren().addAll(mainBox);
        
        Label tempField = new Label();
        content.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
        
    	
        navSettings.setOnAction(event->{
        	settings = new Settings(content, primaryStage, artist);
        	this.getChildren().remove(0);
        	content.getChildren().clear();
        	content.getChildren().addAll(settings);
        	this.getChildren().add(mainBox);
        	
        });
        
    	navUploadSong.setOnAction(event->{
        	uploadsong = new UploadSong(content, primaryStage, artist);
        	this.getChildren().remove(0);
        	content.getChildren().clear();
        	content.getChildren().addAll(uploadsong);
        	this.getChildren().add(mainBox);
        	
        });
        
         navSongList.setOnAction(event->{
        
        	songsView = new SongsView<K, V>(content, primaryStage, user);
        	this.getChildren().remove(0);
        	content.getChildren().clear();
        	content.getChildren().addAll(songsView);
        	this.getChildren().add(mainBox);
        	
        });
         
         navUpdateLicenseTerms.setOnAction(event->{
         	
         	Song selectedSong = SongsView.selectedSong();  
         
         	//put song to song list
         	if(selectedSong == null) {
         		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Update License Terms");
                 alert.setHeaderText("License Terms Update Failed");
                 alert.setContentText("Please select a song first" );
                 alert.showAndWait();
                 return ;
         	}
     	try {
     		FileChooser fc = new FileChooser();
         	fc.setInitialDirectory(new File(System.getProperty("user.dir")));
         	File licenceTerms = fc.showOpenDialog(primaryStage);
         	
       
     	if(artist.UpdateLicenceTerms(selectedSong.getISRC(), licenceTerms)){
     		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update License Terms");
            alert.setHeaderText("License Terms Update Succesful");
             alert.setContentText("The transaction will be validated by peers with stake");
             alert.showAndWait();
     	}else {
     		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update License Terms");
            alert.setHeaderText("License Terms Update Failed");
             alert.setContentText("Please Try Again");
             alert.showAndWait();
     	}
     	}catch(Exception ex) {
     		System.err.print("License Term Update Error");
     	}
         });
        }
    
    /**
     * Distributor/Publisher/Record Label Navigation Bar
     */
    private void setClientsNav() {
    	
    	fundsBox = new HBox();
        accountBox = new VBox();
    	lbbalance = new Label("Available Funds: ");
    	lbbalance.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    	lbfunds = new Label(String.valueOf("R "+(user.getAvailableBalance())));
        lbfunds.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lbfunds.setTextFill(Color.GREEN);
        btnLoadFunds = new Button("Load Funds");
    	btnLoadFunds.setPrefWidth(200);
    	
    	btnLoadFunds.setOnAction(event->{
    		//temporary update balance value =1000
    		user.updateBalance(1000);
    		lbfunds = new Label(String.valueOf("R "+(user.getAvailableBalance())));
    		fundsBox.getChildren().clear();
    		lbfunds.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    		fundsBox.getChildren().addAll(lbbalance, lbfunds);
    	});
    	
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
        content.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, null, null)));
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
        	BuyRights("CopyRights");
        });
        
        navBuy_SyncronizatoinRights.setOnAction(event->{
        	BuyRights("SyncronizationRights");
        });
        
        navBuy_Performance_Rights.setOnAction(event->{
        	BuyRights("PerformanceRights");
        });
        
        navBuy_Mechanical_Rights.setOnAction(event->{
        	BuyRights("MechanicalRights");
        });
        
        navBuy_Masters_Rights.setOnAction(event->{
        	BuyRights("MastersRights");
        });
        
    }
    
    /**
     * 
     * @param rightsType the type of IP rights transaction
     */
	private void BuyRights(String rightsType) {
		//get selected song from SongView Class
    	selectedSong = SongsView.selectedSong();
    	
    	try {
    		
        	if(selectedSong == null) {
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase Transaction");
                alert.setHeaderText("Transaction Falied");
                alert.setContentText("You have to select a song first!");
                alert.showAndWait();
        	}else
        		//Validate Price 
            	if(user.getAvailableBalance() <= selectedSong.getCopyRights_price()) {
            		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Purchase Transaction");
                    alert.setHeaderText("Transaction Falied");
                    alert.setContentText("You have do not have enough Funds to make this transacation");
                    alert.showAndWait();
            		return;
            	}
            	
        	
        	else {
            	String artistAddress  = selectedSong.getArtistAddress() ;        		
        		SongTransaction<T> songTransaction = new SongTransaction<>(selectedSong, rightsType,user.getPublicKey(),artistAddress, 94);
        		
        		boolean TransactionAdded = blockhandler.addTransaction(songTransaction);
            	
        		if(TransactionAdded==true) {
        			
        			//Update User Balance
        			
        			switch(rightsType) {
        			case "CopyRights":{
        				user.updateBalance(-selectedSong.getCopyRights_price());
            				
        			}break;
        			case "SyncronizationRights":{
        				user.updateBalance(-selectedSong.getSyncronizationRights_price());
            				
        			}break;
        			case "PerformanceRights":{
        				user.updateBalance(-selectedSong.getPerfomanceRights_price());
            				
        			}break;
        			case "MechanicalRights":{
        				user.updateBalance(-selectedSong.getMechanicalRights_price());
            				
        			}break;
        			case "MastersRights":{
        				user.updateBalance(-selectedSong.getMastersRights_price());
            				
        			}break;
        			default:{
        				System.err.println("Rights dont exist!");
        			}
        			}
        			
        			
        			//Refresh View
        			lbfunds = new Label(String.valueOf("R "+(user.getAvailableBalance())));
            		fundsBox.getChildren().clear();
            		lbfunds.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            		fundsBox.getChildren().addAll(lbbalance, lbfunds);
        			
            		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Purchase Transaction");
                    alert.setHeaderText("Transaction Successful");
                    alert.setContentText("An Email will be send to you Shortly");
                    alert.showAndWait();
        		}else {
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Purchase Transaction");
                    alert.setHeaderText("Transaction Falied");
                    alert.setContentText("Something went wrong, Please Try Again!");
                    alert.showAndWait();
        		}
            	
        	}
        	selectedSong = null;	
    	}catch(NullPointerException npe) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Purchase Transaction");
            alert.setHeaderText("Transaction Falied");
            alert.setContentText("Please Select song First");
            alert.showAndWait();
    		return;
    	}
    	
    	

	}
}

