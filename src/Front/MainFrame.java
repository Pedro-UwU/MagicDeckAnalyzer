package Front;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class MainFrame extends BorderPane {

    public MainFrame(ArrayList<String> decks, int width, int height) {

        DropDownDecks deckList = new DropDownDecks(decks);
        Desk desk = new Desk(10, width);

        Button deckPrinter = new Button("Print Deck");
        deckPrinter.setPrefWidth(3*width/8);
        Label deckVisualizer = new Label("No deck Selected");
        ScrollPane deckScroll = new ScrollPane(deckVisualizer);
        deckScroll.setFitToWidth(true);
        deckVisualizer.setPrefWidth(width);
        deckPrinter.setOnAction(event -> {
                try {
                    deckVisualizer.setText(deckList.getSelectedDeck().toStringWithRepeated());
                } catch (Exception e) {
                    deckVisualizer.setText("Please Select Deck");
                }
            });
        desk.getChildren().addAll(deckPrinter, deckScroll);

        this.setPrefWidth(width);
        this.setPrefHeight(height);

        this.setTop(deckList);
        this.setCenter(desk);
        this.setPadding(new Insets(5));
    }
}
