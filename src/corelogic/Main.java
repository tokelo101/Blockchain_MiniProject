package corelogic;
import gui.UserAuthentication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
/**
 * 
 * @author TM Monare 221022037
 *
 */
public class Main extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	UserAuthentication<String,String,Song> login = new UserAuthentication<>(primaryStage);
        Scene root = new Scene(login, 750, 500);
        
        //Setting background color
        Image backgroundImage = new Image("img/waves.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
        		BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER , BackgroundSize.DEFAULT);
        login.setBackground(new Background(background));
        
        //setting global text color
        root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        login.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        primaryStage.setTitle("Music Copyrights Hub");
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
