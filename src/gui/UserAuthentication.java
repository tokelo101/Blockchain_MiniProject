package gui;

import corelogic.UserHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        
       btnLogin.setOnAction(event->{
    	 	HomePane home = new HomePane(primaryStage);
        	this.getChildren().remove(0);
        	this.getChildren().remove(0);
        	this.getChildren().addAll(home);
       });
        
       btnRegister.setOnAction(event->{
        	RegisterPane register = new RegisterPane(this, primaryStage);
        	this.getChildren().remove(0);
        	this.getChildren().remove(0);
        	this.getChildren().addAll(register);
        });
       
      
	}
	
}