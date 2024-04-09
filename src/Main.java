import gui.HomePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HomePane home = new HomePane(primaryStage);
        Scene root = new Scene(home, 750, 500);

        primaryStage.setTitle("Music Rights");
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
