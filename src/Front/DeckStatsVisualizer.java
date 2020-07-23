package Front;

import Back.Deck;
import Front.Nodes.ManaColorPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DeckStatsVisualizer extends HBox {

    Label deckName = new Label();
    Label deckStats = new Label();
    ScrollPane scrollDeckStats = new ScrollPane();
    Deck deck;
    int width, height, spacing;

    public DeckStatsVisualizer(int spacing, int width, int height) {
        super(spacing);
        this.width = width;
        this.height = height;
        this.spacing = spacing;

        scrollDeckStats.setFitToWidth(true);
        scrollDeckStats.setContent(deckStats);
        setPadding(new Insets(spacing, 0, spacing, 0));


    }

    public void setDeck(Deck d) {
        deck = d;
        deckName.setText(d.getName());
        deckStats.setText(d.getStats());

        ManaColorPanel manaColorPanel = new ManaColorPanel(40, width, height);
        Button addWinButton = new Button("Win");
        Button addLoseButton = new Button("Lose");
        Button saveButton = new Button("Save");

        addWinButton.setOnAction(event -> {
            String colors = manaColorPanel.getSelected();
            deck.incWin(colors);
            deckStats.setText(d.getStats());
        });

        addLoseButton.setOnAction(event -> {
            String colors = manaColorPanel.getSelected();
            deck.incLoses(colors);
            deckStats.setText(d.getStats());
        });

        saveButton.setOnAction(event -> {
            deck.saveStats();
        });


        HBox buttonsBox = new HBox();
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonsBox.getChildren().addAll(addWinButton, addLoseButton,spacer,saveButton);
        Pane verticalSpacer = new Pane();
        VBox.setVgrow(verticalSpacer, Priority.ALWAYS);
        VBox first = new VBox(10, deckName, scrollDeckStats, verticalSpacer, manaColorPanel, buttonsBox);

        getChildren().clear();
        getChildren().addAll(first);
    }
}
