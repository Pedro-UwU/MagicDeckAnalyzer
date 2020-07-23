package Front;

import Back.DeckFileManager;
import Front.Menu.AppMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    ArrayList<String> deckNames = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        MainFrame mainFrame = new MainFrame(800, 600);
        Scene scene = new Scene(new VBox(new AppMenu(), mainFrame));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
