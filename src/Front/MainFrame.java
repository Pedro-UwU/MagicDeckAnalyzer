package Front;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class MainFrame extends BorderPane {

    public MainFrame(ArrayList<String> decks, int width, int height) {

        DropDownDecks deckList = new DropDownDecks(decks);

        deckList.setOnMousePressed(event -> deckList.updateDecks());

        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Deck Info");
        Tab tab2 = new Tab("Tab2");
        Tab tab3 = new Tab("Tab3");
        tabs.getTabs().addAll(tab1,tab2,tab3);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        DeckInfoVisualizer deckInfo = new DeckInfoVisualizer(5, width, height);

        deckList.setOnAction(event -> {
            deckInfo.setDeck(deckList.getSelectedDeck());
        });

        tab1.setContent(deckInfo);

        this.setPrefWidth(width);
        this.setPrefHeight(height);

        this.setTop(deckList);
        this.setCenter(tabs);
        this.setPadding(new Insets(5));
    }
}
