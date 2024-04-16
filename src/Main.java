import gui.HomePane;
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
        //launch(args);
        
    	System.out.println("Main Running");
        Artist artist1 = new Artist("tokelo101", "Tokelo", "Monare", "tm@mail.com", "Pass1");
        Artist artist2 = new Artist("nf", "Nathan", "Feuerstein", "NF@mail.com", "Pass2");
        
        ArtistManager artistmanager = new ArtistManager();
        
        artistmanager.RegisterNewArtist(artist1);
        
        artistmanager.GetArtist(artist1.getPublicKey());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HomePane home = new HomePane(primaryStage);
        Scene root = new Scene(home, 750, 500);

        primaryStage.setTitle("Music Rights Keeper");
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
