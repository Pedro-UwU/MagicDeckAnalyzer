package Front;

import Back.fileManager.DeckReader;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class MainFrame extends BorderPane {

    public MainFrame(ArrayList<String> decks, int width, int height) {

        DropDownDecks deckList = new DropDownDecks(decks);
        Desk desk = new Desk(10, width);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        MatchLogChart<String,Number> chart = new MatchLogChart<>(xAxis,yAxis);
        chart.setPrefWidth(width/2);

        Button readDeckLogButton = new Button("Read Deck Log File");
        readDeckLogButton.setOnAction(event -> {
            if (deckList.validSelection()) DeckReader.ReadDeckLog(deckList.getValue());
        });


        desk.getChildren().addAll(chart, readDeckLogButton);



        this.setPrefWidth(width);
        this.setPrefHeight(height);

        this.setTop(deckList);
        this.setCenter(desk);
        this.setPadding(new Insets(5));
    }
}
