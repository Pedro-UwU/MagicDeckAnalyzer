package Front;

import Back.Deck;
import Back.DeckFileManager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MainFrame extends BorderPane {

    public MainFrame(int width, int height) {

        int spacing = 5;

        DropDownDecks deckList = new DropDownDecks();

        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Deck Info");
        Tab tab2 = new Tab("Deck Stats");
        Tab tab3 = new Tab("Tab3");
        tabs.getTabs().addAll(tab1,tab2,tab3);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);



        DeckInfoVisualizer deckInfo = new DeckInfoVisualizer(spacing, width, height);
        DeckStatsVisualizer deckStats = new DeckStatsVisualizer(spacing, width, height);

        deckList.setOnMousePressed(event -> deckList.updateDecks());

        deckList.setOnAction(event -> {
            Deck seleceted = deckList.getSelectedDeck();
            deckInfo.setDeck(seleceted);
            deckStats.setDeck(seleceted);
        });

        tab1.setContent(deckInfo);
        tab2.setContent(deckStats);


        this.setPrefWidth(width);
        this.setPrefHeight(height);

        this.setTop(deckList);
        this.setCenter(tabs);
        this.setPadding(new Insets(spacing));
    }
}
