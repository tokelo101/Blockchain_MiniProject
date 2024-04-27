package gui;

import corelogic.Artist;
import corelogic.User;
import corelogic.UserHandler;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserAuthentication extends StackPane{
	private Button btnLogin;
	private Button btnRegister;
	private TextField txtEmail;
	private PasswordField password;
	private HBox content;
	private int LoginAttemptCount = 3;
	private static final int COUNTDOWN_SECONDS = 30;
	private Alert alert;
	private AnimationTimer countdownTimer;
	
	public UserAuthentication(Stage primaryStage) {
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25));
			
		btnRegister = new Button("Register");
		btnRegister.setPrefWidth(200);
		btnLogin = new Button("Login");
		btnLogin.setPrefWidth(200);
	        
		content = new HBox();
		
		content.setAlignment(Pos.TOP_CENTER);
		
		// Add form elements
        Label lbTitle = new Label("Login");
        lbTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        
        Label lbName = new Label("Name:");
        gridPane.add(lbName, 0, 1);

        txtEmail = new TextField();
        gridPane.add(txtEmail, 1, 1);
        Label lbPassword = new Label("Password:");
        gridPane.add(lbPassword, 0, 2);

        password = new PasswordField();
        gridPane.add(password, 1, 2);

        //gridPane.add(btnLogin,0, 3);
        gridPane.add(btnLogin, 1, 3, 4, 1);
        
        Label lbRegister = new Label("New User? ");
        gridPane.add(lbRegister,0 , 4);
        //gridPane.add(btnRegister, 0, 5);
        gridPane.add(btnRegister, 1, 4, 4 , 1);
        
        content.getChildren().add(gridPane);
        this.getChildren().addAll(content);
      
        Label tempField = new Label();
       this.getChildren().add(tempField); //helps avoid the null pointer exception when removing before adding a nav item
	   
       //Test Purposes
       txtEmail.setText("tm@mail.com");
	   password.setText("tm");
	   
       btnLogin.setOnAction(event->{
    	   
    	   UserHandler userhandler = new UserHandler();
   		User user = userhandler.GetUser(txtEmail.getText(), password.getText());
    	
    	   if(user!=null) {
    		   
    		 //Navigate to Distributor UI
       	 	if(user.getUserType().equals("Artist")) {
       	 		Artist artist = new Artist(user.getUserType(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
       	 		//Artist artist = (Artist) user;
       	 		HomePane home = new HomePane(primaryStage, artist);
       	 		this.getChildren().remove(0);
       	 		this.getChildren().remove(0);
       	 		this.getChildren().addAll(home);
       	 	}
       	 	//Navigate to Distributor UI
       	 if(user.getUserType().equals("Distributor")) {
    	 		Artist artist = (Artist)user;
    	 		HomePane home = new HomePane(primaryStage, artist);
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