package gui;
import corelogic.*;
import corelogic.users.Artist;
import corelogic.users.Distributor;
import corelogic.users.User;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * 
 * @author tokel
 *
 * @param <K> SongKey
 * @param <V> SongTitle
 * @param <T> Transaction Data
 */
public class UserAuthentication<K, V , T> extends StackPane{
	private Button btnLogin;
	private Button btnRegister;
	private TextField txtEmail;
	private PasswordField password;
	private HBox content;
	private int LoginAttemptCount = 3;
	private static final int COUNTDOWN_SECONDS = 30;

	
	public UserAuthentication(Stage primaryStage) {
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25));
			
		btnRegister = new Button("Register");
		btnRegister.setPrefWidth(150);
		btnLogin = new Button("Login");
		btnLogin.setPrefWidth(150);
	        
		content = new HBox();
		
		content.setAlignment(Pos.TOP_CENTER);
		
		// Add form elements
        Label lbTitle = new Label("Login");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        
        Label lbEmail = new Label("Email:");
        gridPane.add(lbEmail, 0, 1);

        txtEmail = new TextField();
        gridPane.add(txtEmail, 1, 1);
        Label lbPassword = new Label("Password:");
        gridPane.add(lbPassword, 0, 2);

        password = new PasswordField();
        gridPane.add(password, 1, 2);

        gridPane.add(btnLogin, 1, 3, 4, 1);
        
        Label lbRegister = new Label("New User? ");
        gridPane.add(lbRegister,0 , 4);
        gridPane.add(btnRegister, 1, 4, 4 , 1);
        
        content.getChildren().add(gridPane);
        this.getChildren().addAll(content);
      
        Label tempField = new Label();
       this.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
	   
       //Test Purposes
       txtEmail.setText("nf@mail.com");
	   password.setText("nf");
	   
       btnLogin.setOnAction(event->{
    	   
    	UserHandler userhandler = new UserHandler();
   		User user = userhandler.GetUser(txtEmail.getText(), password.getText());
    	
    	 if(user!=null) {
    		   
    		 //Navigate to Distributor UI
       	 if(user.getUserType().equals("Artist")) {
       	 	Artist artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
       	 	artist.setPUBLIC_KEY(user.getPublicKey());
		    artist.setPRIVATE_KEY(user.getPrivateKey());
       	 	//Artist artist = (Artist) user;
       	 	HomePane<K, V, T> home = new HomePane<K,V, T>(primaryStage, artist);
       	 	this.getChildren().remove(0);
       	 	this.getChildren().remove(0);
       	 	this.getChildren().addAll(home);
       	 	}
       	 	//Navigate to Distributor UI
       	 if(user.getUserType().equals("Distributor")) {
       		Distributor distributor = new Distributor(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
       		distributor.setPUBLIC_KEY(user.getPublicKey());
       		distributor.setPRIVATE_KEY(user.getPrivateKey());
       		HomePane<K, V, T> home = new HomePane<K, V, T>(primaryStage, distributor);
    	 	this.getChildren().remove(0);
    	 	this.getChildren().remove(0);
    	 	this.getChildren().addAll(home);
    	 	}
           	
           }else {
    		   
    		   if(LoginAttemptCount <= 3 && LoginAttemptCount > 0) {
    			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Login Failed");
                   alert.setHeaderText("Incorrect Email or Password");
                   alert.setContentText(LoginAttemptCount + " Remaining, please try again");
                   alert.showAndWait();
    		   }else {
    			   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Login Failed");
                   alert.setHeaderText("Incorrect Email or Password");
                   alert.setContentText("Please Wait "+COUNTDOWN_SECONDS +" Seconds and try Again");
                   alert.showAndWait();
               }
    	   }
    	   LoginAttemptCount --;
       });
        
       btnRegister.setOnAction(event->{
        	RegisterPane register = new RegisterPane(this, primaryStage);
        	this.getChildren().remove(0);
        	this.getChildren().remove(0);
        	this.getChildren().addAll(register);
        });
       
      
	}
	
}