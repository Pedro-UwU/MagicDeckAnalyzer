package Front;

import Back.DeckReader;
import Front.Menu.AppMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<String> deckNames = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //read deck Files
        deckNames = DeckReader.readNames();


        MainFrame mainFrame = new MainFrame(deckNames, 800, 600);
        Scene scene = new Scene(new VBox(new AppMenu(), mainFrame));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
