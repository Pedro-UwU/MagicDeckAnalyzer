package Front;

import Back.Card;
import Back.Deck;
import Back.ScryReader;
import Front.Table.DeckInfoTable;
import Front.Table.TableDeck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DeckInfoVisualizer extends HBox {

    ImageView cardView = new ImageView();
    DeckInfoTable deckTable = new DeckInfoTable(DeckInfoTable.DECK);
    DeckInfoTable sideTable = new DeckInfoTable(DeckInfoTable.SIDEBOARD);
    VBox cardImageBox = new VBox(0);


    Deck deck;
    int space, width, height;

    public DeckInfoVisualizer(int space, int width, int height) {
        super(space);
        this.space = space;
        this.width = width;
        this.height = height;
        this.setPadding(new Insets(space,0,space,0));

        cardView.setPreserveRatio(true);


        cardImageBox.setAlignment(Pos.CENTER_RIGHT);
        cardImageBox.getChildren().addAll(cardView);
        cardView.setFitWidth(width/2 - 5*space);

        deckTable.setOnMouseClicked(event -> {
            Card c = deckTable.getSelectionModel().getSelectedItem();
            if (deck != null && c != null) cardView.setImage(new Image(ScryReader.getImageFromCard(c)));
        });
        sideTable.setOnMouseClicked(event -> {
            Card c = sideTable.getSelectionModel().getSelectedItem();
            if (deck != null && c != null) cardView.setImage(new Image(ScryReader.getImageFromCard(c)));
        });

        deckTable.setMinWidth(width/2);
        sideTable.setMinWidth(width/2);

        //deckTable.setMaxHeight(4f*height/5 - space);
        deckTable.setPrefHeight(height);
        sideTable.setMinHeight(2*height/5f);

    }

    public void setDeck(Deck d) {
        if (d != null) {
            deck = d;
            TableDeck tableDeck = new TableDeck(d);
            deckTable.setDeck(tableDeck);
            sideTable.setDeck(tableDeck);
            cardView.setImage(null);
            this.getChildren().clear();
            if (d.hasSideboard()) {
                this.getChildren().addAll(new VBox(space, new Label("Deck"), deckTable, new Label("Sideboard"), sideTable),cardImageBox);
            } else {
                this.getChildren().addAll(new VBox(space, new Label("Deck"), deckTable),cardImageBox);
            }
        }
    }


}
