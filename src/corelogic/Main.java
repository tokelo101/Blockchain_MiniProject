package corelogic;
import gui.UserAuthentication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author TM Monare 221022037
 *
 */
public class Main extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
        
//    	System.out.println("Main Running");
//        Artist artist1 = new Artist("tokelo101", "Tokelo", "Monare", "tm@mail.com", "Pass1");
//        Artist artist2 = new Artist("nf", "Nathan", "Feuerstein", "NF@mail.com", "Pass2");
//        
//        ArtistManager artistmanager = new ArtistManager();
//        
//        artistmanager.RegisterNewArtist(artist2);
//        
//        artistmanager.GetArtist(artist2.getPublicKey());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	UserAuthentication login = new UserAuthentication(primaryStage);
        Scene root = new Scene(login, 750, 500);

        primaryStage.setTitle("Music Copyrights Hub");
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
